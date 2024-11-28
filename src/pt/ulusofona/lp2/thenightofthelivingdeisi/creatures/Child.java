package pt.ulusofona.lp2.thenightofthelivingdeisi.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

public class Child extends Creature {
    private Equipment equipment = null;

    public Child(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
        this.equipment = null;
    }
}
