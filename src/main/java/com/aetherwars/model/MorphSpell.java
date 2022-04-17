package com.aetherwars.model;

public class MorphSpell extends Spell {

    int target;

    public MorphSpell(String name, String description, String image_path, int mana, int target) {
        super(name, description, image_path, SpellType.MORPH, mana, 0);
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
