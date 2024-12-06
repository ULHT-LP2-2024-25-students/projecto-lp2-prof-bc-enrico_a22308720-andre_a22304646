package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures;

import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Piece;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments.Equipment;

abstract public class Creature extends Piece {
    protected int team;
    protected String name;
    protected int points;
    protected State state;

    public Creature(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard,id);
        this.id = id;
        this.team = team;
        this.name = name;
        this.points = 0;
        this.state = state;
    }

    public int getPoints() {
        return points;
    }
    public State getState() {
        return state;
    }

    abstract public String[] getCreatureInfo();
    abstract public String getCreatureInfoAsString();
    abstract public boolean hasEquipment(int equipmentTypeId);
    abstract public String getIdAndName();
    abstract public boolean moveIsValid (int x0, int y0, int xD, int yD);
    abstract public Creature interactCreature(Creature piece);
    abstract public boolean destroyEquipment(Piece piece);
    abstract public boolean canBeTransformed();
    abstract public boolean canTransform();

    public String getSquareInfo(){
        if(state == State.LIVE){
            return "H:" + id;
        }else{
            return "Z:" + id;
        }
    }

    public boolean canMove(){
        return true;
    }

}
