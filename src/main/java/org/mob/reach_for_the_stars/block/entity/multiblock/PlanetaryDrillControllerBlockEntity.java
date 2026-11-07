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
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.mob.mob_lib.item.custom.UpgradeItem;
import org.mob.mob_lib.util.ModTags;
import org.mob.reach_for_the_stars.block.custom.multiblock.PlanetaryDrillControllerBlock;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.block.entity.hatch.EnergyInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.FluidInputHatchBlockEntity;
import org.mob.reach_for_the_stars.block.entity.hatch.ItemOutputHatchBlockEntity;
import org.mob.reach_for_the_stars.recipe.PlanetaryDrillRecipe;
import org.mob.reach_for_the_stars.screen.menu.PlanetaryDrillControllerMenu;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanetaryDrillControllerBlockEntity extends BlockEntity implements MenuProvider {

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

    private final List<IFluidHandler> fluidInputHatches = new ArrayList<>();
    private final List<IItemHandler> itemOutputHatches = new ArrayList<>();
    private final List<IEnergyStorage> energyInputHatches = new ArrayList<>();

    public PlanetaryDrillControllerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PLANETARY_DRILL_CONTROLLER_BE.get(), pPos, pBlockState);
    }

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> PlanetaryDrillControllerBlockEntity.this.progress;
                case 1 -> PlanetaryDrillControllerBlockEntity.this.MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> PlanetaryDrillControllerBlockEntity.this.progress = value;
                case 1 -> PlanetaryDrillControllerBlockEntity.this.MAX_PROGRESS = value;
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
            MAX_PROGRESS = 500;
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

    public static void tick(Level level, BlockPos pos, BlockState state, PlanetaryDrillControllerBlockEntity be) {
        if (level.isClientSide()) return;

        if (be.checkStructureTimer-- <= 0) {
            be.checkStructureTimer = 20;
            be.updateStructure(level, pos, state);
        }

        if (state.getValue(PlanetaryDrillControllerBlock.FORMED)) {
            be.processRecipe(level);
        }
    }

    private void updateStructure(Level level, BlockPos pos, BlockState state) {
        if (!(state.getBlock() instanceof PlanetaryDrillControllerBlock drillBlock)) return;
        
        MultiblockPattern pattern = drillBlock.getTier().getPattern();
        MultiblockPattern.MatchResult result = pattern.check(level, pos, state.getValue(PlanetaryDrillControllerBlock.FACING));

        boolean wasFormed = state.getValue(PlanetaryDrillControllerBlock.FORMED);

        if (result.isFormed()) {
            if (!wasFormed) {
                level.setBlock(pos, state.setValue(PlanetaryDrillControllerBlock.FORMED, true), 3);
            }
            if (this.fluidInputHatches.isEmpty() && this.itemOutputHatches.isEmpty()) {
                linkHatches(level, pos, result);
            }
        } else {
            if (wasFormed) {
                level.setBlock(pos, state.setValue(PlanetaryDrillControllerBlock.FORMED, false), 3);
                unlinkHatches();
                this.progress = 0;
                setChanged();
            }
        }
    }

    private void linkHatches(Level level, BlockPos controllerPos, MultiblockPattern.MatchResult result) {
        unlinkHatches();

        for (List<BlockPos> positions : result.getMatchedBlocks().values()) {
            for (BlockPos targetPos : positions) {
                BlockEntity be = level.getBlockEntity(targetPos);
                if (be == null) continue;

                if (be instanceof ItemOutputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemOutputHatches::add);
                } else if (be instanceof FluidInputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(fluidInputHatches::add);
                } else if (be instanceof EnergyInputHatchBlockEntity) {
                    be.getCapability(ForgeCapabilities.ENERGY).ifPresent(energyInputHatches::add);
                }
            }
        }
    }

    private void unlinkHatches() {
        this.itemOutputHatches.clear();
        this.energyInputHatches.clear();
        this.fluidInputHatches.clear();
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
            extractedTotal += storage.extractEnergy(amount - extractedTotal, true);
            if (extractedTotal >= amount) break;
        }

        if (extractedTotal < amount) return false;

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

    private Optional<PlanetaryDrillRecipe> getRecipe(Level level) {
        ResourceLocation currentDimension = level.dimension().location();
        ResourceLocation currentBlockId = ForgeRegistries.BLOCKS.getKey(this.getBlockState().getBlock());

        return level.getRecipeManager().getAllRecipesFor(PlanetaryDrillRecipe.Type.INSTANCE)
                .stream()
                .filter(recipe -> recipe.getDimension().equals(currentDimension))
                .filter(recipe -> recipe.getMachine().equals(currentBlockId))
                .filter(recipe -> hasEnoughFluid(recipe.getFluidInput()))
                .findFirst();
    }

    private void processRecipe(Level level) {
        if (fluidInputHatches.isEmpty() || itemOutputHatches.isEmpty() || energyInputHatches.isEmpty()) return;

        Optional<PlanetaryDrillRecipe> match = getRecipe(level);

        if (match.isEmpty()) {
            this.progress = 0;
            return;
        }

        PlanetaryDrillRecipe recipe = match.get();
        ItemStack outputResult = recipe.getResultItem(level.registryAccess());

        if (!tryInsertOutput(outputResult, true)) return;

        if (tryExtractEnergy(ENERGY_PER_TICK, false)) {
            this.progress++;

            if (this.progress >= MAX_PROGRESS) {
                consumeFluid(recipe.getFluidInput());
                tryInsertOutput(outputResult.copy(), false);

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
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new PlanetaryDrillControllerMenu(pContainerId, pPlayerInventory, this, this.data);
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