package org.mob.reach_for_the_stars.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
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
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.RitualSiteRecipe;

import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class RitualSiteRecipeCategory implements IRecipeCategory<RitualSiteRecipe> {
    public static final ResourceLocation UID = resourceLocation("ritual_site");

    public static final RecipeType<RitualSiteRecipe> RITUAL_SITE_RECIPE_TYPE =
            new RecipeType<>(UID, RitualSiteRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public RitualSiteRecipeCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(151, 166);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.RITUAL_SITE_CONTROLLER.get()));
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public RecipeType<RitualSiteRecipe> getRecipeType() {
        return RITUAL_SITE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.reach_for_the_stars.ritual_site_controller");
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
    public void draw(RitualSiteRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 72, 28);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RitualSiteRecipe recipe, IFocusGroup focuses) {

        int baseX = 13;
        int baseY = 10;
        int slotSize = 18;

        List<Ingredient> inputs = recipe.getIngredientsList();
        List<Integer> counts = recipe.getInputCounts();

        // Input Slots
        for (int i = 0; i < inputs.size(); i++) {
            int row = i / 3;
            int col = i % 3;
            int x = baseX + col * slotSize;
            int y = baseY + row * slotSize;

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

        // Output slot
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 27)
                .addItemStack(recipe.getResultItem(null));
    }
}