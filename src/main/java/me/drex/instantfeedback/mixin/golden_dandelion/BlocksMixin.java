package me.drex.instantfeedback.mixin.golden_dandelion;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import me.drex.instantfeedback.block.GoldenDandelionBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Function;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @Definition(id = "register", method = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;")
    @Definition(id = "GOLDEN_DANDELION", field = "Lnet/minecraft/references/BlockItemIds;GOLDEN_DANDELION:Lnet/minecraft/references/BlockItemId;")
    @Expression("register(GOLDEN_DANDELION, ?, ?)")
    @ModifyArg(
        method = "<clinit>",
        at = @At(
            value = "MIXINEXTRAS:EXPRESSION"
        ),
        index = 1
    )
    private static Function<BlockBehaviour.Properties, Block> addCustomBlockClass(Function<BlockBehaviour.Properties, Block> factory) {
        return properties -> new GoldenDandelionBlock(MobEffects.SATURATION, 0.35f, properties);
    }
}
