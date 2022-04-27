package com.aetherwars.spells;

import com.aetherwars.model.Card;

public abstract class Spell extends Card {

    private int duration;
    private SpellType type;

    public Spell(){
        super();
        this.type = SpellType.PTN;
        this.duration = 0;
    }

    public Spell(String name, String description, String image_path, SpellType type, int mana, int duration) {
        super(name, description, image_path, mana);
        this.type = type;
        this.duration = duration;
    }

    // setters
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setType(SpellType type) {
        this.type = type;
    }

    // getters
    public int getDuration() {
        return this.duration;
    }

    public SpellType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return  " Spell{name=" + this.getName() + ", duration=" + duration + ", type=" + type + '}';
    }

    // unused override
    public abstract String toSpecString();
}
