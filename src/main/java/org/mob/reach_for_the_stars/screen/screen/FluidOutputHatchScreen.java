package org.mob.reach_for_the_stars.screen.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.screen.menu.FluidOutputHatchMenu;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class FluidOutputHatchScreen extends AbstractContainerScreen<FluidOutputHatchMenu> {
    private static final ResourceLocation TEXTURE =
            resourceLocation("textures/gui/hatch/fluid_output_hatch_gui.png");

    private static final int TANK_X = 80;
    private static final int TANK_Y = 10;
    private static final int TANK_WIDTH = 48;
    private static final int TANK_HEIGHT = 63;

    public FluidOutputHatchScreen(FluidOutputHatchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {super.init();}

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);

        renderFluidTooltip(guiGraphics, mouseX, mouseY, this.leftPos, this.topPos);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderFluid(guiGraphics, x, y);
    }

    private void renderFluid(GuiGraphics guiGraphics, int x, int y) {
        FluidStack fluid = menu.getFluid();
        if (fluid.isEmpty()) return;

        int capacity = menu.getCapacity();

        int fluidHeight = (int) (TANK_HEIGHT * ((float) fluid.getAmount() / capacity));
        if (fluidHeight <= 0) return;

        int renderY = y + TANK_Y + (TANK_HEIGHT - fluidHeight);
        int renderX = x + TANK_X;

        // Fluid texture and color properties
        IClientFluidTypeExtensions ext = IClientFluidTypeExtensions.of(fluid.getFluid());
        ResourceLocation stillTexture = ext.getStillTexture(fluid);
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTexture);
        int color = ext.getTintColor(fluid);

        float a = ((color >> 24) & 0xFF) / 255f;
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        RenderSystem.setShaderColor(r, g, b, a);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        guiGraphics.blit(renderX, renderY, 0, TANK_WIDTH, fluidHeight, sprite);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void renderFluidTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        int screenTankX = x + TANK_X;
        int screenTankY = y + TANK_Y;

        // Check if the mouse is inside the tank boundaries
        if (mouseX >= screenTankX && mouseX < screenTankX + TANK_WIDTH &&
                mouseY >= screenTankY && mouseY < screenTankY + TANK_HEIGHT) {

            FluidStack fluid = menu.getFluid();
            Component text = fluid.isEmpty() ? Component.literal("Empty") :
                    Component.literal(fluid.getDisplayName().getString() + ": " + fluid.getAmount() + "mB");
            guiGraphics.renderTooltip(this.font, text, mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }
}