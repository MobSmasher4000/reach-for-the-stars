package org.mob.reach_for_the_stars.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.screen.menu.*;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Reach_for_the_stars.MOD_ID);

    public static final RegistryObject<MenuType<ItemInputHatchMenu>> ITEM_INPUT_HATCH_MENU =
            registerMenuType("item_input_hatch_menu", ItemInputHatchMenu::new);

    public static final RegistryObject<MenuType<FluidInputHatchMenu>> FLUID_INPUT_HATCH_MENU =
            registerMenuType("fluid_input_hatch_menu", FluidInputHatchMenu::new);

    public static final RegistryObject<MenuType<ItemOutputHatchMenu>> ITEM_OUTPUT_HATCH_MENU =
            registerMenuType("item_output_hatch_menu", ItemOutputHatchMenu::new);

    public static final RegistryObject<MenuType<FluidOutputHatchMenu>> FLUID_OUTPUT_HATCH_MENU =
            registerMenuType("fluid_output_hatch_menu", FluidOutputHatchMenu::new);

    // fuel distillery
    public static final RegistryObject<MenuType<FuelDistilleryControllerMenu>> FUEL_DISTILLERY_MENU =
            registerMenuType("fuel_distillery_menu", FuelDistilleryControllerMenu::new);

    // drill
    public static final RegistryObject<MenuType<PlanetaryDrillControllerMenu>> PLANETARY_DRILL_MENU =
            registerMenuType("planetary_drill_menu", PlanetaryDrillControllerMenu::new);

    // rocket assembler
    public static final RegistryObject<MenuType<RocketAssemblerControllerMenu>> ROCKET_ASSEMBLER_MENU =
            registerMenuType("rocket_assembler_menu", RocketAssemblerControllerMenu::new);

    // Special multi
    public static final RegistryObject<MenuType<Printer3DControllerMenu>> PRINTER_3D_MENU =
            registerMenuType("printer_3d_menu", Printer3DControllerMenu::new);

    public static final RegistryObject<MenuType<TransmutationConstructorControllerMenu>> TRANSMUTATION_CONSTRUCTOR_MENU =
            registerMenuType("transmutation_constructor", TransmutationConstructorControllerMenu::new);

    public static final RegistryObject<MenuType<RitualSiteControllerMenu>> RITUAL_SITE_MENU =
            registerMenuType("ritual_site_menu", RitualSiteControllerMenu::new);

    public static final RegistryObject<MenuType<SkyblockBuilderControllerMenu>> SKYBLOCK_BUILDER_MENU =
            registerMenuType("skyblock_builder_menu", SkyblockBuilderControllerMenu::new);

    public static final RegistryObject<MenuType<ContainedSpaceStationControllerMenu>> CONTAINED_SPACE_STATION_MENU =
            registerMenuType("contained_space_station_menu", ContainedSpaceStationControllerMenu::new);


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
