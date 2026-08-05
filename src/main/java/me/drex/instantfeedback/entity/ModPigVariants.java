package me.drex.instantfeedback.entity;

import me.drex.instantfeedback.InstantFeedback;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

public class ModPigVariants {
    public static final ResourceKey<PigVariant> MUDDY = createKey("muddy");

    private static ResourceKey<PigVariant> createKey(String path) {
        return ResourceKey.create(Registries.PIG_VARIANT, InstantFeedback.id(path));
    }

    public static void bootstrap(BootstrapContext<PigVariant> bootstrapContext) {
        var holderSet = bootstrapContext.lookup(Registries.BIOME).getOrThrow(ConventionalBiomeTags.IS_SWAMP);
        var spawnPrioritySelectors = SpawnPrioritySelectors.single(new BiomeCheck(holderSet), 1);
        Identifier textureId = InstantFeedback.id("entity/pig/muddy_pig");
        Identifier babyTextureId = InstantFeedback.id("entity/pig/muddy_pig_baby");

        bootstrapContext.register(ModPigVariants.MUDDY, new PigVariant(new ModelAndTexture<>(PigVariant.ModelType.NORMAL, textureId), new ClientAsset.ResourceTexture(babyTextureId), spawnPrioritySelectors));
    }
}
