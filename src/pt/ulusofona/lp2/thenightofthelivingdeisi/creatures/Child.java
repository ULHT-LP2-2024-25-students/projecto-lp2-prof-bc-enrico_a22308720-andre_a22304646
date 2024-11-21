package pt.ulusofona.lp2.thenightofthelivingdeisi.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

public class Child extends Creatures {
    Equipment equipment;

    public Child(int[] positionInBoard, int id, int team, String name, int points, State state, Equipment equipment) {
        super(positionInBoard, id, team, name, points, state);
        this.equipment = equipment;
    }
}
