package org.mob.reach_for_the_stars.datagen;

import net.allthemods.alltheores.blocks.BlockList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        // Item Buses
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ITEM_INPUT_HATCH.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', BlockList.STEEL_PLATE.get())
                .define('B', BlockList.STEEL_INGOT.get())
                .define('C', Items.CHEST)
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ITEM_OUTPUT_HATCH.get())
                .requires(ModBlocks.ITEM_INPUT_HATCH.get())
                .unlockedBy(getHasName(ModBlocks.ITEM_INPUT_HATCH.get()),has(ModBlocks.ITEM_INPUT_HATCH.get()))
                .save(consumer);

        // Fluid Hatches
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLUID_INPUT_HATCH.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', BlockList.STEEL_PLATE.get())
                .define('B', BlockList.STEEL_INGOT.get())
                .define('C', Items.GLASS)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FLUID_OUTPUT_HATCH.get())
                .requires(ModBlocks.FLUID_INPUT_HATCH.get())
                .unlockedBy(getHasName(ModBlocks.FLUID_INPUT_HATCH.get()),has(ModBlocks.FLUID_INPUT_HATCH.get()))
                .save(consumer);

        // Energy Hatches
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENERGY_INPUT_HATCH.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', BlockList.STEEL_INGOT.get())
                .define('B', Items.REDSTONE)
                .define('C', BlockList.STEEL_BLOCK.get())
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLANK_MOLD.get())
                .pattern("PPP")
                .pattern("PBP")
                .pattern("PPP")
                .define('P', BlockList.STEEL_PLATE.get())
                .define('B', BlockList.STEEL_BLOCK.get())
                .unlockedBy(getHasName(BlockList.STEEL_BLOCK.get()), has(BlockList.STEEL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROCKET_NOSE_MOLD.get())
                .pattern(" P ")
                .pattern("PBP")
                .pattern("PPP")
                .define('P', BlockList.STEEL_PLATE.get())
                .define('B', ModItems.BLANK_MOLD.get())
                .unlockedBy(getHasName(BlockList.STEEL_BLOCK.get()), has(BlockList.STEEL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROCKET_FIN_MOLD.get())
                .pattern(" P ")
                .pattern("PBP")
                .pattern("P P")
                .define('P', BlockList.STEEL_PLATE.get())
                .define('B', ModItems.BLANK_MOLD.get())
                .unlockedBy(getHasName(BlockList.STEEL_BLOCK.get()), has(BlockList.STEEL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_FLUX_COIL.get())
                .pattern("  R")
                .pattern(" I ")
                .pattern("R  ")
                .define('R', Items.REDSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        // Controllers
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FUEL_DISTILLERY_CONTROLLER.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', BlockList.STEEL_PLATE.get())
                .define('B', BlockList.STEEL_INGOT.get())
                .define('C', Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', BlockList.STEEL_PLATE.get())
                .define('B', BlockList.STEEL_INGOT.get())
                .define('C', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                .save(consumer);


    }

}
