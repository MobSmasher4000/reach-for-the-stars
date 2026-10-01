package org.mob.reach_for_the_stars.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mob.reach_for_the_stars.Reach_for_the_stars;
import org.mob.reach_for_the_stars.block.ModBlocks;
import org.mob.reach_for_the_stars.block.custom.multiblock.FuelDistilleryControllerBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reach_for_the_stars.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        hatchBlock(ModBlocks.ITEM_INPUT_HATCH.get());
        hatchBlock(ModBlocks.ITEM_OUTPUT_HATCH.get());
        hatchBlock(ModBlocks.FLUID_INPUT_HATCH.get());
        hatchBlock(ModBlocks.FLUID_OUTPUT_HATCH.get());
        hatchBlock(ModBlocks.ENERGY_INPUT_HATCH.get());
    }

    private void hatchBlock(Block block) {
        ModelFile model = cubeAll(block);

        getVariantBuilder(block).forAllStates(state -> {
            Direction dir = state.getValue(BlockStateProperties.FACING);

            int xRot = 0;
            int yRot = 0;

            switch (dir) {
                case UP -> xRot = 270;
                case DOWN -> xRot = 90;
                case SOUTH -> yRot = 180;
                case WEST -> yRot = 270;
                case EAST -> yRot = 90;
                default -> {  }
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .build();
        });

        simpleBlockItem(block, model);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
