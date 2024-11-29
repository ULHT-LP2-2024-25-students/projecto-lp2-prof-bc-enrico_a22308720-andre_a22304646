package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Piece;

public class Creature extends Piece {
    protected int id;
    protected int team;
    protected String name;
    protected int points;
    protected State state;

    public Creature(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard);
        this.id = id;
        this.team = team;
        this.name = name;
        this.points = 0;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[6];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = team == 0? "Zombie" : "Humano";
        creatureInfo[2] = name;
        creatureInfo[3] = "" + positionInBoard[0];
        creatureInfo[4] = "" + positionInBoard[1];
        creatureInfo[5] = null;

        return creatureInfo;
    }

    public Creature(int[] positionInBoard, int team) {
        super(positionInBoard);
        this.team = team;
    }
}
