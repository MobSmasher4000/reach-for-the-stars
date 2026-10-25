package org.mob.reach_for_the_stars.block.custom.multiblock;

import net.allthemods.alltheores.blocks.BlockList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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

    private static boolean isTag(BlockState state, String namespace, String path) {
        TagKey<Block> tagKey = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, path));
        return state.is(tagKey);
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
            .where('E', state -> isTag(state, "forge", "storage_blocks/steel"), getBlockSafe("thermal","steel_block"))
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

            .where('A', state -> isBlock(state, "ad_astra", "ostrum_block"), getBlockSafe("ad_astra", "ostrum_block"))
            .where('B', state -> isBlock(state, "ad_astra", "glowing_ostrum_pillar"), getBlockSafe("ad_astra", "glowing_ostrum_pillar"))
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

    public static final MultiblockPattern GLACIO_DRILL = MultiblockPattern.builder()
            // Layer 19 (Top, dy = 18)
            .layer(
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          BBB         ",
                    "        BBBBBBB       ",
                    "        BBBBBBB       ",
                    "       BBBBBBBBB      ",
                    "       BBBBDBBBB      ",
                    "       BBBBBBBBB      ",
                    "        BBBBBBB       ",
                    "        BBBBBBB       ",
                    "          BBB         ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ")
            // Layer 18 (dy = 17)
            .layer(
                    "                      ",
                    "                      ",
                    "                      ",
                    "        BBBBBBB       ",
                    "      BBBBEEEBBBB     ",
                    "     BBEEFFFFFEEBB    ",
                    "     BEFFFBBBFFFEB    ",
                    "    BBEFBB   BBFEBB   ",
                    "    BBFFB     BFFBB   ",
                    "    BEFB       BFEB   ",
                    "    BEFB   D   BFEB   ",
                    "    BEFB       BFEB   ",
                    "    BBFFB     BFFBB   ",
                    "    BBEFBB   BBFEBB   ",
                    "     BEFFFBBBFFFEB    ",
                    "     BBEEFFFFFEEBB    ",
                    "      BBBBEEEBBBB     ",
                    "        BBBBBBB       ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ")
            // Layer 17 (dy = 16)
            .layer(
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "     GHHHHHHHHHHHG    ",
                    "     H           H    ",
                    "     H           H    ",
                    "     H           H    ",
                    "     H    III    H    ",
                    "     H    III    H    ",
                    "     H    IDI    H    ",
                    "     H    III    H    ",
                    "     H    III    H    ",
                    "     H           H    ",
                    "     H           H    ",
                    "     H           H    ",
                    "     GHHHHHHHHHHHG    ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ")
            // Layer 16 (dy = 15)
            .layer(
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "     GHHHHHHHHHHHG    ",
                    "     H           H    ",
                    "     H           H    ",
                    "     H   JJKJJ   H    ",
                    "     H   JJKJL  H     ",
                    "     H  NMMMMMM  H    ",
                    "     H  NMMDMMO H     ",
                    "     H  NMMMMMM  H    ",
                    "     H  LLMMPP   H    ",
                    "     H   LMMPP   H    ",
                    "     H           H    ",
                    "     H           H    ",
                    "     GHHHH   HHHHG    ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ")
            // Layer 15 (dy = 14)
            .layer(
                    " Q   Q   Q   Q   Q   Q",
                    "                      ",
                    "                      ",
                    "                      ",
                    " Q   GHHHHHHHHHHHG   Q",
                    "     H           H    ",
                    "     H           H    ",
                    "     H   JRKRJ   H    ",
                    " Q   H   JJRJJL  H   Q",
                    "     H  N     S  H    ",
                    "     H  N  VUUST H    ",
                    "     H  N     S  H    ",
                    " Q   H  WLMMPP   H   Q",
                    "     H   WXXPP   H    ",
                    "     H           H    ",
                    "     H           H    ",
                    " Q   GHHHH   HHHHG   Q",
                    "                      ",
                    "                      ",
                    "                      ",
                    " Q   Q   Q   Q   Q   Q")
            // Layer 14 (dy = 13)
            .layer(
                    " YYZYYYZYYYZYYYZYYYZYY",
                    " Y                   Y",
                    " Z                   Z",
                    " Y                   Y",
                    " Y   GHHHHHHHHHHHG   Y",
                    " Y   H           H   Y",
                    " Z   H           H   Z",
                    " Y   H   JJKJJ   H   Y",
                    " Y   H   JJMJJL  H   Y",
                    " Y   H  NMMMMMM  H   Y",
                    " Z   H  NMMaMMM  H   Z",
                    " Y   H  NMMMMMM  H   Y",
                    " Y   H  LLMMPP   H   Y",
                    " Y   H   LMMPP   H   Y",
                    " Z   H           H   Z",
                    " Y   H           H   Y",
                    " Y   GHHHH   HHHHG   Y",
                    " Y                   Y",
                    " Z                   Z",
                    " Y                   Y",
                    " YYZYYYZYYYZYYYZYYYZYY")
            // Layer 13 (dy = 12)
            .layer(
                    " bcccccccccccccccccccb",
                    " cdddddddddddddddddddc",
                    " cdddddddddddddddddddc",
                    " cdddddddddddddddddddc",
                    " cdddbcccccccccccbdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeefeeeeecdddc",
                    " cdddceeeefgfeeeecdddc",
                    " cdddceeeeefeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddceeeeeeeeeeecdddc",
                    " cdddbcccccccccccbdddc",
                    " cdddddddddddddddddddc",
                    " cdddddddddddddddddddc",
                    " cddddddddhdddddddddhc",
                    " bcccccccccccccccccccb",
                    "                      ")
            // Layer 12 (dy = 11)
            .layer(
                    " ci  jjjj  k  jjjj  ic",
                    " ii                 ii",
                    "                      ",
                    "    l             l   ",
                    " j                   j",
                    " j                   j",
                    " j                   j",
                    " j                   j",
                    "                      ",
                    "           f          ",
                    " k        fgf        k",
                    "           f          ",
                    "                      ",
                    " j                   j",
                    " j                   j",
                    " j                   j",
                    " j                   j",
                    "    l             l   ",
                    "                      ",
                    " ii        n        mi",
                    " ci  jjjj  c  jjjj  ic",
                    "                      ")
            // Layer 11 (dy = 10)
            .layer(
                    " ci jj  jj k jj  jj ic",
                    " ii                 ii",
                    "                      ",
                    " j  l             l  j",
                    " j                   j",
                    "                      ",
                    "                      ",
                    " j                   j",
                    " j                   j",
                    "           f          ",
                    " k        fgf        k",
                    "           f          ",
                    " j                   j",
                    " j                   j",
                    "                      ",
                    "                      ",
                    " j                   j",
                    " j  l             l  j",
                    "                      ",
                    " ii        n        mi",
                    " ci jj  jj c jj  jj ic",
                    "                      ")
            // Layer 10 (dy = 9)
            .layer(
                    " cijj    jjkjj    jjic",
                    " ii                 ii",
                    " j                   j",
                    " j  l             l  j",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    " j                   j",
                    " j         f         j",
                    " k        fgf        k",
                    " j         f         j",
                    " j                   j",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    " j  l             l  j",
                    " j                   j",
                    " ii        n        mi",
                    " cijj    jjcjj    jjic",
                    "                      ")
            // Layer 9 (dy = 8)
            .layer(
                    " copqqqqqqqqqqqqqqqpoc",
                    " oiprrrrrrrrrrrrrrrpio",
                    " pppqqqqqqqqqqqqqqqppp",
                    " qrqq             qqrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq       f       qrq",
                    " qrq      fgf      qrq",
                    " qrq       f       qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrq               qrq",
                    " qrqq             qqrq",
                    " pppqqqqqqqqqqqqqqqppp",
                    " oiprrrrrrrrrrrrrrrpmo",
                    " copqqqqqqqqqqqqqqqpoc",
                    "                      ")
            // Layer 8 (dy = 7)
            .layer(
                    " cs                 sc",
                    " ss                 ss",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           f          ",
                    "          fgf         ",
                    "           f          ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           W          ",
                    " ss                 ms",
                    " cs        W        sc",
                    "                      ")
            // Layer 7 (dy = 6)
            .layer(
                    " ci                 ic",
                    " ii                 ii",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          ttt         ",
                    "         ttttt        ",
                    "        ttttttt       ",
                    "        tttgttt       ",
                    "        ttttttt       ",
                    "         ttttt        ",
                    "          ttt         ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           W          ",
                    " ii                 mi",
                    " ci        W        ic",
                    "                      ")
            // Layer 6 (dy = 5)
            .layer(
                    " cu                 uc",
                    " uu                 uu",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          ttt         ",
                    "         ttttt        ",
                    "         ttgtt        ",
                    "         ttttt        ",
                    "          ttt         ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           W          ",
                    " uu                 mu",
                    " cu        W        uc",
                    "                      ")
            // Layer 5 (dy = 4)
            .layer(
                    " ci                 ic",
                    " ii                 ii",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          ttt         ",
                    "          tgt         ",
                    "          ttt         ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           W          ",
                    " ii       Q v       mi",
                    " ci        W        ic",
                    "                      ")
            // Layer 4 (dy = 3)
            .layer(
                    " cw                 wc",
                    " ww                 ww",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           t          ",
                    "          tgt         ",
                    "           t          ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          xyx         ",
                    " ww       yzy       mw",
                    " cw       xyx       wc",
                    "                      ")
            // Layer 3 (dy = 2)
            .layer(
                    " ci                 ic",
                    " ii                 ii",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           t          ",
                    "          t0t         ",
                    "           t          ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          1y1         ",
                    " ii       y2y       mi",
                    " ci       1y1       ic",
                    "                      ")
            // Layer 2 (dy = 1 - with rotated Controller)
            .layer(
                    " 33                 33",
                    " 33                 33",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "           t          ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          1y1         ",
                    " 33       524       n3",
                    " 33       1C1       33",
                    "                      ")
            // Layer 1 (Bottom, dy = 0)
            .layer(
                    " 33                 33",
                    " 33                 33",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "                      ",
                    "          1y1         ",
                    " 33       y2y       n3",
                    " 33       1y1       33",
                    "                      ")

            .where('B', state -> isBlock(state, "ae2", "sky_stone_slab"), getBlockSafe("ae2", "sky_stone_slab"))
            .where('D', state -> isBlock(state, "create", "encased_chain_drive"), getBlockSafe("create", "encased_chain_drive"))
            .where('E', state -> isBlock(state, "advanced_ae", "quantum_alloy_stairs"), getBlockSafe("advanced_ae", "quantum_alloy_stairs"))
            .where('F', state -> isBlock(state, "advanced_ae", "quantum_alloy_block"), getBlockSafe("advanced_ae", "quantum_alloy_block"))
            .where('G', state -> isBlock(state, "ad_astra", "glowing_steel_pillar"), getBlockSafe("ad_astra", "glowing_steel_pillar"))
            .where('H', state -> isBlock(state, "mysticalagriculture", "witherproof_glass"), getBlockSafe("mysticalagriculture", "witherproof_glass"))
            .where('I', state -> isBlock(state, "immersiveengineering", "light_engineering"), getBlockSafe("immersiveengineering", "light_engineering"))
            .where('J', state -> isBlock(state, "mekanism", "dynamic_tank"), getBlockSafe("mekanism", "dynamic_tank"))
            .where('K', state -> isBlock(state, "chisel", "technical/engineering"), getBlockSafe("chisel", "technical/engineering"))
            .where('L', state -> isBlock(state, "immersiveengineering", "heavy_engineering"), getBlockSafe("immersiveengineering", "heavy_engineering"))
            .where('M', state -> isBlock(state, "ad_astra", "steel_plateblock"), getBlockSafe("ad_astra", "steel_plateblock"))
            .where('N', state -> isBlock(state, "ad_astra", "steel_plating"), getBlockSafe("ad_astra", "steel_plating"))
            .where('O', state -> isBlock(state, "pneumaticcraft", "wall_lamp_red"), getBlockSafe("pneumaticcraft", "wall_lamp_red"))
            .where('P', state -> isBlock(state, "immersiveengineering", "thermoelectric_generator"), getBlockSafe("immersiveengineering", "thermoelectric_generator"))
            .where('Q', state -> isBlock(state, "chipped", "blue_tube_soul_lantern"), getBlockSafe("chipped", "blue_tube_soul_lantern"))
            .where('R', state -> isBlock(state, "mekanism", "dynamic_valve"), getBlockSafe("mekanism", "dynamic_valve"))
            .where('S', state -> isBlock(state, "immersiveengineering", "rs_engineering"), getBlockSafe("immersiveengineering", "rs_engineering"))
            .where('T', state -> isBlock(state, "immersiveengineering", "breaker_switch"), getBlockSafe("immersiveengineering", "breaker_switch"))
            .where('U', state -> isBlock(state, "minecraft", "repeater"), getBlockSafe("minecraft", "repeater"))
            .where('V', state -> isBlock(state, "create", "clutch"), getBlockSafe("create", "clutch"))
            .where('W', state -> isBlock(state, "enderio", "dark_steel_bars"), getBlockSafe("enderio", "dark_steel_bars"))
            .where('X', state -> isBlock(state, "ad_astra", "steel_panel"), getBlockSafe("ad_astra", "steel_panel"))
            .where('Y', state -> isBlock(state, "botania", "dreamwood_wall"), getBlockSafe("botania", "dreamwood_wall"))
            .where('Z', state -> isBlock(state, "botania", "dreamwood_fence_gate"), getBlockSafe("botania", "dreamwood_fence_gate"))
            .where('a', state -> isBlock(state, "create", "mechanical_bearing"), getBlockSafe("create", "mechanical_bearing"))
            .where('b', state -> isBlock(state, "ad_astra", "chiseled_permafrost_bricks"), getBlockSafe("ad_astra", "chiseled_permafrost_bricks"))
            .where('c', state -> isBlock(state, "ad_astra", "permafrost_pillar"), getBlockSafe("ad_astra", "permafrost_pillar"))
            .where('d', state -> isBlock(state, "immersiveengineering", "slab_steel_scaffolding_wooden_top"), getBlockSafe("immersiveengineering", "slab_steel_scaffolding_wooden_top"))
            .where('e', state -> isBlock(state, "immersiveengineering", "steel_scaffolding_grate_top"), getBlockSafe("immersiveengineering", "steel_scaffolding_grate_top"))
            .where('f', state -> isBlock(state, "quark", "calcite_vertical_slab"), getBlockSafe("quark", "calcite_vertical_slab"))
            .where('g', state -> isBlock(state, "create", "radial_chassis"), getBlockSafe("create", "radial_chassis"))
            .where('h', state -> isBlock(state, "immersiveengineering", "metal_ladder_steel"), getBlockSafe("immersiveengineering", "metal_ladder_steel"))
            .where('i', state -> isBlock(state, "immersiveengineering", "alu_scaffolding_standard"), getBlockSafe("immersiveengineering", "alu_scaffolding_standard"))
            .where('j', state -> isBlock(state, "ad_astra", "permafrost_brick_stairs"), getBlockSafe("ad_astra", "permafrost_brick_stairs"))
            .where('k', state -> isBlock(state, "ad_astra", "glacio_stone_brick_wall"), getBlockSafe("ad_astra", "glacio_stone_brick_wall"))
            .where('l', state -> isBlock(state, "ad_astra", "permafrost_bricks"), getBlockSafe("ad_astra", "permafrost_bricks"))
            .where('m', state -> isBlock(state, "immersiveengineering", "metal_ladder_alu"), getBlockSafe("immersiveengineering", "metal_ladder_alu"))
            .where('n', state -> isBlock(state, "enderio", "dark_steel_ladder"), getBlockSafe("enderio", "dark_steel_ladder"))
            .where('o', state -> isBlock(state, "ad_astra", "polished_permafrost"), getBlockSafe("ad_astra", "polished_permafrost"))
            .where('p', state -> isBlock(state, "ad_astra", "polished_permafrost_stairs"), getBlockSafe("ad_astra", "polished_permafrost_stairs"))
            .where('q', state -> isBlock(state, "ad_astra", "polished_permafrost_slab"), getBlockSafe("ad_astra", "polished_permafrost_slab"))
            .where('r', state -> isBlock(state, "create", "framed_glass_trapdoor"), getBlockSafe("create", "framed_glass_trapdoor"))
            .where('s', state -> isBlock(state, "powah", "nitro_crystal_block"), getBlockSafe("powah", "nitro_crystal_block"))
            .where('t', state -> isBlock(state, "chisel", "factory/platex"), getBlockSafe("chisel", "factory/platex"))
            .where('u', state -> isBlock(state, "mythicbotany", "alfsteel_block"), getBlockSafe("mythicbotany", "alfsteel_block"))
            .where('v', state -> isBlock(state, "chipped", "yellow_tube_lantern"), getBlockSafe("chipped", "yellow_tube_lantern"))
            .where('w', state -> isBlock(state, "botania", "terrasteel_block"), getBlockSafe("botania", "terrasteel_block"))
            .where('x', state -> isBlock(state, "immersiveengineering", "slab_sheetmetal_aluminum"), getBlockSafe("immersiveengineering", "slab_sheetmetal_aluminum"))
            .where('y', state -> isBlock(state, "immersiveengineering", "sheetmetal_aluminum"), getBlockSafe("immersiveengineering", "sheetmetal_aluminum"))
            .where('0', state -> isBlock(state, "minecraft", "honey_block"), getBlockSafe("minecraft", "honey_block"))
            .where('1', state -> isBlock(state, "ad_astra", "permafrost_brick_wall"), getBlockSafe("ad_astra", "permafrost_brick_wall"))
            .where('2', state -> isBlock(state, "immersiveengineering", "chute_aluminum"), getBlockSafe("immersiveengineering", "chute_aluminum"))
            .where('3', state -> isBlock(state, "megacells", "sky_steel_block"), getBlockSafe("megacells", "sky_steel_block"))

            .where('z', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('4', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('5', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())

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
            // Top Layer (dy = 8)
            .layer(
                    "ABBBABBBA",
                    "BDEFDFEDB",
                    "BDFEDEDFB",
                    "BDEFDFEDB",
                    "ADFEDEFDA",
                    "BDEFDFEDB",
                    "BDFEDEDFB",
                    "BDEFDFEDB",
                    "ABBBABBBA")
            // Layer 7 (dy = 7)
            .layer(
                    "BDDDDDDDB",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "D       D",
                    "BGGGGGGGB")
            // Layer 6 (dy = 6)
            .layer(
                    "BFEFEFEFB",
                    "F       F",
                    "E       E",
                    "F       F",
                    "E       E",
                    "F       F",
                    "E       E",
                    "F       F",
                    "BGGGGGGGB")
            // Layer 5 (dy = 5)
            .layer(
                    "BEFEFEFEB",
                    "E       E",
                    "F       F",
                    "E  HHH  E",
                    "F  H H  F",
                    "E  HHH  E",
                    "F       F",
                    "E       E",
                    "BGGGGGGGB")
            // Layer 4 (dy = 4)
            .layer(
                    "AIIIIIIIA",
                    "I       I",
                    "I       I",
                    "I  H H  I",
                    "I   J   I",
                    "I  H H  I",
                    "I       I",
                    "I       I",
                    "AGGGGGGGA")
            // Layer 3 (dy = 3)
            .layer(
                    "BFEFEFEFB",
                    "F       F",
                    "E       E",
                    "F  HHH  F",
                    "E  H H  E",
                    "F  HHH  F",
                    "E       E",
                    "F       F",
                    "BGGGGGGGB")
            // Layer 2 (dy = 2)
            .layer(
                    "BEFEFEFEB",
                    "E       E",
                    "F       F",
                    "E       E",
                    "F       F",
                    "E       E",
                    "F       F",
                    "E       E",
                    "BGGGGGGGB")
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
                    "BGGGGGGGB")
            // Bottom Layer (dy = 0)
            .layer(
                    "ABBBKBBBA",
                    "BDEFDFEDB",
                    "BDFEDEDFB",
                    "BDEFDFEDB",
                    "LDFEDEFDM",
                    "BDEFDFEDB",
                    "BDFEDEDFB",
                    "BDEFDFEDB",
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

            // Your Custom Hatches & Controller
            .where('M', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('L', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('K', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())

            // Air spaces
            .where(' ', state -> state.isAir(), Blocks.AIR.defaultBlockState())

            .build();

    public static final MultiblockPattern T5_ROCKET_ASSEMBLER = MultiblockPattern.builder()
            // Layer 13 (Top, dy = 12)
            .layer(
                    "ABBBBBDBBBBBA",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "DEFEGHFHGEFED",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "BEFEGHFHGEFEB",
                    "ABBBBBDBBBBBA")
            // Layers 12 to 8 (dy = 11 to 7)
            .layer(
                    "IEFEGHFHGEFEI",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IEFEGHFHGEFEI",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IEFEGHFHGEFEI",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IEFEGHFHGEFEI",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "IJJJJJJJJJJJI")
            .layer(
                    "IEFEGHFHGEFEI",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "IJJJJJJJJJJJI")
            // Layer 7 (dy = 6)
            .layer(
                    "DEFEGHFHGEFED",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "DJJJJJJJJJJJD")
            // Layers 6 to 2 (dy = 5 to 1)
            .layer(
                    "KEFEGHFHGEFEK",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KEFEGHFHGEFEK",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KEFEGHFHGEFEK",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KEFEGHFHGEFEK",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "KJJJJJJJJJJJK")
            .layer(
                    "KEFEGHFHGEFEK",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "J           J",
                    "KJJJJJJJJJJJK")
            // Layer 1 (Bottom, dy = 0)
            .layer(
                    "ALLLLLMLLLLLA",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "NEFEGHFHGEFEO",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
                    "LEFEGHFHGEFEL",
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

    public static final MultiblockPattern PRINTER_3D = MultiblockPattern.builder()
            // Layer 19 (Top, dy = 18)
            .layer(
                    "   A A   A A       ",
                    "   ADA   ABA       ",
                    "   AEA   AEA       ",
                    "   EEE   EEE       ",
                    "   FFFFFFFFF       ",
                    "  FFF     FFF      ",
                    "  FF       FF      ",
                    "  F         F      ",
                    "  F         F      ",
                    "  F         F      ",
                    "  F         F      ",
                    "  F         F      ",
                    "  F         F      ",
                    "  F         F      ",
                    "  FF       FF      ",
                    "  FFF     FFF      ",
                    "   FFFFFFFFF       ",
                    "                   ",
                    "                   ")
            // Layer 18 (dy = 17)
            .layer(
                    " HFIKIFHFIJIFFFFFH ",
                    "HLL N LLL M LLLLLLH",
                    "FLOIKIOOOIJIOOOOOLF",
                    "FLOP POOOP POOOOOLF",
                    "FLOOQQQQQQQOOOOOOLF",
                    "FLOQQRRRRRQQOOOOOLF",
                    "FLQQRRRRRRRQQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQRRRRRRRRRQOOOOLF",
                    "FLQQRRRRRRRQQOOOOLF",
                    "FLOQQRRRRRQQOOOOOLF",
                    "FLOOQQQQQQQOOOOOOLF",
                    "HLLLLLLLLLLLLLLLLLH",
                    " HFFFFFFFFFFFFFFFH ")
            // Layer 17 (dy = 16)
            .layer(
                    " SO N OOO M OOOOOS ",
                    "SOOTTTT TTTTOFFFOOS",
                    "OOO N OOO M OOO  OO",
                    "OFUPDP   PBPU O  OO",
                    "OU        B  UO  OO",
                    "OF        B   O  OO",
                    "OF        BB  O  OO",
                    "OF         BB OV FO",
                    "OF            OV FO",
                    "OF            O  FO",
                    "OF            O  FO",
                    "OF            OV FO",
                    "OF            O  FO",
                    "OF            O  FO",
                    "OU           UOV FO",
                    "OFU         U O  FO",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOOFFFFFFFFFOOOOOOS",
                    " SOOOOOOOOOOOOOOOS ")
            // Layer 16 (dy = 15)
            .layer(
                    " SOIKIOOOIJIOOOXOS ",
                    "SOO N O O M O   OOS",
                    "OOOIKIOOOIJIOOO  OO",
                    "O TP P   P PT O  OO",
                    "OT           TO  OO",
                    "Q             OY OO",
                    "Q             OY OO",
                    "Q          aZ OY  O",
                    "Q          aZ OY  O",
                    "Q             OY  O",
                    "Q             O   O",
                    "Q             OY  O",
                    "Q             OY  O",
                    "Q             OY  O",
                    "OT           TOY  O",
                    "O T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OOOOOOS",
                    " SO         OOOOOS ")
            // Layer 15 (dy = 14)
            .layer(
                    " SOA AOOOA AOOOObS ",
                    "SOOADAO OABAO   OOS",
                    "OOOA AOOOA AOOO  OO",
                    "O T         T O  Y ",
                    "QT           TO  Y ",
                    "Q             OY Y ",
                    "R             OY OO",
                    "R          ZZ OY  O",
                    "R          dc OY  R",
                    "R             OY  O",
                    "R             O   R",
                    "R             OY  O",
                    "R             Oe  R",
                    "Q             Oe  O",
                    "QT           TOe  R",
                    "O T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OfffOOS",
                    " SO         O   OS ")
            // Layer 14 (dy = 13)
            .layer(
                    " SOOOOOOOOOOOOOXOS ",
                    "SOOFgFO OFgFO   OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "Q T         T O  Y ",
                    "QT           TO ih ",
                    "R             OYiY ",
                    "R             OYiOO",
                    "R          dd OYi O",
                    "R          dc OYi R",
                    "R             OYi O",
                    "R             Oii R",
                    "R             OY  O",
                    "R             Oe  R",
                    "R             Oe  O",
                    "QT           TOe  R",
                    "Q T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OfjfOOS",
                    " SO         O   OS ")
            // Layer 13 (dy = 12)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObl     l     OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "Q T         T O  Y ",
                    "RT          TTO  Y ",
                    "R           T OY Y ",
                    "R           T OY OO",
                    "R          cT OY  O",
                    "R          ZT Om  R",
                    "R           T OY  O",
                    "R           T Oi  R",
                    "R           T OY  O",
                    "R           T On  R",
                    "R           T Oe  O",
                    "RT          TTOe  R",
                    "Q T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OfffOOS",
                    " SO         O   OS ")
            // Layer 12 (dy = 11)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObl     l     OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "Q T         T O  Y ",
                    "RT           TO po ",
                    "R             OYpY ",
                    "R             OYpOO",
                    "R          q  Onp O",
                    "R          q  Onp R",
                    "R             OYp O",
                    "R             Oip R",
                    "R             OYp O",
                    "R             OYp R",
                    "R             OYp O",
                    "RT           TOYp R",
                    "Q T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OfjfOOS",
                    " SO         O   OS ")
            // Layer 11 (dy = 10)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObl     l     OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "Q T         T O  Y ",
                    "RT           TO  Y ",
                    "R             OY Y ",
                    "R             OY OO",
                    "R             Oe  O",
                    "R             Oe  R",
                    "R             OY  O",
                    "R             Oi  R",
                    "R             OYp O",
                    "R             OY  R",
                    "R             OY  O",
                    "RT           TOYp R",
                    "Q T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OfjfOOS",
                    " SO         O   OS ")
            // Layer 10 (dy = 9)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObl     l     OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "Q T         T O  Y ",
                    "RT           TO  Y ",
                    "R             OY Y ",
                    "R             OY OO",
                    "R             OY  O",
                    "R             OY  R",
                    "R             OY  O",
                    "R             Oi  R",
                    "R             OYp O",
                    "R             On  R",
                    "R             OY  O",
                    "RT           TOnp R",
                    "Q T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOO         OfffOOS",
                    " SO         O   OS ")
            // Layer 9 (dy = 8)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObl     llllllOOS",
                    "OOOOOOOOOOOOOOOllOO",
                    "Q T         T O lY ",
                    "RT           TO lr ",
                    "R             OY Y ",
                    "R             OY OO",
                    "R             OY  O",
                    "R             OY  R",
                    "R             OY  O",
                    "R             Oi  R",
                    "R             OYp O",
                    "R             OY  R",
                    "R             OY  O",
                    "RT           TOYp R",
                    "Q T         T OY  O",
                    "OOOWWWWWWWWWOOOOOOO",
                    "SOUWWWWWWWWWUfjfOOS",
                    " SO         O   OS ")
            // Layer 8 (dy = 7)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObl           OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "Q T         T O  Y ",
                    "RT           TO  Y ",
                    "R             O  Y ",
                    "R             O  OO",
                    "R             O   O",
                    "R             O   R",
                    "R             O   O",
                    "R             Oi  R",
                    "R             O p O",
                    "R             O   R",
                    "R             O   O",
                    "RT           TO p R",
                    "Q T         T O   O",
                    "OOO         OOOOOOO",
                    "SOOWWWWWWWWWOfjfOOS",
                    " SO         O   OS ")
            // Layer 7 (dy = 6)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOObllllllllllllOOS",
                    "OOOOOOOOOOOOOOOllOO",
                    "Q T         T O lY ",
                    "QT           TO ls ",
                    "R             O  Y ",
                    "R             O  OO",
                    "R             O   O",
                    "R             O   R",
                    "R             Oi  O",
                    "R             Oi  R",
                    "R             O p O",
                    "R             O   R",
                    "R             O   O",
                    "QT           TO p R",
                    "Q T         T O   O",
                    "OOO         OOOOOOO",
                    "SOOWWWWWWWWWOfjfOOS",
                    " SO         O   OS ")
            // Layer 6 (dy = 5)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOOb            OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "O T         T O  Y ",
                    "QT           TO  Y ",
                    "Q             O  Y ",
                    "R             O  OO",
                    "R             O   O",
                    "R             Ott R",
                    "R             Oit O",
                    "R             Ot  R",
                    "R             O p O",
                    "R             O   R",
                    "Q             O   O",
                    "QT           TO p R",
                    "O T         T O   O",
                    "OOO         OOOOOOO",
                    "SOOWWWWWWWWWOfffOOS",
                    " SO         O   OS ")
            // Layer 5 (dy = 4)
            .layer(
                    " SOOkOkOkOkOOOkOS ",
                    "SOOb            OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "O T         T O  OO",
                    "OT           TO  OO",
                    "Q             O  OO",
                    "Q             O  OO",
                    "Q             O   O",
                    "Q             Ott O",
                    "Q             Out O",
                    "Q             Ott O",
                    "Q             O p O",
                    "Q             O   O",
                    "Q             O   O",
                    "OT           TO p O",
                    "O T         T O   O",
                    "OOO         OOOOOOO",
                    "SOOWWWWWWWWWOfffOOS",
                    " SO         O   OS ")
            // Layer 4 (dy = 3)
            .layer(
                    " SOOkOkOkOkOOOOXOS ",
                    "SOOb            OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "O T         T O   k",
                    "OTvvvvvvvvvvvTO   k",
                    "O vvvvvvvvvvv O   k",
                    "O vvvvvvvvvvv O   k",
                    "O vvvvvvvvvvv O   k",
                    "O vvvvvvvvvvv Ott k",
                    "O vvvvvvvvvvv Ott k",
                    "O vvvvvvvvvvv Ott k",
                    "O vvvvvvvvvvv Owp k",
                    "O vvvvvvvvvvv Ow  k",
                    "O vvvvvvvvvvv O   k",
                    "OTvvvvvvvvvvvTOwp k",
                    "O T         T Ow  k",
                    "OOO         OOOOOOO",
                    "SOOWWWWWWWWWOfCfOOS",
                    " SO         O   OS ")
            // Layer 3 (dy = 2)
            .layer(
                    " SOOkOkOkOkOOOObS ",
                    "SOOb            OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "k T         T O   k",
                    "kT           TO   k",
                    "k             O   k",
                    "k             O   k",
                    "k             O   k",
                    "k             Ott k",
                    "k             Ott k",
                    "k             Ott k",
                    "k             Oyy k",
                    "k             Oyy k",
                    "k             O   k",
                    "kT           TOyy k",
                    "k T         T Oyy k",
                    "OOO         OOOOOOO",
                    "SOUWWWWWWWWWUfffOOS",
                    " SO         O   OS ")
            // Layer 2 (dy = 1)
            .layer(
                    " SOOkOkOkOkOOOOXOS ",
                    "SOOb            OOS",
                    "OOOOOOOOOOOOOOO  OO",
                    "k T         T O   k",
                    "kT           TO   k",
                    "k             O   k",
                    "k             O   k",
                    "k             O   k",
                    "k             Ott k",
                    "k             Ott k",
                    "k             Ott k",
                    "k             Oyy k",
                    "k             Oyy k",
                    "k             O   k",
                    "kT           TOyy k",
                    "k T         T Oyy k",
                    "OOO         OOOOOOO",
                    "SOO         O   OOS",
                    " SOOOOOOOOOOOOOOOS ")
            // Layer 1 (Bottom, dy = 0)
            .layer(
                    " SOOOOOOOOOOOOOOOS ",
                    "SOOzzzzzzzzzzzzzOOS",
                    "OOOOOOOOOOOOOOOzzOO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OzzzzzzzzzzzzzOzzzO",
                    "OOOzzzzzzzzzOOOOOOO",
                    "SzOzzzzzzzzzOzzzOOS",
                    " SOOOOOOOOOOOOOOOS ")

            .where('A', state -> isBlock(state, "minecraft", "smooth_quartz_slab"), getBlockSafe("minecraft", "smooth_quartz_slab"))
            .where('B', state -> isBlock(state, "chipped", "barred_mangrove_trapdoor"), getBlockSafe("chipped", "barred_mangrove_trapdoor"))
            .where('D', state -> isBlock(state, "chipped", "barred_acacia_trapdoor"), getBlockSafe("chipped", "barred_acacia_trapdoor"))
            .where('E', state -> isBlock(state, "create", "framed_glass_trapdoor"), getBlockSafe("create", "framed_glass_trapdoor"))
            .where('F', state -> isBlock(state, "immersiveengineering", "slab_sheetmetal_colored_cyan"), getBlockSafe("immersiveengineering", "slab_sheetmetal_colored_cyan"))
            .where('H', state -> isBlock(state, "ad_astra", "iron_plating_stairs"), getBlockSafe("ad_astra", "iron_plating_stairs"))
            .where('I', state -> isBlock(state, "minecraft", "smooth_quartz_stairs"), getBlockSafe("minecraft", "smooth_quartz_stairs"))
            .where('J', state -> isBlock(state, "quark", "red_shingles_stairs"), getBlockSafe("quark", "red_shingles_stairs"))
            .where('K', state -> isBlock(state, "quark", "orange_shingles_stairs"), getBlockSafe("quark", "orange_shingles_stairs"))
            .where('L', state -> isBlock(state, "ad_astra", "iron_pillar"), getBlockSafe("ad_astra", "iron_pillar"))
            .where('M', state -> isBlock(state, "quark", "red_shingles"), getBlockSafe("quark", "red_shingles"))
            .where('N', state -> isBlock(state, "quark", "orange_shingles"), getBlockSafe("quark", "orange_shingles"))
            .where('O', state -> isBlock(state, "immersiveengineering", "sheetmetal_colored_cyan"), getBlockSafe("immersiveengineering", "sheetmetal_colored_cyan"))
            .where('P', state -> isBlock(state, "quark", "smooth_quartz_vertical_slab"), getBlockSafe("quark", "smooth_quartz_vertical_slab"))
            .where('Q', state -> isBlock(state, "immersiveengineering", "sheetmetal_colored_black"), getBlockSafe("immersiveengineering", "sheetmetal_colored_black"))
            .where('R', state -> isBlock(state, "ae2", "quartz_vibrant_glass"), getBlockSafe("ae2", "quartz_vibrant_glass"))
            .where('S', state -> isBlock(state, "ad_astra", "glowing_iron_pillar"), getBlockSafe("ad_astra", "glowing_iron_pillar"))
            .where('T', state -> isBlock(state, "twilightforest", "twisted_stone_pillar"), getBlockSafe("twilightforest", "twisted_stone_pillar"))
            .where('U', state -> isBlock(state, "chisel", "technical/industrialrelic"), getBlockSafe("chisel", "technical/industrialrelic"))
            .where('W', state -> isBlock(state, "ae2", "quartz_glass"), getBlockSafe("ae2", "quartz_glass"))
            .where('X', state -> isBlock(state, "chisel", "technical/fanfast"), getBlockSafe("chisel", "technical/fanfast"))
            .where('Y', state -> isBlock(state, "chisel", "technical/exhaustplating"), getBlockSafe("chisel", "technical/exhaustplating"))
            .where('Z', state -> isBlock(state, "chipped", "wired_gray_concrete"), getBlockSafe("chipped", "wired_gray_concrete"))
            .where('a', state -> isBlock(state, "chisel", "ironpane/spikes"), getBlockSafe("chisel", "ironpane/spikes"))
            .where('b', state -> isBlock(state, "chipped", "smooth_sea_lantern"), getBlockSafe("chipped", "smooth_sea_lantern"))
            .where('c', state -> isBlock(state, "chipped", "gray_concrete_pillar"), getBlockSafe("chipped", "gray_concrete_pillar"))
            .where('d', state -> isBlock(state, "chisel", "concrete_gray/tiles_large"), getBlockSafe("chisel", "concrete_gray/tiles_large"))
            .where('e', state -> isBlock(state, "chisel", "factory/circuit"), getBlockSafe("chisel", "factory/circuit"))
            .where('f', state -> isBlock(state, "antiblocksrechiseled", "bright_black"), getBlockSafe("antiblocksrechiseled", "bright_black"))
            .where('g', state -> isBlock(state, "projectexpansion", "violet_emc_link"), getBlockSafe("projectexpansion", "violet_emc_link"))
            .where('i', state -> isBlock(state, "mekanism", "ultimate_mechanical_pipe"), getBlockSafe("mekanism", "ultimate_mechanical_pipe"))
            .where('j', state -> isBlock(state, "antiblocksrechiseled", "bright_cyan_border"), getBlockSafe("antiblocksrechiseled", "bright_cyan_border"))
            .where('k', state -> isBlock(state, "chisel", "technical/grate"), getBlockSafe("chisel", "technical/grate"))
            .where('l', state -> isBlock(state, "mekanism", "basic_logistical_transporter"), getBlockSafe("mekanism", "basic_logistical_transporter"))
            .where('m', state -> isBlock(state, "mekanism", "elite_bin"), getBlockSafe("mekanism", "elite_bin"))
            .where('n', state -> isBlock(state, "chisel", "factory/tilemosaic"), getBlockSafe("chisel", "factory/tilemosaic"))
            .where('p', state -> isBlock(state, "mekanism", "advanced_universal_cable"), getBlockSafe("mekanism", "advanced_universal_cable"))
            .where('q', state -> isBlock(state, "mob_grinding_utils", "spikes"), getBlockSafe("mob_grinding_utils", "spikes"))
            .where('t', state -> isBlock(state, "mekanism", "dynamic_tank"), getBlockSafe("mekanism", "dynamic_tank"))
            .where('u', state -> isBlock(state, "mekanism", "dynamic_valve"), getBlockSafe("mekanism", "dynamic_valve"))
            .where('v', state -> isBlock(state, "rechiseled", "obsidian_dark_slab_connecting"), getBlockSafe("rechiseled", "obsidian_dark_slab_connecting"))
            .where('w', state -> isBlock(state, "create_new_age", "copper_wire_block"), getBlockSafe("create_new_age", "copper_wire_block"))
            .where('y', state -> isBlock(state, "immersiveengineering", "thermoelectric_generator"), getBlockSafe("immersiveengineering", "thermoelectric_generator"))
            .where('z', state -> isBlock(state, "chisel", "factory/frameblue"), getBlockSafe("chisel", "factory/frameblue"))

            .where('h', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where('o', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('r', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())
            .where('s', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())

            .build();

    public static final MultiblockPattern RITUAL_SITE = MultiblockPattern.builder()
            // Layer 6 (Top, dy = 6)
            .layer(
                    "                 ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "    B       B    ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "    B       B    ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "                 ")
            // Layer 5 (dy = 5)
            .layer(
                    "                 ",
                    "                 ",
                    "      D   D      ",
                    "                 ",
                    "    E       E    ",
                    "                 ",
                    "  D           D  ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "  D           D  ",
                    "                 ",
                    "    E       E    ",
                    "                 ",
                    "      D   D      ",
                    "                 ",
                    "                 ")
            // Layer 4 (dy = 4)
            .layer(
                    "                 ",
                    "                 ",
                    "      E   E      ",
                    "                 ",
                    "    E       E    ",
                    "                 ",
                    "  E           E  ",
                    "                 ",
                    "                 ",
                    "                 ",
                    "  E           E  ",
                    "                 ",
                    "    E       E    ",
                    "                 ",
                    "      E   E      ",
                    "                 ",
                    "                 ")
            // Layer 3 (dy = 3)
            .layer(
                    "                 ",
                    "                 ",
                    "      E   E      ",
                    "                 ",
                    "    E       E    ",
                    "                 ",
                    "  E           E  ",
                    "                 ",
                    "        C        ",
                    "                 ",
                    "  E           E  ",
                    "                 ",
                    "    E       E    ",
                    "                 ",
                    "      E   E      ",
                    "                 ",
                    "                 ")
            // Layer 2 (dy = 2)
            .layer(
                    "                 ",
                    "                 ",
                    "      EGGGE      ",
                    "     GGHHHGG     ",
                    "    EHHIHIHHE    ",
                    "   GHIHHHHHIHG   ",
                    "  EGHHHJJJHHHGE  ",
                    "  GHIHJKKKJHIHG  ",
                    "  GHHHJMFLJHHHG  ",
                    "  GHIHJKKKJHIHG  ",
                    "  EGHHHJJJHHHGE  ",
                    "   GHIHHHHHIHG   ",
                    "    EHHIHIHHE    ",
                    "     GGHHHGG     ",
                    "      EOOOE      ",
                    "                 ",
                    "                 ")
            // Layer 1 (dy = 1)
            .layer(
                    " PPP         PPP ",
                    "PSSSP R   R PQQQP",
                    "PSSSPTEUVUETPQQQP",
                    "PSSSPUWX XWUPQQQP",
                    " PPPEX     XEPPP ",
                    "  YUX       XUY  ",
                    "  EW         WE  ",
                    " ZUX         XUZ ",
                    " aUX         XUa ",
                    " ZUX         XUZ ",
                    "  EW         WE  ",
                    "  YUX       XUY  ",
                    " PPPEX     XEPPP ",
                    "PcccPUWXXXWUPbbbP",
                    "PcccPdEUUUEdPbbbP",
                    "PcccP eOOOe PbbbP",
                    " PPP         PPP ")
            // Layer 0 (Bottom, dy = 0)
            .layer(
                    " fffggg   gggfff ",
                    "fhhhfiig giifhhhf",
                    "fhhhfiEUVUEifhhhf",
                    "fhhhfUXX XXUfhhhf",
                    "gfffEX     XEfffg",
                    "giiUX       XUiig",
                    "giEX         XEig",
                    "giUX         XUig",
                    "giUX         XUig",
                    "giUX         XUig",
                    "giEX         XEig",
                    "giiUX       XUiig",
                    "gfffEX     XEfffg",
                    "fhhhfUXXXXXUfhhhf",
                    "fhhhfiEUUUEifhhhf",
                    "fhhhfiiGGGiifhhhf",
                    " fffgggOOOgggfff ")

            .where('B', state -> isBlock(state, "ars_nouveau", "scryers_oculus"), getBlockSafe("ars_nouveau", "scryers_oculus"))
            .where('D', state -> isBlock(state, "ars_nouveau", "magelight_torch"), getBlockSafe("ars_nouveau", "magelight_torch"))
            .where('E', state -> isBlock(state, "chisel", "fantasy2/pillar"), getBlockSafe("chisel", "fantasy2/pillar"))
            .where('F', state -> isBlock(state, "mysticalagriculture", "tinkering_table"), getBlockSafe("mysticalagriculture", "tinkering_table"))
            .where('G', state -> isBlock(state, "chipped", "ancient_gold_block"), getBlockSafe("chipped", "ancient_gold_block"))
            .where('H', state -> isBlock(state, "ars_nouveau", "magebloom_block"), getBlockSafe("ars_nouveau", "magebloom_block"))
            .where('I', state -> isBlock(state, "projectexpansion", "purple_fuel_block"), getBlockSafe("projectexpansion", "purple_fuel_block"))
            .where('J', state -> isBlock(state, "chipped", "spiraled_crying_obsidian"), getBlockSafe("chipped", "spiraled_crying_obsidian"))
            .where('K', state -> isBlock(state, "projecte", "dark_matter_block"), getBlockSafe("projecte", "dark_matter_block"))
            .where('O', state -> isBlock(state, "rechiseled", "gold_block_tiles_stairs_connecting"), getBlockSafe("rechiseled", "gold_block_tiles_stairs_connecting"))
            .where('P', state -> isBlock(state, "bloodmagic", "blankrune"), getBlockSafe("bloodmagic", "blankrune"))
            .where('Q', state -> isBlock(state, "justdirethings", "coalblock_t3"), getBlockSafe("justdirethings", "coalblock_t3"))
            .where('R', state -> isBlock(state, "botania", "rosa_arcana"), getBlockSafe("botania", "rosa_arcana"))
            .where('S', state -> isBlock(state, "antiblocksrechiseled", "bright_green"), getBlockSafe("antiblocksrechiseled", "bright_green"))
            .where('T', state -> isBlock(state, "botania", "agricarnation"), getBlockSafe("botania", "agricarnation"))
            .where('U', state -> isBlock(state, "chipped", "etched_crying_obsidian_bricks"), getBlockSafe("chipped", "etched_crying_obsidian_bricks"))
            .where('V', state -> isBlock(state, "bloodmagic", "ethereal_mimic"), getBlockSafe("bloodmagic", "ethereal_mimic"))
            .where('W', state -> isBlock(state, "experienceobelisk", "cognitive_crystal_block"), getBlockSafe("experienceobelisk", "cognitive_crystal_block"))
            .where('X', state -> isBlock(state, "chisel", "bookshelf_warped/rainbow"), getBlockSafe("chisel", "bookshelf_warped/rainbow"))
            .where('Y', state -> isBlock(state, "botania", "manastar"), getBlockSafe("botania", "manastar"))
            .where('Z', state -> isBlock(state, "botania", "orechid_ignem"), getBlockSafe("botania", "orechid_ignem"))
            .where('a', state -> isBlock(state, "botania", "dandelifeon"), getBlockSafe("botania", "dandelifeon"))
            .where('b', state -> isBlock(state, "bloodmagic", "largebloodstonebrick"), getBlockSafe("bloodmagic", "largebloodstonebrick"))
            .where('c', state -> isBlock(state, "justdirethings", "coalblock_t4"), getBlockSafe("justdirethings", "coalblock_t4"))
            .where('d', state -> isBlock(state, "botania", "daffomill"), getBlockSafe("botania", "daffomill"))
            .where('e', state -> isBlock(state, "botania", "spectrolus"), getBlockSafe("botania", "spectrolus"))
            .where('f', state -> isBlock(state, "rechiseled", "netherite_block_small_tiles_connecting"), getBlockSafe("rechiseled", "netherite_block_small_tiles_connecting"))
            .where('g', state -> isBlock(state, "minecraft", "crimson_trapdoor"), getBlockSafe("minecraft", "crimson_trapdoor"))
            .where('h', state -> isBlock(state, "rechiseled", "netherite_block_meteoric"), getBlockSafe("rechiseled", "netherite_block_meteoric"))
            .where('i', state -> isBlock(state, "minecraft", "grass_block"), getBlockSafe("minecraft", "grass_block"))

            .where('L', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('M', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())

            .build();

    public static final MultiblockPattern TRANSMUTATION_CONSTRUCTOR = MultiblockPattern.builder()
            // Layer 10 (Top, dy = 9)
            .layer(
                    "           ",
                    "           ",
                    "    B      ",
                    "   B   B   ",
                    "           ",
                    "  B        ",
                    "        B  ",
                    "   B       ",
                    "       B   ",
                    "           ",
                    "           ")
            // Layer 9 (dy = 8)
            .layer(
                    "           ",
                    "           ",
                    "   DD      ",
                    "       B   ",
                    "  D EEE D  ",
                    "  B EEE B  ",
                    "    EEE    ",
                    "   D   D   ",
                    "     D B   ",
                    "           ",
                    "           ")
            // Layer 8 (dy = 7)
            .layer(
                    "           ",
                    "           ",
                    "    F      ",
                    "   FEEED   ",
                    "  FEGGGED  ",
                    "   EGGGE   ",
                    "  DEGGGEF  ",
                    "    EEE    ",
                    "     DD    ",
                    "           ",
                    "           ")
            // Layer 7 (dy = 6)
            .layer(
                    "           ",
                    "           ",
                    "     FF    ",
                    "   FEEE    ",
                    "   EGGGE   ",
                    "  FEGGGEF  ",
                    "  FEGGGE   ",
                    "    EEEF   ",
                    "    F F    ",
                    "           ",
                    "           ")
            // Layer 6 (dy = 5)
            .layer(
                    "           ",
                    "           ",
                    "           ",
                    "    EEE    ",
                    "   EGGGE   ",
                    "   EGGGE   ",
                    "   EGGGE   ",
                    "    EEE    ",
                    "           ",
                    "           ",
                    "           ")
            // Layer 5 (dy = 4)
            .layer(
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "    EEE    ",
                    "    EEE    ",
                    "    EEE    ",
                    "           ",
                    "           ",
                    "           ",
                    "           ")
            // Layer 4 (dy = 3)
            .layer(
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ")
            // Layer 3 (dy = 2)
            .layer(
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ")
            // Layer 2 (dy = 1)
            .layer(
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ")
            // Layer 1 (Bottom, dy = 0) - Controller & Hatches moved to the front!
            .layer(
                    "           ",
                    " HHHHHHHHH ",
                    " HIJIKIIIH ",
                    " HIIJLKIJH ",
                    " HIKKLKJIH ",
                    " HKLLKLLKH ",
                    " HIJKLKKIH ",
                    " HJIKLJIIH ",
                    " HIIIKIJIH ",
                    " HHOHCHMHH ",
                    "           ")

            .where('B', state -> state.is(Blocks.RED_STAINED_GLASS_PANE), Blocks.RED_STAINED_GLASS_PANE.defaultBlockState())
            .where('D', state -> state.is(Blocks.ORANGE_STAINED_GLASS_PANE), Blocks.ORANGE_STAINED_GLASS_PANE.defaultBlockState())
            .where('F', state -> state.is(Blocks.YELLOW_STAINED_GLASS_PANE), Blocks.YELLOW_STAINED_GLASS_PANE.defaultBlockState())
            .where('H', state -> state.is(Blocks.COAL_BLOCK), Blocks.COAL_BLOCK.defaultBlockState())
            .where('I', state -> state.is(Blocks.WHITE_CONCRETE), Blocks.WHITE_CONCRETE.defaultBlockState())
            .where('J', state -> state.is(Blocks.WHITE_TERRACOTTA), Blocks.WHITE_TERRACOTTA.defaultBlockState())
            .where('L', state -> state.is(Blocks.PINK_CONCRETE), Blocks.PINK_CONCRETE.defaultBlockState())
            .where('E', state -> isBlock(state, "antiblocksrechiseled", "wool_magenta"), getBlockSafe("antiblocksrechiseled", "wool_magenta"))
            .where('G', state -> isBlock(state, "antiblocksrechiseled", "bright_black"), getBlockSafe("antiblocksrechiseled", "bright_black"))
            .where('K', state -> isBlock(state, "projectexpansion", "magenta_fuel_block"), getBlockSafe("projectexpansion", "magenta_fuel_block"))

            .where('M', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('O', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())

            .build();

    public static final MultiblockPattern SKYBLOCK_BUILDER = MultiblockPattern.builder()
            // Layer 10 (Top, dy = 10)
            .layer(
                    "               ",
                    "               ",
                    "   DDDDDDDDDDD ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DDDDDDDDDDD ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 9 (dy = 9)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E          E ",
                    "  E  F       E ",
                    "  E FFF      E ",
                    "  E  F       E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 8 (dy = 8)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E FFF      E ",
                    "  EFFFFF     E ",
                    "  EFFFFF     E ",
                    "  EFFFFF     E ",
                    "  E FFF      E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 7 (dy = 7)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E FFF      E ",
                    "  EFFFFF     E ",
                    "  EFFGFF     E ",
                    "  EFFFFF     E ",
                    "  E FFF      E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 6 (dy = 6)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E FFF      E ",
                    "  EFFFFF     E ",
                    "  EFFGFF     E ",
                    "  EFFF       E ",
                    "  E FFF      E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 5 (dy = 5)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E          E ",
                    "  E  F       E ",
                    "  E FG       E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 4 (dy = 4)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E          E ",
                    "  E          E ",
                    "  E  G       E ",
                    "  E          E ",
                    "  E          E ",
                    "   E       H E ",
                    "  E          E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 3 (dy = 3)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E          E ",
                    "  E III      E ",
                    "  E IJI      E ",
                    "  E III      E ",
                    "   E IIIIIII E ",
                    "   E IIMMLKI E ",
                    "   E IIIIIII E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "               ",
                    "               ",
                    "               ")
            // Layer 2 (dy = 2)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E          E ",
                    "  E NNN      E ",
                    "  E JJN      E ",
                    "  E NNN      E ",
                    "   E NNNNNNN E ",
                    "   E NNIMNIN E ",
                    "   E NNNNNNN E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "    OPPPPPO    ",
                    "    OQQRQQO    ",
                    "    OPPPPPO    ")
            // Layer 1 (dy = 1)
            .layer(
                    "               ",
                    "               ",
                    "   DEEEEEEEEED ",
                    "  E          E ",
                    "  E NNJ      E ",
                    "  E JNN      E ",
                    "  E NNJ      E ",
                    "   E NNNNNNN E ",
                    "   E NNNINNN E ",
                    "   E NNNNNNN E ",
                    "  E          E ",
                    "   DEEEEEEEEED ",
                    "    PSSSSSP    ",
                    "    WVUUUUT    ",
                    "    PSSCSSP    ") // Controller mapped to 'C'
            // Layer 0 (Bottom, dy = 0)
            .layer(
                    "               ",
                    "               ",
                    "   DDDDDDDDDDD ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DEEEEEEEEED ",
                    "   DDDDDDDDDDD ",
                    "    PSSSSSP    ",
                    "    YVU   Q    ",
                    "    PSSSSSP    ")


            .where('K', state -> state.is(Blocks.RED_CONCRETE), Blocks.RED_CONCRETE.defaultBlockState())
            .where('L', state -> state.is(Blocks.COBBLESTONE), Blocks.COBBLESTONE.defaultBlockState())
            .where('M', state -> state.is(Blocks.LIGHT_BLUE_CONCRETE), Blocks.LIGHT_BLUE_CONCRETE.defaultBlockState())
            .where('N', state -> state.is(Blocks.DIRT), Blocks.DIRT.defaultBlockState())

            .where('B', state -> isBlock(state, "ae2", "cable_bus"), getBlockSafe("ae2", "cable_bus"))
            .where('D', state -> isBlock(state, "ae2", "spatial_pylon"), getBlockSafe("ae2", "spatial_pylon"))
            .where('E', state -> isBlock(state, "tconstruct", "clear_tinted_glass"), getBlockSafe("tconstruct", "clear_tinted_glass"))
            .where('F', state -> isBlock(state, "twilightforest", "rainbow_oak_leaves"), getBlockSafe("twilightforest", "rainbow_oak_leaves"))
            .where('G', state -> isBlock(state, "twilightforest", "twilight_oak_log"), getBlockSafe("twilightforest", "twilight_oak_log"))
            .where('H', state -> isBlock(state, "create", "framed_glass_trapdoor"), getBlockSafe("create", "framed_glass_trapdoor"))
            .where('I', state -> isBlock(state, "botania", "golden_grass"), getBlockSafe("botania", "golden_grass"))
            .where('J', state -> isBlock(state, "twilightforest", "root"), getBlockSafe("twilightforest", "root"))
            .where('O', state -> isBlock(state, "ad_astra", "moon_stone_brick_stairs"), getBlockSafe("ad_astra", "moon_stone_brick_stairs"))
            .where('P', state -> isBlock(state, "ad_astra", "moon_pillar"), getBlockSafe("ad_astra", "moon_pillar"))
            .where('Q', state -> isBlock(state, "ad_astra", "polished_moon_stone"), getBlockSafe("ad_astra", "polished_moon_stone"))
            .where('S', state -> isBlock(state, "immersiveengineering", "sheetmetal_lead"), getBlockSafe("immersiveengineering", "sheetmetal_lead"))
            .where('U', state -> isBlock(state, "create", "fluid_pipe"), getBlockSafe("create", "fluid_pipe"))
            .where('V', state -> isBlock(state, "create", "glass_fluid_pipe"), getBlockSafe("create", "glass_fluid_pipe"))


            .where('R', state -> state.is(ModBlocks.ENERGY_INPUT_HATCH.get()), ModBlocks.ENERGY_INPUT_HATCH.get().defaultBlockState())
            .where('T', state -> state.is(ModBlocks.ITEM_OUTPUT_HATCH.get()), ModBlocks.ITEM_OUTPUT_HATCH.get().defaultBlockState())
            .where('W', state -> state.is(ModBlocks.FLUID_INPUT_HATCH.get()), ModBlocks.FLUID_INPUT_HATCH.get().defaultBlockState())
            .where('Y', state -> state.is(ModBlocks.ITEM_INPUT_HATCH.get()), ModBlocks.ITEM_INPUT_HATCH.get().defaultBlockState())

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