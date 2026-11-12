package org.mob.reach_for_the_stars.compat.jei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import java.util.HashMap;
import java.util.Map;

public class MultiblockPreviewCategory implements IRecipeCategory<MultiblockPattern> {

    public static final RecipeType<MultiblockPattern> MULTIBLOCK_PATTERN_RECIPE_TYPE =
            RecipeType.create(Reach_for_the_stars.MOD_ID, "multiblock", MultiblockPattern.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MultiblockPreviewCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(200, 250);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.COMPLETION_TROPHY.get()));
    }

    @Override
    public RecipeType<MultiblockPattern> getRecipeType() {
        return MULTIBLOCK_PATTERN_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.reach_for_the_stars.multiblock");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MultiblockPattern recipe, IFocusGroup focuses) {
        Map<net.minecraft.world.item.Item, Integer> materialCounts = new HashMap<>();
        net.minecraft.world.item.Item controllerItem = null;

        // Tally up all required blocks that have an Item form
        for (Map.Entry<BlockPos, BlockState> entry : recipe.getBlueprint().entrySet()) {
            BlockState state = entry.getValue();
            if (state.isAir()) continue;

            net.minecraft.world.item.Item item = state.getBlock().asItem();

            // Skip blocks that don't have an item form
            if (item == net.minecraft.world.item.Items.AIR) continue;


            if (entry.getKey().equals(BlockPos.ZERO)) {
                controllerItem = item;
            }

            materialCounts.put(item, materialCounts.getOrDefault(item, 0) + 1);
        }

        if (controllerItem != null) {
            builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(new ItemStack(controllerItem));
            builder.addInvisibleIngredients(RecipeIngredientRole.CATALYST).addItemStack(new ItemStack(controllerItem));
        }

        // Render materials in grid on the right side
        int slotX = 150;
        int slotY = 5;
        int maxPerRow = 4;
        int currentInRow = 0;

        for (Map.Entry<net.minecraft.world.item.Item, Integer> entry : materialCounts.entrySet()) {
            ItemStack stack = new ItemStack(entry.getKey(), entry.getValue());

            builder.addSlot(RecipeIngredientRole.INPUT, slotX + (currentInRow * 18), slotY)
                    .addItemStack(stack);

            currentInRow++;
            if (currentInRow >= maxPerRow) {
                currentInRow = 0;
                slotY += 18;
            }
        }
    }

    @Override
    public void draw(MultiblockPattern recipe, IRecipeSlotsView recipeSlotsView, net.minecraft.client.gui.GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Map<BlockPos, BlockState> blueprint = recipe.getBlueprint();
        if (blueprint.isEmpty()) return;

        // Calculate dimensions to dynamically scale the 3D model
        int maxX = 0, maxY = 0, maxZ = 0;
        for (BlockPos pos : blueprint.keySet()) {
            maxX = Math.max(maxX, pos.getX());
            maxY = Math.max(maxY, pos.getY());
            maxZ = Math.max(maxZ, pos.getZ());
        }
        int maxDimension = Math.max(Math.max(maxX, maxY), maxZ);
        
        // Dynamically scale down multiblock
        float scale = 40.0f / (maxDimension > 0 ? (maxDimension / 2.0f) : 1.0f);

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        // Position the 3D model on left side
        poseStack.translate(45, 70, 100);

        poseStack.scale(scale, -scale, scale);

        poseStack.mulPose(Axis.XP.rotationDegrees(30f));

        float rotation = (System.currentTimeMillis() / 20f) % 360f;
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        // Center the multiblock around the pivot point
        poseStack.translate(-maxX / 2.0f, -maxY / 2.0f, -maxZ / 2.0f);

        RenderSystem.enableDepthTest();

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();

        // Render each block
        for (Map.Entry<BlockPos, BlockState> entry : blueprint.entrySet()) {
            if (entry.getValue().isAir()) continue;

            poseStack.pushPose();
            BlockPos pos = entry.getKey();
            poseStack.translate(pos.getX(), pos.getY(), pos.getZ());

            blockRenderer.renderSingleBlock(
                    entry.getValue(), 
                    poseStack, 
                    bufferSource, 
                    LightTexture.FULL_BRIGHT,
                    OverlayTexture.NO_OVERLAY, 
                    ModelData.EMPTY, 
                    RenderType.translucent()
            );

            poseStack.popPose();
        }

        bufferSource.endBatch();
        poseStack.popPose();
    }
}