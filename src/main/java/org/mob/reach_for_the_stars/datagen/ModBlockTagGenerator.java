package org.mob.reach_for_the_stars.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Reach_for_the_stars.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ITEM_INPUT_HATCH.get())
                .add(ModBlocks.ITEM_OUTPUT_HATCH.get())
                .add(ModBlocks.FLUID_INPUT_HATCH.get())
                .add(ModBlocks.FLUID_OUTPUT_HATCH.get())
                .add(ModBlocks.ENERGY_INPUT_HATCH.get())
                .add(ModBlocks.FUEL_DISTILLERY_CONTROLLER.get())
                .add(ModBlocks.STEEL_DRILL_CONTROLLER.get())
                .add(ModBlocks.DESH_DRILL_CONTROLLER.get())
                .add(ModBlocks.OSTRUM_DRILL_CONTROLLER.get())
                .add(ModBlocks.CALORITE_DRILL_CONTROLLER.get())
                .add(ModBlocks.T1_ROCKET_ASSEMBLER_CONTROLLER.get())
                .add(ModBlocks.T2_ROCKET_ASSEMBLER_CONTROLLER.get())
                .add(ModBlocks.T3_ROCKET_ASSEMBLER_CONTROLLER.get())
                .add(ModBlocks.T4_ROCKET_ASSEMBLER_CONTROLLER.get())
                .add(ModBlocks.T5_ROCKET_ASSEMBLER_CONTROLLER.get())
        ;
    }
}
