package me.drex.instantfeedback.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import me.drex.instantfeedback.config.ConfigManager;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.AtmosphericFogEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.attribute.EnvironmentAttributeProbe;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AtmosphericFogEnvironment.class)
public abstract class AtmosphericFogEnvironmentMixin {
    @Inject(method = "setupFog", at = @At("HEAD"))
    public void calculatePaleGardenFog(
        FogData fogData, Camera camera, ClientLevel clientLevel, float f, DeltaTracker deltaTracker, CallbackInfo ci,
        @Share("fogEndDivider") LocalFloatRef fogEndDividerRef
    ) {
        BlockPos blockPos = camera.blockPosition();
        Holder<Biome> biome = clientLevel.getBiome(blockPos);
        fogEndDividerRef.set(1);
        if (ConfigManager.config().theGardenAwakensFog && biome.is(Biomes.PALE_GARDEN)) {
            float nightMultiplier = (float) (Math.clamp(Math.cos(((clientLevel.getDayTime() - 18000) / 24000f) * Math.PI * 2), 0, 1) * 3) + 1;
            fogEndDividerRef.set(nightMultiplier);
        }
    }

    @Definition(id = "getValue", method = "Lnet/minecraft/world/attribute/EnvironmentAttributeProbe;getValue(Lnet/minecraft/world/attribute/EnvironmentAttribute;F)Ljava/lang/Object;")
    @Expression("?.getValue(?, ?)")
    @WrapOperation(method = "setupFog", at = @At("MIXINEXTRAS:EXPRESSION"))
    public <Value> Value adjustFogStartDistance(
        EnvironmentAttributeProbe instance, EnvironmentAttribute<Value> environmentAttribute, float partialTick,
        Operation<Value> original, @Share("fogEndDivider") LocalFloatRef fogEndDividerRef
    ) {
        float result = (float) original.call(instance, environmentAttribute, partialTick) / fogEndDividerRef.get();
        return (Value) (Object) result;
    }
}
