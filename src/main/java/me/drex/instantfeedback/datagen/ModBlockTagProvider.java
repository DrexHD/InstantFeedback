package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE)
            .add(ModBlocks.PALE_PUMPKIN)
            .add(ModBlocks.CARVED_PALE_PUMPKIN);

        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add(ModBlocks.PALE_PUMPKIN)
            .add(ModBlocks.CARVED_PALE_PUMPKIN)
            .add(ModBlocks.PALE_BUSH, ModBlocks.TALL_PALE_BUSH);

        this.getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
            .add(ModBlocks.PALE_PUMPKIN)
            .add(ModBlocks.CARVED_PALE_PUMPKIN)
            .add(ModBlocks.PALE_BUSH, ModBlocks.TALL_PALE_BUSH);

        this.getOrCreateTagBuilder(BlockTags.BEE_ATTRACTIVE)
            .add(ModBlocks.PALE_ROSE);

        this.getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.PALE_ROSE);

        this.getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
                .add(ModBlocks.PALE_BUSH, ModBlocks.TALL_PALE_BUSH);

        FabricTagBuilder builder = this.getOrCreateTagBuilder(BlockTags.REPLACEABLE);
        wrapperLookup.lookupOrThrow(Registries.BLOCK)
            .filterElements(block -> block.defaultBlockState().canBeReplaced())
            .listElementIds()
            .filter(key -> key.location().getNamespace().equals(InstantFeedback.MOD_ID))
            .forEach(builder::add);
    }
}
