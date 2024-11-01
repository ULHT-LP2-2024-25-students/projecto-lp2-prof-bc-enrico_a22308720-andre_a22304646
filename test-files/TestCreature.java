import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreature {

    Creature creature;
    int[] position = {0, 0};

    @BeforeEach
    void setUp() {
        creature = new Creature(1, 1, "Test Creature", position);
    }

    @Test
    void testGetCreatureInfo() {
        String[] expectedInfo = {"1", "Humano", "Test Creature", "0", "0", null};
        assertArrayEquals(expectedInfo, creature.getCreatureInfo());
    }

    @Test
    void testIncreasePoint() {
        creature.increasePoint();
        assertEquals(1, creature.getPoints());
    }

    @Test
    void testGetPoints() {
        assertEquals(0, creature.getPoints());
        creature.increasePoint();
        assertEquals(1, creature.getPoints());
    }

    @Test
    void testGetId() {
        assertEquals(1, creature.getId());
    }

    @Test
    void testGetTeam() {
        assertEquals(1, creature.getTeam());
    }

    @Test
    void testHasEquipment() {
        Equipment equipment = new Equipment(1, 0, position);
        creature.addEquipment(equipment);
        assertTrue(creature.hasEquipment(0));
        assertFalse(creature.hasEquipment(1));
    }

    @Test
    void testGetPositionInBoard() {
        assertArrayEquals(position, creature.getPositionInBoard());
    }

    @Test
    void testMove() {
        int[] newPosition = {1, 1};
        creature.move(1, 1);
        assertArrayEquals(newPosition, creature.getPositionInBoard());
    }

    @Test
    void testAddEquipment() {
        Equipment equipment = new Equipment(1, 0, position);
        creature.addEquipment(equipment);
        assertTrue(creature.hasEquipment(0));
    }

    @Test
    void testGetIdAndName() {
        assertEquals("1 Test Creature", creature.getIdAndName(creature));
    }

    @Test
    void testIsHuman() {
        assertTrue(creature.isHuman());
        Creature zombie = new Creature(2, 0, "Zombie Creature", position);
        assertFalse(zombie.isHuman());
    }
}
