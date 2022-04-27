package com.aetherwars.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import com.aetherwars.slot.CardOnField;
import com.aetherwars.spells.Spell;

public class Player {
    private String name;
    private int health;
    private int mana;
    private List<Card> deck;
    private List<Card> hand;
    private List<CardOnField> field;

    public Player(String name, List<Character> characters, List<Spell> spells) {
        this.name = name;
        this.health = 80;
        this.mana = 1;
        this.deck = new ArrayList<Card>();
        this.hand = new ArrayList<Card>();
        this.field = new ArrayList<CardOnField>();
        this.fillDeck(characters, spells);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void reduceHealth(int amount) {
        this.health -= amount;
        this.health = this.health < 0 ? 0 : this.health;
    }

    public void increaseMana() {
        this.mana += 1;
        this.mana = this.mana > 10 ? 10 : this.mana;
    }

    public void reduceMana(int amount) {
        this.mana -= amount;
        this.mana = this.mana < 0 ? 0 : this.mana;
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public Card getCardFromHand(int slot) {
        return this.hand.remove(slot);
    }

    public void fillDeck(List<Character> characters, List<Spell> spells) {
        // Randomly insert a number of characters and spells into deck with a total of
        // 40 cards
        int numCharacters = 25;
        int numSpells = 15;
        int numCards = numCharacters + numSpells;
        for (int i = 0; i < numCards; i++) {
            Random ran = new Random();
            if (i < numCharacters) {
                this.deck.add(characters.get(ran.nextInt(characters.size())));
            } else {
                this.deck.add(spells.get(ran.nextInt(characters.size())));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    public List<Card> getTopCardsFromDeck() {
        List<Card> topCards = new ArrayList<Card>();
        for (int i = 0; i < 3; i++) {
            topCards.add(this.deck.remove(0));
        }
        return topCards;
    }

    public void returnCardsToDeck(List<Card> card) {
        this.deck.addAll(card);
        this.shuffleDeck();
    }
}
