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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.FuelDistilleryRecipe;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class FuelDistilleryRecipeCategory implements IRecipeCategory<FuelDistilleryRecipe> {
    
    public static final ResourceLocation UID = resourceLocation("fuel_distillery_controller");
    public static final ResourceLocation TEXTURE = resourceLocation("textures/gui/jei/fluid_to_fluid_jei.png");

    public static final mezz.jei.api.recipe.RecipeType<FuelDistilleryRecipe> FUEL_DISTILLERY_RECIPE_TYPE =
            new mezz.jei.api.recipe.RecipeType<>(UID, FuelDistilleryRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public FuelDistilleryRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0,130, 70);

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FUEL_DISTILLERY_CONTROLLER.get()));

        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<FuelDistilleryRecipe> getRecipeType() {
        return FUEL_DISTILLERY_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.reach_for_the_stars.fuel_distillery_controller");
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
    public void draw(FuelDistilleryRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 65, 30);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FuelDistilleryRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 11)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getInputFluid())
                .setFluidRenderer(recipe.getInputFluid().getAmount(), true, 25, 50);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 12)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getOutputFluid())
                .setFluidRenderer(recipe.getOutputFluid().getAmount(), true, 25, 50);
    }
}