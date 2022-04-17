package com.aetherwars.battle;

import com.aetherwars.model.CardOnHand;

public class activeCard {
    private int id;
    private CardOnHand card;
    private boolean active;

    public activeCard(int id, Character character) {
        this.id=id; //id dari 1-5 yang dipilih pada kotak kosong
        this.card = new CardOnHand(character);
        this.active = true;
    }
    public int getId() {
        return this.id;
    }
    public CardOnHand getCard() {
        return card;
    }
    public void setCard(CardOnHand card) {
        this.card = card;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive() {
        this.active = true;
    }
    public void setNonActive() {
        this.active = false;
    }
}
