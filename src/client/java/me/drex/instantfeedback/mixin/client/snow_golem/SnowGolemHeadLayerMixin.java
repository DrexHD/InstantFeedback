package me.drex.instantfeedback.mixin.client.snow_golem;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.duck.snow_golem.ISnowGolemRenderState;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowGolemHeadLayer.class)
public abstract class SnowGolemHeadLayerMixin {

    @WrapOperation(
        method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;FF)V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;hasPumpkin:Z"
        )
    )
    public boolean instantfeedback$orPalePumpking(SnowGolemRenderState instance, Operation<Boolean> original) {
        return original.call(instance) || ((ISnowGolemRenderState) instance).instantfeedback$hasPalePumpkin();
    }

    @WrapOperation(
        method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;FF)V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/level/block/Blocks;CARVED_PUMPKIN:Lnet/minecraft/world/level/block/Block;"
        )
    )
    private Block instantfeedback$renderPalePumpkin(Operation<Block> original, @Local(argsOnly = true) SnowGolemRenderState snowGolemRenderState) {
        if (((ISnowGolemRenderState) snowGolemRenderState).instantfeedback$hasPalePumpkin()) {
            return ModBlocks.CARVED_PALE_PUMPKIN;
        }
        return original.call();
    }

}
