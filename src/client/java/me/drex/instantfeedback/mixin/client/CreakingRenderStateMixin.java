package me.drex.instantfeedback.mixin.client;

import me.drex.instantfeedback.ducks.ICreakingRenderState;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CreakingRenderState.class)
public abstract class CreakingRenderStateMixin implements ICreakingRenderState {

    @Unique
    private long instantfeedback$ticksSinceLastMove = 0;

    @Override
    public long instantfeedback$getTicksSinceLastMove() {
        return this.instantfeedback$ticksSinceLastMove;
    }

    @Override
    public void instantfeedback$setTicksSinceLastMove(long ticksSinceLastMove) {
        this.instantfeedback$ticksSinceLastMove = ticksSinceLastMove;
    }
}
