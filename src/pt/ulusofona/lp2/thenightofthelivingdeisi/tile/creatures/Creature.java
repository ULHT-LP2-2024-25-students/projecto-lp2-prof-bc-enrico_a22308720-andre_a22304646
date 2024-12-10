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

    abstract public TypeMove getTypeMove(Tile tile, Tile tileDestiny);
//
//
//        if (tile.getCreature() != null){
//            if(tileDestiny.getCreature() == null){
//                if(tileDestiny.getEquipment() == null) {
//                    if(tileDestiny.getDoor() == null){
//                        //empty tile
//                        return TypeMove.MOVE;
//                    }else if(tileDestiny.getDoor() != null){
//                        if (!tile.getCreature().canTransform()){
//                            //tile with door
//                            return TypeMove.SAFEHEAVEN;
//                        }
//                    }
//                } else if(tileDestiny.getEquipment() != null){
//                    //tile with equipment
//                    return TypeMove.WEAPON;
//                }
//            } else if(tile.getCreature().canBeTransformed() ){
//                if(tileDestiny.getCreature().canTransform() && tile.getCreature().getEquipment() != null && tile.getCreature().getEquipment().canAttack()){
//                    //tile with creature LIVE with atack weapon and destination tile with creature DEAD or TRANSFORMED
//
//                    return TypeMove.KILL;
//                }else {
//                    return TypeMove.INVALID;
//                }
//            } else if (tile.getCreature().canTransform()){
//                if (tileDestiny.getCreature().canBeTransformed()){
//                    if ((tileDestiny.getCreature().getEquipment() != null || tileDestiny.getEquipment() != null) &&
//                            (tileDestiny.getCreature().getEquipment().canDefend() || tileDestiny.getCreature().getEquipment().canAttack())){
//                        //tile with creature DEAD and destination tile with creature LIVE with weapon to defend
//                        return TypeMove.DEFENDED;
//                    }
//                    else{
//                        //tile with creature DEAD and destination tile with creature LIVE with no defend weapon
//                        return TypeMove.INFECT;
//                    }
//
//                }else {
//                    //tile with creature DEAD and destination tile with creature DEAD or TRANSFORMED
//                    return TypeMove.INVALID;
//                }
//
//            }
//        }
//        return TypeMove.INVALID;
//    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public boolean canMove(){
        return true;
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
}
