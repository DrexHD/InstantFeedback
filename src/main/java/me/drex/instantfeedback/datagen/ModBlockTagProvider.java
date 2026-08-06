package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.block.ModBlockTags;
import me.drex.instantfeedback.references.ModBlockIds;
import me.drex.instantfeedback.references.ModBlockItemIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.builder(BlockTags.ENDERMAN_HOLDABLE)
            .add(ModBlockItemIds.PALE_PUMPKIN)
            .add(ModBlockItemIds.CARVED_PALE_PUMPKIN);

        this.builder(BlockTags.MINEABLE_WITH_AXE)
            .add(ModBlockItemIds.PALE_PUMPKIN)
            .add(ModBlockItemIds.CARVED_PALE_PUMPKIN)
            .add(ModBlockItemIds.PALE_BUSH, ModBlockItemIds.TALL_PALE_BUSH);

        this.builder(BlockTags.SWORD_EFFICIENT)
            .add(ModBlockItemIds.PALE_PUMPKIN)
            .add(ModBlockItemIds.CARVED_PALE_PUMPKIN)
            .add(ModBlockItemIds.PALE_BUSH, ModBlockItemIds.TALL_PALE_BUSH)
            .add(ModBlockItemIds.GLOWING_VINES)
        ;

        this.builder(BlockTags.BEE_ATTRACTIVE)
            .add(ModBlockItemIds.PALE_ROSE);

        this.builder(BlockTags.SMALL_FLOWERS)
                .add(ModBlockItemIds.PALE_ROSE);

        this.builder(BlockTags.FLOWER_POTS)
            .add(ModBlockIds.POTTED_PALE_BUSH)
            .add(ModBlockIds.POTTED_TALL_PALE_BUSH)
            .add(ModBlockIds.POTTED_PALE_ROSE)
            .add(ModBlockIds.POTTED_CACTUS_FLOWER)
            .add(ModBlockIds.POTTED_ROSE_BUSH)
            .add(ModBlockIds.POTTED_PEONY)
            .add(ModBlockIds.POTTED_LILAC)
            .add(ModBlockIds.POTTED_SUNFLOWER)
            .add(ModBlockIds.POTTED_PITCHER_PLANT);

        this.builder(BlockTags.REPLACEABLE_BY_TREES)
                .add(ModBlockItemIds.PALE_BUSH, ModBlockItemIds.TALL_PALE_BUSH);

        this.builder(ModBlockTags.LEAVES_NEEDLES)
            .add(BlockItemIds.SPRUCE_LEAVES);

        this.builder(BlockTags.CAMPFIRES)
            .add(ModBlockItemIds.SULFUR_CAMPFIRE);

        this.builder(BlockTags.SHEARS_MINOR_BREAKING_SPEED)
            .add(ModBlockItemIds.GLOWING_VINES);

        this.builder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            .add(ModBlockItemIds.GLOWING_VINES);

        var builder = this.builder(BlockTags.REPLACEABLE);
        wrapperLookup.lookupOrThrow(Registries.BLOCK)
            .filterElements(block -> block.defaultBlockState().canBeReplaced())
            .listElementIds()
            .filter(key -> key.identifier().getNamespace().equals(InstantFeedback.MOD_ID))
            .forEach(builder::add);
    }
}
