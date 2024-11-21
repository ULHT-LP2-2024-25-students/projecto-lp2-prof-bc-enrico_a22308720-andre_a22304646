package pt.ulusofona.lp2.thenightofthelivingdeisi.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.Piece;

public class Creatures extends Piece {
    int id;
    int team;
    String name;
    int points;
    State state;

    public Creatures(int[] positionInBoard, int id, int team, String name, int points, State state) {
        super(positionInBoard);
        this.id = id;
        this.team = team;
        this.name = name;
        this.points = 0;
        this.state = state;
    }
}
