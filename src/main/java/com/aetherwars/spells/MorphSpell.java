package com.aetherwars.spells;

public class MorphSpell extends Spell {

    int target;

    public MorphSpell(String name, String description, String image_path, int mana, int target) {
        super(name, description, image_path, SpellType.MORPH, mana, 0);
        this.target = target;
    }

    public MorphSpell(String[] data) {
        super(data[1], data[2], data[3], SpellType.MORPH, Integer.parseInt(data[5]), 0);
        this.target = Integer.parseInt(data[4]);
    }

    // Setter
    public void setTarget(int target) {
        this.target = target;
    }

    // Getter
    public int getTarget() {
        return this.target;
    }

    @Override
    public String toSpecString() {
        return "MORPH";
    }
}
