package me.drex.instantfeedback.mixin.call_happy_ghast;

import me.drex.instantfeedback.duck.IHappyGhast;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InstrumentItem.class)
public abstract class InstrumentItemMixin {
    @Inject(method = "play", at = @At("TAIL"))
    private static void callGhasts(Level level, Player player, Instrument instrument, CallbackInfo ci) {
        for (HappyGhast happyGhast : level.getEntitiesOfClass(HappyGhast.class, player.getBoundingBox().inflate(64))) {
            ((IHappyGhast)happyGhast).instantfeedback$setCallerPosition(player.position());
        }
    }
}
