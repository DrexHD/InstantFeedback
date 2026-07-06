package me.drex.instantfeedback.references;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class ModBlockIds {

    public static final ResourceKey<Block> POTTED_PALE_BUSH = create("potted_pale_bush");
    public static final ResourceKey<Block> POTTED_TALL_PALE_BUSH = create("potted_tall_pale_bush");
    public static final ResourceKey<Block> POTTED_PALE_ROSE = create("potted_pale_rose");
    public static final ResourceKey<Block> POTTED_CACTUS_FLOWER = create("potted_cactus_flower");
    public static final ResourceKey<Block> POTTED_ROSE_BUSH = create("potted_rose_bush");
    public static final ResourceKey<Block> POTTED_PEONY = create("potted_peony");
    public static final ResourceKey<Block> POTTED_LILAC = create("potted_lilac");
    public static final ResourceKey<Block> POTTED_SUNFLOWER = create("potted_sunflower");
    public static final ResourceKey<Block> POTTED_PITCHER_PLANT = create("potted_pitcher_plant");

    private static ResourceKey<Block> create(String name) {
        return ResourceKey.create(Registries.BLOCK, InstantFeedback.id(name));
    }
}
