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

    public static final RegistryObject<BlockEntityType<SteelDrillControllerBlockEntity>> STEEL_DRILL_CONTROLLER_BE =
            BLOCK_ENTITIES.register("steel_drill_controller_be", () ->
                    BlockEntityType.Builder.of(SteelDrillControllerBlockEntity::new,
                            ModBlocks.STEEL_DRILL_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<DeshDrillControllerBlockEntity>> DESH_DRILL_CONTROLLER_BE =
            BLOCK_ENTITIES.register("desh_drill_controller_be", () ->
                    BlockEntityType.Builder.of(DeshDrillControllerBlockEntity::new,
                            ModBlocks.DESH_DRILL_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<OstrumDrillControllerBlockEntity>> OSTRUM_DRILL_CONTROLLER_BE =
            BLOCK_ENTITIES.register("ostrum_drill_controller_be", () ->
                    BlockEntityType.Builder.of(OstrumDrillControllerBlockEntity::new,
                            ModBlocks.OSTRUM_DRILL_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<CaloriteDrillControllerBlockEntity>> CALORITE_DRILL_CONTROLLER_BE =
            BLOCK_ENTITIES.register("calorite_drill_controller_be", () ->
                    BlockEntityType.Builder.of(CaloriteDrillControllerBlockEntity::new,
                            ModBlocks.CALORITE_DRILL_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<GlacioDrillControllerBlockEntity>> GLACIO_DRILL_CONTROLLER_BE =
            BLOCK_ENTITIES.register("glacio_drill_controller_be", () ->
                    BlockEntityType.Builder.of(GlacioDrillControllerBlockEntity::new,
                            ModBlocks.GLACIO_DRILL_CONTROLLER.get()).build(null));


    // Rocket assemblers
    public static final RegistryObject<BlockEntityType<T1RocketAssemblerControllerBlockEntity>> T1_ROCKET_ASSEMBLER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("t1_rocket_assembler_controller_be", () ->
                    BlockEntityType.Builder.of(T1RocketAssemblerControllerBlockEntity::new,
                            ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<T2RocketAssemblerControllerBlockEntity>> T2_ROCKET_ASSEMBLER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("t2_rocket_assembler_controller_be", () ->
                    BlockEntityType.Builder.of(T2RocketAssemblerControllerBlockEntity::new,
                            ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<T3RocketAssemblerControllerBlockEntity>> T3_ROCKET_ASSEMBLER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("t3_rocket_assembler_controller_be", () ->
                    BlockEntityType.Builder.of(T3RocketAssemblerControllerBlockEntity::new,
                            ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<T4RocketAssemblerControllerBlockEntity>> T4_ROCKET_ASSEMBLER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("t4_rocket_assembler_controller_be", () ->
                    BlockEntityType.Builder.of(T4RocketAssemblerControllerBlockEntity::new,
                            ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<T5RocketAssemblerControllerBlockEntity>> T5_ROCKET_ASSEMBLER_CONTROLLER_BE =
            BLOCK_ENTITIES.register("t5_rocket_assembler_controller_be", () ->
                    BlockEntityType.Builder.of(T5RocketAssemblerControllerBlockEntity::new,
                            ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get()).build(null));

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



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
