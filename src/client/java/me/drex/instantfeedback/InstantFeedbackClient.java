package me.drex.instantfeedback;

import me.drex.instantfeedback.particle.GoldenDandelionParticle;
import me.drex.instantfeedback.particle.TintedNeedlesProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import me.drex.instantfeedback.particle.CreakingEyesParticle;
import net.minecraft.client.particle.FlameParticle;

public class InstantFeedbackClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(InstantFeedback.CREAKING_EYES, CreakingEyesParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(InstantFeedback.GOLDEN_DANDELION, GoldenDandelionParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(InstantFeedback.TINTED_NEEDLES, TintedNeedlesProvider::new);
        ParticleProviderRegistry.getInstance().register(InstantFeedback.SULFUR_FLAME, FlameParticle.Provider::new);
    }
}
