package org.mob.reach_for_the_stars.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ITEM_INPUT_HATCH.get());
        dropSelf(ModBlocks.ITEM_OUTPUT_HATCH.get());
        dropSelf(ModBlocks.FLUID_INPUT_HATCH.get());
        dropSelf(ModBlocks.FLUID_OUTPUT_HATCH.get());
        dropSelf(ModBlocks.ENERGY_INPUT_HATCH.get());
        dropSelf(ModBlocks.FUEL_DISTILLERY_CONTROLLER.get());
        dropSelf(ModBlocks.STEEL_DRILL_CONTROLLER.get());
        dropSelf(ModBlocks.DESH_DRILL_CONTROLLER.get());
        dropSelf(ModBlocks.OSTRUM_DRILL_CONTROLLER.get());
        dropSelf(ModBlocks.CALORITE_DRILL_CONTROLLER.get());
        dropSelf(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get());
        dropSelf(ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get());
        dropSelf(ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get());
        dropSelf(ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get());
        dropSelf(ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
