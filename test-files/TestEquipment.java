import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

import static org.junit.jupiter.api.Assertions.*;

public class TestEquipment {

    Equipment equipment;
    int[] position = {0, 0};

    @BeforeEach
    void setUp() {
        equipment = new Equipment(-1, 0, position);
    }

    @Test
    void testGetPositionInBoard() {
        assertArrayEquals(position, equipment.getPositionInBoard());
    }

    @Test
    void testGetId() {
        assertEquals(-1, equipment.getId());
    }

    @Test
    void testGetTypeAsString() {
        assertEquals("0", equipment.getTypeAsString());
    }

    @Test
    void testGetEquipmentInfo() {
        String[] expectedInfo = {"-1", "0", "0", "0", null};
        assertArrayEquals(expectedInfo, equipment.getEquipmentInfo());
    }

    @Test
    void testGetNameOfEquipment() {
        assertEquals("Escudo de madeira", equipment.getNameOfEquipment());
        Equipment samuraiSword = new Equipment(-2, 1, position);
        assertEquals("Espada samurai", samuraiSword.getNameOfEquipment());
    }
}

