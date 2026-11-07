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
        registration.addRecipeCategories(new PlanetaryDrillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RocketAssemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new TransmutationConstructorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new Printer3DRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RitualSiteRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new SkyblockBuilderRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ContainedSpaceStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FuelDistilleryRecipe> fuelDistilleryRecipes = recipeManager.getAllRecipesFor(FuelDistilleryRecipe.Type.INSTANCE);
        registration.addRecipes(FuelDistilleryRecipeCategory.FUEL_DISTILLERY_RECIPE_TYPE, fuelDistilleryRecipes);

        List<PlanetaryDrillRecipe> planetaryDrillRecipes = recipeManager.getAllRecipesFor(PlanetaryDrillRecipe.Type.INSTANCE);
        registration.addRecipes(PlanetaryDrillRecipeCategory.PLANETARY_DRILL_RECIPE_TYPE, planetaryDrillRecipes);

        List<RocketAssemblerRecipe> rocketAssemblerRecipes = recipeManager.getAllRecipesFor(RocketAssemblerRecipe.Type.INSTANCE);
        registration.addRecipes(RocketAssemblerRecipeCategory.ROCKET_ASSEMBLER_RECIPE_TYPE, rocketAssemblerRecipes);

        List<TransmutationConstructorRecipe> transmutationConstructorRecipes = recipeManager.getAllRecipesFor(TransmutationConstructorRecipe.Type.INSTANCE);
        registration.addRecipes(TransmutationConstructorRecipeCategory.TRANSMUTATION_CONSTRUCTOR_RECIPE_TYPE, transmutationConstructorRecipes);

        List<RitualSiteRecipe> ritualSiteRecipes = recipeManager.getAllRecipesFor(RitualSiteRecipe.Type.INSTANCE);
        registration.addRecipes(RitualSiteRecipeCategory.RITUAL_SITE_RECIPE_TYPE, ritualSiteRecipes);

        List<Printer3DRecipe> printer3DRecipes = recipeManager.getAllRecipesFor(Printer3DRecipe.Type.INSTANCE);
        registration.addRecipes(Printer3DRecipeCategory.PRINTER_3D_RECIPE_TYPE, printer3DRecipes);

        List<SkyblockBuilderRecipe> skyblockBuilderRecipes = recipeManager.getAllRecipesFor(SkyblockBuilderRecipe.Type.INSTANCE);
        registration.addRecipes(SkyblockBuilderRecipeCategory.SKYBLOCK_BUILDER_RECIPE_TYPE, skyblockBuilderRecipes);

        List<ContainedSpaceStationRecipe> containedSpaceStationRecipes = recipeManager.getAllRecipesFor(ContainedSpaceStationRecipe.Type.INSTANCE);
        registration.addRecipes(ContainedSpaceStationRecipeCategory.CONTAINED_SPACE_STATION_RECIPE_TYPE, containedSpaceStationRecipes);

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FUEL_DISTILLERY_CONTROLLER.get()), FuelDistilleryRecipeCategory.FUEL_DISTILLERY_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.STEEL_DRILL_CONTROLLER.get()), PlanetaryDrillRecipeCategory.PLANETARY_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DESH_DRILL_CONTROLLER.get()), PlanetaryDrillRecipeCategory.PLANETARY_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.OSTRUM_DRILL_CONTROLLER.get()), PlanetaryDrillRecipeCategory.PLANETARY_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CALORITE_DRILL_CONTROLLER.get()), PlanetaryDrillRecipeCategory.PLANETARY_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.GLACIO_DRILL_CONTROLLER.get()), PlanetaryDrillRecipeCategory.PLANETARY_DRILL_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get()), RocketAssemblerRecipeCategory.ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get()), RocketAssemblerRecipeCategory.ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get()), RocketAssemblerRecipeCategory.ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get()), RocketAssemblerRecipeCategory.ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get()), RocketAssemblerRecipeCategory.ROCKET_ASSEMBLER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.TRANSMUTATION_CONSTRUCTOR_CONTROLLER.get()), TransmutationConstructorRecipeCategory.TRANSMUTATION_CONSTRUCTOR_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SKYBLOCK_BUILDER_CONTROLLER.get()), SkyblockBuilderRecipeCategory.SKYBLOCK_BUILDER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.RITUAL_SITE_CONTROLLER.get()), RitualSiteRecipeCategory.RITUAL_SITE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PRINTER_3D_CONTROLLER.get()), Printer3DRecipeCategory.PRINTER_3D_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CONTAINED_SPACE_STATION_CONTROLLER.get()), ContainedSpaceStationRecipeCategory.CONTAINED_SPACE_STATION_RECIPE_TYPE);
    }
}