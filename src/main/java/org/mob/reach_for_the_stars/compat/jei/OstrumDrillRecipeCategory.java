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
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.OstrumDrillRecipe;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class OstrumDrillRecipeCategory implements IRecipeCategory<OstrumDrillRecipe> {

    public static final ResourceLocation UID = resourceLocation("ostrum_drill");
    public static final ResourceLocation TEXTURE = resourceLocation("textures/gui/jei/fluid_to_item_jei.png");
    public static final mezz.jei.api.recipe.RecipeType<OstrumDrillRecipe> OSTRUM_DRILL_RECIPE_TYPE =
            new mezz.jei.api.recipe.RecipeType<>(UID, OstrumDrillRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public OstrumDrillRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0,130, 70);

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.OSTRUM_DRILL_CONTROLLER.get()));
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<OstrumDrillRecipe> getRecipeType() {
        return OSTRUM_DRILL_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.reach_for_the_stars.ostrum_drill_controller");
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
    public void draw(OstrumDrillRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 65, 30);

        Font font = Minecraft.getInstance().font;

        String dimPath = recipe.getDimension().getPath();

        String formattedDim = dimPath.substring(0, 1).toUpperCase() + dimPath.substring(1);

        Component text = Component.literal("Dim: " + formattedDim);
        guiGraphics.drawString(font, text, 60, 62, 0xFF888888, false);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, OstrumDrillRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 28, 11)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidInput())
                .setFluidRenderer(recipe.getFluidInput().getAmount(), true, 25, 50);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 25)
                .addItemStack(recipe.getResultItem());
    }
}