package org.mob.reach_for_the_stars.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.custom.hatch.*;
import org.mob.reach_for_the_stars.block.custom.multiblock.*;
import org.mob.reach_for_the_stars.fluid.ModFluids;
import org.mob.reach_for_the_stars.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Reach_for_the_stars.MOD_ID);


    public static final RegistryObject<Block> ITEM_INPUT_HATCH = registerBlock("item_input_hatch",
            () -> new ItemInputHatchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ITEM_OUTPUT_HATCH = registerBlock("item_output_hatch",
            () -> new ItemOutputHatchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FLUID_INPUT_HATCH = registerBlock("fluid_input_hatch",
            () -> new FluidInputHatchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FLUID_OUTPUT_HATCH = registerBlock("fluid_output_hatch",
            () -> new FluidOutputHatchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> ENERGY_INPUT_HATCH = registerBlock("energy_input_hatch",
            () -> new EnergyInputHatchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // Fuel Distillery
    public static final RegistryObject<Block> FUEL_DISTILLERY_CONTROLLER = registerBlock("fuel_distillery_controller",
            () -> new FuelDistilleryControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // Drills
    public static final RegistryObject<Block> STEEL_DRILL_CONTROLLER = registerBlock("steel_drill_controller",
            () -> new SteelDrillControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DESH_DRILL_CONTROLLER = registerBlock("desh_drill_controller",
            () -> new DeshDrillControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> OSTRUM_DRILL_CONTROLLER = registerBlock("ostrum_drill_controller",
            () -> new OstrumDrillControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CALORITE_DRILL_CONTROLLER = registerBlock("calorite_drill_controller",
            () -> new CaloriteDrillControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // Rocket Assemblers
    public static final RegistryObject<Block> T1_ROCKET_ASSEMBLER_CONTROLLER = registerBlock("t1_rocket_assembler_controller",
            () -> new T1RocketAssemblerControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> T2_ROCKET_ASSEMBLER_CONTROLLER = registerBlock("t2_rocket_assembler_controller",
            () -> new T2RocketAssemblerControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> T3_ROCKET_ASSEMBLER_CONTROLLER = registerBlock("t3_rocket_assembler_controller",
            () -> new T3RocketAssemblerControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> T4_ROCKET_ASSEMBLER_CONTROLLER = registerBlock("t4_rocket_assembler_controller",
            () -> new T4RocketAssemblerControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> T5_ROCKET_ASSEMBLER_CONTROLLER = registerBlock("t5_rocket_assembler_controller",
            () -> new T5RocketAssemblerControllerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));


    // Drill Fluids
    public static final RegistryObject<LiquidBlock> STEEL_DRILL_FLUID_BLOCK = BLOCKS.register("steel_drill_fluid",
            () -> new LiquidBlock(ModFluids.SOURCE_STEEL_DRILL_FLUID, BlockBehaviour.Properties.copy(Blocks.LAVA).noLootTable()));

    public static final RegistryObject<LiquidBlock> DESH_DRILL_FLUID_BLOCK = BLOCKS.register("desh_drill_fluid",
            () -> new LiquidBlock(ModFluids.SOURCE_DESH_DRILL_FLUID, BlockBehaviour.Properties.copy(Blocks.LAVA).noLootTable()));

    public static final RegistryObject<LiquidBlock> OSTRUM_DRILL_FLUID_BLOCK = BLOCKS.register("ostrum_drill_fluid",
            () -> new LiquidBlock(ModFluids.SOURCE_OSTRUM_DRILL_FLUID, BlockBehaviour.Properties.copy(Blocks.LAVA).noLootTable()));

    public static final RegistryObject<LiquidBlock> CALORITE_DRILL_FLUID_BLOCK = BLOCKS.register("calorite_drill_fluid",
            () -> new LiquidBlock(ModFluids.SOURCE_CALORITE_DRILL_FLUID, BlockBehaviour.Properties.copy(Blocks.LAVA).noLootTable()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
