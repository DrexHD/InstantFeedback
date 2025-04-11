package me.drex.instantfeedback.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.block.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TintedParticleLeavesBlock.class)
public abstract class TintedParticleLeavesBlockMixin {

    @WrapOperation(
        method = "spawnFallingLeavesParticle",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/core/particles/ParticleTypes;TINTED_LEAVES:Lnet/minecraft/core/particles/ParticleType;"
        )
    )
    public ParticleType<ColorParticleOption> needleParticles(Operation<ParticleType<ColorParticleOption>> original, Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(ModBlockTags.LEAVES_NEEDLES)) {
            return InstantFeedback.TINTED_NEEDLES;
        }
        return original.call();
    }

}
