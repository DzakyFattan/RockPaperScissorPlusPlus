package com.aetherwars.model;

enum LevelSpellType {
    UP, DOWN
}

public class LevelSpell extends Spell {

    private LevelSpellType level_type;

    public LevelSpell(String name, String description, int mana, LevelSpellType level_type) {
        super(name, description, SpellType.LVL, mana, 0);
        this.level_type = level_type;
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
