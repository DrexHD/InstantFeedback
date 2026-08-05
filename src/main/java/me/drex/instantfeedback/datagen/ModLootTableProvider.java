package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    protected ModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.CARVED_PALE_PUMPKIN);
        this.dropSelf(ModBlocks.PALE_PUMPKIN);
        this.dropSelf(ModBlocks.PALE_ROSE);
        this.dropSelf(ModBlocks.PALE_BUSH);
        this.add(ModBlocks.TALL_PALE_BUSH, block -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.dropSelf(ModBlocks.CERULEAN_FROGLIGHT);
        this.dropSelf(ModBlocks.SULFUR_TORCH);
        this.add(ModBlocks.SULFUR_WALL_TORCH, block -> this.createSingleItemTable(ModBlocks.SULFUR_TORCH));
        this.dropSelf(ModBlocks.SULFUR_LANTERN);
        this.dropSelf(ModBlocks.SULFUR_CAMPFIRE);
        this.dropPottedContents(ModBlocks.POTTED_PALE_BUSH);
        this.dropPottedContents(ModBlocks.POTTED_TALL_PALE_BUSH);
        this.dropPottedContents(ModBlocks.POTTED_PALE_ROSE);
        this.dropPottedContents(ModBlocks.POTTED_CACTUS_FLOWER);
        this.dropPottedContents(ModBlocks.POTTED_ROSE_BUSH);
        this.dropPottedContents(ModBlocks.POTTED_PEONY);
        this.dropPottedContents(ModBlocks.POTTED_LILAC);
        this.dropPottedContents(ModBlocks.POTTED_SUNFLOWER);
        this.dropPottedContents(ModBlocks.POTTED_PITCHER_PLANT);
    }
}
