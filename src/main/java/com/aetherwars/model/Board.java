package com.aetherwars.model;

import com.aetherwars.player.Player;
import com.aetherwars.slot.CardOnField;
import com.aetherwars.spells.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

enum BattlePhase {
    DRAW, PLAN, ATTACK, END
};

public class Board {
    private BattlePhase Phase;
    private Player P1;
    private Player P2;

    private List<SwapEffect> swapEffects;
    private List<Character> characters;
    private int turnCounter;
    private String whoseTurn;
    private int manaCounter;

    public Board(List<Character> characters, List<Spell> spells) {
        Phase = BattlePhase.DRAW;
        this.characters = characters;
        P1 = new Player("P1", characters, spells);
        P2 = new Player("P2", characters, spells);
        manaCounter = 1;
        turnCounter = 1;
        whoseTurn = "P1";
        swapEffects = new ArrayList<SwapEffect>();
    }

    public String getPhase() {
        return Phase.toString();
    }
    public void nextPhase() {
        BattlePhase currentPhase = Phase;
        switch(currentPhase) {
            case DRAW:
                Phase = BattlePhase.PLAN;
                break;
            case PLAN:
                Phase = BattlePhase.ATTACK;
                break;
            case ATTACK:
                Phase = BattlePhase.END;
                break;
            case END:
                Phase = BattlePhase.DRAW;
                if (whoseTurn.equals("P2")) {
                    turnCounter++;
                    tickAllSpells();
                }
                whoseTurn = (whoseTurn.equals("P1")) ? "P2" : "P1";
                updateMana();
        }
    }

    public Player getP1() {
        return P1;
    }

    public Player getP2() {
        return P2;
    }

    public String getWhoseTurn() {
        return whoseTurn;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public int addTurnCounter() {
        return turnCounter++;
    }

    public void updateMana() {
        if (whoseTurn.equals("P2"))
            P2.updateMana(turnCounter);
        else
            P1.updateMana(turnCounter);
            manaCounter = turnCounter;
            manaCounter = Math.min(manaCounter, 10);
    }

    public int getManaCounter() {
        return manaCounter;
    }

    public int getCurrentPlayerMana() {
        if (whoseTurn.equals("P2"))
            return P2.getMana();
        else
            return P1.getMana();
    };

    public int getCurrentPlayerDeckCount() {
        if (whoseTurn.equals("P2"))
            return P2.getDeckCount();
        else
            return P1.getDeckCount();
    }

    public List<Card> getCurrentPlayerHand() {
        if (whoseTurn.equals("P2"))
            return P2.getHand();
        else
            return P1.getHand();
    }

    public List<Card> getCurrentPlayerTopDeck() {
        if (whoseTurn.equals("P2"))
            return P2.getTopCardsFromDeck();
        else
            return P1.getTopCardsFromDeck();
    }

    public Map<Integer, CardOnField> getPlayerField(String player) {
        if (player.equals("P2"))
            return P2.getField();
        else
            return P1.getField();
    }

    public Map<Integer, CardOnField> getCurrentPlayerField() {
        if (whoseTurn.equals("P2"))
            return P2.getField();
        else
            return P1.getField();
    }

    public Map<Integer, CardOnField> getCurrentOpponentField() {
        if (whoseTurn.equals("P2"))
            return P1.getField();
        else
            return P2.getField();
    }

    public void addToCurrentPlayerField(int slot, CardOnField card) {
        if (whoseTurn.equals("P2"))
            P2.addCardToField(slot, card);
        else
            P1.addCardToField(slot, card);
    }

    public void addToCurrentPlayerHand(Card card) {
        if (whoseTurn.equals("P2"))
            P2.addCardToHand(card);
        else
            P1.addCardToHand(card);
    }

    public void removeFromCurrentPlayerHand(int slot) {
        if (whoseTurn.equals("P2"))
            P2.removeCardFromHand(slot);
        else
            P1.removeCardFromHand(slot);
    }

    public void returnToCurrentPlayerDeck(List<Card> cards) {
        if (whoseTurn.equals("P2"))
            P2.returnCardsToDeck(cards);
        else
            P1.returnCardsToDeck(cards);
    }

    public void reduceCurrentPlayerMana(int amount) {
        if (whoseTurn.equals("P2"))
            P2.reduceMana(amount);
        else
            P1.reduceMana(amount);
    }

    public void reduceCurrentOpponentHealth(int amount) {
        if (whoseTurn.equals("P2"))
            P1.reduceHealth(amount);
        else
            P2.reduceHealth(amount);
    }
    // Bagian Battle

    // Bagian prep potion
    public void applyMorphSpell(int targetCardSlot, MorphSpell morphSpell){
        addToCurrentPlayerField(targetCardSlot, new CardOnField(characters.get(morphSpell.getTarget() - 1)));
    }

    public void tickAllSpells() {
        for (Map.Entry<Integer, CardOnField> entry : getPlayerField("P1").entrySet()) {
            entry.getValue().tick();
        }
        for (Map.Entry<Integer, CardOnField> entry : getPlayerField("P2").entrySet()) {
            entry.getValue().tick();
        }
    }
}
