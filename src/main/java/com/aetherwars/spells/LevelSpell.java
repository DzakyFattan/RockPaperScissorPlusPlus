package com.aetherwars.spells;

public class LevelSpell extends Spell {

    private LevelSpellType level_type;

    public LevelSpell(String name, String description, String image_path, int mana, LevelSpellType level_type, int level_change) {
        super(name, description, image_path, SpellType.LVL, mana, 0);
        this.level_type = level_type;
    }

    public LevelSpell(String[] data) {
        super(data[1], data[2], data[3], SpellType.LVL, 0, 0);
        this.level_type = LevelSpellType.valueOf(data[4]);
    }

    // Setter
    public void setLevelType(LevelSpellType level_type) {
        this.level_type = level_type;
    }
    public void setManaCost(int mana) {
        super.manaCost = mana;
    }

    // Getter
    public LevelSpellType getLevelType() {
        return this.level_type;
    }

    public static int getLvlSpellManaCost(int characterLevel){
        return (int) Math.ceil((double) characterLevel/2.0);
    }

    @Override
    public String toSpecString() {
        return "LVL " + this.level_type.toString();
    }
}
