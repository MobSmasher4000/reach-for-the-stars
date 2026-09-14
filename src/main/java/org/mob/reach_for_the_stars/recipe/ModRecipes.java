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

    public static final RegistryObject<RecipeSerializer<T1RocketAssemblerRecipe>> T1_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t1_rocket_assembler", () -> T1RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T2RocketAssemblerRecipe>> T2_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t2_rocket_assembler", () -> T2RocketAssemblerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<T3RocketAssemblerRecipe>> T3_ROCKET_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("t3_rocket_assembler", () -> T3RocketAssemblerRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
