package com.aetherwars.battle;

import com.aetherwars.model.Board;
import com.aetherwars.model.Character;
import com.aetherwars.model.CharacterType;
import com.aetherwars.player.Player;
import com.aetherwars.slot.CardOnField;
import com.aetherwars.battle.BattleAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Battle {
    private Map<Integer, BattleAction> P1Actions;
    private Map<Integer, BattleAction> P2Actions;
    private Board board;

    public Battle(Board board) {
        this.board = board;
        this.P1Actions = new HashMap<Integer, BattleAction>();
        this.P2Actions = new HashMap<Integer, BattleAction>();
    }

    public void addAction(int player, int cardSlot, BattleAction action) {
        if (player == 1) {
            this.P1Actions.put(cardSlot, action);
        } else {
            this.P2Actions.put(cardSlot, action);
        }
    }

    public void clearActions() {
        this.P1Actions.clear();
        this.P2Actions.clear();
    }

    public void runCachedActions() {
        Map<Integer, CardOnField> P1Field = this.board.getP1().getField();
        Map<Integer, CardOnField> P2Field = this.board.getP2().getField();

        // run actions for player 1 // .reduceHealth(actionEntry.getValue().getAttackDamage())
        for (Map.Entry<Integer, BattleAction> actionEntry : this.P1Actions.entrySet()) {
            CardOnField newCOF = P2Field.get(actionEntry.getValue().getOpponentSlot());
            newCOF.reduceHealth(actionEntry.getValue().getAttackDamage());
            if (newCOF.getHealth() <= 0) {
                P2Field.remove(actionEntry.getValue().getOpponentSlot());
            } else {
                P2Field.put(actionEntry.getValue().getOpponentSlot(), newCOF);
            }
        }

        // run actions for player 2
        for (Map.Entry<Integer, BattleAction> actionEntry : this.P2Actions.entrySet()) {
            CardOnField newCOF = P1Field.get(actionEntry.getValue().getOpponentSlot());
            newCOF.reduceHealth(actionEntry.getValue().getAttackDamage());
            if (newCOF.getHealth() <= 0) {
                P1Field.remove(actionEntry.getValue().getOpponentSlot());
            } else {
                P1Field.put(actionEntry.getValue().getOpponentSlot(), newCOF);
            }
        }
    }

    public void characterAttacked(int attackingCardSlot, int defendingCardSlot) {
        Map<Integer, CardOnField> P1Field = this.board.getP1().getField();
        Map<Integer, CardOnField> P2Field = this.board.getP2().getField();
        if (board.getWhoseTurn().equals("P1")) {
            this.P1Actions.put(attackingCardSlot, new BattleAction(Battle.calculateAtk(P1Field.get(attackingCardSlot), P2Field.get(defendingCardSlot)), defendingCardSlot));
            this.P2Actions.put(defendingCardSlot, new BattleAction(Battle.calculateAtk(P2Field.get(defendingCardSlot), P1Field.get(attackingCardSlot)), attackingCardSlot));
        } else {
            this.P2Actions.put(attackingCardSlot, new BattleAction(Battle.calculateAtk(P2Field.get(attackingCardSlot), P1Field.get(defendingCardSlot)), defendingCardSlot));
            this.P1Actions.put(defendingCardSlot, new BattleAction(Battle.calculateAtk(P1Field.get(defendingCardSlot), P2Field.get(attackingCardSlot)), attackingCardSlot));
        }
    }

    public Board getBoard() {
        return board;
    }

    private static int calculateAtk(Character attacking, Character defending) {
        double modifier = Battle.calculateModifier(attacking, defending);

        return (int) Math.ceil(attacking.getAttack() * modifier);
    }

    private static double calculateModifier(Character attacking, Character defending) {
        //Set Attack Modifier; 1 jika tipe sama, 2 jika tipe lawan lebih lemah, 0.5 jika tipe lawan lebih kuat
        double atkModifier;
        if (attacking.getType() == defending.getType()) {
            atkModifier = 1;
        } else if (attacking.getType() == CharacterType.OVERWORLD && defending.getType() == CharacterType.END || attacking.getType() == CharacterType.NETHER && defending.getType() == CharacterType.OVERWORLD || attacking.getType() == CharacterType.END && defending.getType() == CharacterType.NETHER) {
            atkModifier = 2;
        } else {
            atkModifier = 0.5;
        }
        return atkModifier;

    }
}
