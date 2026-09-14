package org.mob.reach_for_the_stars.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.fluid.ModFluids;
import org.mob.reach_for_the_stars.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Reach_for_the_stars.MOD_ID);

    // music disc
    public static final RegistryObject<Item> STELLAR_ODYSSEY_MUSIC_DISC = ITEMS.register("stellar_odyssey_music_disc",
            () -> new RecordItem(6, ModSounds.STELLAR_ODYSSEY, new Item.Properties().stacksTo(1), 4480));

    // Essences
    public static final RegistryObject<Item> EARTH_ESSENCE = ITEMS.register("earth_essence",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AIR_ESSENCE = ITEMS.register("air_essence",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATER_ESSENCE = ITEMS.register("water_essence",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FIRE_ESSENCE = ITEMS.register("fire_essence",
            () -> new Item(new Item.Properties()));

    // Cores
    public static final RegistryObject<Item> EARTH_CORE = ITEMS.register("earth_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLACIO_CORE = ITEMS.register("glacio_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MARS_CORE = ITEMS.register("mars_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MERCURY_CORE = ITEMS.register("mercury_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOON_CORE = ITEMS.register("moon_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VENUS_CORE = ITEMS.register("venus_core",
            () -> new Item(new Item.Properties()));

    // Shards
    public static final RegistryObject<Item> EARTH_SHARD = ITEMS.register("earth_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLACIO_SHARD = ITEMS.register("glacio_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MARS_SHARD = ITEMS.register("mars_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MERCURY_SHARD = ITEMS.register("mercury_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOON_SHARD = ITEMS.register("moon_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VENUS_SHARD = ITEMS.register("venus_shard",
            () -> new Item(new Item.Properties()));

    // planet ingots
    public static final RegistryObject<Item> EARTH_INGOT = ITEMS.register("earth_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOON_INGOT = ITEMS.register("moon_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLACIO_INGOT = ITEMS.register("glacio_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MARS_INGOT = ITEMS.register("mars_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MERCURY_INGOT = ITEMS.register("mercury_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VENUS_INGOT = ITEMS.register("venus_ingot",
            () -> new Item(new Item.Properties()));

    // Mold
    public static final RegistryObject<Item> BLANK_MOLD = ITEMS.register("blank_mold",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROCKET_FIN_MOLD = ITEMS.register("rocket_fin_mold",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROCKET_NOSE_MOLD = ITEMS.register("rocket_nose_mold",
            () -> new Item(new Item.Properties()));

    // Special
    public static final RegistryObject<Item> RFTS = ITEMS.register("rfts",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MARS_CLAY = ITEMS.register("mars_clay",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IRON_FLUX_COIL = ITEMS.register("iron_flux_coil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EXCLAMATION_MARK = ITEMS.register("exclamation_mark",
            () -> new Item(new Item.Properties()));


    // Drill Fluids
    public static final RegistryObject<Item> STEEL_DRILL_FLUID_BUCKET = ITEMS.register("steel_drill_fluid_bucket",
            () -> new BucketItem(ModFluids.SOURCE_STEEL_DRILL_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> DESH_DRILL_FLUID_BUCKET = ITEMS.register("desh_drill_fluid_bucket",
            () -> new BucketItem(ModFluids.SOURCE_DESH_DRILL_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> OSTRUM_DRILL_FLUID_BUCKET = ITEMS.register("ostrum_drill_fluid_bucket",
            () -> new BucketItem(ModFluids.SOURCE_OSTRUM_DRILL_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> CALORITE_DRILL_FLUID_BUCKET = ITEMS.register("calorite_drill_fluid_bucket",
            () -> new BucketItem(ModFluids.SOURCE_CALORITE_DRILL_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
