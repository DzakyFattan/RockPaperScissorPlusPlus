package com.aetherwars.player;

import java.util.*;

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
    private Map<Integer, CardOnField> field;

    public Player(String name, List<Character> characters, List<Spell> spells) {
        this.name = name;
        this.health = 80;
        this.mana = 1;
        this.deck = new ArrayList<Card>();
        this.hand = new ArrayList<Card>();
        this.field = new HashMap<Integer, CardOnField>();
        this.fillDeck(characters, spells);
        this.shuffleDeck();
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

    public Map<Integer, CardOnField> getField() {
        return field;
    }

    public int getDeckCount() {
        return this.deck.size();
    };

    public void reduceHealth(int amount) {
        this.health -= amount;
        this.health = Math.max(this.health, 0);
    }

    public void updateMana(int turn) {
        this.mana = turn;
        this.mana = Math.min(this.mana, 10);
    }

    public void reduceMana(int amount) {
        this.mana -= amount;
        this.mana = Math.max(this.mana, 0);
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public void removeCardFromHand(int slot) {
        this.hand.remove(slot);
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
                this.deck.add(spells.get(ran.nextInt(spells.size())));
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

    public void addCardToField(Integer slot, CardOnField card) {
        this.field.put(slot, card);
    }

    public void checkForDeathOnField() {
        this.field.entrySet().removeIf(entry -> entry.getValue().getHealth() <= 0);
    }
}
