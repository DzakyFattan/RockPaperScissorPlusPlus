package com.aetherwars.model;

public class Spell extends Card{
    private int mana;
    private SpellType type;

    public Spell(){
        super();
        this.type = SpellType.PTN;
        this.mana = 1;
    }

    public Spell(String name, String description, SpellType type, int mana) {
        super(name, description);
        this.type = type;
        this.mana = mana;
    }

    // setters
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setType(SpellType type) {
        this.type = type;
    }

    // getters
    public int getMana() {
        return mana;
    }

    public SpellType getType() {
        return type;
    }

}
