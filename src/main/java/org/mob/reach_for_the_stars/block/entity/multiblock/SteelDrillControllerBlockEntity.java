package org.mob.reach_for_the_stars.block.entity.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.mob.mob_lib.item.custom.UpgradeItem;
import org.mob.mob_lib.util.ModTags;
import org.mob.reach_for_the_stars.block.custom.multiblock.SteelDrillControllerBlock;
import org.mob.reach_for_the_stars.block.custom.multiblock.MultiblockPatterns;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.block.entity.hatch.EnergyInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.FluidInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.ItemOutputHatchBlockEntity;
import org.mob.reach_for_the_stars.recipe.SteelDrillRecipe;
import org.mob.reach_for_the_stars.screen.menu.SteelDrillControllerMenu;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SteelDrillControllerBlockEntity extends BlockEntity implements MenuProvider {

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
    private int MAX_PROGRESS = 500;
    private final int ENERGY_PER_TICK = 100;

    // Cached Hatch Locations
    private final List<BlockPos> fluidInputHatches = new ArrayList<>();
    private final List<BlockPos> itemOutputHatches = new ArrayList<>();
    private final List<BlockPos> energyInputHatches = new ArrayList<>();

    public SteelDrillControllerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.STEEL_DRILL_CONTROLLER_BE.get(), pPos, pBlockState);
    }

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> SteelDrillControllerBlockEntity.this.progress;
                case 1 -> SteelDrillControllerBlockEntity.this.MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> SteelDrillControllerBlockEntity.this.progress = value;
                case 1 -> SteelDrillControllerBlockEntity.this.MAX_PROGRESS = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    private void upgrade() {
        ItemStack upgradeStack = upgradeHandler.getStackInSlot(0);
        if (upgradeStack.isEmpty()) {
            MAX_PROGRESS = 100;
            return;
        }
        if (upgradeStack.getItem() instanceof UpgradeItem upgradeItem) {
            MAX_PROGRESS = upgradeItem.getSpeed();
        }
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.setItem(0, upgradeHandler.getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SteelDrillControllerBlockEntity be) {
        if (level.isClientSide()) return;

        if (be.checkStructureTimer-- <= 0) {
            be.checkStructureTimer = 20;
            be.updateStructure(level, pos, state);
        }

        if (state.getValue(SteelDrillControllerBlock.FORMED)) {
            be.processRecipe(level);
        }
    }

    private void updateStructure(Level level, BlockPos pos, BlockState state) {
        MultiblockPattern.MatchResult result = MultiblockPatterns.STEEL_DRILL.check(
                level, pos, state.getValue(SteelDrillControllerBlock.FACING)
        );

        boolean wasFormed = state.getValue(SteelDrillControllerBlock.FORMED);

        if (result.isFormed()) {
            if (!wasFormed) {
                level.setBlock(pos, state.setValue(SteelDrillControllerBlock.FORMED, true), 3);
            }

            if (this.fluidInputHatches.isEmpty() || this.itemOutputHatches.isEmpty() || this.energyInputHatches.isEmpty()) {
                linkHatches(level, pos, result);
            }

        } else {
            if (wasFormed) {
                level.setBlock(pos, state.setValue(SteelDrillControllerBlock.FORMED, false), 3);
                unlinkHatches(level);
                this.progress = 0;
                setChanged();
            }
        }
    }

    private void linkHatches(Level level, BlockPos controllerPos, MultiblockPattern.MatchResult result) {
        unlinkHatches(level);

        // 'I' is Fluid Input
        for (BlockPos targetPos : result.getPositions('I')) {
            if (level.getBlockEntity(targetPos) instanceof FluidInputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.fluidInputHatches.add(targetPos);
            }
        }

        // 'F' is Item Output
        for (BlockPos targetPos : result.getPositions('F')) {
            if (level.getBlockEntity(targetPos) instanceof ItemOutputHatchBlockEntity hatch) {
                hatch.setControllerPos(controllerPos);
                this.itemOutputHatches.add(targetPos);
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
        for (BlockPos targetPos : itemOutputHatches) {
            if (level.getBlockEntity(targetPos) instanceof ItemOutputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }
        for (BlockPos targetPos : energyInputHatches) {
            if (level.getBlockEntity(targetPos) instanceof EnergyInputHatchBlockEntity hatch) hatch.setControllerPos(null);
        }

        this.fluidInputHatches.clear();
        this.itemOutputHatches.clear();
        this.energyInputHatches.clear();
    }

    private Optional<SteelDrillRecipe> getRecipe(FluidStack inputBuffer, Level level) {
        if (inputBuffer.isEmpty()) return Optional.empty();

        ResourceLocation currentDimension = level.dimension().location();

        return level.getRecipeManager().getAllRecipesFor(SteelDrillRecipe.Type.INSTANCE).stream()
                .filter(recipe -> recipe.getFluidInput().getFluid() == inputBuffer.getFluid() &&
                        inputBuffer.getAmount() >= recipe.getFluidInput().getAmount())
                .filter(recipe-> recipe.getDimension().equals(currentDimension))
                .findFirst();
    }

    private void processRecipe(Level level) {
        if (fluidInputHatches.isEmpty() || itemOutputHatches.isEmpty() || energyInputHatches.isEmpty()) return;

        FluidInputHatchBlockEntity fluidIn = (FluidInputHatchBlockEntity) level.getBlockEntity(fluidInputHatches.get(0));
        ItemOutputHatchBlockEntity itemOutBE = (ItemOutputHatchBlockEntity) level.getBlockEntity(itemOutputHatches.get(0));
        ItemStackHandler inventory = itemOutBE.getInternalInventory();
        EnergyInputHatchBlockEntity energyIn = (EnergyInputHatchBlockEntity) level.getBlockEntity(energyInputHatches.get(0));

        if (fluidIn == null || itemOutBE == null || energyIn == null) return;

        FluidStack currentInputFluid = fluidIn.getInternalTank().getFluid();
        Optional<SteelDrillRecipe> match = getRecipe(currentInputFluid, level);

        if (match.isEmpty()) {
            this.progress = 0;
            return;
        }

        SteelDrillRecipe recipe = match.get();
        ItemStack outputResult = recipe.getResultItem(level.registryAccess());

        itemOutBE.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {

            ItemStack remaining = ItemHandlerHelper.insertItem(inventory, outputResult, true);
            if (!remaining.isEmpty()) return;

            int extracted = energyIn.getInternalEnergyStorage().extractEnergy(ENERGY_PER_TICK, false);
            if (extracted == ENERGY_PER_TICK) {
                this.progress++;

                if (this.progress >= MAX_PROGRESS) {
                    fluidIn.getInternalTank().drain(recipe.getFluidInput(), IFluidHandler.FluidAction.EXECUTE);
                    ItemHandlerHelper.insertItem(inventory, outputResult.copy(), false);

                    this.progress = 0;
                    setChanged();
                }
            }
        });
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
        return Component.translatable("block.reach_for_the_stars.steel_drill_controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SteelDrillControllerMenu(pContainerId, pPlayerInventory, this, this.data);
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