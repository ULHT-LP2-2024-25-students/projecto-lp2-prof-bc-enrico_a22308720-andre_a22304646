import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Board;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestBoard {

    Board board;
    int[] position = {0, 0};

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
    }

    @Test
    void testAddCreature() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        assertTrue(board.addCreature(creature));
        assertEquals(creature, board.getCreatureById(1));
    }

    @Test
    void testAddCreatureInvalidPosition() {
        Creature creature = new Creature(1, 1, "Test Creature", new int[]{5, 5});
        assertFalse(board.addCreature(creature));
    }

    @Test
    void testAddEquipment() {
        Equipment equipment = new Equipment(1, 0, position);
        board.addEquipment(equipment);
        assertEquals(equipment, board.getEquipment(1));
    }

    @Test
    void testRemoveEquipment() {
        Equipment equipment = new Equipment(1, 0, position);
        board.addEquipment(equipment);
        board.removeEquipment(equipment);
        assertNull(board.getEquipment(1));
    }

    @Test
    void testGetSizeX() {
        assertEquals(5, board.getSizeX());
    }

    @Test
    void testGetSizeY() {
        assertEquals(5, board.getSizeY());
    }

    @Test
    void testGetSquareInfo() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        board.addCreature(creature);
        assertEquals("H:1", board.getSquareInfo(0, 0));
    }

    @Test
    void testGetCreatureById() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        board.addCreature(creature);
        assertEquals(creature, board.getCreatureById(1));
    }

    @Test
    void testGetEquipment() {
        Equipment equipment = new Equipment(1, 0, position);
        board.addEquipment(equipment);
        assertEquals(equipment, board.getEquipment(1));
    }

    @Test
    void testMove() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        board.addCreature(creature);
        assertTrue(board.move(0, 0, 1, 0));
        assertEquals(1, board.positionId(1, 0));
        assertEquals(0, board.positionId(0, 0));
    }

    @Test
    void testMoveInvalidPosition() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        board.addCreature(creature);
        assertFalse(board.move(0, 0, 5, 5));
    }

    @Test
    void testPositionIsValid() {
        assertTrue(board.positionIsValid(0, 0));
        assertFalse(board.positionIsValid(5, 5));
    }

    @Test
    void testMoveIsValid() {
        assertTrue(board.moveIsValid(0, 0, 1, 0));
        assertFalse(board.moveIsValid(0, 0, 2, 0));
    }

    @Test
    void testPositionId() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        board.addCreature(creature);
        assertEquals(1, board.positionId(0, 0));
    }

    @Test
    void testGetCreatures() {
        Creature creature = new Creature(1, 1, "Test Creature", position);
        board.addCreature(creature);
        ArrayList<Creature> creatures = board.getCreatures();
        assertEquals(1, creatures.size());
        assertEquals(creature, creatures.get(0));
    }

    @Test
    void testGetEquipments() {
        Equipment equipment = new Equipment(1, 0, position);
        board.addEquipment(equipment);
        ArrayList<Equipment> equipments = board.getEquipments();
        assertEquals(1, equipments.size());
        assertEquals(equipment, equipments.get(0));
    }
}