package com.aetherwars.spells;

public class SwapSpell extends Spell {

    public SwapSpell(String name, String description, String image_path, int mana, int duration) {
        super(name, description, image_path, SpellType.SWAP, mana, duration);
    }

}
