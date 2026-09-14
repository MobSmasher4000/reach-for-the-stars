package org.mob.reach_for_the_stars;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.item.ModItems;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reach_for_the_stars.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RFTS_TAB = CREATIVE_MODE_TABS.register("rfts_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RFTS.get()))
                    .title(Component.translatable("creative_tab.rftsc_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.STELLAR_ODYSSEY_MUSIC_DISC.get());
                        pOutput.accept(ModBlocks.ITEM_INPUT_HATCH.get());
                        pOutput.accept(ModBlocks.ITEM_OUTPUT_HATCH.get());
                        pOutput.accept(ModBlocks.FLUID_INPUT_HATCH.get());
                        pOutput.accept(ModBlocks.FLUID_OUTPUT_HATCH.get());
                        pOutput.accept(ModBlocks.ENERGY_INPUT_HATCH.get());
                        pOutput.accept(ModBlocks.FUEL_DISTILLERY_CONTROLLER.get());
                        pOutput.accept(ModBlocks.STEEL_DRILL_CONTROLLER.get());
                        pOutput.accept(ModBlocks.DESH_DRILL_CONTROLLER.get());
                        pOutput.accept(ModBlocks.OSTRUM_DRILL_CONTROLLER.get());
                        pOutput.accept(ModBlocks.CALORITE_DRILL_CONTROLLER.get());
                        pOutput.accept(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get());
                        pOutput.accept(ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get());
                        pOutput.accept(ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get());
                        pOutput.accept(ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get());
                        pOutput.accept(ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get());


                        pOutput.accept(ModItems.RFTS.get());
                        pOutput.accept(ModItems.IRON_FLUX_COIL.get());
                        pOutput.accept(ModItems.MARS_CLAY.get());
                        pOutput.accept(ModItems.EXCLAMATION_MARK.get());

                        pOutput.accept(ModItems.EARTH_INGOT.get());
                        pOutput.accept(ModItems.MOON_INGOT.get());
                        pOutput.accept(ModItems.MARS_INGOT.get());
                        pOutput.accept(ModItems.MERCURY_INGOT.get());
                        pOutput.accept(ModItems.GLACIO_INGOT.get());
                        pOutput.accept(ModItems.VENUS_INGOT.get());

                        pOutput.accept(ModItems.BLANK_MOLD.get());
                        pOutput.accept(ModItems.ROCKET_FIN_MOLD.get());
                        pOutput.accept(ModItems.ROCKET_NOSE_MOLD.get());

                        pOutput.accept(ModItems.EARTH_ESSENCE.get());
                        pOutput.accept(ModItems.AIR_ESSENCE.get());
                        pOutput.accept(ModItems.WATER_ESSENCE.get());
                        pOutput.accept(ModItems.FIRE_ESSENCE.get());

                        pOutput.accept(ModItems.EARTH_CORE.get());
                        pOutput.accept(ModItems.GLACIO_CORE.get());
                        pOutput.accept(ModItems.MARS_CORE.get());
                        pOutput.accept(ModItems.MOON_CORE.get());
                        pOutput.accept(ModItems.MERCURY_CORE.get());
                        pOutput.accept(ModItems.VENUS_CORE.get());

                        pOutput.accept(ModItems.EARTH_SHARD.get());
                        pOutput.accept(ModItems.GLACIO_SHARD.get());
                        pOutput.accept(ModItems.MARS_SHARD.get());
                        pOutput.accept(ModItems.MOON_SHARD.get());
                        pOutput.accept(ModItems.MERCURY_SHARD.get());
                        pOutput.accept(ModItems.VENUS_SHARD.get());

                        pOutput.accept(ModItems.STEEL_DRILL_FLUID_BUCKET.get());
                        pOutput.accept(ModItems.DESH_DRILL_FLUID_BUCKET.get());
                        pOutput.accept(ModItems.OSTRUM_DRILL_FLUID_BUCKET.get());
                        pOutput.accept(ModItems.CALORITE_DRILL_FLUID_BUCKET.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
