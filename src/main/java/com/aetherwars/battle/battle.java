//package com.aetherwars.battle;
//
//import java.util.ArrayList;
//
//import com.aetherwars.slot.CardOnField;
//
//public class battle {
//    private ArrayList<activeCard> player1;
//    private ArrayList<activeCard> player2;
//
//    public battle() {
////        this.player1 = new ArrayList<CardOnHand>();
////        this.player2 = new ArrayList<CardOnHand>();
//    }
////    public void addCardToHandPlayer1(int id, Character card) {
////        this.player1.add(new activeCard(id, card));
////    }
////    public void addCardToHandPlayer2(int id, Character card) {
////        this.player2.add(new activeCard(id, card));
////    }
//    public int getIndexPlayer1(int id) { //id harus selalu 1-5
//        for (int i = 0; i < this.player1.size(); i++) {
//            if (this.player1.get(i).getId() == id) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public int getIndexPlayer2(int id) { //id harus selalu 1-5
//        for (int i = 0; i < this.player2.size(); i++) {
//            if (this.player2.get(i).getId() == id) {
//                return i;
//            }
//        }
//        return -1;
//    }
//    public activeCard getCardPlayer1(int id) {
//        int index = getIndexPlayer1(id);
//        if (index == -1) {
//            return null;
//        }
//        return this.player1.get(index);
//    }
//    public activeCard getCardPlayer2(int id) {
//        int index = getIndexPlayer2(id);
//        if (index == -1) {
//            return null;
//        }
//        return this.player2.get(index);
//    }
//    public void removeCardPlayer1(int id) {
//        int index = getIndexPlayer1(id);
//        if (index == -1) {
//            return;
//        }
//        this.player1.remove(index);
//    }
//    public void removeCardPlayer2(int id) {
//        int index = getIndexPlayer2(id);
//        if (index == -1) {
//            return;
//        }
//        this.player2.remove(index);
//    }
//    public void setCardPlayer1(int id, CardOnField card) {
//        int index = getIndexPlayer1(id);
//        if (index == -1) {
//            return;
//        }
//        this.player1.get(index).setCard(card);
//    }
//
//    public void setCardIdPlayer2(int id, CardOnField card) {
//        int index = getIndexPlayer2(id);
//        if (index == -1) {
//            return;
//        }
//        this.player2.get(index).setCard(card);
//    }
////    setAllCardPlayer1Active() {
////        for (int i = 0; i < this.player1.size(); i++) {
////            this.player1.get(i).setActive();
////        }
////    }
////    setAllCardPlayer2Active() {
////        for (int i = 0; i < this.player2.size(); i++) {
////            this.player2.get(i).setActive();
////        }
////    }
//
//    public void characterAtk(int turn, int id1, int id2) {
//        //karakter menyerang dengan turn=0 player1, turn=1 player2 dan pada id=1-5
//        int index1=getIndexPlayer1(id1);
//        int index2=getIndexPlayer2(id2);
//        CardOnField card1=this.player1.get(index1).getCard();
//        CardOnField card2=this.player2.get(index2).getCard();
//        Character character1 = card1.getCharacter();
//        Character character2 = card2.getCharacter();
//        double char1AtkMod=setAtkModifier(character1, character2);
//        double char2AtkMod=setAtkModifier(character2, character1);
//        double atkChar1=character1.getAtk()*char1AtkMod;
//        double atkChar2=character2.getCharacter().getAtk()*char2AtkMod;
//        character1.reduceHealth((atkChar2);
//        character2.reduceHealth((atkChar1);
//        if (character1.getHealth()==0) {
//            this.player1.remove(id1);
//        }
//        else {
//            if (turn==0) {
//                this.player1.get(index1).setNonActive();
//                if (character2.getHealth()==0) {
//                    card1.addExp(character1.getLevel());
//                    card1.levelUp();
//                }
//            }
//            card1.setCharacter(character2);
//            this.player1.setCard(id1, card1);
//            }
//        if (character2.getHealth()==0) {
//            this.player2.remove(id2);
//        }
//        else {
//            if (turn==1) {
//                this.player2.get(index2).setNonActive();
//                if (character1.getHealth()==0) {
//                    card2.addExp(character2.getLevel());
//                    card2.levelUp();
//                }
//            }
//            card2.setCharacter(character1);
//            this.player2.setCard(id2, card2);
//        }
//    }
//
//    public double setAtkModifier(Character character1, Character character2) {
//        //Set Attack Modifier; 1 jika tipe sama, 2 jika tipe lawan lebih lemah, 0.5 jika tipe lawan lebih kuat
//        double atkModifier;
//        if (character1.getType()==character2.getType()) {
//            atkModifier=1;
//        } else if (character1.getType()==CharacterType.OVERWORLD && character2.getType()==CharacterType.END || character1.getType()==CharacterType.NETHER && character2.getType()==CharacterType.OVERWORLD || character1.getType()==CharacterType.END && character2.getType()==CharacterType.NETHER) {
//            atkModifier=2;
//        } else {
//            atkModifier=0.5;
//        }
//        return atkModifier;
//    }
//}
