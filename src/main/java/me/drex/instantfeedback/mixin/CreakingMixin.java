package me.drex.instantfeedback.mixin;

import net.minecraft.world.entity.monster.creaking.Creaking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Creaking.class)
public abstract class CreakingMixin {

    @ModifyConstant(
        method = "createAttributes",
        constant = @Constant(doubleValue = 0.4000000059604645)
    )
    private static double increaseMovementSpeed(double constant) {
        return 0.45F;
    }

    @ModifyConstant(
        method = "createAttributes",
        constant = @Constant(doubleValue = 3.0)
    )
    private static double increaseAttackDamage(double constant) {
        return 10;
    }
}
