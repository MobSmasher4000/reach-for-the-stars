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
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.T1RocketAssemblerRecipe;

import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class T1RocketAssemblerRecipeCategory implements IRecipeCategory<T1RocketAssemblerRecipe> {
    public static final ResourceLocation UID = resourceLocation("t1_rocket_assembler");
    public static final ResourceLocation TEXTURE = resourceLocation("textures/gui/jei/item_to_item_jei.png");

    public static final RecipeType<T1RocketAssemblerRecipe> T1_ROCKET_ASSEMBLER_RECIPE_TYPE =
            new RecipeType<>(UID, T1RocketAssemblerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public T1RocketAssemblerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 151, 66);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get()));
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public RecipeType<T1RocketAssemblerRecipe> getRecipeType() {
        return T1_ROCKET_ASSEMBLER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.reach_for_the_stars.t1_rocket_assembler_controller");
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
    public void draw(T1RocketAssemblerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 72, 28);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, T1RocketAssemblerRecipe recipe, IFocusGroup focuses) {

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