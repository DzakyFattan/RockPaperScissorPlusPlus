package com.aetherwars.model;

import com.aetherwars.player.Player;
import com.aetherwars.spells.Spell;

import java.util.ArrayList;
import java.util.List;

enum BattlePhase {
    DRAW, PLAN, ATTACK, END
};

public class Board {
    private BattlePhase Phase;

    private Player P1;
    private Player P2;

    private int turnCounter;
    private String whoseTurn;
    private int manaCounter;

    public Board(List<Character> characters, List<Spell> spells) {
        Phase = BattlePhase.DRAW;
        P1 = new Player("P1", characters, spells);
        P2 = new Player("P2", characters, spells);
        manaCounter = 1;
        turnCounter = 1;
        whoseTurn = "P1";
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
}
