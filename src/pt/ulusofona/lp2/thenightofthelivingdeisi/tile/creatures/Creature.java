package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;


import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

abstract public class Creature {
    protected int[] positionInBoard;
    protected int id;
    protected int team;
    protected String name;
    protected int points;
    protected State state;

    public Creature(int[] positionInBoard, int id, int team, String name, State state) {
        this.positionInBoard = positionInBoard;
        this.id = id;
        this.team = team;
        this.name = name;
        this.points = 0;
        this.state = state;
    }

    public int getPoints() {
        return points;
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

    public TypeMove getTypeMove(Tile tile, Tile tileDestiny){
        if (tile.getCreature() != null){
            if(tileDestiny.getCreature() == null){
                if(tileDestiny.getEquipment() == null) {
                    if(tile.getDoor() == null){
                        //empty tile
                        return TypeMove.MOVE;
                    }else if(tileDestiny.getDoor() != null){
                        //tile with door
                        return TypeMove.SAFEHEAVEN;
                    }
                } else if(tileDestiny.getEquipment() != null){
                    //tile with equipment
                    return TypeMove.WEAPON;
                }
            } else if(tile.getCreature().state == State.LIVE ){
                if (tileDestiny.getCreature().state == State.LIVE){
                    //tile with creature LIVE and destination tile with creature LIVE
                    return TypeMove.INVALID;
                }else {
                    //tile with creature LIVE and destination tile with creature DEAD or TRANSFORMED
                    return TypeMove.KILL;
                }
            } else if (tile.getCreature().state == State.DEAD){
                if (tileDestiny.getCreature().state == State.DEAD || tileDestiny.getCreature().state == State.TRANSFORMED){
                    //tile with creature DEAD and destination tile with creature DEAD or TRANSFORMED
                    return TypeMove.INVALID;
                }else {
                    //tile with creature DEAD and destination tile with creature LIVE
                    return TypeMove.INFECT;
                }

            }
        }
        return TypeMove.INVALID;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public boolean canMove(){
        return true;
    }


    // Abstract Methods
    abstract public void addEquipment(Equipment equipment);
    abstract public String[] getCreatureInfo();
    abstract public String getCreatureInfoAsString();
    abstract public String getIdAndName();
    abstract public boolean moveIsValid (int x0, int y0, int xD, int yD);
    abstract public boolean canBeTransformed();
    abstract public boolean canTransform();
    abstract public  boolean canHoldEquipment();
    abstract public  boolean canDestroyEquipment();
    abstract public boolean hasEquipment(int equipmentTypeId);





}
