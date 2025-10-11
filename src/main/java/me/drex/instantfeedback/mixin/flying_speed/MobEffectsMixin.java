package me.drex.instantfeedback.mixin.flying_speed;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEffects.class)
public abstract class MobEffectsMixin {
    @Definition(id = "MOVEMENT_SPEED", field = "Lnet/minecraft/world/entity/ai/attributes/Attributes;MOVEMENT_SPEED:Lnet/minecraft/core/Holder;")
    @Definition(id = "addAttributeModifier", method = "Lnet/minecraft/world/effect/MobEffect;addAttributeModifier(Lnet/minecraft/core/Holder;Lnet/minecraft/resources/ResourceLocation;DLnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;)Lnet/minecraft/world/effect/MobEffect;")
    @Expression("?.addAttributeModifier(MOVEMENT_SPEED, ?, ?, ?)")
    @WrapOperation(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static MobEffect addFlyingSpeedAttributeModifier(
        MobEffect instance, Holder<Attribute> holder, ResourceLocation resourceLocation, double d,
        AttributeModifier.Operation operation, Operation<MobEffect> original
    ) {
        MobEffect effect = original.call(instance, holder, resourceLocation, d, operation);
        effect.addAttributeModifier(Attributes.FLYING_SPEED, resourceLocation, d, operation);
        return effect;
    }
}
