package me.drex.instantfeedback.config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.util.ExtraCodecs;
import org.jspecify.annotations.Nullable;

import net.minecraft.resources.RegistryOps;

import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;

public record ConfigEnabledResourceCondition(String option) implements ResourceCondition {

    public static final MapCodec<ConfigEnabledResourceCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        ExtraCodecs.NON_EMPTY_STRING.fieldOf("options").forGetter(ConfigEnabledResourceCondition::option)
    ).apply(instance, ConfigEnabledResourceCondition::new));

    public static final ResourceConditionType<ConfigEnabledResourceCondition> TYPE = ResourceConditionType.create(InstantFeedback.id("config"), ConfigEnabledResourceCondition.CODEC);
    @Override
    public ResourceConditionType<?> getType() {
        return TYPE;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoLookup registryInfo) {
        return ConfigManager.enabledFeatures().contains(option);
    }

}
