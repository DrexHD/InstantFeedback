package me.drex.instantfeedback.mixin.clock;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.drex.instantfeedback.item.component.ModDataComponents;
import me.drex.instantfeedback.item.component.SavedTime;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity {

    @Shadow
    public abstract ItemStack getItem();

    @Unique
    private float instantfeedback$lastSunAngle = -1;

    @Unique
    private boolean instantfeedback$triggeredThisTick = false;

    protected ItemFrameMixin(EntityType<? extends HangingEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        if (instantfeedback$triggeredThisTick) {
            this.level().updateNeighbourForOutputSignal(this.pos, Blocks.AIR);
            instantfeedback$triggeredThisTick = false;
        }


        ItemStack item = getItem();
        SavedTime savedTime = item.get(ModDataComponents.SAVED_TIME);

        if (savedTime != null) {
            float targetAngle = savedTime.sunAngle();
            float currentAngle = level().environmentAttributes()
                .getValue(EnvironmentAttributes.SUN_ANGLE, position());

            if (instantfeedback$lastSunAngle >= 0) {
                float delta = (currentAngle - instantfeedback$lastSunAngle + 360.0F) % 360.0F;

                float targetDelta = (targetAngle - instantfeedback$lastSunAngle + 360.0F) % 360.0F;

                if (targetDelta > 0.0F && targetDelta <= delta) {
                    instantfeedback$triggeredThisTick = true;
                    this.level().updateNeighbourForOutputSignal(this.pos, Blocks.AIR);
                }
            }
            instantfeedback$lastSunAngle = currentAngle;
        } else {
            instantfeedback$lastSunAngle = -1;
        }

        super.tick();
    }

    @ModifyReturnValue(method = "getAnalogOutput", at = @At("RETURN"))
    public int instantfeedback$getAnalogOutput(int original) {
        ItemStack item = getItem();
        if (item.has(ModDataComponents.SAVED_TIME)) {
            if (instantfeedback$triggeredThisTick) {
                return 15;
            }
            return 0;
        }
        return original;
    }
}
