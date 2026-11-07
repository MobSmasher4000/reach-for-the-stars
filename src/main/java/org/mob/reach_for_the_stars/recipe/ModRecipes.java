package org.mob.reach_for_the_stars.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reach_for_the_stars.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FuelDistilleryRecipe>> FUEL_DISTILLERY_SERIALIZER =
            SERIALIZERS.register("fuel_distillery", () -> FuelDistilleryRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<PlanetaryDrillRecipe>> PLANETARY_DRILL_SERIALIZER =
            SERIALIZERS.register("planetary_drill", () -> PlanetaryDrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<RocketAssemblerRecipe>> ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("rocket_assembler", () -> RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<Printer3DRecipe>> PRINTER_3D_SERIALIZER =
            SERIALIZERS.register("printer_3d", () -> Printer3DRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<TransmutationConstructorRecipe>> TRANSMUTATION_CONSTRUCTOR_SERIALIZER =
            SERIALIZERS.register("transmutation_constructor", () -> TransmutationConstructorRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<SkyblockBuilderRecipe>> SKYBLOCK_BUILDER_SERIALIZER =
            SERIALIZERS.register("skyblock_builder", () -> SkyblockBuilderRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<RitualSiteRecipe>> RITUAL_SITE_SERIALIZER =
            SERIALIZERS.register("ritual_site", () -> RitualSiteRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ContainedSpaceStationRecipe>> CONTAINED_SPACE_STATION_SERIALIZER =
            SERIALIZERS.register("contained_space_station", () -> ContainedSpaceStationRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
