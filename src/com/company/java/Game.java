package com.company.java;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Player activePlayer, passivePlayer;
    private GameSettings gameSettings = new GameSettings();
    boolean isGameOn = true;

    public void startGame() {
        initPlayers();
        Scanner scanner = new Scanner(System.in);
        int control;
        while (isGameOn) {
            System.out.println("Turn: " + activePlayer.getName());
            System.out.println("Health: " + activePlayer.getHealth() + "\tOpponent's Health: " + passivePlayer.getHealth());
            System.out.println("Remaining Mana/Total Mana: " + activePlayer.getRemainingMana() + "/" + activePlayer.getTotalMana());
            System.out.println("Hand: " + activePlayer.getHand());
            System.out.println("Play Card(0) or Pass(1)");
            try {
                control = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number\n");
                scanner.nextLine();
                continue;
            }
            switch (control) {

                case 0:
                    System.out.println("Index ?");
                    try {
                        control = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Enter a valid number\n");
                        scanner.nextLine();
                        continue;
                    }
                    playCard(control);
                    break;
                case 1:
                    changeTurn();
                    break;
                default:
                    System.out.println("Enter a valid number\n");
                    break;
            }

        }
    }


    public void initPlayers() {
        this.activePlayer = initPlayer("PLAYER 1");
        this.passivePlayer = initPlayer("PLAYER 2");
        draw();
        refresh();
    }

    public Player initPlayer(String playerName) {
        Player player = new Player(playerName, gameSettings);
        player.setDeck(gameSettings.getDeck());
        Collections.shuffle(player.getDeck());
        player.getHand().add(player.getDeck().remove(0));
        player.getHand().add(player.getDeck().remove(1));
        player.getHand().add(player.getDeck().remove(2));
        return player;
    }

    public void draw() {
        if (activePlayer.getDeck().size() > 0) {
            int card = activePlayer.getDeck().remove(0);
            if (activePlayer.getHand().size() < gameSettings.getHandSize()) {
                activePlayer.getHand().add(card);
            }
        } else {
            damage(activePlayer, gameSettings.getPenalty());
        }
    }

    public void damage(Player player, int damage) {
        player.setHealth(player.getHealth() - damage);
        playerCheck(player);
    }

    public void playerCheck(Player player) {
        if (player.getHealth() <= 0) {
            endGame(player);
        }
    }

    public void endGame(Player loser) {
        this.isGameOn = false;
        Player winner = activePlayer == loser ? passivePlayer : activePlayer;
        System.out.println("Winner is " + winner.getName());
    }

    public void changeTurn() {
        Player tempPlayer = activePlayer;
        activePlayer = passivePlayer;
        passivePlayer = tempPlayer;
        draw();
        refresh();
    }

    public void refresh() {
        if (activePlayer.getTotalMana() < gameSettings.getMaxMana())
            activePlayer.setTotalMana(activePlayer.getTotalMana() + 1);
        activePlayer.setRemainingMana(activePlayer.getTotalMana());
    }

    public void playCard(int index) {
        if (index < 0 || index >= activePlayer.getHand().size()) {
            System.out.println("Invalid Index");
            return;
        }
        int cardValue = activePlayer.getHand().get(index);
        if (cardValue <= activePlayer.getRemainingMana()) {
            damage(passivePlayer, cardValue);
            activePlayer.getHand().remove(index);
            activePlayer.setRemainingMana(activePlayer.getRemainingMana() - cardValue);
        } else {
            System.out.println("Not enough mana!");
        }
    }

    public Player getActivePlayer() {
        return activePlayer;
    }


    public Player getPassivePlayer() {
        return passivePlayer;
    }


    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public boolean isGameOn() {
        return isGameOn;
    }

    public void setGameOn(boolean gameOn) {
        isGameOn = gameOn;
    }
}