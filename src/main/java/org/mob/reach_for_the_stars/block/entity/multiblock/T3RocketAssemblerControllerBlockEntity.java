package org.mob.reach_for_the_stars.block.entity.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.mob.mob_lib.item.custom.UpgradeItem;
import org.mob.mob_lib.util.ModTags;
import org.mob.reach_for_the_stars.block.custom.multiblock.MultiblockPatterns;
import org.mob.reach_for_the_stars.block.custom.multiblock.T3RocketAssemblerControllerBlock;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.block.entity.hatch.EnergyInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.ItemInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.ItemOutputHatchBlockEntity;
import org.mob.reach_for_the_stars.recipe.T3RocketAssemblerRecipe;
import org.mob.reach_for_the_stars.screen.menu.T3RocketAssemblerControllerMenu;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class T3RocketAssemblerControllerBlockEntity extends BlockEntity implements MenuProvider {

    private int checkStructureTimer = 0;
    public boolean showPreview = false;

    private int progress = 0;
    private int MAX_PROGRESS = 12000; // 10 min
    private final int ENERGY_PER_TICK = 100;

    // Cached Hatch Locations
    private final List<BlockPos> itemInputHatches = new ArrayList<>();
    private final List<BlockPos> itemOutputHatches = new ArrayList<>();
    private final List<BlockPos> energyInputHatches = new ArrayList<>();

    public T3RocketAssemblerControllerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.T3_ROCKET_ASSEMBLER_CONTROLLER_BE.get(), pPos, pBlockState);
    }

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> T3RocketAssemblerControllerBlockEntity.this.progress;
                case 1 -> T3RocketAssemblerControllerBlockEntity.this.MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> T3RocketAssemblerControllerBlockEntity.this.progress = value;
                case 1 -> T3RocketAssemblerControllerBlockEntity.this.MAX_PROGRESS = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public static void tick(Level level, BlockPos pos, BlockState state, T3RocketAssemblerControllerBlockEntity be) {
        if (level.isClientSide()) return;

        if (be.checkStructureTimer-- <= 0) {
            be.checkStructureTimer = 20;
            be.updateStructure(level, pos, state);
        }

        if (state.getValue(T3RocketAssemblerControllerBlock.FORMED)) {
            be.processRecipe(level);
        }
    }

    private void updateStructure(Level level, BlockPos pos, BlockState state) {
        MultiblockPattern.MatchResult result = MultiblockPatterns.T3_ROCKET_ASSEMBLER.check(
                level, pos, state.getValue(T3RocketAssemblerControllerBlock.FACING)
        );

        boolean wasFormed = state.getValue(T3RocketAssemblerControllerBlock.FORMED);

        if (result.isFormed()) {
            if (!wasFormed) {
                level.setBlock(pos, state.setValue(T3RocketAssemblerControllerBlock.FORMED, true), 3);
            }

            if (this.itemInputHatches.isEmpty() || this.itemOutputHatches.isEmpty() || this.energyInputHatches.isEmpty()) {
                linkHatches(level, pos, result);
            }

        } else {
            if (wasFormed) {
                level.setBlock(pos, state.setValue(T3RocketAssemblerControllerBlock.FORMED, false), 3);
                unlinkHatches(level);
                this.progress = 0;
                setChanged();
            }
        }
    }

    private void linkHatches(Level level, BlockPos controllerPos, MultiblockPattern.MatchResult result) {
        unlinkHatches(level);

        // 'W' is Item Input Hatch
        for (BlockPos targetPos : result.getPositions('W')) {
            if (level.getBlockEntity(targetPos) instanceof ItemInputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.itemInputHatches.add(targetPos);
            }
        }

        // 'X' is Item Output Hatch
        for (BlockPos targetPos : result.getPositions('X')) {
            if (level.getBlockEntity(targetPos) instanceof ItemOutputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.itemOutputHatches.add(targetPos);
            }
        }

        // 'Y' is Energy Input Hatch
        for (BlockPos targetPos : result.getPositions('Y')) {
            if (level.getBlockEntity(targetPos) instanceof EnergyInputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.energyInputHatches.add(targetPos);
            }
        }
    }

    private void unlinkHatches(Level level) {
        for (BlockPos targetPos : itemInputHatches) {
            if (level.getBlockEntity(targetPos) instanceof ItemInputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }
        for (BlockPos targetPos : itemOutputHatches) {
            if (level.getBlockEntity(targetPos) instanceof ItemOutputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }
        for (BlockPos targetPos : energyInputHatches) {
            if (level.getBlockEntity(targetPos) instanceof EnergyInputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }

        this.itemInputHatches.clear();
        this.itemOutputHatches.clear();
        this.energyInputHatches.clear();
    }

    private Optional<T3RocketAssemblerRecipe> getRecipe(SimpleContainer container, Level level) {
        if (container.isEmpty()) return Optional.empty();

        return level.getRecipeManager().getRecipeFor(T3RocketAssemblerRecipe.Type.INSTANCE, container, level);
    }

    private void processRecipe(Level level) {
        if (itemInputHatches.isEmpty() || itemOutputHatches.isEmpty() || energyInputHatches.isEmpty()) return;

        ItemInputHatchBlockEntity inputBe = (ItemInputHatchBlockEntity) level.getBlockEntity(itemInputHatches.get(0));
        ItemOutputHatchBlockEntity itemOutBE = (ItemOutputHatchBlockEntity) level.getBlockEntity(itemOutputHatches.get(0));
        EnergyInputHatchBlockEntity energyIn = (EnergyInputHatchBlockEntity) level.getBlockEntity(energyInputHatches.get(0));

        if (inputBe == null || itemOutBE == null || energyIn == null) return;

        ItemStackHandler inventory = itemOutBE.getInternalInventory();

        IItemHandler inputItemHandler = inputBe.getInternalInventory();
        if (inputItemHandler == null) return;

        SimpleContainer container = new SimpleContainer(inputItemHandler.getSlots());
        for (int i = 0; i < inputItemHandler.getSlots(); i++) {
            container.setItem(i, inputItemHandler.getStackInSlot(i));
        }

        Optional<T3RocketAssemblerRecipe> match = getRecipe(container, level);

        if (match.isEmpty()) {
            this.progress = 0;
            return;
        }

        T3RocketAssemblerRecipe recipe = match.get();
        ItemStack outputResult = recipe.getResultItem(level.registryAccess());

        itemOutBE.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {

            ItemStack remaining = ItemHandlerHelper.insertItem(inventory, outputResult, true);
            if (!remaining.isEmpty()) return;

            // Simulated energy check (preventing power drain on stalled progress)
            int simulatedEnergy = energyIn.getInternalEnergyStorage().extractEnergy(ENERGY_PER_TICK, true);
            if (simulatedEnergy == ENERGY_PER_TICK) {
                energyIn.getInternalEnergyStorage().extractEnergy(ENERGY_PER_TICK, false);
                this.progress++;

                if (this.progress >= MAX_PROGRESS) {
                    // Consume ingredients out of the input hatch utilizing the recipe's requirements
                    consumeRecipeIngredients(recipe, inputItemHandler);

                    ItemHandlerHelper.insertItem(inventory, outputResult.copy(), false);

                    this.progress = 0;
                    setChanged();
                }
            }
        });
    }

    private void consumeRecipeIngredients(T3RocketAssemblerRecipe recipe, IItemHandler inputHandler) {
        List<Ingredient> ingredients = recipe.getIngredientsList();
        List<Integer> counts = recipe.getInputCounts();

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            int requiredCount = counts.get(i);

            for (int slot = 0; slot < inputHandler.getSlots(); slot++) {
                if (requiredCount <= 0) break;

                ItemStack stack = inputHandler.getStackInSlot(slot);
                if (!stack.isEmpty() && ingredient.test(stack)) {
                    int extractAmount = Math.min(stack.getCount(), requiredCount);
                    inputHandler.extractItem(slot, extractAmount, false);
                    requiredCount -= extractAmount;
                }
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("Progress", this.progress);
        pTag.putBoolean("ShowPreview", this.showPreview);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.progress = pTag.getInt("Progress");
        this.showPreview = pTag.getBoolean("ShowPreview");
    }

    public boolean isShowPreview() { return this.showPreview; }

    public void togglePreview() {
        this.showPreview = !this.showPreview;
        this.setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.reach_for_the_stars.t3_rocket_assembler_controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new T3RocketAssemblerControllerMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (pkt.getTag() != null) {
            load(pkt.getTag());
        }
    }
}