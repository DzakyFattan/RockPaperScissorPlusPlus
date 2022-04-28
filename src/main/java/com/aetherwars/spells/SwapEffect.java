package com.aetherwars.spells;

import java.util.List;
import java.util.ArrayList;
import com.aetherwars.slot.CardOnField;

public class SwapEffect {
    private int duration;
    private List<Integer> targets;

    public SwapEffect(int duration, int cardIdx1, int cardIdx2) {
        this.duration = duration;
        this.targets = new ArrayList<Integer>();
        targets.add(cardIdx1);
        targets.add(cardIdx2);
    }

    public void tick(){
        duration--;
    }

    public int getDuration() {
        return duration;
    }

    public void addDuration(int duration){
        this.duration += duration;
    }

    public boolean isActive(){
        return duration > 0;
    }

    public static void swap(CardOnField card1, CardOnField card2){
        List<PotionSpell> newActivePotsForCard2 = card1.getActivePots();
        int newAttackForCard2 = card1.getAttack() - card1.getAttackBuff();
        int newHealthForCard2 = card1.getHealth() - card1.getHealthBuff();

        card1.setActivePots(card2.getActivePots());
        card1.setAttack(card2.getAttack() - card2.getAttackBuff());
        card1.setHealth(card2.getHealth() - card2.getHealthBuff());

        card2.setActivePots(newActivePotsForCard2);
        card2.setAttack(newAttackForCard2);
        card2.setHealth(newHealthForCard2);
    }
}
