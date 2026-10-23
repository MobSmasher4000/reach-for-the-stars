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

    public static final RegistryObject<RecipeSerializer<SteelDrillRecipe>> STEEL_DRILL_SERIALIZER =
            SERIALIZERS.register("steel_drill", () -> SteelDrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<DeshDrillRecipe>> DESH_DRILL_SERIALIZER =
            SERIALIZERS.register("desh_drill", () -> DeshDrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<OstrumDrillRecipe>> OSTRUM_DRILL_SERIALIZER =
            SERIALIZERS.register("ostrum_drill", () -> OstrumDrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CaloriteDrillRecipe>> CALORITE_DRILL_SERIALIZER =
            SERIALIZERS.register("calorite_drill", () -> CaloriteDrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<GlacioDrillRecipe>> GLACIO_DRILL_SERIALIZER =
            SERIALIZERS.register("glacio_drill", () -> GlacioDrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T1RocketAssemblerRecipe>> T1_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t1_rocket_assembler", () -> T1RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T2RocketAssemblerRecipe>> T2_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t2_rocket_assembler", () -> T2RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T3RocketAssemblerRecipe>> T3_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t3_rocket_assembler", () -> T3RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T4RocketAssemblerRecipe>> T4_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t4_rocket_assembler", () -> T4RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T5RocketAssemblerRecipe>> T5_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t5_rocket_assembler", () -> T5RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<Printer3DRecipe>> PRINTER_3D_SERIALIZER =
            SERIALIZERS.register("printer_3d", () -> Printer3DRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<TransmutationConstructorRecipe>> TRANSMUTATION_CONSTRUCTOR_SERIALIZER =
            SERIALIZERS.register("transmutation_constructor", () -> TransmutationConstructorRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<SkyblockBuilderRecipe>> SKYBLOCK_BUILDER_SERIALIZER =
            SERIALIZERS.register("skyblock_builder", () -> SkyblockBuilderRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<RitualSiteRecipe>> RITUAL_SITE_SERIALIZER =
            SERIALIZERS.register("ritual_site", () -> RitualSiteRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
