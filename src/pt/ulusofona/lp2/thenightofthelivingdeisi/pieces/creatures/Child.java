package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Equipment;

public class Child extends Creature {
    private Equipment equipment = null;

    public Child(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
        this.equipment = null;
    }
}
