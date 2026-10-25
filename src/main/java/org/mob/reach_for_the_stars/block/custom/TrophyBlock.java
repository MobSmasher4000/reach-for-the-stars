package org.mob.reach_for_the_stars.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrophyBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH_SOUTH = Shapes.or(
            Block.box(5, 0, 5, 11, 2, 11),      // Base
            Block.box(7, 2, 7, 9, 6, 9),        // Rod
            Block.box(5, 6, 5, 11, 7, 11),      // CupB
            Block.box(5, 7, 4, 11, 14, 5),      // CupN
            Block.box(11, 7, 5, 12, 14, 11),    // CupE
            Block.box(5, 7, 11, 11, 14, 12),    // CupS
            Block.box(4, 7, 5, 5, 14, 11),      // CupW
            Block.box(12, 8, 7.5, 15, 14, 8.5), // HandleE
            Block.box(1, 8, 7.5, 4, 14, 8.5)    // HandleW
    );


    private static final VoxelShape SHAPE_EAST_WEST = Shapes.or(
            Block.box(5, 0, 5, 11, 2, 11),      // Base
            Block.box(7, 2, 7, 9, 6, 9),        // Rod
            Block.box(5, 6, 5, 11, 7, 11),      // CupB
            Block.box(4, 7, 5, 5, 14, 11),      // CupN
            Block.box(5, 7, 11, 11, 14, 12),    // CupE
            Block.box(11, 7, 5, 12, 14, 11),    // CupS
            Block.box(5, 7, 4, 11, 14, 5),      // CupW
            Block.box(7.5, 8, 12, 8.5, 14, 15), // HandleE
            Block.box(7.5, 8, 1, 8.5, 14, 4)    // HandleW
    );

    public TrophyBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).dynamicShape().noOcclusion().requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case EAST, WEST -> SHAPE_EAST_WEST;
            default -> SHAPE_NORTH_SOUTH;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}