package me.drex.instantfeedback.entity;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.PigVariant;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

import static me.drex.instantfeedback.InstantFeedback.MOD_ID;

public class ModPigVariants {
    public static final ResourceKey<PigVariant> MUDDY = createKey("muddy");

    private static ResourceKey<PigVariant> createKey(String path) {
        return ResourceKey.create(Registries.PIG_VARIANT, ResourceLocation.fromNamespaceAndPath(MOD_ID, path));
    }

    public static void bootstrap(BootstrapContext<PigVariant> bootstrapContext) {
        var holderSet = bootstrapContext.lookup(Registries.BIOME).getOrThrow(ConventionalBiomeTags.IS_SWAMP);
        var spawnPrioritySelectors = SpawnPrioritySelectors.single(new BiomeCheck(holderSet), 1);
        bootstrapContext.register(ModPigVariants.MUDDY, new PigVariant(new ModelAndTexture<>(PigVariant.ModelType.NORMAL, ResourceLocation.fromNamespaceAndPath(MOD_ID, "entity/pig/muddy_pig")), spawnPrioritySelectors));
    }
}
