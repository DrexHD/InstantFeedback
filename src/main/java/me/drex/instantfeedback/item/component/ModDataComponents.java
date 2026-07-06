package me.drex.instantfeedback.item.component;

import me.drex.instantfeedback.InstantFeedback;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<SavedTime> SAVED_TIME = register(
        "saved_time", b -> b.persistent(SavedTime.CODEC).networkSynchronized(SavedTime.STREAM_CODEC)
    );

    public static void initialize() {
        ItemComponentTooltipProviderRegistry.addFirst(ModDataComponents.SAVED_TIME);
    }

    private static <T> DataComponentType<T> register(String path, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(InstantFeedback.MOD_ID, path), (builder.apply(DataComponentType.builder())).build());
    }
}
