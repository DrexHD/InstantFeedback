package me.drex.instantfeedback.ducks;

public interface ICreakingRenderState {
    void instantfeedback$setTicksSinceLastMove(long ticksSinceLastMove);
    long instantfeedback$getTicksSinceLastMove();
}
