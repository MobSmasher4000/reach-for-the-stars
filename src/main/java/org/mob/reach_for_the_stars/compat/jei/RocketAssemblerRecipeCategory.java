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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.RocketAssemblerRecipe;

import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class RocketAssemblerRecipeCategory implements IRecipeCategory<RocketAssemblerRecipe> {
    public static final ResourceLocation UID = resourceLocation("rocket_assembler");

    public static final RecipeType<RocketAssemblerRecipe> ROCKET_ASSEMBLER_RECIPE_TYPE =
            new RecipeType<>(UID, RocketAssemblerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public RocketAssemblerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(180, 100);

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, 
                new ItemStack(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get()));
                
        this.arrow = helper.createAnimatedRecipeArrow(100);
    }

    @Override
    public RecipeType<RocketAssemblerRecipe> getRecipeType() {
        return ROCKET_ASSEMBLER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("category.reach_for_the_stars.rocket_assembler");
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
    public void draw(RocketAssemblerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 72, 28 + 20);

        Font font = Minecraft.getInstance().font;

        // Draw "Processed in or higher"
        // Draw "tier rocket assembler:"
        Component line1 = Component.literal("Processed in or higher");
        Component line2 = Component.literal("tier rocket assembler:");

        int textWidth1 = font.width(line1);
        int textWidth2 = font.width(line2);

        int textX1 = 140 - textWidth1 - 4;
        int textX2 = 140 - textWidth2 - 4;

        guiGraphics.drawString(font, line1, textX1, 7, 0xFF555555, false);
        guiGraphics.drawString(font, line2, textX2, 17, 0xFF555555, false);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RocketAssemblerRecipe recipe, IFocusGroup focuses) {

        int baseX = 13;
        int baseY = 10 + 20;
        int slotSize = 18;

        List<Ingredient> inputs = recipe.getIngredientsList();
        List<Integer> counts = recipe.getInputCounts();

        // Input Slots (3x3 grid)
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
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 27 + 20)
                .addItemStack(recipe.getResultItem(null));
        
        Block machineBlock;
        switch (recipe.getTier()){
            case 1-> machineBlock = ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get();
            case 2-> machineBlock = ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get();
            case 3-> machineBlock = ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get();
            case 4-> machineBlock = ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get();
            default -> machineBlock = ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get();
        }

        builder.addSlot(RecipeIngredientRole.CATALYST, 140, 8)
                .addItemStack(new ItemStack(machineBlock));
    }
}