package me.drex.instantfeedback;

import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.item.ModItems;
import me.drex.instantfeedback.worldgen.ModVegetationPlacements;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.WinterDropBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstantFeedback implements ModInitializer {

    public static final String MOD_ID = "instantfeedback";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final SimpleParticleType CREAKING_EYES = FabricParticleTypes.simple();

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "creaking_eyes"), CREAKING_EYES);
        ModBlocks.initialize();
        ModItems.initialize();
        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_garden_remove_spawn"))
            .add(ModificationPhase.REMOVALS, context -> context.getBiomeKey() == WinterDropBiomes.PALE_GARDEN, context -> {
                context.getSpawnSettings().clearSpawns();
            });

        BiomeModifications.addFeature(
            context -> context.getBiomeKey() == WinterDropBiomes.PALE_GARDEN,
            GenerationStep.Decoration.VEGETAL_DECORATION,
            ModVegetationPlacements.PATCH_PALE_PUMPKIN
        );
        BiomeModifications.addFeature(
            context -> context.getBiomeKey() == WinterDropBiomes.PALE_GARDEN,
            GenerationStep.Decoration.VEGETAL_DECORATION,
            ModVegetationPlacements.PILE_PALE_LEAVES
        );
        BiomeModifications.addFeature(
            context -> context.getBiomeKey() == WinterDropBiomes.PALE_GARDEN,
            GenerationStep.Decoration.VEGETAL_DECORATION,
            ModVegetationPlacements.PALE_VEGETATION
        );
    }
}