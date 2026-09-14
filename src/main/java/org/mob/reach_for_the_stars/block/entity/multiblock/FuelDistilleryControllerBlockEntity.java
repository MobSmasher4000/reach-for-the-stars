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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.mob.mob_lib.item.custom.UpgradeItem;
import org.mob.mob_lib.util.ModTags;
import org.mob.reach_for_the_stars.block.custom.multiblock.FuelDistilleryControllerBlock;
import org.mob.reach_for_the_stars.block.custom.multiblock.MultiblockPatterns;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.block.entity.hatch.EnergyInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.FluidInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.FluidOutputHatchBlockEntity;
import org.mob.reach_for_the_stars.recipe.FuelDistilleryRecipe;
import org.mob.reach_for_the_stars.screen.menu.FuelDistilleryControllerMenu;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuelDistilleryControllerBlockEntity extends BlockEntity implements MenuProvider {

    private int checkStructureTimer = 0;
    public boolean showPreview = false;

    public final ItemStackHandler upgradeHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            upgrade();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.is(ModTags.Items.MOB_UPGRADES_SPEED);
        }
    };

    private int progress = 0;
    private int MAX_PROGRESS = 100;
    private final int ENERGY_PER_TICK = 100;

    // Cached Hatch Locations
    private final List<BlockPos> fluidInputHatches = new ArrayList<>();
    private final List<BlockPos> fluidOutputHatches = new ArrayList<>();
    private final List<BlockPos> energyInputHatches = new ArrayList<>();

    public FuelDistilleryControllerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FUEL_DISTILLERY_CONTROLLER_BE.get(), pPos, pBlockState);
    }

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> FuelDistilleryControllerBlockEntity.this.progress;
                case 1 -> FuelDistilleryControllerBlockEntity.this.MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> FuelDistilleryControllerBlockEntity.this.progress = value;
                case 1 -> FuelDistilleryControllerBlockEntity.this.MAX_PROGRESS = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    private void upgrade(){
        ItemStack upgradeStack = upgradeHandler.getStackInSlot(0);
        if (upgradeStack.isEmpty()){
            MAX_PROGRESS = 100;
            return;
        }
        if (!upgradeStack.isEmpty() && upgradeStack.getItem() instanceof UpgradeItem upgradeItem){
            MAX_PROGRESS = upgradeItem.getSpeed();
        }
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.setItem(0, upgradeHandler.getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level level, BlockPos pos, BlockState state, FuelDistilleryControllerBlockEntity be) {
        if (level.isClientSide()) return;

        if (be.checkStructureTimer-- <= 0) {
            be.checkStructureTimer = 20;
            be.updateStructure(level, pos, state);
        }

        if (state.getValue(FuelDistilleryControllerBlock.FORMED)) {
            be.processRecipe(level);
        }
    }

    private void updateStructure(Level level, BlockPos pos, BlockState state) {
        MultiblockPattern.MatchResult result = MultiblockPatterns.FUEL_DISTILLERY.check(
                level, pos, state.getValue(FuelDistilleryControllerBlock.FACING)
        );

        boolean wasFormed = state.getValue(FuelDistilleryControllerBlock.FORMED);

        if (result.isFormed()) {
            if (!wasFormed) {
                level.setBlock(pos, state.setValue(FuelDistilleryControllerBlock.FORMED, true), 3);
            }

            if (this.fluidInputHatches.isEmpty() || this.fluidOutputHatches.isEmpty() || this.energyInputHatches.isEmpty()) {
                linkHatches(level, pos, result);
            }

        } else {
            if (wasFormed) {
                level.setBlock(pos, state.setValue(FuelDistilleryControllerBlock.FORMED, false), 3);
                unlinkHatches(level);
                this.progress = 0;
                setChanged();
            }
        }
    }

    private void linkHatches(Level level, BlockPos controllerPos, MultiblockPattern.MatchResult result) {
        unlinkHatches(level);

        // 'F' is Fluid Input
        for (BlockPos targetPos : result.getPositions('F')) {
            if (level.getBlockEntity(targetPos) instanceof FluidInputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.fluidInputHatches.add(targetPos);
            }
        }

        // 'G' is Fluid Output
        for (BlockPos targetPos : result.getPositions('G')) {
            if (level.getBlockEntity(targetPos) instanceof FluidOutputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.fluidOutputHatches.add(targetPos);
            }
        }

        // 'H' is Energy Input
        for (BlockPos targetPos : result.getPositions('H')) {
            if (level.getBlockEntity(targetPos) instanceof EnergyInputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.energyInputHatches.add(targetPos);
            }
        }
    }


    private void unlinkHatches(Level level) {
        for (BlockPos targetPos : fluidInputHatches) {
            if (level.getBlockEntity(targetPos) instanceof FluidInputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }
        for (BlockPos targetPos : fluidOutputHatches) {
            if (level.getBlockEntity(targetPos) instanceof FluidOutputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }
        for (BlockPos targetPos : energyInputHatches) {
            if (level.getBlockEntity(targetPos) instanceof EnergyInputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }

        this.fluidInputHatches.clear();
        this.fluidOutputHatches.clear();
        this.energyInputHatches.clear();
    }

    private Optional<FuelDistilleryRecipe> getRecipe(FluidStack inputBuffer, Level level) {
        if (inputBuffer.isEmpty()) return Optional.empty();

        return level.getRecipeManager().getAllRecipesFor(FuelDistilleryRecipe.Type.INSTANCE)
                .stream()
                .filter(recipe -> recipe.getInputFluid().isFluidEqual(inputBuffer) &&
                        inputBuffer.getAmount() >= recipe.getInputFluid().getAmount())
                .findFirst();
    }

    private void processRecipe(Level level) {
        if (fluidInputHatches.isEmpty() || fluidOutputHatches.isEmpty() || energyInputHatches.isEmpty()) return;

        FluidInputHatchBlockEntity fluidIn = (FluidInputHatchBlockEntity) level.getBlockEntity(fluidInputHatches.get(0));
        FluidOutputHatchBlockEntity fluidOut = (FluidOutputHatchBlockEntity) level.getBlockEntity(fluidOutputHatches.get(0));
        EnergyInputHatchBlockEntity energyIn = (EnergyInputHatchBlockEntity) level.getBlockEntity(energyInputHatches.get(0));

        if (fluidIn == null || fluidOut == null || energyIn == null) return;

        FluidStack currentInputFluid = fluidIn.getInternalTank().getFluid();

        Optional<FuelDistilleryRecipe> match = getRecipe(currentInputFluid, level);

        if (match.isEmpty()) {
            this.progress = 0;
            return;
        }

        FuelDistilleryRecipe recipe = match.get();
        FluidStack outputResult = recipe.getOutputFluid();

        // Check capacity in the output hatch
        if (fluidOut.getInternalTank().fill(outputResult, IFluidHandler.FluidAction.SIMULATE) < outputResult.getAmount()) {
            return;
        }

        int extracted = energyIn.getInternalEnergyStorage().extractEnergy(ENERGY_PER_TICK, false);
        if (extracted == ENERGY_PER_TICK) {
            this.progress++;

            if (this.progress >= MAX_PROGRESS) {
                fluidIn.getInternalTank().drain(recipe.getInputFluid(), IFluidHandler.FluidAction.EXECUTE);
                fluidOut.getInternalTank().fill(outputResult, IFluidHandler.FluidAction.EXECUTE);

                this.progress = 0;
                setChanged();
            }
        }
    }
    
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("Progress", this.progress);
        pTag.put("upgrade", upgradeHandler.serializeNBT());
        pTag.putBoolean("ShowPreview", this.showPreview);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        upgrade();
        this.progress = pTag.getInt("Progress");
        upgradeHandler.deserializeNBT(pTag.getCompound("upgrade"));
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
        return Component.translatable("block.reach_for_the_stars.fuel_distillery_controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FuelDistilleryControllerMenu(pContainerId, pPlayerInventory, this, this.data);
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