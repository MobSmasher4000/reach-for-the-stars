package org.mob.reach_for_the_stars.block.custom.multiblock;

import net.allthemods.alltheores.blocks.BlockList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

public class MultiblockPatterns {

    private static boolean isBlock(BlockState state, String modid, String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(modid, path);
        return state.getBlock().equals(ForgeRegistries.BLOCKS.getValue(id));
    }

    private static BlockState getBlockSafe(String modid, String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(modid, path);
        Block block = net.minecraftforge.registries.ForgeRegistries.BLOCKS.getValue(id);
        return block != null ? block.defaultBlockState() : Blocks.IRON_BLOCK.defaultBlockState();
    }

    public static final MultiblockPattern FUEL_DISTILLERY = MultiblockPattern.builder()
            // Top Layer (dy = 3)
            .layer("AAA",
                    "ABA",
                    "AAA")
            // Mid Layer 1 (dy = 2)
            .layer("DED",
                    "E E",
                    "DED")
            // Mid Layer 2 (dy = 1)
            .layer("DED",
                    "E E",
                    "DED")
            // Bottom Layer (dy = 0)
            .layer("AHA",
                    "FBG",
                    "ACA")

            .where('A', state -> isBlock(state, "immersiveengineering", "steel_scaffolding_standard"), getBlockSafe("immersiveengineering", "steel_scaffolding_standard"))
            .where('B', state -> isBlock(state, "immersiveengineering", "rs_engineering"), getBlockSafe("immersiveengineering", "rs_engineering"))
            .where('D', state -> isBlock(state, "immersiveengineering", "sheetmetal_steel"), getBlockSafe("immersiveengineering", "sheetmetal_steel"))
            .where('E', state -> isBlock(state, "tconstruct", "clear_glass"), getBlockSafe("tconstruct", "clear_glass"))

