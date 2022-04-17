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

  public Character(String name, String description, String image_path, CharacterType element, int attack, int health, int attackUp, int healthUp) {
    super(name, description, image_path);
    this.type = element;
    this.attack = attack;
    this.health = health;
    this.attackUp = attackUp;
    this.healthUp = healthUp;
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
    this.health = this.health < 0 ? 0 : this.health;
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

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\nDescription: " + this.getDescription() + "\nType: " + this.type;
  }
}
