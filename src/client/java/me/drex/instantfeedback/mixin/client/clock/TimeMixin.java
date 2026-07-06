package me.drex.instantfeedback.mixin.client.clock;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import me.drex.instantfeedback.item.component.ModDataComponents;
import me.drex.instantfeedback.item.component.SavedTime;
import net.minecraft.client.renderer.item.properties.numeric.NeedleDirectionHelper;
import net.minecraft.client.renderer.item.properties.numeric.Time;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Time.class)
public abstract class TimeMixin {
    @Shadow
    @Final
    private Time.TimeSource source;

    @WrapOperation(
        method = "calculate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/item/properties/numeric/NeedleDirectionHelper$Wobbler;rotation()F"
        )
    )
    public float fixTime(NeedleDirectionHelper.Wobbler instance, Operation<Float> original, @Local(argsOnly = true) ItemStack itemStack) {
        SavedTime savedTime = itemStack.get(ModDataComponents.SAVED_TIME);
        if (savedTime != null && this.source == Time.TimeSource.DAYTIME) {
            return savedTime.sunAngle() / 360.0F;
        }
        return original.call(instance);
    }
}
