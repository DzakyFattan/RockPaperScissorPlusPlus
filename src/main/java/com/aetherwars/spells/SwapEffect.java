package com.aetherwars.spells;

public class SwapEffect implements TemporarySpell {
    private int durationLeft;
    private boolean isPermanent;

    public SwapEffect(int duration) {
        this.durationLeft = duration;
        this.isPermanent = duration == 0;
    }

    public void tick() {
        if (durationLeft > 0 && !isPermanent) {
            durationLeft--;
        }
    }

    public int getDurationLeft() {
        if (isPermanent) {
            return 0;
        }
        return durationLeft;
    }

    public void addDuration(int duration) {
        if (isPermanent) {
            return;
        }
        if (duration == 0) {
            isPermanent = true;
            this.durationLeft = 0;
            return;
        }
        this.durationLeft += duration;
    }

    public boolean isActive() {
        return durationLeft > 0 || isPermanent;
    }
}
