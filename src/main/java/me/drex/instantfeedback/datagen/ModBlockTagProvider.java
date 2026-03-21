package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.block.ModBlockTags;
import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.valueLookupBuilder(BlockTags.ENDERMAN_HOLDABLE)
            .add(ModBlocks.PALE_PUMPKIN)
            .add(ModBlocks.CARVED_PALE_PUMPKIN);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add(ModBlocks.PALE_PUMPKIN)
            .add(ModBlocks.CARVED_PALE_PUMPKIN)
            .add(ModBlocks.PALE_BUSH, ModBlocks.TALL_PALE_BUSH);

        this.valueLookupBuilder(BlockTags.SWORD_EFFICIENT)
            .add(ModBlocks.PALE_PUMPKIN)
            .add(ModBlocks.CARVED_PALE_PUMPKIN)
            .add(ModBlocks.PALE_BUSH, ModBlocks.TALL_PALE_BUSH);

        this.valueLookupBuilder(BlockTags.BEE_ATTRACTIVE)
            .add(ModBlocks.PALE_ROSE);

        this.valueLookupBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.PALE_ROSE);

        this.valueLookupBuilder(BlockTags.FLOWER_POTS)
            .add(ModBlocks.POTTED_PALE_BUSH)
            .add(ModBlocks.POTTED_TALL_PALE_BUSH)
            .add(ModBlocks.POTTED_PALE_ROSE)
            .add(ModBlocks.POTTED_CACTUS_FLOWER)
            .add(ModBlocks.POTTED_ROSE_BUSH)
            .add(ModBlocks.POTTED_PEONY)
            .add(ModBlocks.POTTED_LILAC)
            .add(ModBlocks.POTTED_SUNFLOWER)
            .add(ModBlocks.POTTED_PITCHER_PLANT);

        this.valueLookupBuilder(BlockTags.REPLACEABLE_BY_TREES)
                .add(ModBlocks.PALE_BUSH, ModBlocks.TALL_PALE_BUSH);

        this.valueLookupBuilder(ModBlockTags.LEAVES_NEEDLES)
            .add(Blocks.SPRUCE_LEAVES);

        TagAppender<ResourceKey<Block>, Block> builder = this.builder(BlockTags.REPLACEABLE);
        wrapperLookup.lookupOrThrow(Registries.BLOCK)
            .filterElements(block -> block.defaultBlockState().canBeReplaced())
            .listElementIds()
            .filter(key -> key.identifier().getNamespace().equals(InstantFeedback.MOD_ID))
            .forEach(builder::add);
    }
}
