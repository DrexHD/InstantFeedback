package me.drex.instantfeedback.block;

import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.config.ConfigManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GoldenDandelionBlock extends FlowerBlock {
    public GoldenDandelionBlock(Holder<MobEffect> suspiciousStewEffect, float effectSeconds, Properties properties) {
        super(suspiciousStewEffect, effectSeconds, properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!ConfigManager.config().tinyTakeoverGoldenDandelionParticles) return;
        double plantX = pos.getX() + .5f;
        double plantY = pos.getY() + .4f;
        double plantZ = pos.getZ() + .5f;
        if (random.nextInt(3) != 0) return;
        level.addParticle(InstantFeedback.GOLDEN_DANDELION, plantX, plantY, plantZ, 0.0, 0.0, 0.0);
    }
}
