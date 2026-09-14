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
    public static final RegistryObject<MenuType<SteelDrillControllerMenu>> STEEL_DRILL_MENU =
            registerMenuType("steel_drill_menu", SteelDrillControllerMenu::new);

    public static final RegistryObject<MenuType<DeshDrillControllerMenu>> DESH_DRILL_MENU =
            registerMenuType("desh_drill_menu", DeshDrillControllerMenu::new);

    public static final RegistryObject<MenuType<OstrumDrillControllerMenu>> OSTRUM_DRILL_MENU =
            registerMenuType("ostrum_drill_menu", OstrumDrillControllerMenu::new);

    public static final RegistryObject<MenuType<CaloriteDrillControllerMenu>> CALORITE_DRILL_MENU =
            registerMenuType("calorite_drill_menu", CaloriteDrillControllerMenu::new);

    // rocket assembler
    public static final RegistryObject<MenuType<T1RocketAssemblerControllerMenu>> T1_ROCKET_ASSEMBLER_MENU =
            registerMenuType("t1_rocket_assembler_menu", T1RocketAssemblerControllerMenu::new);

    public static final RegistryObject<MenuType<T2RocketAssemblerControllerMenu>> T2_ROCKET_ASSEMBLER_MENU =
            registerMenuType("t2_rocket_assembler_menu", T2RocketAssemblerControllerMenu::new);

    public static final RegistryObject<MenuType<T3RocketAssemblerControllerMenu>> T3_ROCKET_ASSEMBLER_MENU =
            registerMenuType("t3_rocket_assembler_menu", T3RocketAssemblerControllerMenu::new);

    public static final RegistryObject<MenuType<T4RocketAssemblerControllerMenu>> T4_ROCKET_ASSEMBLER_MENU =
            registerMenuType("t4_rocket_assembler_menu", T4RocketAssemblerControllerMenu::new);

    public static final RegistryObject<MenuType<T5RocketAssemblerControllerMenu>> T5_ROCKET_ASSEMBLER_MENU =
            registerMenuType("t5_rocket_assembler_menu", T5RocketAssemblerControllerMenu::new);


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
