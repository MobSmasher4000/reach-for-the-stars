package org.mob.reach_for_the_stars.block.custom.multiblock;

import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.util.MultiblockConfigLoader;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

public class MultiblockPatterns {

    public static MultiblockPattern FUEL_DISTILLERY;
    public static MultiblockPattern STEEL_DRILL;
    public static MultiblockPattern DESH_DRILL;
    public static MultiblockPattern OSTRUM_DRILL;
    public static MultiblockPattern CALORITE_DRILL;
    public static MultiblockPattern GLACIO_DRILL;
    public static MultiblockPattern T1_ROCKET_ASSEMBLER;
    public static MultiblockPattern T2_ROCKET_ASSEMBLER;
    public static MultiblockPattern T3_ROCKET_ASSEMBLER;
    public static MultiblockPattern T4_ROCKET_ASSEMBLER;
    public static MultiblockPattern T5_ROCKET_ASSEMBLER;
    public static MultiblockPattern PRINTER_3D;
    public static MultiblockPattern RITUAL_SITE;
    public static MultiblockPattern TRANSMUTATION_CONSTRUCTOR;
    public static MultiblockPattern SKYBLOCK_BUILDER;
    public static MultiblockPattern CONTAINED_SPACE_STATION;
    public static MultiblockPattern STEEL_DRILL1;

    public static void init() {
        FUEL_DISTILLERY = MultiblockConfigLoader.loadFromConfig("fuel_distillery.json");
        STEEL_DRILL = MultiblockConfigLoader.loadFromConfig("steel_drill.json");
        DESH_DRILL = MultiblockConfigLoader.loadFromConfig("desh_drill.json");
        OSTRUM_DRILL = MultiblockConfigLoader.loadFromConfig("ostrum_drill.json");
        CALORITE_DRILL = MultiblockConfigLoader.loadFromConfig("calorite_drill.json");
        GLACIO_DRILL = MultiblockConfigLoader.loadFromConfig("glacio_drill.json");
        T1_ROCKET_ASSEMBLER = MultiblockConfigLoader.loadFromConfig("t1_rocket_assembler.json");
        T2_ROCKET_ASSEMBLER = MultiblockConfigLoader.loadFromConfig("t2_rocket_assembler.json");
        T3_ROCKET_ASSEMBLER = MultiblockConfigLoader.loadFromConfig("t3_rocket_assembler.json");
        T4_ROCKET_ASSEMBLER = MultiblockConfigLoader.loadFromConfig("t4_rocket_assembler.json");
        T5_ROCKET_ASSEMBLER = MultiblockConfigLoader.loadFromConfig("t5_rocket_assembler.json");
        PRINTER_3D = MultiblockConfigLoader.loadFromConfig("3d_printer.json");
        RITUAL_SITE = MultiblockConfigLoader.loadFromConfig("ritual_site.json");
        TRANSMUTATION_CONSTRUCTOR = MultiblockConfigLoader.loadFromConfig("transmutation_constructor.json");
        SKYBLOCK_BUILDER = MultiblockConfigLoader.loadFromConfig("skyblock_builder.json");
        CONTAINED_SPACE_STATION = MultiblockConfigLoader.loadFromConfig("contained_space_station.json");

        STEEL_DRILL1 = MultiblockPattern.builder()
                .layer(" H ",
                        "I F",
                        " C ")
                .where('F', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
                .where('H', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
                .where('I', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
                .build();
    }
}