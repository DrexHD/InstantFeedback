package me.drex.instantfeedback;

import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.particle.TintedNeedlesProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import me.drex.instantfeedback.particle.CreakingEyesParticle;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class InstantFeedbackClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(InstantFeedback.CREAKING_EYES, CreakingEyesParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(InstantFeedback.TINTED_NEEDLES, TintedNeedlesProvider::new);

        BlockRenderLayerMap.putBlock(ModBlocks.CARVED_PALE_PUMPKIN, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PALE_ROSE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PALE_BUSH, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TALL_PALE_BUSH, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_PALE_BUSH, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_TALL_PALE_BUSH, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_PALE_ROSE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_CACTUS_FLOWER, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_ROSE_BUSH, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_PEONY, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_LILAC, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_SUNFLOWER, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_PITCHER_PLANT, ChunkSectionLayer.CUTOUT);
    }
}