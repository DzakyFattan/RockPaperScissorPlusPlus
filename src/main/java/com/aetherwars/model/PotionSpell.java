package com.aetherwars.model;

public class PotionSpell extends Spell {

    private int attack_change;
    private int health_change;

    public PotionSpell(String name, String description, int mana,
                       int duration, int attack_change, int health_change) {
        super(name, description, SpellType.PTN, mana, duration);
        this.attack_change = attack_change;
        this.health_change = health_change;
    }

    // Setters
    public void setAttackChange(int attack_change) {
        this.attack_change = attack_change;
    }

    public void setHealthChange(int health_change) {
        this.health_change = health_change;
    }

    // Getters
    public int getAttackChange() {
        return this.attack_change;
    }

    public int getHealthChange() {
        return this.health_change;
    }

}
