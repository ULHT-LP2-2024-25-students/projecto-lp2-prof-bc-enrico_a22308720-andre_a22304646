package pt.ulusofona.lp2.thenightofthelivingdeisi.tile;

import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

public class Tile {
    public Creature creature;
    Equipment equipment;
    Door door;

    public Tile() {
        this.creature = null;
        this.equipment = null;
        this.door = null;
    }

    public void addCreature(Creature creature, int x, int y) {
        this.creature = creature;
        this.creature.setPositionInBoard(x,y);
    }
    public void addEquipment(Equipment equipment, int x, int y) {
        this.equipment = equipment;
        this.equipment.setPositionInBoard(x, y);
    }
    public void removeEquipment() {
        this.equipment = null;
    }
    public void removeCreature() {
        this.creature = null;
    }
    public void addDoor(Door door) {
        this.door = door;
    }
    public String getSquareInfo() {
        if (creature != null) {
            return creature.getSquareInfo();
        } else if (equipment != null) {
            return equipment.getSquareInfo();
        } else if (door != null) {
            return door.getSquareInfo();
        } else {
            return "";
        }
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Door getDoor() {return door;
    }
}
