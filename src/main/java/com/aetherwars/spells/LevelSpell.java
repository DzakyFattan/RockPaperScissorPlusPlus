package com.aetherwars.spells;

enum LevelSpellType {
    UP, DOWN
}

public class LevelSpell extends Spell {

    private LevelSpellType level_type;

    public LevelSpell(String name, String description, String image_path, int mana, LevelSpellType level_type) {
        super(name, description, image_path, SpellType.LVL, mana, 0);
        this.level_type = level_type;
    }

    public LevelSpell(String[] data) {
        super(data[1], data[2], data[3], SpellType.LVL, Integer.parseInt(data[5]), Integer.parseInt(data[6]));
        this.level_type = LevelSpellType.valueOf(data[4]);
    }

    // Setter
    public void setLevelType(LevelSpellType level_type) {
        this.level_type = level_type;
    }

    // Getter
    public LevelSpellType getLevelType() {
        return this.level_type;
    }

}
