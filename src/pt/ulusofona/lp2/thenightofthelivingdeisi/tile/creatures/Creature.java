package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;


import pt.ulusofona.lp2.thenightofthelivingdeisi.InvalidFileException;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

abstract public class Creature {
    protected int[] positionInBoard;
    protected int id;
    protected int team;
    protected String name;
    protected int points;
    protected State state;
    protected Equipment equipment;
    protected int type;

    public Creature(int[] positionInBoard, int id, int team, String name, State state, int type) {
        this.positionInBoard = positionInBoard;
        this.id = id;
        this.team = team;
        this.name = name;
        this.points = 0;
        this.state = state;
        this.equipment=null;
        this.type = type;
    }

    public void increasePoints(){points++;}

    public State getState() {
        return state;
    }

    public void setPositionInBoard(int x, int y) {
        this.positionInBoard[0] = x;
        this.positionInBoard[1] = y;
    }

    public String getSquareInfo() {
        if (state == State.LIVE) {
            return "H:" + id;
        } else {
            return "Z:" + id;
        }
    }

    public int getId() {
        return id;
    }

    abstract public TypeMove getTypeMove(Tile tile, Tile tileDestiny);


    public void setState(State state) {
        this.state = state;
    }

    public void setTeam(int team) {
        this.team = team;
    }


    public int getTeam() {
        return team;
    }

    public Equipment getEquipment() {
        return null;
    }

    public void removeEquipment (){
        if (this.equipment != null){
            this.equipment.drop();
            this.equipment = null;
        }
    }

    public String getSave(){
        return id + " : " + team + " : " + type + " : " + name + " : " + positionInBoard[0] + " : " + positionInBoard[1];
    }

public void setPositionNull(){
    this.positionInBoard[0] = -1;
    this.positionInBoard[1] = -1;
}

    // Abstract Methods
    abstract public void addEquipment(Equipment equipment);
    abstract public String[] getCreatureInfo();
    abstract public String getCreatureInfoAsString();
    abstract public String getIdAndName();
    abstract public boolean moveIsValid (int x0, int y0, int xD, int yD);
    abstract public boolean canBeTransformed();
    abstract public boolean canTransform();
    abstract public  boolean canHoldEquipment(Equipment equipment);
    abstract public  boolean canDestroyEquipment();
    abstract public boolean hasEquipment(int equipmentTypeId);
    abstract public boolean canMoveAtNight();
    abstract public boolean canMoveAtDay();

    public static Creature createCreature(int creatureType, int[] positionInBoard, int creatureId, int teamId, String creatureName) {
        Creature creature = null;
        if (creatureType < 0 || creatureType > 4){return null;}
        if (teamId != 10 && teamId != 20 ){return null;}
        switch (creatureType) {
            case 0: {
                creature = new Child(positionInBoard, creatureId, teamId, creatureName, teamId == 10 ? State.DEAD : State.LIVE, creatureType);
                break;
            } case 1: {
                creature = new Adult(positionInBoard, creatureId, teamId, creatureName, teamId == 10 ? State.DEAD : State.LIVE, creatureType);
                break;
            } case 2: {
                creature = new Old(positionInBoard, creatureId, teamId, creatureName, teamId == 10 ? State.DEAD : State.LIVE, creatureType);
                break;
            } case 3: {
                if (teamId != 20){
                    return null;
                }
                creature = new Dog(positionInBoard, creatureId, teamId, creatureName, State.LIVE, creatureType);
                break;
            } case 4: {
                creature = new Vampire(positionInBoard, creatureId, teamId, creatureName, State.DEAD, creatureType);
                break;
            } default: {
                return null;
            }
        }
        return creature;
    }
}
