package me.drex.instantfeedback.mixin.client;

import me.drex.instantfeedback.ducks.ICreaking;
import me.drex.instantfeedback.ducks.ICreakingRenderState;
import net.minecraft.client.renderer.entity.CreakingRenderer;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import net.minecraft.world.entity.monster.creaking.Creaking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreakingRenderer.class)
public abstract class CreakingRendererMixin<T extends Creaking> {

    @Inject(
        method = "extractRenderState(Lnet/minecraft/world/entity/monster/creaking/Creaking;Lnet/minecraft/client/renderer/entity/state/CreakingRenderState;F)V",
        at = @At("RETURN")
    )
    public void addFields(T creaking, CreakingRenderState creakingRenderState, float f, CallbackInfo ci) {
        var renderState = (ICreakingRenderState) creakingRenderState;
        renderState.instantfeedback$setTicksSinceLastMove(((ICreaking) creaking).instantfeedback$getTicksSinceLastMove());
    }

}
