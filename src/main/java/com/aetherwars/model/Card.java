package com.aetherwars.model;

public abstract class Card {
    private String name;
    private String description;

    public Card() {
        this.name = "Steve";
        this.description = "The OG player since the beginning of time";
    }

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
