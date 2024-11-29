package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Equipment;

public class Adult extends Creature {
    private Equipment equipment;

    public Adult(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
        this.equipment = null;
    }
}
