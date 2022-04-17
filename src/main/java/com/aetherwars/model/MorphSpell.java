package com.aetherwars.model;

public class MorphSpell extends Spell {

    int target;

    public MorphSpell(String name, String description, int mana, int target) {
        super(name, description, SpellType.MORPH, mana, 0);
        this.target = target;
    }

    // Setter
    public void setTarget(int target) {
        this.target = target;
    }

    // Getter
    public int getTarget() {
        return this.target;
    }

}
