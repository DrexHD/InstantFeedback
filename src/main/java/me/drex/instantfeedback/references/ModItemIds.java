package me.drex.instantfeedback.references;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItemIds {
    private static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, InstantFeedback.id(name));
    }
}
