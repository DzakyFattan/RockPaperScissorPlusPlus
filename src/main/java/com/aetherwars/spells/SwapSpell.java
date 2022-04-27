package com.aetherwars.spells;

public class SwapSpell extends Spell {

    public SwapSpell(String name, String description, String image_path, int mana, int duration) {
        super(name, description, image_path, SpellType.SWAP, mana, duration);
    }

    public SwapSpell(String[] data) {
        super(data[1], data[2], data[3], SpellType.SWAP, Integer.parseInt(data[5]), Integer.parseInt(data[4]));
    }

    @Override
    public String toSpecString() {
        return "ATK<->HP";
    }
}
