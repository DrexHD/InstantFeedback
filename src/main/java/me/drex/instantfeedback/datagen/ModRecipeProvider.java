package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                SuspiciousEffectHolder suspiciousEffectHolder = SuspiciousEffectHolder.tryGet(ModBlocks.PALE_ROSE);
                assert suspiciousEffectHolder != null;
                suspiciousStew(ModBlocks.PALE_ROSE, suspiciousEffectHolder);
            }

            public void suspiciousStew(ItemLike item, SuspiciousEffectHolder suspiciousEffectHolder) {
                ItemStack itemStack = new ItemStack(
                    Items.SUSPICIOUS_STEW.builtInRegistryHolder(),
                    1,
                    DataComponentPatch.builder().set(DataComponents.SUSPICIOUS_STEW_EFFECTS, suspiciousEffectHolder.getSuspiciousEffects()).build()
                );
                this.shapeless(RecipeCategory.FOOD, itemStack)
                    .requires(Items.BOWL)
                    .requires(Items.BROWN_MUSHROOM)
                    .requires(Items.RED_MUSHROOM)
                    .requires(item)
                    .group("suspicious_stew")
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, getItemName(itemStack.getItem()) + "_from_" + getItemName(item));
            }
        };
    }

    @Override
    public String getName() {
        return "InstantFeedback Recipes";
    }
}
