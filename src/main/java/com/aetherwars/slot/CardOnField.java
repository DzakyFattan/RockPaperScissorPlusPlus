package com.aetherwars.slot;

import com.aetherwars.model.Character;
import com.aetherwars.spells.*;
import java.util.*;

public class CardOnField extends Character{
    private int level;
    private int exp;
    private boolean canAttack;
    private List<PotionSpell> activePots;
    private SwapEffect swapEffect;

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
        this.canAttack = true;
        this.activePots = new ArrayList<PotionSpell>();
        this.swapEffect = new SwapEffect(-1);           // -1 means no effect
    }

    public void applyPotionSpell(PotionSpell potion) {
        activePots.add(potion);
    }

    public void applyLevelSpell(LevelSpell levelSpell) {
        if (levelSpell.getLevelType() == LevelSpellType.UP) {
            if (level < 10) {
                this.level++;
                super.levelUp();
            }
        }
        else {
            if (this.level > 1) {
                this.level--;
            }
        }
        this.exp = 0;
    }

    public void applySwapSpell(SwapSpell swapSpell) {
        if(!swapEffect.isActive()){
            SwapEffect newSwapEffect = new SwapEffect(swapSpell.getDuration());
            this.swapEffect = newSwapEffect;

            // swaps stats
            super.setAttack(super.getHealth());
            super.setHealth(super.getAttack());
        } else {
            swapEffect.addDuration(swapSpell.getDuration());
        }
    }

    // reduces the duration of all active potions, as in when a round has passed
    public void tick() {
        // ticks the potion
        for (PotionSpell potion : activePots) {
            potion.tick();
        }

        // removes the potion if duration is below equals 0
        activePots.removeIf(potion -> !potion.isActive());

        // removes the potion if it has no effect
        activePots.removeIf(potion -> potion.getHealthChange() == 0 && potion.getAttackChange() == 0);
    }

    public boolean getStatus() {
        return canAttack;
    }

    public void setStatus(boolean status) {
        this.canAttack = status;
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
        return Math.max(super.getAttack() + this.getAttackBuff(), 0);
    }

    public int getAttackUnbounded(){
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
