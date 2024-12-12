package me.drex.instantfeedback.mixin.client.snow_golem;

import me.drex.instantfeedback.duck.snow_golem.ISnowGolem;
import me.drex.instantfeedback.duck.snow_golem.ISnowGolemRenderState;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.world.entity.animal.SnowGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowGolemRenderer.class)
public abstract class SnowGolemRendererMixin {

    @Inject(
        method = "extractRenderState(Lnet/minecraft/world/entity/animal/SnowGolem;Lnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;F)V",
        at = @At("TAIL")
    )
    public void instantfeedback$extractPalePumpkinState(SnowGolem snowGolem, SnowGolemRenderState snowGolemRenderState, float f, CallbackInfo ci) {
        ((ISnowGolemRenderState) snowGolemRenderState).instantfeedback$setPalePumpkin(((ISnowGolem) snowGolem).instantfeedback$hasPalePumpkin());
    }

}
