package me.drex.instantfeedback.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.FrogVariant;

import static me.drex.instantfeedback.InstantFeedback.MOD_ID;

public class ModFrogs {
    public static ResourceKey<FrogVariant> DARK = createKey("dark");
    public static void inititalize() {
        register(BuiltInRegistries.FROG_VARIANT, DARK , "textures/entity/frog/dark_frog.png");
    }

    private static FrogVariant register(Registry<FrogVariant> registry, ResourceKey<FrogVariant> resourceKey, String string) {
        return Registry.register(registry, resourceKey, new FrogVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, string)));
    }

    private static ResourceKey<FrogVariant> createKey(String string) {
        return ResourceKey.create(Registries.FROG_VARIANT, ResourceLocation.fromNamespaceAndPath(MOD_ID,string));
    }
}
