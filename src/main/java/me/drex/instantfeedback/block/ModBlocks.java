package me.drex.instantfeedback.block;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {

    public static final Block PALE_PUMPKIN = register(
        "pale_pumpkin",
        PalePumpkinBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .instrument(NoteBlockInstrument.DIDGERIDOO)
            .strength(1.0F)
            .sound(SoundType.WOOD)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block CARVED_PALE_PUMPKIN = register(
        "carved_pale_pumpkin",
        CarvedPalePumpkinBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .strength(1.0F)
            .sound(SoundType.WOOD)
            .isValidSpawn(Blocks::always)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block PALE_ROSE = register(
        "pale_rose",
        properties -> new FlowerBlock(MobEffects.WEAKNESS, 9.0F, properties),
        BlockBehaviour.Properties.of()
            .mapColor(DyeColor.WHITE)
            .noCollission()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY));

    public static final Block PALE_BUSH = register(
        "pale_bush",
        DeadBushBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN)
            .replaceable()
            .noCollission()
            .instabreak()
            .sound(SoundType.GRASS)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block TALL_PALE_BUSH = register(
        "tall_pale_bush",
        TallFlowerBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN)
            .noCollission()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
    );

    public static void initialize() {
    }

    public static Block register(Block block, String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, path);
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM, id, blockItem);
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static Block register(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        Block block = function.apply(properties.setId(resourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    public static Block register(ResourceKey<Block> resourceKey, BlockBehaviour.Properties properties) {
        return register(resourceKey, Block::new, properties);
    }

    private static ResourceKey<Block> blockId(String path) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, path));
    }

    private static Block register(String string, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        return register(blockId(string), function, properties);
    }

    private static Block register(String string, BlockBehaviour.Properties properties) {
        return register(string, Block::new, properties);
    }

}
