import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Board;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;
import pt.ulusofona.lp2.thenightofthelivingdeisi.GameManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {

    GameManager gameManager;

    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }

    @Test
    void testLoadGame() throws IOException {
        File file = new File("testLoadGame.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("5 5\n");
            writer.write("1\n");
            writer.write("1\n");
            writer.write("1 : 1 : Test Creature : 0 : 0\n");
            writer.write("1\n");
            writer.write("1 : 0 : 0 : 0\n");
        }
        assertTrue(gameManager.loadGame(file));
        file.delete();
    }

    @Test
    void testGetWorldSize() {
        gameManager.board = new Board(5, 5);
        int[] expectedSize = {5, 5};
        assertArrayEquals(expectedSize, gameManager.getWorldSize());
    }

    @Test
    void testGetInitialTeamId() {
        gameManager.initialTeam = 1;
        assertEquals(1, gameManager.getInitialTeamId());
    }

    @Test
    void testGetCurrentTeamId() {
        gameManager.initialTeam = 1;
        gameManager.turns = 0;
        assertEquals(1, gameManager.getCurrentTeamId());
        gameManager.turns = 1;
        assertEquals(0, gameManager.getCurrentTeamId());
    }

    @Test
    void testIsDay() {
        gameManager.initialTeam = 1;
        gameManager.turns = 0;
        assertTrue(gameManager.isDay());
        gameManager.turns = 2;
        assertFalse(gameManager.isDay());
    }

    @Test
    void testIncreaseTurn() {
        gameManager.turns = 0;
        gameManager.increaseTurn();
        assertEquals(1, gameManager.turns);
    }

    @Test
    void testGetSquareInfo() {
        gameManager.board = new Board(5, 5);
        Creature creature = new Creature(1, 1, "Test Creature", new int[]{0, 0});
        gameManager.board.addCreature(creature);
        assertEquals("H:1", gameManager.getSquareInfo(0, 0));
    }

    @Test
    void testGetCreatureInfo() {
        gameManager.board = new Board(5, 5);
        Creature creature = new Creature(1, 1, "Test Creature", new int[]{0, 0});
        gameManager.board.addCreature(creature);
        String[] expectedInfo = {"1", "Humano", "Test Creature", "0", "0", null};
        assertArrayEquals(expectedInfo, gameManager.getCreatureInfo(1));
    }

    @Test
    void testGetCreatureInfoAsString() {
        gameManager.board = new Board(5, 5);
        Creature creature = new Creature(1, 1, "Test Creature", new int[]{0, 0});
        gameManager.board.addCreature(creature);
        String expectedInfo = "1 | Humano | Test Creature | +0 @ (0, 0)";
        assertEquals(expectedInfo, gameManager.getCreatureInfoAsString(1));
    }

    @Test
    void testGetEquipmentInfo() {
        gameManager.board = new Board(5, 5);
        Equipment equipment = new Equipment(1, 0, new int[]{0, 0});
        gameManager.board.addEquipment(equipment);
        String[] expectedInfo = {"1", "0", "0", "0", null};
        assertArrayEquals(expectedInfo, gameManager.getEquipmentInfo(1));
    }

    @Test
    void testGetEquipmentInfoAsString() {
        gameManager.board = new Board(5, 5);
        Equipment equipment = new Equipment(1, 0, new int[]{0, 0});
        gameManager.board.addEquipment(equipment);
        String expectedInfo = "1 | Escudo de madeira @ (0,0)";
        assertEquals(expectedInfo, gameManager.getEquipmentInfoAsString(1));
    }

    @Test
    void testHasEquipment() {
        gameManager.board = new Board(5, 5);
        Creature creature = new Creature(1, 1, "Test Creature", new int[]{0, 0});
        Equipment equipment = new Equipment(1, 0, new int[]{0, 0});
        gameManager.board.addCreature(creature);
        gameManager.board.addEquipment(equipment);
        creature.addEquipment(equipment);
        assertTrue(gameManager.hasEquipment(1, 0));
    }

    @Test
    void testMove() {
        gameManager.board = new Board(5, 5);
        Creature creature = new Creature(1, 1, "Test Creature", new int[]{0, 0});
        gameManager.board.addCreature(creature);
        gameManager.initialTeam = 1;
        assertTrue(gameManager.move(0, 0, 1, 0));
        assertEquals(1, gameManager.board.positionId(1, 0));
        assertEquals(0, gameManager.board.positionId(0, 0));
    }

    @Test
    void testGameIsOver() {
        gameManager.turns = 12;
        assertTrue(gameManager.gameIsOver());
        gameManager.turns = 11;
        assertFalse(gameManager.gameIsOver());
    }

    @Test
    void testGetSurvivors() {
        gameManager.board = new Board(5, 5);
        Creature human = new Creature(1, 1, "Human", new int[]{0, 0});
        Creature zombie = new Creature(2, 0, "Zombie", new int[]{1, 1});
        gameManager.board.addCreature(human);
        gameManager.board.addCreature(zombie);
        ArrayList<String> survivors = gameManager.getSurvivors();
        assertTrue(survivors.contains("1 Human"));
        assertTrue(survivors.contains("2 (antigamente conhecido comoZombie)"));
    }
}