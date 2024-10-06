package me.drex.instantfeedback.mixin.client;

import me.drex.instantfeedback.ducks.ICreakingRenderState;
import net.minecraft.client.model.CreakingModel;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreakingModel.class)
public abstract class CreakingModelMixin {

    @Redirect(
        method = "getHeadModelParts",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/entity/state/CreakingRenderState;isActive:Z"
        )
    )
    public boolean dimEyesOnStare(CreakingRenderState instance) {
        return ((ICreakingRenderState)instance).instantfeedback$getTicksSinceLastMove() < 5;
    }

}
