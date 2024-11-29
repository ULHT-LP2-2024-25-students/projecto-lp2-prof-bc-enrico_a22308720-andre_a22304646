package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Piece;

abstract public class Equipment extends Piece {
    protected int id;
    protected int type;
    protected String name;


    public Equipment(int[] positionInBoard, int id, int type) {
        super(positionInBoard);
        this.id = id;
        this.type = type;
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
        return id + " | " + name + " @ (" + positionInBoard[0] + "," + positionInBoard[1] + ")";
    }

    abstract public boolean canAttack();
    abstract public boolean canDefend();

}
