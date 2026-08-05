package me.drex.instantfeedback.mixin.geyser;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.drex.instantfeedback.config.ConfigManager;
import net.minecraft.world.level.block.PotentSulfurBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotentSulfurBlock.class)
public abstract class PotentSulfurBlockMixin {
    @WrapOperation(method = {"validBlockState", "animateTick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;isSourceOfType(Lnet/minecraft/world/level/material/Fluid;)Z"))
    private static boolean lavaGeysers(FluidState instance, Fluid fluidType, Operation<Boolean> original) {
        if (!ConfigManager.config().chaosCubedLavaGeysers) return original.call(instance, fluidType);
        return original.call(instance, fluidType) || original.call(instance, Fluids.LAVA);
    }
}
