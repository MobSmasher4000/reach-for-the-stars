package org.mob.reach_for_the_stars.screen.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.block.entity.hatch.FluidInputHatchBlockEntity;
import org.mob.reach_for_the_stars.screen.ModMenuTypes;

public class FluidInputHatchMenu extends AbstractContainerMenu {
    public final FluidInputHatchBlockEntity blockEntity;
    private final Level level;

    public FluidInputHatchMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public FluidInputHatchMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.FLUID_INPUT_HATCH_MENU.get(), pContainerId);

        this.blockEntity = (FluidInputHatchBlockEntity) entity;
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    public FluidStack getFluid() {
        return this.blockEntity.getInternalTank().getFluid();
    }

    public int getCapacity() {
        return this.blockEntity.getInternalTank().getCapacity();
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.FLUID_INPUT_HATCH.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}