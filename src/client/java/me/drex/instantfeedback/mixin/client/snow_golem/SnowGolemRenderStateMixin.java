package me.drex.instantfeedback.mixin.client.snow_golem;

import me.drex.instantfeedback.duck.snow_golem.ISnowGolemRenderState;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SnowGolemRenderState.class)
public abstract class SnowGolemRenderStateMixin implements ISnowGolemRenderState {
    @Unique
    public boolean instantfeedback$hasPalePumpkin;

    @Override
    public boolean instantfeedback$hasPalePumpkin() {
        return instantfeedback$hasPalePumpkin;
    }

    @Override
    public void instantfeedback$setPalePumpkin(boolean palePumpkin) {
        instantfeedback$hasPalePumpkin = palePumpkin;
    }
}
