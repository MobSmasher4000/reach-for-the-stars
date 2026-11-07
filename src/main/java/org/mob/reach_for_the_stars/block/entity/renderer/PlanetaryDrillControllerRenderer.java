package org.mob.reach_for_the_stars.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.mob.reach_for_the_stars.block.custom.multiblock.PlanetaryDrillControllerBlock;
import org.mob.reach_for_the_stars.block.entity.multiblock.PlanetaryDrillControllerBlockEntity;

import java.util.Map;

public class PlanetaryDrillControllerRenderer implements BlockEntityRenderer<PlanetaryDrillControllerBlockEntity> {

    public PlanetaryDrillControllerRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(PlanetaryDrillControllerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        BlockState state = pBlockEntity.getBlockState();

        if (pBlockEntity.showPreview && !state.getValue(PlanetaryDrillControllerBlock.FORMED)) {

            if (state.getBlock() instanceof PlanetaryDrillControllerBlock drillBlock) {
                Direction facing = state.getValue(PlanetaryDrillControllerBlock.FACING);

                Map<BlockPos, BlockState> preview = drillBlock.getTier().getPattern().getPreviewOffsets(facing);
                BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();

                for (Map.Entry<BlockPos, BlockState> entry : preview.entrySet()) {
                    BlockPos offset = entry.getKey();

                    pPoseStack.pushPose();
                    // Move the renderer to the offset location
                    pPoseStack.translate(offset.getX(), offset.getY(), offset.getZ());

                    // Shrink the blocks slightly
                    pPoseStack.translate(0.1, 0.1, 0.1);
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);

                    dispatcher.renderSingleBlock(entry.getValue(), pPoseStack, pBufferSource, 15728880,
                            net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
                            net.minecraftforge.client.model.data.ModelData.EMPTY, null);

                    pPoseStack.popPose();
                }
            }
        }
    }
}