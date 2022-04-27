package com.aetherwars.model;


public class Character extends Card{
  private CharacterType type;
  private int attack;
  private int attackUp;
  private int health;
  private int healthUp;

  public Character() {
    super();
    this.type = CharacterType.OVERWORLD;
    this.attack = 1;
    this.health = 1;
    this.attackUp = 1;
    this.healthUp = 1;

  }
// row[1], row[3], row[2], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[8]), Integer.parseInt(row[9])
  public Character(String[] chara) {
    super(chara[1], chara[3], chara[4], Integer.parseInt(chara[7]));
    this.type = CharacterType.valueOf(chara[2]);
    this.attack = Integer.parseInt(chara[5]);
    this.health = Integer.parseInt(chara[6]);
    this.attackUp = Integer.parseInt(chara[8]);
    this.healthUp = Integer.parseInt(chara[9]);
  }

  // setters
  public void setType(CharacterType type) {
    this.type = type;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setAttackUp(int attackUp) {
    this.attackUp = attackUp;
  }

  public void setHealthUp(int healthUp) {
    this.healthUp = healthUp;
  }
  public void reduceHealth(int amount) {
    this.health -= amount;
    this.health = Math.max(this.health, 0);
  }

  // getters
  public CharacterType getType() {
    return this.type;
  }

  public int getAttack() {
    return this.attack;
  }

  public int getHealth() {
    return this.health;
  }

  public int getAttackUp() {
    return this.attackUp;
  }

  public int getHealthUp() {
    return this.healthUp;
  }

  public void levelUp() {
    this.attack += this.attackUp;
    this.health += this.healthUp;
  }

  @Override
  public String toSpecString() {
    return "ATK " + this.attack + "/HP " + this.health;
  }
  @Override
  public String toString() {
    return "Name: " + this.getName() + "\nDescription: " + this.getDescription() + "\nType: " + this.type + "\n";
  }
}
