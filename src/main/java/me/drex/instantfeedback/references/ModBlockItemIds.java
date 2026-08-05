package me.drex.instantfeedback.references;

import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {
    public static final BlockItemId PALE_PUMPKIN = create("pale_pumpkin");
    public static final BlockItemId CARVED_PALE_PUMPKIN = create("carved_pale_pumpkin");
    public static final BlockItemId PALE_ROSE = create("pale_rose");
    public static final BlockItemId PALE_BUSH = create("pale_bush");
    public static final BlockItemId TALL_PALE_BUSH = create("tall_pale_bush");
    public static final BlockItemId CERULEAN_FROGLIGHT = create("cerulean_froglight");
    public static final BlockItemId SULFUR_TORCH = create("sulfur_torch");
    public static final BlockItemId SULFUR_LANTERN = create("sulfur_lantern");
    public static final BlockItemId SULFUR_CAMPFIRE = create("sulfur_campfire");

    private static BlockItemId create(String name) {
        final Identifier id = InstantFeedback.id(name);
        return BlockItemId.create(id, id);
    }
}