            .where('F', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where('G', state -> state.is(ModBlocks.FLUID_OUTPUT_HATCH.get()), ModBlocks.FLUID_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())
            .build();

    public static final MultiblockPattern STEEL_DRILL = MultiblockPattern.builder()
            // Top Layer (dy =
            .layer(
                    "ABBBA",
                    "BAAAB",
                    "BAAAB",
                    "BAAAB",
                    "ABBBA")
            // Mid Layer 1 (dy = 4)
            .layer(
                "BDDDB",
                    "DAAAD",
                    "DAAAD",
                    "DAAAD",
                    "BDDDB")
            // Mid Layer 2 (dy = 3)
            .layer(
                "BDDDB",
                    "DEEED",
                    "DEEED",
                    "DEEED",
                    "BDDDB")
            // Mid Layer 3 (dy = 2)
            .layer(
                "BDDDB",
                    "D E D",
                    "DEEED",
                    "D E D",
                    "BDDDB")
            // Mid Layer 4 (dy = 1)
            .layer(
                "BDDDB",
                "D   D",
                "D E D",
                "D   D",
                "BDDDB")
            // Bottom Layer (dy = 0)
            .layer(
                "AAHAA",
                "AGGGA",
                "IGGGF",
                "AGGGA",
                "AACAA")

            .where('A', state -> isBlock(state, "immersiveengineering", "sheetmetal_steel"), getBlockSafe("immersiveengineering", "sheetmetal_steel"))
            .where('B', state -> isBlock(state, "immersiveengineering", "steel_scaffolding_standard"), getBlockSafe("immersiveengineering", "steel_scaffolding_standard"))
            .where('D', state -> isBlock(state, "tconstruct", "clear_glass"), getBlockSafe("tconstruct", "clear_glass"))
            .where('E', state -> state.is(BlockList.STEEL_BLOCK.get()), BlockList.STEEL_BLOCK.get().defaultBlockState())
            .where('G', state -> isBlock(state, "ad_astra", "moon_sand"), getBlockSafe("ad_astra", "moon_sand"))

            .where('F', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('I', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern DESH_DRILL = MultiblockPattern.builder()
            // Top Layer (dy = 4)
            .layer(
                    "ABBBBBA",
                    "BAAAAAB",
                    "BAAAAAB",
                    "BAAAAAB",
                    "ABBBBBA")
            // Mid Layer 1 (dy = 3)
            .layer(
                    "BDDDDDB",
                    "D AAA D",
                    "D AAA D",
                    "D AAA D",
                    "BDDDDDB")
            // Mid Layer 2 (dy = 2)
            .layer(
                    "BDDDDDB",
                    "D  A  D",
                    "D AAA D",
                    "D  A  D",
                    "BDDDDDB")
            // Mid Layer 3 (dy = 1)
            .layer(
                    "BDDDDDB",
                    "D     D",
                    "D  A  D",
                    "D     D",
                    "BDDDDDB")
            // Bottom Layer (dy = 0)
            .layer(
                    "ABBEBBA",
                    "BFFFFFB",
                    "HFFFFFG",
                    "BFFFFFB",
                    "ABBCBBA")

            .where('A', state -> isBlock(state, "ad_astra", "desh_block"), getBlockSafe("ad_astra", "desh_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_desh_pillar"), getBlockSafe("ad_astra", "glowing_desh_pillar"))
            .where('D', state -> isBlock(state, "tconstruct", "clear_glass"), getBlockSafe("tconstruct", "clear_glass"))
            .where('F', state -> isBlock(state, "ad_astra", "mars_sand"), getBlockSafe("ad_astra", "mars_sand"))

            .where('E', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('G', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern OSTRUM_DRILL = MultiblockPattern.builder()
            // Top Layer (dy = 8)
            .layer(
                    "ABBBABBBA",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "AAAAAAAAA",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "ABBBABBBA")
            // Mid Layer 7 (dy = 7)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D  AAA  D",
                    "D AAAAA D",
                    "D AAAAA D",
                    "D AAAAA D",
                    "D  AAA  D",
                    "D       D",
                    "BDDDDDDDB")
            // Mid Layer 6 (dy = 6)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D  ABA  D",
                    "D  BAB  D",
                    "D  ABA  D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // MidLayer 5 (dy = 5)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D  ABA  D",
                    "D  BAB  D",
                    "D  ABA  D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Mid Layer 4 (dy = 4)
            .layer(
                    "ADDDDDDDA",
                    "D       D",
                    "D       D",
                    "D  ABA  D",
                    "D  BAB  D",
                    "D  ABA  D",
                    "D       D",
                    "D       D",
                    "ADDDDDDDA")
            // Mid Layer 3 (dy = 3)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D   A   D",
                    "D  AAA  D",
                    "D   A   D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Mid Layer 2 (dy = 2)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D   A   D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Mid Layer 1 (dy = 1)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Bottom Layer (dy = 0)
            .layer(
                    "ABBBIBBBA",
                    "BFFFFFFFB",
                    "BFFFFFFFB",
                    "BFFFFFFFB",
                    "GFFFFFFFH",
                    "BFFFFFFFB",
                    "BFFFFFFFB",
                    "BFFFFFFFB",
                    "ABBBCBBBA")

            .where('A', state -> isBlock(state, "ad_astra", "calorite_block"), getBlockSafe("ad_astra", "calorite_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_calorite_pillar"), getBlockSafe("ad_astra", "glowing_calorite_pillar"))
            .where('D', state -> isBlock(state, "ae2", "quartz_vibrant_glass"), getBlockSafe("ae2", "quartz_vibrant_glass"))
            .where('F', state -> isBlock(state, "ad_astra", "mercury_stone"), getBlockSafe("ad_astra", "mercury_stone"))

            .where('I', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('G', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern CALORITE_DRILL = MultiblockPattern.builder()
            // Top Layer (dy = 8)
            .layer(
                    "ABBBABBBA",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "AAAAAAAAA",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "BAAAAAAAB",
                    "ABBBABBBA")
            // Layer 7 (dy = 7)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D  AAA  D",
                    "D AAAAA D",
                    "D AAAAA D",
                    "D AAAAA D",
                    "D  AAA  D",
                    "D       D",
                    "BDDDDDDDB")
            // Layer 6 (dy = 6)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D  ABA  D",
                    "D  BAB  D",
                    "D  ABA  D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Layer 5 (dy = 5)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D  EBE  D",
                    "D  BAB  D",
                    "D  EBE  D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Layer 4 (dy = 4)
            .layer(
                    "ADDDDDDDA",
                    "D       D",
                    "D       D",
                    "D  EEE  D",
                    "D  EAE  D",
                    "D  EBE  D",
                    "D       D",
                    "D       D",
                    "ADDDDDDDA")
            // Layer 3 (dy = 3)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D   E   D",
                    "D  EAE  D",
                    "D   E   D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Layer 2 (dy = 2)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D   E   D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Layer 1 (dy = 1)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "BDDDDDDDB")
            // Bottom Layer (dy = 0)
            .layer(
                    "ABBBJBBBA",
                    "BGGGGGGGB",
                    "BGGGGGGGB",
                    "BGGGGGGGB",
                    "HGGGGGGGI",
                    "BGGGGGGGB",
                    "BGGGGGGGB",
                    "BGGGGGGGB",
                    "ABBBCBBBA")

            .where('A', state -> isBlock(state, "ad_astra", "calorite_block"), getBlockSafe("ad_astra", "calorite_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_calorite_pillar"), getBlockSafe("ad_astra", "glowing_calorite_pillar"))
            .where('D', state -> isBlock(state, "mekanism", "structural_glass"), getBlockSafe("mekanism", "structural_glass"))
            .where('E', state -> isBlock(state, "powah", "nitro_crystal_block"), getBlockSafe("powah", "nitro_crystal_block"))
            .where('G', state -> isBlock(state, "ad_astra", "venus_sand"), getBlockSafe("ad_astra", "venus_sand"))

            .where('J', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('I', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern T1_ROCKET_ASSEMBLER = MultiblockPattern.builder()
            // Top Layer (dy = 3)
            .layer(
                    "ABA",
                    "BDB",
                    "ABA")
            // Mid Layer 1 (dy = 2)
            .layer(
                    "BEB",
                    "E E",
                    "BEB")
            // Mid Layer 2 (dy = 1)
            .layer(
                    "BEB",
                    "E E",
                    "BEB")
            // Bottom Layer (dy = 0)
            .layer(
                    "AHA",
                    "FDG",
                    "ACA")

            .where('A', state -> isBlock(state, "immersiveengineering", "light_engineering"), getBlockSafe("immersiveengineering", "light_engineering"))
            .where('B', state -> isBlock(state, "immersiveengineering", "sheetmetal_steel"), getBlockSafe("immersiveengineering", "sheetmetal_steel"))
            .where('D', state -> isBlock(state, "immersiveengineering", "rs_engineering"), getBlockSafe("immersiveengineering", "rs_engineering"))
            .where('E', state -> isBlock(state, "immersiveengineering", "steel_scaffolding_standard"), getBlockSafe("immersiveengineering", "steel_scaffolding_standard"))

            .where('F', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('G', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern T2_ROCKET_ASSEMBLER = MultiblockPattern.builder()
            // Top Layer (dy = 4)
            .layer(
                    "ABBBA",
                    "BDEDB",
                    "BEDEB",
                    "BDEDB",
                    "ABBBA")
            // Layer 3 (dy = 3)
            .layer(
                    "FEDEF",
                    "E   E",
                    "D H D",
                    "E   E",
                    "FGGGF")
            // Layer 2 (dy = 2)
            .layer(
                    "FDEDF",
                    "D H D",
                    "EH HE",
                    "D   D",
                    "FGGGF")
            // Layer 1 (dy = 1)
            .layer(
                    "FEDEF",
                    "E   E",
                    "D H D",
                    "E   E",
                    "FGGGF")
            // Bottom Layer (dy = 0)
            .layer(
                    "ABJBA",
                    "BDEDB",
                    "IEDEK",
                    "BDEDB",
                    "ABCBA")

            .where('A', state -> isBlock(state, "ad_astra", "desh_block"), getBlockSafe("ad_astra", "desh_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_desh_pillar"), getBlockSafe("ad_astra", "glowing_desh_pillar"))
            .where('D', state -> isBlock(state, "pneumaticcraft", "pressure_chamber_wall"), getBlockSafe("pneumaticcraft", "pressure_chamber_wall"))
            .where('E', state -> isBlock(state, "ars_nouveau", "sourcestone"), getBlockSafe("ars_nouveau", "sourcestone"))
            .where('F', state -> isBlock(state, "ad_astra", "desh_pillar"), getBlockSafe("ad_astra", "desh_pillar"))
            .where('G', state -> isBlock(state, "tconstruct", "clear_glass"), getBlockSafe("tconstruct", "clear_glass"))
            .where('H', state -> isBlock(state, "ars_nouveau", "arcane_platform"), getBlockSafe("ars_nouveau", "arcane_platform"))

            .where('I', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('J', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('K', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern T3_ROCKET_ASSEMBLER = MultiblockPattern.builder()
            // Top Layer (dy = 6)
            .layer(
                    "ABBBBBA",
                    "BJJJJJB",
                    "BIIFIIB",
                    "BGFHFGB",
                    "BEEFEEB",
                    "BDDDDDB",
                    "ABBBBBA")
            // Layer 5 (dy = 5)
            .layer(
                    "BLFLFLB",
                    "L     L",
                    "F     F",
                    "L  M  L",
                    "F     F",
                    "L     L",
                    "BKKKKKB")
            // Layer 4 (dy = 4)
            .layer(
                    "BFLFLFB",
                    "F     F",
                    "L     L",
                    "F  N  F",
                    "L     L",
                    "F     F",
                    "BKKKKKB")
            // Layer 3 (dy = 3)
            .layer(
                    "BLFHFLB",
                    "L  T  L",
                    "F  S  F",
                    "HOP QRH",
                    "F     F",
                    "L     L",
                    "BKKKKKB")
            // Layer 2 (dy = 2)
            .layer(
                    "BFLFLFB",
                    "F     F",
                    "L     L",
                    "F  U  F",
                    "L     L",
                    "F     F",
                    "BKKKKKB")
            // Layer 1 (dy = 1)
            .layer(
                    "BLFLFLB",
                    "L     L",
                    "F     F",
                    "L  V  L",
                    "F     F",
                    "L     L",
                    "BKKKKKB")
            // Bottom Layer (dy = 0)
            .layer(
                    "ABBYBBA",
                    "BJJJJJB",
                    "BIIFIIB",
                    "WGFHFGX",
                    "BEEFEEB",
                    "BDDDDDB",
                    "ABBCBBA")

            .where('A', state -> isBlock(state, "ad_astra", "ostrum_block"), getBlockSafe("ad_astra", "ostrum_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_ostrum_pillar"), getBlockSafe("ad_astra", "glowing_ostrum_pillar"))
            .where('D', state -> isBlock(state, "powah", "niotic_crystal_block"), getBlockSafe("powah", "niotic_crystal_block"))
            .where('E', state -> isBlock(state, "powah", "spirited_crystal_block"), getBlockSafe("powah", "spirited_crystal_block"))
            .where('F', state -> isBlock(state, "ae2", "quartz_block"), getBlockSafe("ae2", "quartz_block"))
            .where('G', state -> isBlock(state, "powah", "nitro_crystal_block"), getBlockSafe("powah", "nitro_crystal_block"))
            .where('H', state -> isBlock(state, "ae2", "smooth_sky_stone_block"), getBlockSafe("ae2", "smooth_sky_stone_block"))
            .where('I', state -> isBlock(state, "powah", "blazing_crystal_block"), getBlockSafe("powah", "blazing_crystal_block"))
            .where('J', state -> isBlock(state, "powah", "energized_steel_block"), getBlockSafe("powah", "energized_steel_block"))
            .where('K', state -> isBlock(state, "tconstruct", "clear_glass"), getBlockSafe("tconstruct", "clear_glass"))
            .where('L', state -> isBlock(state, "ae2", "fluix_block"), getBlockSafe("ae2", "fluix_block"))
            .where('M', state -> isBlock(state, "powah", "energy_cable_nitro"), getBlockSafe("powah", "energy_cable_nitro"))
            .where('N', state -> isBlock(state, "powah", "energizing_rod_nitro"), getBlockSafe("powah", "energizing_rod_nitro"))
            .where('O', state -> isBlock(state, "powah", "energy_cable_blazing"), getBlockSafe("powah", "energy_cable_blazing"))
            .where('P', state -> isBlock(state, "powah", "energizing_rod_blazing"), getBlockSafe("powah", "energizing_rod_blazing"))
            .where('Q', state -> isBlock(state, "powah", "energizing_rod_spirited"), getBlockSafe("powah", "energizing_rod_spirited"))
            .where('R', state -> isBlock(state, "powah", "energy_cable_spirited"), getBlockSafe("powah", "energy_cable_spirited"))
            .where('S', state -> isBlock(state, "powah", "energizing_rod_niotic"), getBlockSafe("powah", "energizing_rod_niotic"))
            .where('T', state -> isBlock(state, "powah", "energy_cable_niotic"), getBlockSafe("powah", "energy_cable_niotic"))
            .where('U', state -> isBlock(state, "powah", "energizing_rod_hardened"), getBlockSafe("powah", "energizing_rod_hardened"))
            .where('V', state -> isBlock(state, "powah", "energy_cable_hardened"), getBlockSafe("powah", "energy_cable_hardened"))

            .where('W', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('X', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('Y', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern T4_ROCKET_ASSEMBLER = MultiblockPattern.builder()
            // Top Layer
            .layer(
                    "ABBBABBBA",
                    "BDDDDDDDB",
                    "BEFEFEFEB",
                    "BFEFEFEFB",
                    "ADDDDDDDA",
                    "BFEFEFEFB",
                    "BEFEFEFEB",
                    "BDDDDDDDB",
                    "ABBBABBBA")
            // Layer 7
            .layer(
                    "BDDDDDDDB",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "BDDDDDDDB")
            // Layer 6
            .layer(
                    "BFEFEFEFB",
                    "F       G",
                    "E       G",
                    "F       G",
                    "E       G",
                    "F       G",
                    "E       G",
                    "F       G",
                    "BFEFEFEFB")
            // Layer 5
            .layer(
                    "BEFEFEFEB",
                    "E       G",
                    "F       G",
                    "E  HHH  G",
                    "F  H H  G",
                    "E  HHH  G",
                    "F       G",
                    "E       G",
                    "BEFEFEFEB")
            // Layer 4
            .layer(
                    "AIIIIIIIA",
                    "I       G",
                    "I       G",
                    "I  H H  G",
                    "I   J   G",
                    "I  H H  G",
                    "I       G",
                    "I       G",
                    "AIIIIIIIA")
            // Layer 3
            .layer(
                    "BFEFEFEFB",
                    "F       G",
                    "E       G",
                    "F  HHH  G",
                    "E  H H  G",
                    "F  HHH  G",
                    "E       G",
                    "F       G",
                    "BFEFEFEFB")
            // Layer 2
            .layer(
                    "BEFEFEFEB",
                    "E       G",
                    "F       G",
                    "E       G",
                    "F       G",
                    "E       G",
                    "F       G",
                    "E       G",
                    "BEFEFEFEB")
            // Layer 1
            .layer(
                    "BDDDDDDDB",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "D       G",
                    "BDDDDDDDB")
            // Bottom Layer
            .layer(
                    "ABBBKBBBA",
                    "BDDDDDDDB",
                    "BEFEFEFEB",
                    "BFEFEFEFB",
                    "LDDDDDDDM",
                    "BFEFEFEFB",
                    "BEFEFEFEB",
                    "BDDDDDDDB",
                    "ABBBCBBBA")

            .where('A', state -> isBlock(state, "ad_astra", "calorite_block"), getBlockSafe("ad_astra", "calorite_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_calorite_pillar"), getBlockSafe("ad_astra", "glowing_calorite_pillar"))
            .where('D', state -> isBlock(state, "mekanism", "block_osmium"), getBlockSafe("mekanism", "block_osmium"))
            .where('E', state -> isBlock(state, "mekanism", "block_refined_glowstone"), getBlockSafe("mekanism", "block_refined_glowstone"))
            .where('F', state -> isBlock(state, "mekanism", "block_refined_obsidian"), getBlockSafe("mekanism", "block_refined_obsidian"))
            .where('G', state -> isBlock(state, "mekanism", "structural_glass"), getBlockSafe("mekanism", "structural_glass"))
            .where('H', state -> isBlock(state, "chisel", "futura/controller"), getBlockSafe("chisel", "futura/controller"))
            .where('I', state -> isBlock(state, "chisel", "futura/wavy"), getBlockSafe("chisel", "futura/wavy"))
            .where('J', state -> isBlock(state, "chisel", "futura/mysterious_cube"), getBlockSafe("chisel", "futura/mysterious_cube"))


            .where('L', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('M', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('K', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern T5_ROCKET_ASSEMBLER = MultiblockPattern.builder()
            // Layer 13 (Top, dy = 12)
            .layer(
                    "ABBBBBDBBBBBA",
                    "BEEEEEEEEEEEB",
                    "BFFFFFFFFFFFB",
                    "BEEEEEEEEEEEB",
                    "BGGGGGGGGGGGB",
                    "BHHHHHHHHHHHB",
                    "DFFFFFFFFFFFD",
                    "BHHHHHHHHHHHB",
                    "BGGGGGGGGGGGB",
                    "BEEEEEEEEEEEB",
                    "BFFFFFFFFFFFB",
                    "BEEEEEEEEEEEB",
                    "ABBBBBDBBBBBA")
            // Layers 12 to 8 (dy = 11 to 7)
            .layer(
                    "IJJJJJJJJJJJI",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IJJJJJJJJJJJI",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IJJJJJJJJJJJI",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IJJJJJJJJJJJI",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IJJJJJJJJJJJI",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "IJJJJJJJJJJJI")
            // Layer 7 (dy = 6)
            .layer(
                    "DJJJJJJJJJJJD",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "DJJJJJJJJJJJD")
            // Layers 6 to 2 (dy = 5 to 1)
            .layer(
                    "KJJJJJJJJJJJK",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KJJJJJJJJJJJK",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KJJJJJJJJJJJK",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KJJJJJJJJJJJK",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KJJJJJJJJJJJK",
                    "E           J",
                    "F           J",
                    "E           J",
                    "G           J",
                    "H           J",
                    "F           J",
                    "H           J",
                    "G           J",
                    "E           J",
                    "F           J",
                    "E           J",
                    "KJJJJJJJJJJJK")
            // Layer 1 (Bottom, dy = 0)
            .layer(
                    "ALLLLLMLLLLLA",
                    "LEEEEEEEEEEEL",
                    "LFFFFFFFFFFFL",
                    "LEEEEEEEEEEEL",
                    "LGGGGGGGGGGGL",
                    "LHHHHHHHHHHHL",
                    "NFFFFFFFFFFFO",
                    "LHHHHHHHHHHHL",
                    "LGGGGGGGGGGGL",
                    "LEEEEEEEEEEEL",
                    "LFFFFFFFFFFFL",
                    "LEEEEEEEEEEEL",
                    "ALLLLLCLLLLLA")

            .where('A', state -> isBlock(state, "ad_astra", "calorite_block"), getBlockSafe("ad_astra", "calorite_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_calorite_pillar"), getBlockSafe("ad_astra", "glowing_calorite_pillar"))
            .where('D', state -> isBlock(state, "ad_astra", "glowing_ostrum_pillar"), getBlockSafe("ad_astra", "glowing_ostrum_pillar"))
            .where('E', state -> isBlock(state, "botania", "terrasteel_block"), getBlockSafe("botania", "terrasteel_block"))
            .where('F', state -> isBlock(state, "mysticalagriculture", "supremium_block"), getBlockSafe("mysticalagriculture", "supremium_block"))
            .where('G', state -> isBlock(state, "mekanism", "sps_casing"), getBlockSafe("mekanism", "sps_casing"))
            .where('H', state -> isBlock(state, "powah", "nitro_crystal_block"), getBlockSafe("powah", "nitro_crystal_block"))
            .where('I', state -> isBlock(state, "ad_astra", "glowing_desh_pillar"), getBlockSafe("ad_astra", "glowing_desh_pillar"))
            .where('J', state -> isBlock(state, "ae2", "quartz_vibrant_glass"), getBlockSafe("ae2", "quartz_vibrant_glass"))
            .where('K', state -> isBlock(state, "ad_astra", "glowing_steel_pillar"), getBlockSafe("ad_astra", "glowing_steel_pillar"))
            .where('L', state -> isBlock(state, "ad_astra", "glowing_iron_pillar"), getBlockSafe("ad_astra", "glowing_iron_pillar"))

            .where('N', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('O', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('M', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern STEEL_DRILL1 = MultiblockPattern.builder()
            .layer(" H ",
                    "I F",
                    " C ")

            .where('F', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('I', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())
            .build();

    public static final MultiblockPattern DESH_DRILL1 = MultiblockPattern.builder()
            .layer(" E ",
                    "H G",
                    " C ")

            .where('E', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('G', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())
            .build();

    public static final MultiblockPattern T1_ROCKET_ASSEMBLER1 = MultiblockPattern.builder()
            .layer(" H ",
                    "F G",
                    " C ")

            .where('F', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('G', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('H', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())
            .build();


}