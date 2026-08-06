package me.drex.instantfeedback.mixin.sulfur.campfire;

import com.llamalad7.mixinextras.sugar.Local;
import me.drex.instantfeedback.block.ModBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CampfireBlockEntity.class)
public abstract class CampfireBlockEntityMixin {
    @ModifyArg(
        method = "cookTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/Containers;dropItemStack(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"
        ),
        index = 4
    )
    private static ItemStack nauseaFood(ItemStack itemStack, @Local(argsOnly = true) BlockState state) {
        if (state.is(ModBlocks.SULFUR_CAMPFIRE)) {
            itemStack.update(DataComponents.CONSUMABLE, null, consumable -> {
                if (consumable == null) return null;
                var builder = Consumable.builder()
                    .consumeSeconds(consumable.consumeSeconds())
                    .animation(consumable.animation())
                    .sound(consumable.sound())
                    .hasConsumeParticles(consumable.hasConsumeParticles());
                consumable.onConsumeEffects().forEach(builder::onConsume);
                builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 0)));
                return builder.build();
            });
        }
        return itemStack;
    }
}
