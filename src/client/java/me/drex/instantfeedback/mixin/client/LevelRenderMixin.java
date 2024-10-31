package me.drex.instantfeedback.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LevelRenderer.class)
public abstract class LevelRenderMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Unique
    float targetFogDistance = -1;
    float previousFogDistance = -1;
    long biomeChangedTime = Long.MAX_VALUE;

    @ModifyArg(
        method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/FogRenderer;setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;Lorg/joml/Vector4f;FZF)Lnet/minecraft/client/renderer/FogParameters;"
        ),
        index = 3
    )
    public float shortFogDistance(float original, @Local(argsOnly = true) Camera camera) {
        float fogDistance = original;
        if (this.minecraft.level.getBiome(BlockPos.containing(camera.getPosition())).is(Biomes.PALE_GARDEN)) {
            boolean night = this.minecraft.level.isNight();
            fogDistance = Math.min(original, night ? 48 : 96);
        }

        // Lerp fog distance
        long millis = Util.getMillis();
        // Set start value
        if (biomeChangedTime == Long.MAX_VALUE) {
            previousFogDistance = fogDistance;
            targetFogDistance = fogDistance;
            biomeChangedTime = 0;
        }

        float lerpValue = Mth.clamp((float) (millis - biomeChangedTime) / 5000.0F, 0.0F, 1.0F);

        float lerpedFogDistance = Mth.lerp(lerpValue, previousFogDistance, targetFogDistance);
        if (targetFogDistance != fogDistance) {
            targetFogDistance = fogDistance;
            previousFogDistance = lerpedFogDistance;
            biomeChangedTime = millis;
        }
        return lerpedFogDistance;
    }

    @ModifyArg(
        method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/FogRenderer;setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;Lorg/joml/Vector4f;FZF)Lnet/minecraft/client/renderer/FogParameters;"
        ),
        index = 4
    )
    public boolean enableFog(boolean original, @Local(argsOnly = true) Camera camera) {
        if (this.minecraft.level.getBiome(BlockPos.containing(camera.getPosition())).is(Biomes.PALE_GARDEN)) {
            return true;
        }

        if (Util.getMillis() - biomeChangedTime < 5000) {
            return true;
        }
        return original;
    }

}
