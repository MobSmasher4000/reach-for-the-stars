package org.mob.reach_for_the_stars.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.entity.ModBlockEntities;
import org.mob.reach_for_the_stars.block.entity.renderer.*;
import org.mob.reach_for_the_stars.fluid.ModFluids;
import org.mob.reach_for_the_stars.item.ModItems;
import org.mob.reach_for_the_stars.screen.ModMenuTypes;
import org.mob.reach_for_the_stars.screen.screen.*;

@Mod.EventBusSubscriber(modid = Reach_for_the_stars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        BlockEntityRenderers.register(ModBlockEntities.FUEL_DISTILLERY_CONTROLLER_BE.get(), FuelDistilleryControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.STEEL_DRILL_CONTROLLER_BE.get(), SteelDrillControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.DESH_DRILL_CONTROLLER_BE.get(), DeshDrillControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.OSTRUM_DRILL_CONTROLLER_BE.get(), OstrumDrillControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.CALORITE_DRILL_CONTROLLER_BE.get(), CaloriteDrillControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.T1_ROCKET_ASSEMBLER_CONTROLLER_BE.get(), T1RocketAssemblerControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.T2_ROCKET_ASSEMBLER_CONTROLLER_BE.get(), T2RocketAssemblerControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.T3_ROCKET_ASSEMBLER_CONTROLLER_BE.get(), T3RocketAssemblerControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.T4_ROCKET_ASSEMBLER_CONTROLLER_BE.get(), T4RocketAssemblerControllerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.T5_ROCKET_ASSEMBLER_CONTROLLER_BE.get(), T5RocketAssemblerControllerRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.ITEM_OUTPUT_HATCH_MENU.get(), ItemOutputHatchScreen::new);
        MenuScreens.register(ModMenuTypes.FLUID_OUTPUT_HATCH_MENU.get(), FluidOutputHatchScreen::new);
        MenuScreens.register(ModMenuTypes.FLUID_INPUT_HATCH_MENU.get(), FluidInputHatchScreen::new);
        MenuScreens.register(ModMenuTypes.ITEM_INPUT_HATCH_MENU.get(), ItemInputHatchScreen::new);
        MenuScreens.register(ModMenuTypes.FUEL_DISTILLERY_MENU.get(), FuelDistilleryControllerScreen::new);
        MenuScreens.register(ModMenuTypes.STEEL_DRILL_MENU.get(), SteelDrillControllerScreen::new);
        MenuScreens.register(ModMenuTypes.DESH_DRILL_MENU.get(), DeshDrillControllerScreen::new);
        MenuScreens.register(ModMenuTypes.OSTRUM_DRILL_MENU.get(), OstrumDrillControllerScreen::new);
        MenuScreens.register(ModMenuTypes.CALORITE_DRILL_MENU.get(), CaloriteDrillControllerScreen::new);
        MenuScreens.register(ModMenuTypes.T1_ROCKET_ASSEMBLER_MENU.get(), T1RocketAssemblerControllerScreen::new);
        MenuScreens.register(ModMenuTypes.T2_ROCKET_ASSEMBLER_MENU.get(), T2RocketAssemblerControllerScreen::new);
        MenuScreens.register(ModMenuTypes.T3_ROCKET_ASSEMBLER_MENU.get(), T3RocketAssemblerControllerScreen::new);
        MenuScreens.register(ModMenuTypes.T4_ROCKET_ASSEMBLER_MENU.get(), T4RocketAssemblerControllerScreen::new);
        MenuScreens.register(ModMenuTypes.T5_ROCKET_ASSEMBLER_MENU.get(), T5RocketAssemblerControllerScreen::new);

        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_STEEL_DRILL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_STEEL_DRILL_FLUID.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_DESH_DRILL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_DESH_DRILL_FLUID.get(), RenderType.translucent());
        });
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

        event.register((stack, tintIndex) -> {
            return tintIndex == 1 ? 0xFFC0C0C0 : -1;
        }, ModItems.STEEL_DRILL_FLUID_BUCKET.get());

        event.register((stack, tintIndex) -> {
            return tintIndex == 1 ? 0xFFF57D27 : -1;
        }, ModItems.DESH_DRILL_FLUID_BUCKET.get());

        event.register((stack, tintIndex) -> {
            return tintIndex == 1 ? 0xFF775360 : -1;
        }, ModItems.OSTRUM_DRILL_FLUID_BUCKET.get());

        event.register((stack, tintIndex) -> {
            return tintIndex == 1 ? 0xFF9E1F3F : -1;
        }, ModItems.CALORITE_DRILL_FLUID_BUCKET.get());

    }
}
