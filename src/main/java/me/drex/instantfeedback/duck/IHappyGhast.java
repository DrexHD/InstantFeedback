package me.drex.instantfeedback.duck;

import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public interface IHappyGhast {
    void instantfeedback$setCallerPosition(Vec3 vec3);
    @Nullable
    Vec3 instantfeedback$getCallerPosition();
}
