package com.aetherwars.spells;

import java.util.List;
import java.util.ArrayList;
import com.aetherwars.slot.CardOnField;

public class SwapEffect {
    private int durationLeft;
    private boolean isPermanent;
    private List<Integer> targets;

    public SwapEffect(int duration, int cardIdx1, int cardIdx2) {
        this.durationLeft = duration;
        this.isPermanent = duration == 0;
        this.targets = new ArrayList<Integer>();
        targets.add(cardIdx1);
        targets.add(cardIdx2);
    }

    public void tick(){
        if(durationLeft > 0 && !isPermanent){
            durationLeft--;
        }
    }

    public int getDurationLeft() {
        if (isPermanent) {
            return 0;
        }
        return durationLeft;
    }

    public void addDuration(int duration){
        if(isPermanent){
            return;
        }
        this.durationLeft += duration;
    }

    public boolean isActive(){
        return durationLeft > 0 | isPermanent;
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
