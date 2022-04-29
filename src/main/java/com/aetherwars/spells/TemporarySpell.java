package com.aetherwars.spells;

public interface TemporarySpell {
    int getDurationLeft();

    void tick();

    boolean isActive();
}
