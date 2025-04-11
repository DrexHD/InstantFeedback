package me.drex.instantfeedback.block;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> LEAVES_NEEDLES = create("leaves/needles");

    private ModBlockTags() {
    }


    private static TagKey<Block> create(String string) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, string));
    }
}
