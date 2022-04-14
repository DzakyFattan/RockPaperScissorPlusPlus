package com.aetherwars.slot;
import java.util.*;

public class CardOnHand{
    private Character character;
    private int level;
    private int exp;
    private static HashMap<Integer, Integer> expReq = new HashMap<Integer, Integer>(){
        {
            for(int i = 1; i <= 10; i++){
                put(i, 2 * i -1);
            }
        }
    };


    public CardOnHand(Character character) {
        this.character = character;
        this.level = 1;
        this.exp = 0;
    }

    // setters getters
    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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

    public void addExp(int exp){
        this.exp += exp;
    }

    public void levelUp(){
        if(this.exp < expReq.get(this.level)) {
            return;
        }
        this.exp -= expReq.get(this.level);
        this.level++;
    }
}
