package pt.ulusofona.lp2.thenightofthelivingdeisi.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

public class Adult extends Creatures {
    Equipment equipment;

    public Adult(int[] positionInBoard, int id, int team, String name, int points, State state, Equipment equipment) {
        super(positionInBoard, id, team, name, points, state);
        this.equipment = null;
    }
}
