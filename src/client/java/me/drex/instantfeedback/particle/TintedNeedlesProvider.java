package me.drex.instantfeedback.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ColorParticleOption;

@Environment(EnvType.CLIENT)
public class TintedNeedlesProvider implements ParticleProvider<ColorParticleOption> {
    private final SpriteSet sprites;

    public TintedNeedlesProvider(SpriteSet spriteSet) {
        this.sprites = spriteSet;
    }

    public Particle createParticle(ColorParticleOption colorParticleOption, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
        Particle particle = new FallingLeavesParticle(clientLevel, d, e, f, this.sprites, 0.28F, 7.0F, true, false, 2.0F, 0.021F);
        particle.setColor(colorParticleOption.getRed(), colorParticleOption.getGreen(), colorParticleOption.getBlue());
        return particle;
    }
}
