package me.drex.instantfeedback.item;

import me.drex.instantfeedback.InstantFeedback;
import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {

    public static final Item PALE_PUMPKIN = registerBlock(ModBlocks.PALE_PUMPKIN);
    public static final Item CARVED_PALE_PUMPKIN = registerBlock(ModBlocks.CARVED_PALE_PUMPKIN);
    public static final Item PALE_ROSE = registerBlock(ModBlocks.PALE_ROSE);
    public static final Item PALE_BUSH = registerBlock(ModBlocks.PALE_BUSH);
    public static final Item TALL_PALE_BUSH = registerBlock(ModBlocks.TALL_PALE_BUSH);
    public static final Item CERULEAN_FROGLIGHT = registerBlock(ModBlocks.CERULEAN_FROGLIGHT);

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.JACK_O_LANTERN, PALE_PUMPKIN);
                    itemGroup.addAfter(PALE_PUMPKIN, CARVED_PALE_PUMPKIN);
                    itemGroup.addAfter(Items.OPEN_EYEBLOSSOM, PALE_ROSE);
                    itemGroup.addAfter(Items.PALE_HANGING_MOSS, PALE_BUSH);
                    itemGroup.addAfter(PALE_BUSH, TALL_PALE_BUSH);
                    itemGroup.addAfter(Blocks.PEARLESCENT_FROGLIGHT, CERULEAN_FROGLIGHT);
                });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Blocks.PEARLESCENT_FROGLIGHT, CERULEAN_FROGLIGHT);
                });
    }


    private static Function<Item.Properties, Item> createBlockItemWithCustomItemName(Block block) {
        return properties -> new BlockItem(block, properties.useItemDescriptionPrefix());
    }

    private static ResourceKey<Item> vanillaItemId(String path) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InstantFeedback.MOD_ID, path));
    }

    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> resourceKey) {
        return ResourceKey.create(Registries.ITEM, resourceKey.location());
    }

    public static Item registerBlock(Block block) {
        return registerBlock(block, BlockItem::new);
    }

    public static Item registerBlock(Block block, Item.Properties properties) {
        return registerBlock(block, BlockItem::new, properties);
    }

    public static Item registerBlock(Block block, UnaryOperator<Item.Properties> unaryOperator) {
        return registerBlock(
            block, (blockx, properties) -> new BlockItem(blockx, unaryOperator.apply(properties))
        );
    }

    public static Item registerBlock(Block block, Block... blocks) {
        Item item = registerBlock(block);

        for (Block block2 : blocks) {
            Item.BY_BLOCK.put(block2, item);
        }

        return item;
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction) {
        return registerBlock(block, biFunction, new Item.Properties());
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction, Item.Properties properties) {
        return registerItem(
            blockIdToItemId(block.builtInRegistryHolder().key()), propertiesx -> biFunction.apply(block, propertiesx), properties.useBlockDescriptionPrefix()
        );
    }

    public static Item registerItem(String string, Function<Item.Properties, Item> function) {
        return registerItem(vanillaItemId(string), function, new Item.Properties());
    }

    public static Item registerItem(String string, Function<Item.Properties, Item> function, Item.Properties properties) {
        return registerItem(vanillaItemId(string), function, properties);
    }

    public static Item registerItem(String string, Item.Properties properties) {
        return registerItem(vanillaItemId(string), Item::new, properties);
    }

    public static Item registerItem(String string) {
        return registerItem(vanillaItemId(string), Item::new, new Item.Properties());
    }

    public static Item registerItem(ResourceKey<Item> resourceKey, Function<Item.Properties, Item> function) {
        return registerItem(resourceKey, function, new Item.Properties());
    }

    public static Item registerItem(ResourceKey<Item> resourceKey, Function<Item.Properties, Item> function, Item.Properties properties) {
        Item item = function.apply(properties.setId(resourceKey));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

}
