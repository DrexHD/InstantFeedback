package me.drex.instantfeedback.mixin.client.snow_golem;

import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.duck.ISnowGolem;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.renderer.entity.SnowGolemRenderer.BLOCK_DISPLAY_CONTEXT;

@Mixin(SnowGolemRenderer.class)
public abstract class SnowGolemRendererMixin {

    @Shadow
    @Final
    private BlockModelResolver blockModelResolver;

    @Inject(
        method = "extractRenderState(Lnet/minecraft/world/entity/animal/golem/SnowGolem;Lnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;F)V",
        at = @At("TAIL")
    )
    public void instantfeedback$extractPalePumpkinState(SnowGolem snowGolem, SnowGolemRenderState state, float f, CallbackInfo ci) {
        if (((ISnowGolem) snowGolem).instantfeedback$hasPalePumpkin()) {
            this.blockModelResolver.update(state.headBlock, ModBlocks.CARVED_PALE_PUMPKIN.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
        }
    }

}
