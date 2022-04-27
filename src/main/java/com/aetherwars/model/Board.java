package com.aetherwars.model;

import com.aetherwars.player.Player;

import java.util.ArrayList;

enum BattlePhase {
    DRAW, PLAN, ATTACK, END
};

public class Board {
    private BattlePhase Phase;

    private Player P1;
    private Player P2;

    private int TurnCounter;

    public Board() {
        Phase = BattlePhase.DRAW;
        P1 = new Player("P1", new ArrayList<>(), new ArrayList<>());
        P2 = new Player("P2", new ArrayList<>(), new ArrayList<>());
        TurnCounter = 1;
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
                TurnCounter++;
        }
        System.out.println("Next Phase: " + Phase);
    }

    public Player getP1() {
        return P1;
    }

    public Player getP2() {
        return P2;
    }

    public int getTurnCounter() {
        return TurnCounter;
    }
}
