package pt.ulusofona.lp2.thenightofthelivingdeisi.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.Equipment;

public class Adult extends Creatures {
    private Equipment equipment;

    public Adult(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
        this.equipment = null;
    }
}
