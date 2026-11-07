package org.mob.reach_for_the_stars.block.entity.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mob.reach_for_the_stars.block.custom.multiblock.MultiblockPatterns;
import org.mob.reach_for_the_stars.block.custom.multiblock.Printer3DControllerBlock;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.block.entity.hatch.EnergyInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.FluidInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.ItemInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.ItemOutputHatchBlockEntity;
import org.mob.reach_for_the_stars.recipe.Printer3DRecipe;
import org.mob.reach_for_the_stars.screen.menu.Printer3DControllerMenu;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Printer3DControllerBlockEntity extends BlockEntity implements MenuProvider {

    private int checkStructureTimer = 0;
    public boolean showPreview = false;

    private int progress = 0;
    private int MAX_PROGRESS = 12000; // 10 min
    private final int ENERGY_PER_TICK = 100;

    private final List<IItemHandler> itemInputHatches = new ArrayList<>();
    private final List<IItemHandler> itemOutputHatches = new ArrayList<>();
    private final List<IEnergyStorage> energyInputHatches = new ArrayList<>();
    private final List<IFluidHandler> fluidInputHatches = new ArrayList<>();

    public Printer3DControllerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PRINTER_3D_CONTROLLER_BE.get(), pPos, pBlockState);
    }

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> Printer3DControllerBlockEntity.this.progress;
                case 1 -> Printer3DControllerBlockEntity.this.MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> Printer3DControllerBlockEntity.this.progress = value;
                case 1 -> Printer3DControllerBlockEntity.this.MAX_PROGRESS = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public static void tick(Level level, BlockPos pos, BlockState state, Printer3DControllerBlockEntity be) {
        if (level.isClientSide()) return;

        if (be.checkStructureTimer-- <= 0) {
            be.checkStructureTimer = 20;
            be.updateStructure(level, pos, state);
        }

        if (state.getValue(Printer3DControllerBlock.FORMED)) {
            be.processRecipe(level);
        }
    }

    private void updateStructure(Level level, BlockPos pos, BlockState state) {
        MultiblockPattern.MatchResult result = MultiblockPatterns.PRINTER_3D.check(
                level, pos, state.getValue(Printer3DControllerBlock.FACING)
        );

        boolean wasFormed = state.getValue(Printer3DControllerBlock.FORMED);

        if (result.isFormed()) {
            if (!wasFormed) {
                level.setBlock(pos, state.setValue(Printer3DControllerBlock.FORMED, true), 3);
            }
            if (this.itemInputHatches.isEmpty() && this.itemOutputHatches.isEmpty()) {
                linkHatches(level, pos, result);
            }
        } else {
            if (wasFormed) {
                level.setBlock(pos, state.setValue(Printer3DControllerBlock.FORMED, false), 3);
                unlinkHatches(level);
                this.progress = 0;
                setChanged();
            }
        }
    }

    private void linkHatches(Level level, BlockPos controllerPos, MultiblockPattern.MatchResult result) {
        unlinkHatches(level);

        // Dynamically find capabilities across the entire machine structure!
        for (List<BlockPos> positions : result.getMatchedBlocks().values()) {
            for (BlockPos targetPos : positions) {
                BlockEntity be = level.getBlockEntity(targetPos);
                if (be == null) continue;

                if (be instanceof ItemInputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemInputHatches::add);
                } else if (be instanceof ItemOutputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemOutputHatches::add);
                } else if (be instanceof FluidInputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(fluidInputHatches::add);
                } else if (be instanceof EnergyInputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.ENERGY).ifPresent(energyInputHatches::add);
                }
            }
        }
    }

    private void unlinkHatches(Level level) {
        this.itemInputHatches.clear();
        this.itemOutputHatches.clear();
        this.energyInputHatches.clear();
        this.fluidInputHatches.clear();
    }

    private SimpleContainer getAggregatedItemInventory() {
        List<ItemStack> allItems = new ArrayList<>();
        for (IItemHandler handler : itemInputHatches) {
            for (int i = 0; i < handler.getSlots(); i++) {
                ItemStack stack = handler.getStackInSlot(i);
                if (!stack.isEmpty()) allItems.add(stack);
            }
        }
        SimpleContainer container = new SimpleContainer(allItems.size());
        for (int i = 0; i < allItems.size(); i++) {
            container.setItem(i, allItems.get(i));
        }
        return container;
    }

    private boolean hasEnoughFluid(FluidStack required) {
        int totalFound = 0;
        for (IFluidHandler hatch : this.fluidInputHatches) {
            FluidStack simulated = hatch.drain(new FluidStack(required.getFluid(), required.getAmount() - totalFound), IFluidHandler.FluidAction.SIMULATE);
            if (!simulated.isEmpty()) totalFound += simulated.getAmount();
            if (totalFound >= required.getAmount()) return true;
        }
        return false;
    }

    private void consumeFluid(FluidStack required) {
        int remaining = required.getAmount();
        for (IFluidHandler hatch : this.fluidInputHatches) {
            if (remaining <= 0) break;
            FluidStack drained = hatch.drain(new FluidStack(required.getFluid(), remaining), IFluidHandler.FluidAction.EXECUTE);
            if (!drained.isEmpty()) remaining -= drained.getAmount();
        }
    }

    private boolean tryExtractEnergy(int amount, boolean simulate) {
        int extractedTotal = 0;
        for (IEnergyStorage storage : energyInputHatches) {
            extractedTotal += storage.extractEnergy(amount - extractedTotal, true); // Simulate first
            if (extractedTotal >= amount) break;
        }

        if (extractedTotal < amount) return false; // Not enough power

        if (!simulate) {
            int remainingToExtract = amount;
            for (IEnergyStorage storage : energyInputHatches) {
                if (remainingToExtract <= 0) break;
                remainingToExtract -= storage.extractEnergy(remainingToExtract, false);
            }
        }
        return true;
    }

    private boolean tryInsertOutput(ItemStack output, boolean simulate) {
        ItemStack remaining = output.copy();
        for (IItemHandler handler : itemOutputHatches) {
            remaining = ItemHandlerHelper.insertItem(handler, remaining, simulate);
            if (remaining.isEmpty()) return true;
        }
        return false;
    }


    private Optional<Printer3DRecipe> getRecipe(Level level) {
        SimpleContainer container = getAggregatedItemInventory();
        if (container.isEmpty()) return Optional.empty();

        return level.getRecipeManager().getRecipeFor(Printer3DRecipe.Type.INSTANCE, container, level).filter(recipe -> {
            for (FluidStack req : recipe.getFluidInputs()) {
                if (!hasEnoughFluid(req)) return false;
            }
            return true;
        });
    }

    private void processRecipe(Level level) {
        if (itemInputHatches.isEmpty() || itemOutputHatches.isEmpty() || energyInputHatches.isEmpty() || fluidInputHatches.isEmpty()) return;

        Optional<Printer3DRecipe> match = getRecipe(level);

        if (match.isEmpty()) {
            this.progress = 0;
            return;
        }

        Printer3DRecipe recipe = match.get();
        ItemStack outputResult = recipe.getResultItem(level.registryAccess());

        // Check if there is room in the output buses
        if (!tryInsertOutput(outputResult, true)) return;

        if (tryExtractEnergy(ENERGY_PER_TICK, false)) {
            this.progress++;

            if (this.progress >= MAX_PROGRESS) {
                // Consume Items
                consumeRecipeIngredients(recipe);

                // Consume Fluids
                for (FluidStack req : recipe.getFluidInputs()) {
                    consumeFluid(req);
                }

                // Output Result
                tryInsertOutput(outputResult.copy(), false);

                this.progress = 0;
                setChanged();
            }
        }
    }

    private void consumeRecipeIngredients(Printer3DRecipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredientsList();
        List<Integer> counts = recipe.getInputCounts();

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            int requiredCount = counts.get(i);

            // Look through every attached input hatch
            for (IItemHandler inputHandler : itemInputHatches) {
                for (int slot = 0; slot < inputHandler.getSlots(); slot++) {
                    if (requiredCount <= 0) break;

                    ItemStack stack = inputHandler.getStackInSlot(slot);
                    if (!stack.isEmpty() && ingredient.test(stack)) {
                        int extractAmount = Math.min(stack.getCount(), requiredCount);
                        inputHandler.extractItem(slot, extractAmount, false);
                        requiredCount -= extractAmount;
                    }
                }
                if (requiredCount <= 0) break; // Move to next ingredient
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
        return Component.translatable("block.reach_for_the_stars.printer_3d_controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new Printer3DControllerMenu(pContainerId, pPlayerInventory, this, this.data);
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