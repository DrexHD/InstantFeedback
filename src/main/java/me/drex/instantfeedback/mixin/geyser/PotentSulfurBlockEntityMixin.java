package me.drex.instantfeedback.mixin.geyser;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.drex.instantfeedback.config.ConfigManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.PotentSulfurBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotentSulfurBlockEntity.class)
public abstract class PotentSulfurBlockEntityMixin {
    @WrapOperation(method = {"isWater", "findNoxiousGasSourceBlock"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;isSourceOfType(Lnet/minecraft/world/level/material/Fluid;)Z"))
    private static boolean lavaGeysers(FluidState instance, Fluid fluidType, Operation<Boolean> original) {
        if (!ConfigManager.config().chaosCubedLavaGeysers) return original.call(instance, fluidType);
        return original.call(instance, fluidType) || original.call(instance, Fluids.LAVA);
    }

    @WrapOperation(method = {"isGeyserPassableBlock", "findNoxiousGasSourceBlock"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
    private static boolean lavaGeyserPassableBlocks(BlockState instance, Object o, Operation<Boolean> original) {
        if (!ConfigManager.config().chaosCubedLavaGeysers) return original.call(instance, o);
        return original.call(instance, o) || original.call(instance, Blocks.LAVA);
    }
}
