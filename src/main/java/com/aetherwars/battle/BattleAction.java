package com.aetherwars.battle;

import com.aetherwars.model.Character;
import com.aetherwars.model.CharacterType;

public class BattleAction {
    private int attackDamage;
    private int opponentSlot;

    public BattleAction(int attackDamageGiven, int opponentSlot){
        this.attackDamage = attackDamageGiven;
        this.opponentSlot = opponentSlot;
    }

    // getters
    public int getAttackDamage(){
        return attackDamage;
    }

    public int getOpponentSlot(){
        return opponentSlot;
    }

}
