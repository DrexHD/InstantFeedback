package me.drex.instantfeedback.item;

import me.drex.instantfeedback.config.ConfigManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModCauldronInteraction {
    public static void bootstrap() {
        if (ConfigManager.config().chaseTheSkiesUndyeBundles) {
            for (Item item : BuiltInRegistries.ITEM) {
                if (item instanceof BundleItem bundleItem) {
                    if (bundleItem.equals(Items.BUNDLE)) continue;
                    CauldronInteractions.WATER.put(bundleItem, ModCauldronInteraction::bundleInteraction);
                }
            }
        }
    }

    private static InteractionResult bundleInteraction(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack) {
        if (!level.isClientSide()) {
            ItemStack cleanedBundle = itemStack.transmuteCopy(Items.BUNDLE, 1);
            player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, cleanedBundle, false));
            player.awardStat(Stats.CLEAN_SHULKER_BOX);
            LayeredCauldronBlock.lowerFillLevel(blockState, level, blockPos);
        }
        return InteractionResult.SUCCESS;
    }
}
