package org.mob.reach_for_the_stars.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reach_for_the_stars.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.STELLAR_ODYSSEY_MUSIC_DISC);
        simpleItem(ModItems.RFTS);
        simpleItem(ModItems.IRON_FLUX_COIL);
        simpleItem(ModItems.MARS_CLAY);
        simpleItem(ModItems.EXCLAMATION_MARK);

        simpleItem(ModItems.BLANK_MOLD);
        simpleItem(ModItems.ROCKET_FIN_MOLD);
        simpleItem(ModItems.ROCKET_NOSE_MOLD);

        simpleItem(ModItems.EARTH_ESSENCE);
        simpleItem(ModItems.AIR_ESSENCE);
        simpleItem(ModItems.WATER_ESSENCE);
        simpleItem(ModItems.FIRE_ESSENCE);

        simpleItem(ModItems.EARTH_CORE);
        simpleItem(ModItems.GLACIO_CORE);
        simpleItem(ModItems.MARS_CORE);
        simpleItem(ModItems.MOON_CORE);
        simpleItem(ModItems.MERCURY_CORE);
        simpleItem(ModItems.VENUS_CORE);
        simpleItem(ModItems.PLANET_CORE_CONGLOMERATE);

        simpleItem(ModItems.EARTH_SHARD);
        simpleItem(ModItems.GLACIO_SHARD);
        simpleItem(ModItems.MARS_SHARD);
        simpleItem(ModItems.MOON_SHARD);
        simpleItem(ModItems.MERCURY_SHARD);
        simpleItem(ModItems.VENUS_SHARD);

        simpleItem(ModItems.EARTH_INGOT);
        simpleItem(ModItems.MOON_INGOT);
        simpleItem(ModItems.MARS_INGOT);
        simpleItem(ModItems.MERCURY_CORE);
        simpleItem(ModItems.VENUS_INGOT);
        simpleItem(ModItems.GLACIO_INGOT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Reach_for_the_stars.MOD_ID, "item/" + item.getId().getPath()));
    }
}
