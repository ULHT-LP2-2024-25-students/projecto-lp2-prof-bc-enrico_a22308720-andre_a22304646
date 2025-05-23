package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.*;

abstract public class Equipment {

    protected int type;
    protected String name;
    protected int[] positionInBoard;
    protected int id;
    protected boolean isHolded;
    public Equipment(int type, int[] positionInBoard, int id) {
        this.type = type;
        this.positionInBoard = positionInBoard;
        this.id = id;
        this.isHolded=false;
    }


    public void setPositionInBoard(int x, int y){
        this.positionInBoard[0] = x;
        this.positionInBoard[1] = y;
    }

    public void hold(){
        this.isHolded=true;
   }

    public void drop(){
        this.isHolded=false;
    }

    public boolean isHolded() {
        return isHolded;
    }

    public int[] getPositionInBoard() {
        return positionInBoard;
    }

    public String getSquareInfo(){
        return "E:" + id;
    }

    public int getId() {
        return id;
    }

    public String[] getEquipmentInfo() {
        String[] equipmentInfo = new String[5];
        equipmentInfo[0] = "" + id;
        equipmentInfo[1] = "" + type;
        equipmentInfo[2] = "" + positionInBoard[0];
        equipmentInfo[3] = "" + positionInBoard[1];
        equipmentInfo[4] = null;
        return equipmentInfo;
    }

    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @ (" + positionInBoard[0] + ", " + positionInBoard[1] + ")";
    }

    public int getType() {
        return type;
    }

    public void defend(){}

    public String getSave(){
        return id + " : " + type + " : " + positionInBoard[0] + " : " + positionInBoard[1];
    }

    abstract public boolean canAttack();
    abstract public boolean canDefend();

    public static Equipment createEquipment(int equipmentType, int[] positionInBoard, int equipmentId) {
        Equipment equipment = null;
        switch (equipmentType) {
            case 0: {
                equipment = new Shield(equipmentType, positionInBoard, equipmentId);
                break;
            } case 1: {
                equipment = new Sword(equipmentType, positionInBoard, equipmentId);
                break;
            } case 2: {
                equipment = new Pistol(equipmentType, positionInBoard, equipmentId);
                break;
            } case 3: {
                equipment = new Leach(equipmentType, positionInBoard, equipmentId);
                break;
            } default:{
                return null;
            }
        }
        return equipment;
    }

}
