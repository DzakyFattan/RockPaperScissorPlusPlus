package com.aetherwars.battle;

public class BattleAction {
    private final int attackDamage;
    private final int opponentSlot;

    public BattleAction(int attackDamageGiven, int opponentSlot) {
        this.attackDamage = attackDamageGiven;
        this.opponentSlot = opponentSlot;
    }

    // getters
    public int getAttackDamage() {
        return attackDamage;
    }

    public int getOpponentSlot() {
        return opponentSlot;
    }

}
