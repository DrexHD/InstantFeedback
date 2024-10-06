package me.drex.instantfeedback.worldgen;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

public class ModVegetationPlacements {

    public static final PlacementModifier HEIGHTMAP_NO_LEAVES = HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES);

    public static final ResourceKey<PlacedFeature> PATCH_PALE_PUMPKIN = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, "patch_pale_pumpkin"));
    public static final ResourceKey<PlacedFeature> PILE_PALE_LEAVES = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, "pile_pale_leaves"));
    public static final ResourceKey<PlacedFeature> PALE_VEGETATION = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, "pale_vegetation"));

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
        var palePumpkinPatch = holderGetter.getOrThrow(ModVegetationFeatures.PATCH_PALE_PUMPKIN);
        var paleLeavesPile = holderGetter.getOrThrow(ModVegetationFeatures.PILE_PALE_LEAVES);
        var paleVegetation = holderGetter.getOrThrow(ModVegetationFeatures.PALE_VEGETATION);
        PlacementUtils.register(
            bootstrapContext, ModVegetationPlacements.PATCH_PALE_PUMPKIN, palePumpkinPatch, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
        );
        PlacementUtils.register(
            bootstrapContext, ModVegetationPlacements.PILE_PALE_LEAVES, paleLeavesPile, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), HEIGHTMAP_NO_LEAVES, BiomeFilter.biome()
        );
        PlacementUtils.register(
            bootstrapContext,
            ModVegetationPlacements.PALE_VEGETATION,
            paleVegetation,
            NoiseThresholdCountPlacement.of(-0.8, 1, 5),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        );
    }

}
