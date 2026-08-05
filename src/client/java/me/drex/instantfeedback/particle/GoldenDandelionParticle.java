package me.drex.instantfeedback.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class GoldenDandelionParticle extends SingleQuadParticle {
    GoldenDandelionParticle(ClientLevel clientLevel, final double x, final double y, final double z, TextureAtlasSprite spriteSet) {
        super(clientLevel, x, y, z, 0.0F, 0.0F, 0.0F, spriteSet);

        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = Mth.randomBetweenInclusive(random, 500, 1000);
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = -0.005F;
        this.setColor(0.98F, 0.8F, 0.13F);
        setParticleSpeed(Mth.nextDouble(this.random, -.02, .02), Mth.nextDouble(this.random, 0, .01), Mth.nextDouble(this.random, -.02, .02));
    }

    public SingleQuadParticle.Layer getLayer() {
        return Layer.OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(final SimpleParticleType options, final ClientLevel level, final double x, final double y, final double z, final double xAux, final double yAux, final double zAux, final RandomSource random) {
            return new GoldenDandelionParticle(level, x, y, z, this.sprites.get(random));
        }
    }
}
