package org.mob.reach_for_the_stars.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.*;
import java.util.function.Predicate;

public class MultiblockPattern {
    private final List<String[]> layers;
    private final Map<Character, PatternElement> dictionary;

    private int centerLayer = -1;
    private int centerRow = -1;
    private int centerCol = -1;

    private MultiblockPattern(List<String[]> layers, Map<Character, PatternElement> dictionary) {
        this.layers = layers;
        this.dictionary = dictionary;
        findCenter();
    }

    private void findCenter() {
        for (int l = 0; l < layers.size(); l++) {
            String[] layer = layers.get(l);
            for (int r = 0; r < layer.length; r++) {
                String row = layer[r];
                for (int c = 0; c < row.length(); c++) {
                    if (row.charAt(c) == 'C') {
                        this.centerLayer = l;
                        this.centerRow = r;
                        this.centerCol = c;
                        return;
                    }
                }
            }
        }
        if (centerLayer == -1) throw new IllegalStateException("Multiblock pattern is missing a 'C' (Controller) character!");
    }

    /**
     * Checks the structure and returns a MatchResult containing the positions of all mapped characters.
     */
    public MatchResult check(Level level, BlockPos controllerPos, Direction facing) {
        Map<Character, List<BlockPos>> foundBlocks = new HashMap<>();
        int totalLayers = layers.size();

        for (int l = 0; l < totalLayers; l++) {
            String[] layer = layers.get(l);
            for (int r = 0; r < layer.length; r++) {
                String rowText = layer[r];
                for (int c = 0; c < rowText.length(); c++) {
                    char ch = rowText.charAt(c);
                    if (ch == 'C') continue;

                    PatternElement predicate = dictionary.get(ch);
                    if (predicate == null) continue;

                    // Calculate offsets relative to the Controller's position in the pattern
                    // Negative forward = Behind the controller
                    // Negative right = Left of the controller
                    int forwardOffset = r - centerRow;
                    int rightOffset = centerCol - c;
                    int upOffset = (totalLayers - 1 - l) - (totalLayers - 1 - centerLayer);

                    BlockPos targetPos = controllerPos
                            .relative(facing, forwardOffset)
                            .relative(facing.getClockWise(), rightOffset)
                            .above(upOffset);

                    BlockState stateInWorld = level.getBlockState(targetPos);

                    if (!predicate.predicate.test(stateInWorld)) {
                        return MatchResult.FAIL;
                    }

                    foundBlocks.computeIfAbsent(ch, k -> new ArrayList<>()).add(targetPos);
                }
            }
        }
        return new MatchResult(true, foundBlocks);
    }

    public static class PatternElement {
        public final Predicate<BlockState> predicate;
        public final BlockState displayState;

        public PatternElement(Predicate<BlockState> predicate, BlockState displayState) {
            this.predicate = predicate;
            this.displayState = displayState;
        }
    }

    public void placeStructure(Level level, BlockPos controllerPos, Direction facing, Player player) {
        Map<BlockPos, BlockState> blocksToPlace = getPreviewOffsets(facing);

        for (Map.Entry<BlockPos, BlockState> entry : blocksToPlace.entrySet()) {
            BlockPos targetPos = controllerPos.offset(entry.getKey());
            BlockState stateToPlace = entry.getValue();

            // Skip if the block is already correct (saves items and prevents overriding)
            if (level.getBlockState(targetPos).getBlock() == stateToPlace.getBlock()) {
                continue;
            }

            if (player.isCreative()) {
                // Creative mode
                level.setBlockAndUpdate(targetPos, stateToPlace);
            } else {
                // Survival mode: Check for the item in the inventory
                ItemStack requiredItem = new ItemStack(stateToPlace.getBlock().asItem());
                if (requiredItem.isEmpty()) continue; // Block has no item form, skip

                if (consumeItemFromPlayer(player, requiredItem)) {
                    // Break whatever is currently in the way
                    level.destroyBlock(targetPos, true);
                    // Place the multiblock part
                    level.setBlockAndUpdate(targetPos, stateToPlace);
                }
            }
        }
    }

    // Searches the inventory for a specific item and removes 1 of it.
    private boolean consumeItemFromPlayer(Player player, ItemStack requiredItem) {
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack slotStack = inv.getItem(i);
            if (ItemStack.isSameItem(slotStack, requiredItem)) {
                slotStack.shrink(1); // Remove 1 from the stack
                return true;
            }
        }
        return false;
    }

    // Preview
    public Map<BlockPos, BlockState> getPreviewOffsets(Direction facing) {
        Map<BlockPos, BlockState> offsets = new HashMap<>();
        int totalLayers = layers.size();

        for (int l = 0; l < totalLayers; l++) {
            String[] layer = layers.get(l);
            for (int r = 0; r < layer.length; r++) {
                String rowText = layer[r];
                for (int c = 0; c < rowText.length(); c++) {
                    char ch = rowText.charAt(c);
                    if (ch == 'C' || ch == ' ') continue;

                    PatternElement element = dictionary.get(ch);
                    if (element == null || element.displayState == null) continue;

                    int forwardOffset = r - centerRow;
                    int rightOffset = centerCol - c;
                    int upOffset = (totalLayers - 1 - l) - (totalLayers - 1 - centerLayer);

                    // Calculate position relative to 0,0,0
                    BlockPos offsetPos = BlockPos.ZERO
                            .relative(facing, forwardOffset)
                            .relative(facing.getClockWise(), rightOffset)
                            .above(upOffset);

                    // Rotate the blockstate if it's a hatch that can face directions
                    BlockState stateToDisplay = element.displayState;
                    if (stateToDisplay.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                        stateToDisplay = stateToDisplay.setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
                    }

                    offsets.put(offsetPos, stateToDisplay);
                }
            }
        }
        return offsets;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final List<String[]> layers = new ArrayList<>();
        private final Map<Character, PatternElement> dictionary = new HashMap<>();

        public Builder layer(String... rows) {
            layers.add(rows);
            return this;
        }

        public Builder where(char c, Predicate<BlockState> predicate, BlockState displayState) {
            dictionary.put(c, new PatternElement(predicate, displayState));
            return this;
        }

        public MultiblockPattern build() { return new MultiblockPattern(layers, dictionary); }
    }


    public static class MatchResult {
        public static final MatchResult FAIL = new MatchResult(false, Collections.emptyMap());

        private final boolean isFormed;
        private final Map<Character, List<BlockPos>> matchedBlocks;

        private MatchResult(boolean isFormed, Map<Character, List<BlockPos>> matchedBlocks) {
            this.isFormed = isFormed;
            this.matchedBlocks = matchedBlocks;
        }

        public boolean isFormed() { return isFormed; }

        public List<BlockPos> getPositions(char character) {
            return matchedBlocks.getOrDefault(character, Collections.emptyList());
        }
    }
}