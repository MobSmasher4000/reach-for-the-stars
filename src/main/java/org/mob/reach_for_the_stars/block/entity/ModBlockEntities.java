package org.mob.reach_for_the_stars.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.block.entity.hatch.*;
import org.mob.reach_for_the_stars.block.entity.multiblock.*;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reach_for_the_stars.MOD_ID);

    public static final RegistryObject<BlockEntityType<ItemInputHatchBlockEntity>> ITEM_INPUT_HATCH_BE =
            BLOCK_ENTITIES.register("item_input_hatch_be", () ->
                    BlockEntityType.Builder.of(ItemInputHatchBlockEntity::new,
                            ModBlocks.ITEM_INPUT_HATCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<FluidInputHatchBlockEntity>> FLUID_INPUT_HATCH_BE =
            BLOCK_ENTITIES.register("fluid_input_hatch_be", () ->
                    BlockEntityType.Builder.of(FluidInputHatchBlockEntity::new,
                            ModBlocks.FLUID_INPUT_HATCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<ItemOutputHatchBlockEntity>> ITEM_OUTPUT_HATCH_BE =
            BLOCK_ENTITIES.register("item_output_hatch_be", () ->
                    BlockEntityType.Builder.of(ItemOutputHatchBlockEntity::new,
                            ModBlocks.ITEM_OUTPUT_HATCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<FluidOutputHatchBlockEntity>> FLUID_OUTPUT_HATCH_BE =
            BLOCK_ENTITIES.register("fluid_output_hatch_be", () ->
                    BlockEntityType.Builder.of(FluidOutputHatchBlockEntity::new,
                            ModBlocks.FLUID_OUTPUT_HATCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<EnergyInputHatchBlockEntity>> ENERGY_INPUT_HATCH_BE =
            BLOCK_ENTITIES.register("energy_input_hatch_be", () ->
                    BlockEntityType.Builder.of(EnergyInputHatchBlockEntity::new,
                            ModBlocks.ENERGY_INPUT_HATCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<FuelDistilleryControllerBlockEntity>> FUEL_DISTILLERY_CONTROLLER_BE =
            BLOCK_ENTITIES.register("fuel_distillery_controller_be", () ->
                    BlockEntityType.Builder.of(FuelDistilleryControllerBlockEntity::new,
                            ModBlocks.FUEL_DISTILLERY_CONTROLLER.get()).build(null));

    // Drill
    public static final RegistryObject<BlockEntityType<PlanetaryDrillControllerBlockEntity>> PLANETARY_DRILL_CONTROLLER_BE =
            BLOCK_ENTITIES.register("planetary_drill_controller_be", () ->
                    BlockEntityType.Builder.of(PlanetaryDrillControllerBlockEntity::new,
                            ModBlocks.STEEL_DRILL_CONTROLLER.get(), ModBlocks.DESH_DRILL_CONTROLLER.get(), ModBlocks.OSTRUM_DRILL_CONTROLLER.get(), ModBlocks.CALORITE_DRILL_CONTROLLER.get(), ModBlocks.GLACIO_DRILL_CONTROLLER.get()).build(null));

    // Rocket assemblers
    public static final RegistryObject<BlockEntityType<RocketAssemblerControllerBlockEntity>> ROCKET_ASSEMBLER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("rocket_assembler_controller_be", () ->
                    BlockEntityType.Builder.of(RocketAssemblerControllerBlockEntity::new,
                            ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get(),ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get(),ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get(),ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get(),ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get()).build(null));

    // Special multi
    public static final RegistryObject<BlockEntityType<Printer3DControllerBlockEntity>> PRINTER_3D_CONTROLLER_BE =
            BLOCK_ENTITIES.register("printer_3d_controller_be", () ->
                    BlockEntityType.Builder.of(Printer3DControllerBlockEntity::new,
                            ModBlocks.PRINTER_3D_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<TransmutationConstructorControllerBlockEntity>> TRANSMUTATION_CONSTRUCTOR_CONTROLLER_BE =
            BLOCK_ENTITIES.register("transmutation_constructor_controller_be", () ->
                    BlockEntityType.Builder.of(TransmutationConstructorControllerBlockEntity::new,
                            ModBlocks.TRANSMUTATION_CONSTRUCTOR_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<SkyblockBuilderControllerBlockEntity>> SKYBLOCK_BUILDER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("skyblock_builder_controller_be", () ->
                    BlockEntityType.Builder.of(SkyblockBuilderControllerBlockEntity::new,
                            ModBlocks.SKYBLOCK_BUILDER_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<RitualSiteControllerBlockEntity>> RITUAL_SITE_CONTROLLER_BE =
            BLOCK_ENTITIES.register("ritual_site_controller_be", () ->
                    BlockEntityType.Builder.of(RitualSiteControllerBlockEntity::new,
                            ModBlocks.RITUAL_SITE_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ContainedSpaceStationControllerBlockEntity>> CONTAINED_SPACE_STATION_CONTROLLER_BE =
            BLOCK_ENTITIES.register("contained_space_station_controller_be", () ->
                    BlockEntityType.Builder.of(ContainedSpaceStationControllerBlockEntity::new,
                            ModBlocks.CONTAINED_SPACE_STATION_CONTROLLER.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
