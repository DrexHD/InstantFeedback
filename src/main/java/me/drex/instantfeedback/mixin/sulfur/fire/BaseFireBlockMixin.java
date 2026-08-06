package me.drex.instantfeedback.mixin.sulfur.fire;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.block.SulfurFireBlock;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin {
    @ModifyReturnValue(method = "getState", at = @At("RETURN"))
    private static BlockState sulfurFire(BlockState original, @Local(name = "belowState") BlockState belowState) {
        if (SulfurFireBlock.canSurviveOnBlock(belowState)) {
            return ModBlocks.SULFUR_FIRE.defaultBlockState();
        }
        return original;
    }
}
