package me.drex.instantfeedback.item;

import me.drex.instantfeedback.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {

    public static final Item PALE_PUMPKIN = registerBlock(ModBlocks.PALE_PUMPKIN);
    public static final Item CARVED_PALE_PUMPKIN = registerBlock(ModBlocks.CARVED_PALE_PUMPKIN);
    public static final Item PALE_ROSE = registerBlock(ModBlocks.PALE_ROSE);
    public static final Item PALE_BUSH = registerBlock(ModBlocks.PALE_BUSH);
    public static final Item TALL_PALE_BUSH = registerBlock(ModBlocks.TALL_PALE_BUSH);
    public static final Item CERULEAN_FROGLIGHT = registerBlock(ModBlocks.CERULEAN_FROGLIGHT);

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.insertAfter(Items.JACK_O_LANTERN, PALE_PUMPKIN);
                    itemGroup.insertAfter(PALE_PUMPKIN, CARVED_PALE_PUMPKIN);
                    itemGroup.insertAfter(Items.OPEN_EYEBLOSSOM, PALE_ROSE);
                    itemGroup.insertAfter(Items.PALE_HANGING_MOSS, PALE_BUSH);
                    itemGroup.insertAfter(PALE_BUSH, TALL_PALE_BUSH);
                    itemGroup.insertAfter(Blocks.VERDANT_FROGLIGHT, CERULEAN_FROGLIGHT);
                });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.insertAfter(Blocks.VERDANT_FROGLIGHT, CERULEAN_FROGLIGHT);
                });
    }


    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> resourceKey) {
        return ResourceKey.create(Registries.ITEM, resourceKey.identifier());
    }

    public static Item registerBlock(Block block) {
        return registerBlock(block, BlockItem::new);
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction) {
        return registerBlock(block, biFunction, new Item.Properties());
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction, Item.Properties properties) {
        return registerItem(
            blockIdToItemId(block.builtInRegistryHolder().key()), propertiesx -> biFunction.apply(block, propertiesx), properties.useBlockDescriptionPrefix()
        );
    }

    public static Item registerItem(ResourceKey<Item> resourceKey, Function<Item.Properties, Item> function, Item.Properties properties) {
        Item item = function.apply(properties.setId(resourceKey));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

}
