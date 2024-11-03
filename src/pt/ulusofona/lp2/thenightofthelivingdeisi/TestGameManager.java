package pt.ulusofona.lp2.thenightofthelivingdeisi;

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

    @Test
    void testLoadGame() {
        GameManager gameManager = new GameManager();
        File loadFile = new File("test-files/test.txt");
        gameManager.loadGame(loadFile);
        assertEquals(1,gameManager.getInitialTeamId());
        assertEquals("1 | Humano | Human1 | +0 @ (0, 0)",gameManager.getCreatureInfoAsString(1));
        assertEquals("2 | Humano | Human2 | +0 @ (0, 4)",gameManager.getCreatureInfoAsString(2));
        assertEquals("3 | Zombie | Zombie1 | -0 @ (1, 2)",gameManager.getCreatureInfoAsString(3));
        assertEquals("4 | Zombie | Zombie2 | -0 @ (5, 1)",gameManager.getCreatureInfoAsString(4));
        assertEquals(6,gameManager.getWorldSize()[0]);
        assertEquals(7,gameManager.getWorldSize()[1]);
        assertEquals("-1 | Escudo de madeira @ (1,0)",gameManager.getEquipmentInfoAsString(-1));
        assertEquals("-2 | Espada samurai @ (4,3)",gameManager.getEquipmentInfoAsString(-2));

    }

    @Test
    void testMove(){
        GameManager gameManager = new GameManager();
        File loadFile = new File("test-files/test.txt");
        gameManager.loadGame(loadFile);
        assertFalse(gameManager.move(0,0,2,0));
        assertTrue(gameManager.move(0,0,1,0));
        assertEquals("",gameManager.getSquareInfo(0,0));
        assertEquals("H:1",gameManager.getSquareInfo(1,0));
    }
    @Test
    void testHasEquipment (){
        GameManager gameManager = new GameManager();
        File loadFile = new File("test-files/test.txt");
        gameManager.loadGame(loadFile);
        gameManager.move(0,0,1,0);
        Creature creature=gameManager.board.getCreatureById(1);
        assertTrue(creature.hasEquipment(0));

    }






}