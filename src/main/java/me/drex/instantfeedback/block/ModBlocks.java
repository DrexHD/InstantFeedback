package me.drex.instantfeedback.block;

import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.references.ModBlockIds;
import me.drex.instantfeedback.references.ModBlockItemIds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

public class ModBlocks {

    public static final Block SULFUR_TORCH = register(
        ModBlockItemIds.SULFUR_TORCH,
        properties -> new TorchBlock(InstantFeedback.SULFUR_FLAME, properties),
        BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH)
    );

    public static final Block SULFUR_WALL_TORCH = register(
        ModBlockIds.SULFUR_WALL_TORCH,
        properties -> new WallTorchBlock(InstantFeedback.SULFUR_FLAME, properties),
        BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_WALL_TORCH)
    );

    public static final Block SULFUR_LANTERN = register(
        ModBlockItemIds.SULFUR_LANTERN,
        LanternBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)
    );

    public static final Block SULFUR_CAMPFIRE = register(
        ModBlockItemIds.SULFUR_CAMPFIRE,
        properties -> new CampfireBlock(true, 2, properties),
        BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_CAMPFIRE)
    );

    public static final Block SULFUR_FIRE = register(
        ModBlockIds.SULFUR_FIRE,
        SulfurFireBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_FIRE)
    );

    public static final Block PALE_PUMPKIN = register(
        ModBlockItemIds.PALE_PUMPKIN,
        PalePumpkinBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .instrument(NoteBlockInstrument.DIDGERIDOO)
            .strength(1.0F)
            .sound(SoundType.WOOD)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block CARVED_PALE_PUMPKIN = register(
        ModBlockItemIds.CARVED_PALE_PUMPKIN,
        CarvedPalePumpkinBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .strength(1.0F)
            .sound(SoundType.WOOD)
            .isValidSpawn(Blocks::always)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block PALE_ROSE = register(
        ModBlockItemIds.PALE_ROSE,
        properties -> new FlowerBlock(MobEffects.GLOWING, 5.0F, properties),
        BlockBehaviour.Properties.of()
            .mapColor(DyeColor.WHITE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY));

    public static final Block PALE_BUSH = register(
        ModBlockItemIds.PALE_BUSH,
        DryVegetationBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN)
            .replaceable()
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block TALL_PALE_BUSH = register(
        ModBlockItemIds.TALL_PALE_BUSH,
        TallFlowerBlock::new,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN)
            .replaceable()
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block CERULEAN_FROGLIGHT = register(
            ModBlockItemIds.CERULEAN_FROGLIGHT,
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .strength(0.3f)
                    .sound(SoundType.FROGLIGHT)
                    .lightLevel(blockStatex -> 15)
    );

    public static final Block POTTED_PALE_BUSH = register(ModBlockIds.POTTED_PALE_BUSH,
        properties -> new FlowerPotBlock(PALE_BUSH, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_TALL_PALE_BUSH = register(ModBlockIds.POTTED_TALL_PALE_BUSH,
        properties -> new FlowerPotBlock(TALL_PALE_BUSH, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_PALE_ROSE = register(ModBlockIds.POTTED_PALE_ROSE,
        properties -> new FlowerPotBlock(PALE_ROSE, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_CACTUS_FLOWER = register(ModBlockIds.POTTED_CACTUS_FLOWER,
        properties -> new FlowerPotBlock(Blocks.CACTUS_FLOWER, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_ROSE_BUSH = register(ModBlockIds.POTTED_ROSE_BUSH,
        properties -> new FlowerPotBlock(Blocks.ROSE_BUSH, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_PEONY = register(ModBlockIds.POTTED_PEONY,
        properties -> new FlowerPotBlock(Blocks.PEONY, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_LILAC = register(ModBlockIds.POTTED_LILAC,
        properties -> new FlowerPotBlock(Blocks.LILAC, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_SUNFLOWER = register(ModBlockIds.POTTED_SUNFLOWER,
        properties -> new FlowerPotBlock(Blocks.SUNFLOWER, properties), Blocks.flowerPotProperties());

    public static final Block POTTED_PITCHER_PLANT = register(ModBlockIds.POTTED_PITCHER_PLANT,
        properties -> new FlowerPotBlock(Blocks.PITCHER_PLANT, properties), Blocks.flowerPotProperties());

    public static void initialize() {
        BlockEntityTypes.CAMPFIRE.addValidBlock(SULFUR_CAMPFIRE);
    }

    public static Block register(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        Block block = function.apply(properties.setId(resourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    public static Block register(ResourceKey<Block> resourceKey, BlockBehaviour.Properties properties) {
        return register(resourceKey, Block::new, properties);
    }

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        return register(id.block(), function, properties);
    }

    private static Block register(BlockItemId id, BlockBehaviour.Properties properties) {
        return register(id.block(), Block::new, properties);
    }

}
