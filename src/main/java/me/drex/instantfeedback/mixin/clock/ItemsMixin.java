package me.drex.instantfeedback.mixin.clock;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import me.drex.instantfeedback.item.ClockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Shadow
    private static Item registerItem(String name, Function<Item.Properties, Item> itemFactory) {
        return null;
    }

    @Definition(id = "CLOCK", field = "Lnet/minecraft/world/item/Items;CLOCK:Lnet/minecraft/world/item/Item;")
    @Definition(id = "registerItem", method = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;)Lnet/minecraft/world/item/Item;")
    @Expression("CLOCK = @(registerItem(?))")
    @Redirect(
        method = "<clinit>",
        at = @At(
            value = "MIXINEXTRAS:EXPRESSION"
        )
    )
    private static Item addClockItemClass(String name) {
        return registerItem(name, ClockItem::new);
    }
}
