package com.aetherwars.spells;

public class PotionSpell extends Spell implements TemporarySpell{

    private int attack_change;
    private int health_change;
    private int durationLeft;

    public PotionSpell(String name, String description, String image_path, int mana,
                       int duration, int attack_change, int health_change) {
        super(name, description, image_path, SpellType.PTN, mana, duration);
        this.attack_change = attack_change;
        this.health_change = health_change;
        this.durationLeft = this.duration;
    }

    public PotionSpell(String[] data) {
        super(data[1], data[2], data[3], SpellType.PTN, Integer.parseInt(data[6]), Integer.parseInt(data[7]));
        this.attack_change = Integer.parseInt(data[4]);
        this.health_change = Integer.parseInt(data[5]);
        this.durationLeft = this.duration;
    }

    // Reduces the duration of the spell, as in for each round that has passed
    public void tick(){
        if(this.durationLeft > 0 && this.duration != 0){
            durationLeft--;
        }
    }

    public boolean isActive(){
        return this.getDurationLeft() > 0 || super.getDuration() == 0;
    }

    public int getDurationLeft() {
        return this.durationLeft;
    }

    // Setters
    public void reduceHealthChange(int attack_damage){
        this.health_change -= attack_damage;
    }

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

    @Override
    public String toSpecString() {
        return "ATK" + (this.attack_change >= 0 ? "+" : "") + this.attack_change + "/HP" + (this.health_change >= 0 ? "+" : "") + this.health_change;
    }
}
