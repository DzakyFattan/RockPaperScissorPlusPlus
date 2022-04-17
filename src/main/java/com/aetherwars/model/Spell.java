package com.aetherwars.model;

public class Spell extends Card{

    private int duration;
    private int mana;
    private SpellType type;

    public Spell(){
        super();
        this.type = SpellType.PTN;
        this.mana = 1;
        this.duration = 0;
    }

    public Spell(String name, String description, SpellType type, int mana, int duration) {
        super(name, description);
        this.type = type;
        this.mana = mana;
        this.duration = duration;
    }

    // setters
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setType(SpellType type) {
        this.type = type;
    }

    // getters
    public int getDuration() {
        return this.duration;
    }

    public int getMana() {
        return this.mana;
    }

    public SpellType getType() {
        return this.type;
    }

}
