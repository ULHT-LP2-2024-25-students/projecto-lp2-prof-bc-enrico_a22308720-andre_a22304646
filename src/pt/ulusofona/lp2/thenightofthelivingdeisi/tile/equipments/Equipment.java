package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.State;

abstract public class Equipment {

    protected int type;
    protected String name;
    protected int[] positionInBoard;
    protected int id;

    public Equipment(int type, String name, int[] positionInBoard, int id) {
        this.type = type;
        this.name = name;
        this.positionInBoard = positionInBoard;
        this.id = id;
    }


    public void setPositionInBoard(int y, int x){
        this.positionInBoard[0] = y;
        this.positionInBoard[1] = x;
    }

    public String getSquareInfo(){
        return "E:" + id;
    }

    public int getId() {
        return id;
    }

//    public String[] getEquipmentInfo() {
//        String[] equipmentInfo = new String[5];
//        equipmentInfo[0] = "" + id;
//        equipmentInfo[1] = "" + type;
//        equipmentInfo[2] = "" + positionInBoard[0];
//        equipmentInfo[3] = "" + positionInBoard[1];
//        equipmentInfo[4] = null;
//        return equipmentInfo;
//    }

//    public String getEquipmentInfoAsString() {
//        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ")";
//    }

//    abstract public boolean canAttack();
//    abstract public boolean canDefend();




//    public boolean canMove(){
//        return false;
//    }
//
//    public boolean moveIsValid (int x0, int y0, int xD, int yD){
//        return false;
//    }
//
//    @Override
//    public boolean canBeHolded(){
//        return true;
//    }
//
//    public boolean canTransform(){return false;}
//
//    public boolean canBeTransformed(){return false;}
//
//    abstract public void atack();
}
