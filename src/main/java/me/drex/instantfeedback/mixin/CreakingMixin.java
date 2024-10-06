package me.drex.instantfeedback.mixin;

import me.drex.instantfeedback.ducks.ICreaking;
import net.minecraft.world.entity.monster.creaking.Creaking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creaking.class)
public abstract class CreakingMixin implements ICreaking {

    @Shadow
    public abstract boolean canMove();

    @Unique
    private long instantfeedback$ticksSinceLastMove = 0;

    @ModifyConstant(
        method = "createAttributes",
        constant = @Constant(doubleValue = 0.3F)
    )
    private static double increaseMovementSpeed(double constant) {
        return 0.45F;
    }

    @ModifyConstant(
        method = "createAttributes",
        constant = @Constant(doubleValue = 2.0)
    )
    private static double increaseAttackDamage(double constant) {
        return 10;
    }

    @Inject(method = "setupAnimationStates", at = @At("HEAD"))
    public void tickAnimation(CallbackInfo ci) {
        if (canMove()) {
            instantfeedback$ticksSinceLastMove = 0;
        } else {
            instantfeedback$ticksSinceLastMove++;
        }
    }

    @Override
    public long instantfeedback$getTicksSinceLastMove() {
        return this.instantfeedback$ticksSinceLastMove;
    }
}
