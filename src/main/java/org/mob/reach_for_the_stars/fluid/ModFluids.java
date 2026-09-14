package org.mob.reach_for_the_stars.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Reach_for_the_stars.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reach_for_the_stars.MOD_ID);

    private static final ResourceLocation THICK_STILL = ResourceLocation.parse("block/water_still");
    private static final ResourceLocation THICK_FLOW = ResourceLocation.parse("block/water_flow");

    // Steel Drill Fluid
    public static final RegistryObject<FluidType> STEEL_DRILL_FLUID_TYPE = FLUID_TYPES.register("steel_drill_fluid",
            () -> new BaseFluidType(FluidType.Properties.create()
                    .density(3000).viscosity(6000),
                    0xFFC0C0C0, THICK_STILL, THICK_FLOW));

    public static final RegistryObject<FlowingFluid> SOURCE_STEEL_DRILL_FLUID = FLUIDS.register("steel_drill_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.STEEL_DRILL_PROPERTIES));
    
    public static final RegistryObject<FlowingFluid> FLOWING_STEEL_DRILL_FLUID = FLUIDS.register("flowing_steel_drill_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.STEEL_DRILL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties STEEL_DRILL_PROPERTIES = new ForgeFlowingFluid.Properties(
            STEEL_DRILL_FLUID_TYPE, SOURCE_STEEL_DRILL_FLUID, FLOWING_STEEL_DRILL_FLUID)
            .block(ModBlocks.STEEL_DRILL_FLUID_BLOCK).bucket(ModItems.STEEL_DRILL_FLUID_BUCKET);

    // Desh Drill Fluid
    public static final RegistryObject<FluidType> DESH_DRILL_FLUID_TYPE = FLUID_TYPES.register("desh_drill_fluid",
            () -> new BaseFluidType(FluidType.Properties.create()
                    .density(3000).viscosity(6000), 
                    0xFFF57D27, THICK_STILL, THICK_FLOW));

    public static final RegistryObject<FlowingFluid> SOURCE_DESH_DRILL_FLUID = FLUIDS.register("desh_drill_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.DESH_DRILL_PROPERTIES));
    
    public static final RegistryObject<FlowingFluid> FLOWING_DESH_DRILL_FLUID = FLUIDS.register("flowing_desh_drill_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.DESH_DRILL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties DESH_DRILL_PROPERTIES = new ForgeFlowingFluid.Properties(
            DESH_DRILL_FLUID_TYPE, SOURCE_DESH_DRILL_FLUID, FLOWING_DESH_DRILL_FLUID)
            .block(ModBlocks.DESH_DRILL_FLUID_BLOCK).bucket(ModItems.DESH_DRILL_FLUID_BUCKET);

    // Ostrum Drill Fluid
    public static final RegistryObject<FluidType> OSTRUM_DRILL_FLUID_TYPE = FLUID_TYPES.register("ostrum_drill_fluid",
            () -> new BaseFluidType(FluidType.Properties.create()
                    .density(3000).viscosity(6000),
                    0xFF775360, THICK_STILL, THICK_FLOW));

    public static final RegistryObject<FlowingFluid> SOURCE_OSTRUM_DRILL_FLUID = FLUIDS.register("ostrum_drill_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.OSTRUM_DRILL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_OSTRUM_DRILL_FLUID = FLUIDS.register("flowing_ostrum_drill_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.OSTRUM_DRILL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties OSTRUM_DRILL_PROPERTIES = new ForgeFlowingFluid.Properties(
            OSTRUM_DRILL_FLUID_TYPE, SOURCE_OSTRUM_DRILL_FLUID, FLOWING_OSTRUM_DRILL_FLUID)
            .block(ModBlocks.OSTRUM_DRILL_FLUID_BLOCK).bucket(ModItems.OSTRUM_DRILL_FLUID_BUCKET);

    // Callorite Drill Fluid
    public static final RegistryObject<FluidType> CALORITE_DRILL_FLUID_TYPE = FLUID_TYPES.register("calorite_drill_fluid",
            () -> new BaseFluidType(FluidType.Properties.create()
                    .density(3000).viscosity(6000),
                    0xFF9E1F3F, THICK_STILL, THICK_FLOW));

    public static final RegistryObject<FlowingFluid> SOURCE_CALORITE_DRILL_FLUID = FLUIDS.register("calorite_drill_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.CALORITE_DRILL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_CALORITE_DRILL_FLUID = FLUIDS.register("flowing_calorite_drill_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.CALORITE_DRILL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CALORITE_DRILL_PROPERTIES = new ForgeFlowingFluid.Properties(
            CALORITE_DRILL_FLUID_TYPE, SOURCE_CALORITE_DRILL_FLUID, FLOWING_CALORITE_DRILL_FLUID)
            .block(ModBlocks.CALORITE_DRILL_FLUID_BLOCK).bucket(ModItems.CALORITE_DRILL_FLUID_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}