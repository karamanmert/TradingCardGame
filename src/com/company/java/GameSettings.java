package com.company.java;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSettings {
    private ArrayList<Integer> deck;
    private int maxMana;
    private int health;
    private int handSize;
    private int penalty;

    public GameSettings() {
        this.deck = new ArrayList<>(Arrays.asList(0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,7,8));
        this.maxMana = 10;
        this.health = 30;
        this.handSize=5;
        this.penalty=1;
    }

    public ArrayList<Integer> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Integer> deck) {
        this.deck = deck;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}
