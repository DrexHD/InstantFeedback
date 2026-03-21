package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                SuspiciousEffectHolder suspiciousEffectHolder = SuspiciousEffectHolder.tryGet(ModBlocks.PALE_ROSE);
                assert suspiciousEffectHolder != null;
                suspiciousStew(ModBlocks.PALE_ROSE.asItem(), suspiciousEffectHolder);
            }
        };
    }

    @Override
    public String getName() {
        return "InstantFeedback Recipes";
    }
}
