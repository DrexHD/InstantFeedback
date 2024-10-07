package particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class CreakingEyesParticle extends SimpleAnimatedParticle {
    CreakingEyesParticle(ClientLevel clientLevel, double d, double e, double f, SpriteSet spriteSet) {
        super(clientLevel, d, e, f, spriteSet, 0);
        this.quadSize *= 2;
        this.setSpriteFromAge(spriteSet);
        if (clientLevel.isNight()) {
            this.lifetime = 200 + this.random.nextInt(40);
        } else {
            this.lifetime = 0;
            this.remove();
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            return new CreakingEyesParticle(clientLevel, d, e, f, this.sprites);
        }
    }
}
