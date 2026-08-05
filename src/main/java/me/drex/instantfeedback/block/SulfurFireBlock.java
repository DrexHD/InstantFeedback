package me.drex.instantfeedback.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SulfurFireBlock extends BaseFireBlock {
    public static final MapCodec<SulfurFireBlock> CODEC = simpleCodec(SulfurFireBlock::new);

    public SulfurFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 2.0F);
    }

    @Override
    protected MapCodec<? extends SulfurFireBlock> codec() {
        return CODEC;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
                                     net.minecraft.core.Direction direction, BlockPos neighborPos,
                                     BlockState neighborState, RandomSource random) {
        return canSurvive(state, level, pos) ? getStateDefinition().any() : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(Blocks.SULFUR) || state.is(Blocks.POTENT_SULFUR);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return false;
    }
}
