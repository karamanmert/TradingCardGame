package com.company.java;

import java.util.ArrayList;

public class Player {
    private final String name;
    private ArrayList<Integer> hand;
    private int totalMana;
    private int remainingMana;
    private ArrayList<Integer> deck;
    private int health;

    public Player(String name, GameSettings gameSettings) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.remainingMana = 0;
        this.totalMana = 0;
        this.health=gameSettings.getHealth();
    }


    public ArrayList<Integer> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Integer> hand) {
        this.hand = hand;
    }

    public int getTotalMana() {
        return totalMana;
    }

    public void setTotalMana(int totalMana) {
        this.totalMana = totalMana;
    }

    public int getRemainingMana() {
        return remainingMana;
    }

    public void setRemainingMana(int remainingMana) {
        this.remainingMana = remainingMana;
    }

    public ArrayList<Integer> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Integer> deck) {
        this.deck = deck;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }
}
