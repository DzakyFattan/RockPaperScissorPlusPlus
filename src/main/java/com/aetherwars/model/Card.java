package com.aetherwars.model;

public abstract class Card {

    protected int manaCost;
    private String name;
    private String description;
    private String image_path;

    public Card() {
        this.name = "Steve";
        this.description = "The OG player since the beginning of time";
        this.image_path = "";
    }

    public Card(String name, String description, String image_path, int manaCost) {
        this.name = name;
        this.description = description;
        this.image_path = image_path;
        this.manaCost = manaCost;
    }

    // getters
    public String getName() {
        return this.name;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return this.image_path;
    }

    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public abstract String toSpecString();

    public abstract String getCardType();
}
