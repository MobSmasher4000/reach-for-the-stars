package org.mob.reach_for_the_stars.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.ContainedSpaceStationRecipe;

import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class ContainedSpaceStationRecipeCategory implements IRecipeCategory<ContainedSpaceStationRecipe> {
    public static final ResourceLocation UID = resourceLocation("contained_space_station");

    public static final RecipeType<ContainedSpaceStationRecipe> CONTAINED_SPACE_STATION_RECIPE_TYPE =
            new RecipeType<>(UID, ContainedSpaceStationRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public ContainedSpaceStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(200, 70);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CONTAINED_SPACE_STATION_CONTROLLER.get()));
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public RecipeType<ContainedSpaceStationRecipe> getRecipeType() {
        return CONTAINED_SPACE_STATION_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.reach_for_the_stars.printer_3d_controller");
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
    public void draw(ContainedSpaceStationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int fluidCols = recipe.getFluidInputs().isEmpty() ? 0 : Math.min(3, recipe.getFluidInputs().size());

        int fluidBaseX = 10;
        int itemBaseX = fluidBaseX + (fluidCols * 18) + (fluidCols > 0 ? 10 : 0);

        int arrowX = itemBaseX + 54 + 10;

        arrow.draw(guiGraphics, arrowX, 28);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ContainedSpaceStationRecipe recipe, IFocusGroup focuses) {

        int slotSize = 18;

        // Fluid tanks
        int fluidBaseX = 10;
        int fluidBaseY = 10;
        List<FluidStack> fluids = recipe.getFluidInputs();

        for (int i = 0; i < fluids.size(); i++) {
            int row = i / 3;
            int col = i % 3;
            int x = fluidBaseX + col * slotSize;
            int y = fluidBaseY + row * slotSize;

            builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                    .addIngredient(ForgeTypes.FLUID_STACK, fluids.get(i))
                    .setFluidRenderer(fluids.get(i).getAmount(), true, 16, 16);
        }

        int fluidCols = fluids.isEmpty() ? 0 : Math.min(3, fluids.size());

        // Item inputs
        int itemBaseX = fluidBaseX + (fluidCols * slotSize) + (fluidCols > 0 ? 10 : 0);
        int itemBaseY = 10;

        List<Ingredient> inputs = recipe.getIngredientsList();
        List<Integer> counts = recipe.getInputCounts();

        for (int i = 0; i < inputs.size(); i++) {
            int row = i / 3;
            int col = i % 3;
            int x = itemBaseX + col * slotSize;
            int y = itemBaseY + row * slotSize;

            Ingredient ing = inputs.get(i);
            int count = counts.get(i);

            ItemStack[] matching = ing.getItems();
            if (matching.length > 0) {
                ItemStack stack = matching[0].copy();
                stack.setCount(count);
                builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                        .addItemStack(stack);
            } else {
                builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                        .addIngredients(ing);
            }
        }

        // Item output
        int arrowX = itemBaseX + 54 + 10;
        int outputX = arrowX + 24 + 10;

        builder.addSlot(RecipeIngredientRole.OUTPUT, outputX, 27)
                .addItemStack(recipe.getResultItem(null));
    }
}