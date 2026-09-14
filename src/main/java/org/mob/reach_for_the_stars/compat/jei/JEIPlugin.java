package org.mob.reach_for_the_stars.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.recipe.*;

import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return resourceLocation("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FuelDistilleryRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new SteelDrillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DeshDrillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new OstrumDrillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CaloriteDrillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new T1RocketAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new T2RocketAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new T3RocketAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new T4RocketAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new T5RocketAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FuelDistilleryRecipe> fuelDistilleryRecipes = recipeManager.getAllRecipesFor(FuelDistilleryRecipe.Type.INSTANCE);
        registration.addRecipes(FuelDistilleryRecipeCategory.FUEL_DISTILLERY_RECIPE_TYPE, fuelDistilleryRecipes);

        List<SteelDrillRecipe> steelDrillRecipes = recipeManager.getAllRecipesFor(SteelDrillRecipe.Type.INSTANCE);
        registration.addRecipes(SteelDrillRecipeCategory.STEEL_DRILL_RECIPE_TYPE, steelDrillRecipes);

        List<DeshDrillRecipe> deshDrillRecipes = recipeManager.getAllRecipesFor(DeshDrillRecipe.Type.INSTANCE);
        registration.addRecipes(DeshDrillRecipeCategory.DESH_DRILL_RECIPE_TYPE, deshDrillRecipes);

        List<OstrumDrillRecipe> ostrumDrillRecipes = recipeManager.getAllRecipesFor(OstrumDrillRecipe.Type.INSTANCE);
        registration.addRecipes(OstrumDrillRecipeCategory.OSTRUM_DRILL_RECIPE_TYPE, ostrumDrillRecipes);

        List<CaloriteDrillRecipe> caloriteDrillRecipes = recipeManager.getAllRecipesFor(CaloriteDrillRecipe.Type.INSTANCE);
        registration.addRecipes(CaloriteDrillRecipeCategory.CALORITE_DRILL_RECIPE_TYPE, caloriteDrillRecipes);

        List<T1RocketAssemblerRecipe> t1RocketAssemblerRecipes = recipeManager.getAllRecipesFor(T1RocketAssemblerRecipe.Type.INSTANCE);
        registration.addRecipes(T1RocketAssemblerRecipeCategory.T1_ROCKET_ASSEMBLER_RECIPE_TYPE, t1RocketAssemblerRecipes);

        List<T2RocketAssemblerRecipe> t2RocketAssemblerRecipes = recipeManager.getAllRecipesFor(T2RocketAssemblerRecipe.Type.INSTANCE);
        registration.addRecipes(T2RocketAssemblerRecipeCategory.T2_ROCKET_ASSEMBLER_RECIPE_TYPE, t2RocketAssemblerRecipes);

        List<T3RocketAssemblerRecipe> t3RocketAssemblerRecipes = recipeManager.getAllRecipesFor(T3RocketAssemblerRecipe.Type.INSTANCE);
        registration.addRecipes(T3RocketAssemblerRecipeCategory.T3_ROCKET_ASSEMBLER_RECIPE_TYPE, t3RocketAssemblerRecipes);

        List<T4RocketAssemblerRecipe> t4RocketAssemblerRecipes = recipeManager.getAllRecipesFor(T4RocketAssemblerRecipe.Type.INSTANCE);
        registration.addRecipes(T4RocketAssemblerRecipeCategory.T4_ROCKET_ASSEMBLER_RECIPE_TYPE, t4RocketAssemblerRecipes);

        List<T5RocketAssemblerRecipe> t5RocketAssemblerRecipes = recipeManager.getAllRecipesFor(T5RocketAssemblerRecipe.Type.INSTANCE);
        registration.addRecipes(T5RocketAssemblerRecipeCategory.T5_ROCKET_ASSEMBLER_RECIPE_TYPE, t5RocketAssemblerRecipes);

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FUEL_DISTILLERY_CONTROLLER.get()), FuelDistilleryRecipeCategory.FUEL_DISTILLERY_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.STEEL_DRILL_CONTROLLER.get()), SteelDrillRecipeCategory.STEEL_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DESH_DRILL_CONTROLLER.get()), DeshDrillRecipeCategory.DESH_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.OSTRUM_DRILL_CONTROLLER.get()), OstrumDrillRecipeCategory.OSTRUM_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CALORITE_DRILL_CONTROLLER.get()), CaloriteDrillRecipeCategory.CALORITE_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get()), T1RocketAssemblerRecipeCategory.T1_ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get()), T2RocketAssemblerRecipeCategory.T2_ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get()), T3RocketAssemblerRecipeCategory.T3_ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get()), T4RocketAssemblerRecipeCategory.T4_ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get()), T5RocketAssemblerRecipeCategory.T5_ROCKET_ASSEMBLER_RECIPE_TYPE);
    }
}