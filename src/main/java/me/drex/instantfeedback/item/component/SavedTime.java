package me.drex.instantfeedback.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record SavedTime(float sunAngle) implements TooltipProvider {

    public static final Codec<SavedTime> CODEC = ExtraCodecs.floatRange(0, 360).xmap(SavedTime::new, SavedTime::sunAngle);
    public static final StreamCodec<ByteBuf, SavedTime> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, SavedTime::sunAngle, SavedTime::new);
    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        float approxHour = (((sunAngle / 360) * 24) + 12) % 24;

        int hour = (int) Math.floor(approxHour);
        int minute = (int) Math.floor((approxHour - hour) * 60);
        consumer.accept(Component.translatable("item.instantfeedback.saved_time", Component.literal(String.format("%02d:%02d", hour, minute))).withStyle(ChatFormatting.GRAY));
    }
}
