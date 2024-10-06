package me.drex.instantfeedback.mixin.client;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingMossBlock.class)
public abstract class HangingMossBlockMixin {

    @Shadow
    @Final
    public static BooleanProperty TIP;

    @Inject(
        method = "animateTick",
        at = @At("HEAD")
    )
    public void addParticle(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (randomSource.nextInt(10) == 0) {
            if (blockState.getValue(TIP)) {
                level.addParticle(
                    InstantFeedback.CREAKING_EYES,
                    blockPos.getX() + .5, blockPos.getY() + .5, blockPos.getZ() + .5,
                    0.0,
                    0.0,
                    0.0
                );
            }
        }
    }

}
