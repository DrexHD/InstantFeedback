package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
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

                shaped(RecipeCategory.DECORATIONS, ModItems.SULFUR_TORCH, 4)
                    .define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
                    .define('#', Items.STICK)
                    .define('S', Blocks.POTENT_SULFUR)
                    .pattern("X")
                    .pattern("#")
                    .pattern("S")
                    .unlockedBy("has_potent_sulfur", has(Blocks.POTENT_SULFUR))
                    .save(output);

                shaped(RecipeCategory.DECORATIONS, ModItems.SULFUR_LANTERN)
                    .define('#', ModItems.SULFUR_TORCH)
                    .define('X', Items.IRON_NUGGET)
                    .pattern("XXX")
                    .pattern("X#X")
                    .pattern("XXX")
                    .unlockedBy("has_sulfur_torch", has(ModItems.SULFUR_TORCH))
                    .save(output);

                shaped(RecipeCategory.DECORATIONS, ModItems.SULFUR_CAMPFIRE)
                    .define('#', Blocks.POTENT_SULFUR)
                    .define('L', ItemTags.LOGS)
                    .define('S', Items.STICK)
                    .pattern(" S ")
                    .pattern("S#S")
                    .pattern("LLL")
                    .unlockedBy("has_potent_sulfur", has(Blocks.POTENT_SULFUR))
                    .save(output);

                shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2)
                    .requires(Items.POTENT_SULFUR)
                    .requires(Items.CHARCOAL, 2)
                    .unlockedBy("has_potent_sulfur", has(Blocks.POTENT_SULFUR))
                    .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "InstantFeedback Recipes";
    }
}
