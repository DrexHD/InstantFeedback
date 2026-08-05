package me.drex.instantfeedback.entity;

import me.drex.instantfeedback.InstantFeedback;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

public class ModFrogVariants {
    public static final ResourceKey<FrogVariant> DARK = createKey("dark");

    private static ResourceKey<FrogVariant> createKey(String path) {
        return ResourceKey.create(Registries.FROG_VARIANT, InstantFeedback.id(path));
    }

    public static void bootstrap(BootstrapContext<FrogVariant> bootstrapContext) {
        var holderSet = bootstrapContext.lookup(Registries.BIOME).getOrThrow(ConventionalBiomeTags.IS_DARK_FOREST);
        var spawnPrioritySelectors = SpawnPrioritySelectors.single(new BiomeCheck(holderSet), 1);
        bootstrapContext.register(ModFrogVariants.DARK, new FrogVariant(new ClientAsset.ResourceTexture(InstantFeedback.id("entity/frog/dark_frog")), spawnPrioritySelectors));
    }
}
