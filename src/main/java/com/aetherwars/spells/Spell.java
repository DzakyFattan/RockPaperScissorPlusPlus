package com.aetherwars.spells;

import com.aetherwars.model.Card;

public abstract class Spell extends Card {

    private int duration;
    private int durationLeft;
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
        this.durationLeft = duration;
    }

    // Reduces the duration of the spell, as in for each round that has passed
    public void tick(){
        if(this.durationLeft > 0 && this.duration != 0){
            durationLeft--;
        }
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

    public int getDurationLeft() {
        return this.durationLeft;
    }

    public SpellType getType() {
        return this.type;
    }

    @Override
    public String getCardType() {
        return "Spell";
    }

    @Override
    public String toString() {
        return  " Spell{name=" + this.getName() + ", duration=" + duration + ", type=" + type + '}';
    }

    // unused override
    public abstract String toSpecString();
}
