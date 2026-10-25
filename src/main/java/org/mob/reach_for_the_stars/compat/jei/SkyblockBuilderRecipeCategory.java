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
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.SkyblockBuilderRecipe;

import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class SkyblockBuilderRecipeCategory implements IRecipeCategory<SkyblockBuilderRecipe> {
    public static final ResourceLocation UID = resourceLocation("skyblock_builder");

    public static final RecipeType<SkyblockBuilderRecipe> SKYBLOCK_BUILDER_RECIPE_TYPE =
            new RecipeType<>(UID, SkyblockBuilderRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public SkyblockBuilderRecipeCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(160, 160);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SKYBLOCK_BUILDER_CONTROLLER.get()));
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public RecipeType<SkyblockBuilderRecipe> getRecipeType() {
        return SKYBLOCK_BUILDER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.reach_for_the_stars.skyblock_builder_controller");
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
    public void draw(SkyblockBuilderRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 92, 28);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SkyblockBuilderRecipe recipe, IFocusGroup focuses) {

        // Fluid Tank (Far Left)
        builder.addSlot(RecipeIngredientRole.INPUT, 6, 11)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidInput())
                .setFluidRenderer(recipe.getFluidInput().getAmount(), true, 16, 45);

        // Item Grids (Middle)
        int baseX = 33;
        int baseY = 10;
        int slotSize = 18;

        List<Ingredient> inputs = recipe.getIngredientsList();
        List<Integer> counts = recipe.getInputCounts();

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

        // Item Output (Right)
        builder.addSlot(RecipeIngredientRole.OUTPUT, 126, 27)
                .addItemStack(recipe.getResultItem(null));
    }
}