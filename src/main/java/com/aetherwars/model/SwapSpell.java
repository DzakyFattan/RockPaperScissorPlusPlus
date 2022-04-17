package com.aetherwars.model;

public class SwapSpell extends Spell {

    public SwapSpell(String name, String description, int mana, int duration) {
        super(name, description, SpellType.SWAP, mana, duration);
    }

}
