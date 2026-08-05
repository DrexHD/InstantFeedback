package me.drex.instantfeedback.mixin.golden_dandelion.tempt;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.drex.instantfeedback.config.ConfigManager;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TemptGoal.class)
public abstract class TemptGoalMixin {
    @Shadow
    @Final
    protected Mob mob;

    @ModifyReturnValue(method = "shouldFollow", at = @At("RETURN"))
    public boolean babyGoldenDandelionCanTempt(boolean original, @Local(name = "player") LivingEntity player) {
        if (!ConfigManager.config().tinyTakeoverGoldenDandelionTemptBabies) return original;
        boolean hasGoldenDandelion = player.getMainHandItem().is(Items.GOLDEN_DANDELION) || player.getOffhandItem().is(Items.GOLDEN_DANDELION);
        boolean isTemptableBaby = this.mob instanceof AgeableMob ageable && ageable.isBaby() && !ageable.isAgeLocked();
        return original || (hasGoldenDandelion && isTemptableBaby);
    }
}
