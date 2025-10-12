package me.drex.instantfeedback.mixin.call_happy_ghast;

import me.drex.instantfeedback.duck.IHappyGhast;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Animal implements IHappyGhast {

    protected HappyGhastMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private Vec3 instantfeedback$callerPosition;

    @Override
    public void instantfeedback$setCallerPosition(Vec3 instantfeedback$callerPosition) {
        this.instantfeedback$callerPosition = instantfeedback$callerPosition;
    }

    @Override
    public @Nullable Vec3 instantfeedback$getCallerPosition() {
        return instantfeedback$callerPosition;
    }
}
