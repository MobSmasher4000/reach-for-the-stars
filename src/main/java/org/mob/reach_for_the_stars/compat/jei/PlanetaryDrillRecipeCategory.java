package org.mob.reach_for_the_stars.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.PlanetaryDrillRecipe;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class PlanetaryDrillRecipeCategory implements IRecipeCategory<PlanetaryDrillRecipe> {

    public static final ResourceLocation UID = resourceLocation("planetary_drill");
    public static final mezz.jei.api.recipe.RecipeType<PlanetaryDrillRecipe> PLANETARY_DRILL_RECIPE_TYPE =
            new mezz.jei.api.recipe.RecipeType<>(UID, PlanetaryDrillRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public PlanetaryDrillRecipeCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(160, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.STEEL_DRILL_CONTROLLER.get()));
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<PlanetaryDrillRecipe> getRecipeType() {
        return PLANETARY_DRILL_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("category.reach_for_the_stars.planetary_drill");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void draw(PlanetaryDrillRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 65, 30);

        Font font = Minecraft.getInstance().font;

        // 1. Draw Dimension Text
        String dimPath = recipe.getDimension().getPath();
        String formattedDim = dimPath.substring(0, 1).toUpperCase() + dimPath.substring(1);
        Component dimText = Component.literal("Dim: " + formattedDim);
        guiGraphics.drawString(font, dimText, 60, 65, 0xFF888888, false);

        // Draw "Processed in:"
        Component processedText = Component.literal("Processed in:");
        int textWidth = font.width(processedText);

        int textX = 138 - textWidth - 4;

        guiGraphics.drawString(font, processedText, textX, 8, 0xFF555555, false);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PlanetaryDrillRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 28, 11)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidInput())
                .setFluidRenderer(recipe.getFluidInput().getAmount(), true, 25, 50);


        builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 25)
                .addItemStack(recipe.getResultItem(null));

        Block machineBlock = ForgeRegistries.BLOCKS.getValue(recipe.getMachine());
        if (machineBlock != null) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 138, 4)
                    .addItemStack(new ItemStack(machineBlock));
        }
    }
}