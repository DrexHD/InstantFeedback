package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    protected ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.CARVED_PALE_PUMPKIN);
        this.dropSelf(ModBlocks.PALE_PUMPKIN);
        this.dropSelf(ModBlocks.PALE_ROSE);
        this.dropSelf(ModBlocks.PALE_BUSH);
        this.add(ModBlocks.TALL_PALE_BUSH, block -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
    }
}
