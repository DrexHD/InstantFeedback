package me.drex.instantfeedback.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.AtmosphericFogEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AtmosphericFogEnvironment.class)
public abstract class AtmosphericFogEnvironmentMixin {
    @Unique
    private float instantfeedback$paleGardenDivider = 1;

    @Inject(
        method = "setupFog",
        at = @At("RETURN")
    )
    public void addPaleGardenFog(
        FogData fogData, Entity entity, BlockPos blockPos,
        ClientLevel clientLevel, float f, DeltaTracker deltaTracker,
        CallbackInfo ci
    ) {
        Holder<Biome> biome = clientLevel.getBiome(blockPos);
        if (biome.is(Biomes.PALE_GARDEN)) {
            // during midnight the divider is 4x bigger than during day
            float nightMultiplier = (Mth.clamp(Mth.cos(clientLevel.getTimeOfDay(1.0F) * (float) (Math.PI * 2)), -1, 0) * -3) + 1;

            instantfeedback$paleGardenDivider += deltaTracker.getGameTimeDeltaTicks() * 0.2f;
            instantfeedback$paleGardenDivider = Math.min(16 * nightMultiplier, instantfeedback$paleGardenDivider);
        } else {
            instantfeedback$paleGardenDivider -= deltaTracker.getGameTimeDeltaTicks() * 0.8f;
            instantfeedback$paleGardenDivider = Math.max(instantfeedback$paleGardenDivider, 1);
        }
        fogData.environmentalStart /= instantfeedback$paleGardenDivider;
        fogData.environmentalEnd /= instantfeedback$paleGardenDivider;
        fogData.cloudEnd /= instantfeedback$paleGardenDivider;
        fogData.skyEnd /= instantfeedback$paleGardenDivider;
    }
}
