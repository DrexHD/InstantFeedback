package me.drex.instantfeedback;

import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.renderer.RenderType;
import particle.CreakingEyesParticle;

public class InstantFeedbackClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(InstantFeedback.CREAKING_EYES, CreakingEyesParticle.Provider::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CARVED_PALE_PUMPKIN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_ROSE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_BUSH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_PALE_BUSH, RenderType.cutout());
    }
}