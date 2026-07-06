package me.drex.instantfeedback.item;

import me.drex.instantfeedback.config.ConfigManager;
import me.drex.instantfeedback.item.component.ModDataComponents;
import me.drex.instantfeedback.item.component.SavedTime;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ClockItem extends Item {
    private static final Component SAVED_TIME_CLOCK_NAME = Component.translatable("item.instantfeedback.saved_time_clock");

    public ClockItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(final ItemStack itemStack) {
        return itemStack.has(ModDataComponents.SAVED_TIME) || super.isFoil(itemStack);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!ConfigManager.config().tinyTakeoverClockUtility) return super.use(level, player, hand);
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            SavedTime oldValue = itemStack.remove(ModDataComponents.SAVED_TIME);
            if (oldValue != null) {
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        } else {
            boolean replaceExistingStack = !player.hasInfiniteMaterials() && itemStack.getCount() == 1;
            Float sunAngle = level.environmentAttributes().getValue(EnvironmentAttributes.SUN_ANGLE, player.position());
            SavedTime savedTime = new SavedTime(sunAngle);
            player.sendOverlayMessage(Component.translatable("item.instantfeedback.alarm_clock.set_time", savedTime.formatTime()));
            if (replaceExistingStack) {
                itemStack.set(ModDataComponents.SAVED_TIME, savedTime);
            } else {
                ItemStack savedTimeClock = itemStack.transmuteCopy(Items.CLOCK, 1);
                itemStack.consume(1, player);
                savedTimeClock.set(ModDataComponents.SAVED_TIME, savedTime);
                if (!player.getInventory().add(savedTimeClock)) {
                    player.drop(savedTimeClock, false);
                }
            }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public Component getName(final ItemStack itemStack) {
        return itemStack.has(ModDataComponents.SAVED_TIME) ? SAVED_TIME_CLOCK_NAME : super.getName(itemStack);
    }
}
