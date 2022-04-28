package com.aetherwars.slot;

import com.aetherwars.model.Character;
import com.aetherwars.spells.*;
import java.util.*;

public class CardOnField extends Character{
    private int level;
    private int exp;
    private List<PotionSpell> activePots;

    private static HashMap<Integer, Integer> expReq = new HashMap<Integer, Integer>() {
        {
            for (int i = 1; i <= 10; i++) {
                put(i, 2 * i - 1);
            }
        }
    };

    public CardOnField(Character character) {
        super(character);
        this.level = 1;
        this.exp = 0;
        this.activePots = new ArrayList<PotionSpell>();
    }

    public void addPotionSpell(PotionSpell potion) {
        activePots.add(potion);
    }

    public void addLevelSpell(LevelSpell levelSpell) {
        if (levelSpell.getLevelType() == LevelSpellType.UP) {
            this.level++;
            super.levelUp();
        }
        else {
            this.level--;
        }
        this.exp = 0;
    }

    // reduces the duration of all active potions, as in when a round has passed
    public void tick() {
        // ticks the potion
        for (PotionSpell potion : activePots) {
            potion.tick();
        }

        // removes the potion if duration is below equals 0
        for (PotionSpell potion : activePots) {
            if(potion.getDuration() <= 0) {
                activePots.remove(potion);
            }
        }

        // removes the potion if it has no effect
        for (PotionSpell potion : activePots) {
            if(potion.getHealthChange() == 0 && potion.getAttackChange() == 0) {
                activePots.remove(potion);
            }
        }
    }

    public int getAttackBuff(){
        int attackBuff = 0;
        for (PotionSpell potion : activePots) {
            attackBuff += potion.getAttackChange();
        }
        return attackBuff;
    }

    @Override
    public int getAttack(){
        return super.getAttack() + this.getAttackBuff();
    }

    public int getHealthBuff(){
        int healthBuff = 0;
        for (PotionSpell potion : activePots) {
            healthBuff += potion.getHealthChange();
        }
        return healthBuff;
    }

    @Override
    public int getHealth(){
        return super.getHealth() + this.getHealthBuff();
    }

    @Override
    public void reduceHealth(int amount){
        int remainingAmount = amount;
        // reduces health from potion buff first
        if (this.getHealthBuff() != 0){
            for(PotionSpell potion : activePots){
                if (potion.getHealthChange() > 0 && remainingAmount > 0){
                    int reducePotBuff = Math.min(potion.getHealthChange(), remainingAmount);
                    potion.reduceHealthChange(reducePotBuff);
                    remainingAmount -= reducePotBuff;
                }
            }
        }

        // reduces health from char card
        if(remainingAmount > 0){
            super.reduceHealth(remainingAmount);
        }
    }

    public List<PotionSpell> getActivePots(){
        return activePots;
    }

    public void setActivePots(List<PotionSpell> newActivePots){
        this.activePots = newActivePots;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addExp(int exp) {
        if (this.level < 10){
            this.exp += exp;
        }
    }

    public int getCurrentExpReq() {
        return expReq.get(level);
    }

    public void levelUp() {
        while(this.exp >= expReq.get(this.level)) {
            this.exp -= expReq.get(this.level);
            this.level++;
            super.levelUp();
        }
    }
}
