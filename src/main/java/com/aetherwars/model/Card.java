package com.aetherwars.model;

abstract class Card {
    private String name;
    private String description;
    private Type type;
    private int attack;
    private int health;

    public Card() {
        this.name = "Steve";
        this.description = "The OG player since the beginning of time";
        this.type = Type.OVERWORLD;
        this.attack = 1;
        this.health = 1;
    }

    public Card(String name, String description, Type type, int attack, int health) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.attack = attack;
        this.health = health;
    }

    // setters
    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
    public void setType(Type type){ this.type = type; }
    public void setAttack(int attack){ this.attack = attack; }
    public void setHealth(int health){ this.health = health; }

    // getters
    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }
    public Type getType(){ return this.type; }
    public int getAttack(){ return this.attack; }
    public int getHealth(){ return this.health; }
}
