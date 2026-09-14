package org.mob.reach_for_the_stars.block.entity.hatch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.screen.menu.FluidOutputHatchMenu;

public class FluidOutputHatchBlockEntity extends BlockEntity implements MenuProvider {

    // Fluid storage 1000mB = 1 Buckets.
    private final FluidTank fluidTank = new FluidTank(16000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    // The wrapper exposes the tank to pipes (Extract Only)
    private final LazyOptional<IFluidHandler> externalCapability = LazyOptional.of(() -> new IFluidHandler() {
        @Override public int getTanks() { return fluidTank.getTanks(); }
        @Override public @NotNull FluidStack getFluidInTank(int tank) { return fluidTank.getFluidInTank(tank); }
        @Override public int getTankCapacity(int tank) { return fluidTank.getTankCapacity(tank); }
        @Override public boolean isFluidValid(int tank, @NotNull FluidStack stack) { return false; }

        // Block external insertion
        @Override public int fill(FluidStack resource, FluidAction action) { return 0; }

        // Allow external extraction
        @Override public @NotNull FluidStack drain(FluidStack resource, FluidAction action) { return fluidTank.drain(resource, action); }
        @Override public @NotNull FluidStack drain(int maxDrain, FluidAction action) { return fluidTank.drain(maxDrain, action); }
    });

    // Controller Block position
    private BlockPos controllerPos = null;

    public FluidOutputHatchBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FLUID_OUTPUT_HATCH_BE.get(), pPos, pBlockState);
    }

    public FluidTank getInternalTank() {
        return this.fluidTank;
    }

    public void setControllerPos(@Nullable BlockPos pos) {
        this.controllerPos = pos;
        setChanged();
    }

    @Nullable
    public BlockPos getControllerPos() {
        return this.controllerPos;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return externalCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        externalCapability.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("FluidData", fluidTank.writeToNBT(new CompoundTag()));

        if (this.controllerPos != null) {
            pTag.put("ControllerPos", NbtUtils.writeBlockPos(this.controllerPos));
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("FluidData")) {
            fluidTank.readFromNBT(pTag.getCompound("FluidData"));
        }

        if (pTag.contains("ControllerPos")) {
            this.controllerPos = NbtUtils.readBlockPos(pTag.getCompound("ControllerPos"));
        } else {
            this.controllerPos = null;
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
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

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.reach_for_the_stars.fluid_output_hatch");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FluidOutputHatchMenu(pContainerId, pPlayerInventory, this);
    }
}