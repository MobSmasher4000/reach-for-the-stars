package org.mob.reach_for_the_stars.screen.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.mob.reach_for_the_stars.screen.menu.T5RocketAssemblerControllerMenu;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class T5RocketAssemblerControllerScreen extends AbstractContainerScreen<T5RocketAssemblerControllerMenu> {

    private static final ResourceLocation TEXTURE = resourceLocation("textures/gui/multiblock_rocket_gui.png");

    public T5RocketAssemblerControllerScreen(T5RocketAssemblerControllerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 173;
        this.imageHeight = 221;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();

        String progressText = "Progress : " + progress + " / " + maxProgress;

        int textWidth = this.font.width(progressText);
        int textX = (this.imageWidth - textWidth) / 2; 

        int textY = 40;

        guiGraphics.drawString(this.font, progressText, textX, textY, 4210752, false);
    }
}