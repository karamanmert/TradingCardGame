package com.company.test;

import com.company.java.Game;
import com.company.java.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static Game game;

    @BeforeAll
    static void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void beforeEach() throws IOException {
        game = new Game();
        game.initPlayers();
    }


    @Test
    void ShouldPlayersBeInitialized() {
        assertNotNull(game.getActivePlayer());
        assertNotNull(game.getPassivePlayer());
        assertEquals(game.getActivePlayer().getName(), "PLAYER 1");
        assertEquals(game.getPassivePlayer().getName(), "PLAYER 2");

    }

    @Test
    void ShouldTakeDamageFromCard() {
        int preHealth = game.getActivePlayer().getHealth();
        int damage = 5;
        game.damage(game.getActivePlayer(), damage);
        assertEquals(preHealth - damage, game.getActivePlayer().getHealth());
    }



    @Test
    void ShouldNotValidIndexWhenGivenOutOfHandSize() {
        game.playCard(game.getGameSettings().getHandSize() + 1);
        assertTrue(outContent.toString().contains("Invalid Index"));
    }

    @Test
    void ShouldNotEnoughMana() {
        //oyun başında mana 1
        game.getActivePlayer().getHand().set(0, 2);
        game.playCard(0);
        assertTrue(outContent.toString().contains("Not enough mana!"));
    }

    @Test
    void ShouldPlayAValidCard() {
        //oyun başında mana 1
        game.getActivePlayer().getHand().set(0, 2);
        game.getActivePlayer().setRemainingMana(5);
        game.getActivePlayer().setTotalMana(5);
        int size = game.getActivePlayer().getHand().size();
        int health = game.getPassivePlayer().getHealth();
        game.playCard(0);
        assertEquals(health - 2, game.getPassivePlayer().getHealth());
        assertEquals(size - 1, game.getActivePlayer().getHand().size());
        assertEquals(3, game.getActivePlayer().getRemainingMana());
        assertEquals(5, game.getActivePlayer().getTotalMana());
    }

    @Test
    void ShouldRefreshActivePlayerRemainingAndTotalMana() {
        Player player = game.getActivePlayer();
        int remainingMana = player.getRemainingMana();
        int totalMana = player.getTotalMana();
        game.refresh();
        assertEquals(player.getRemainingMana(), player.getTotalMana());
        assertEquals(totalMana + 1, player.getTotalMana());
        assertEquals(remainingMana + 1, player.getRemainingMana());
    }

    @Test
    void ShouldChangeTurn() {
        game.changeTurn();
        assertEquals("PLAYER 2", game.getActivePlayer().getName());
        assertEquals("PLAYER 1", game.getPassivePlayer().getName());
    }

    @Test
    void ShouldDrawACard() {
        int deckSize = game.getActivePlayer().getDeck().size();
        int handSize = game.getActivePlayer().getHand().size();
        game.draw();
        assertEquals(deckSize - 1, game.getActivePlayer().getDeck().size());
        assertEquals(handSize + 1, game.getActivePlayer().getHand().size());
    }
    @Test
    void ShouldPlayerTakeDamageWhenTryToDrawACard() {
        game.getActivePlayer().getDeck().clear();
        int health=game.getActivePlayer().getHealth();
        game.draw();
        assertEquals(health - game.getGameSettings().getPenalty(), game.getActivePlayer().getHealth());
    }
    @Test
    void ShouldGameOverWhenAPlayerHealthIsZeroOrBelow() {
        game.damage(game.getPassivePlayer(), game.getGameSettings().getHealth());
        assertFalse(game.isGameOn());
        assertTrue(outContent.toString().contains(game.getActivePlayer().getName()));
    }

}