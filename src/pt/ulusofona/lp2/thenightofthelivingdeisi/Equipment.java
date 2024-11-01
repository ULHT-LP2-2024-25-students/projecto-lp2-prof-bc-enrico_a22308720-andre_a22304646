package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Equipment {
    int id;
    int type;
    String name;
    int[] positionInBoard;


    public Equipment(int id, int type, int[] positionInBoard) {
        this.id = id;
        this.type = type;
        this.positionInBoard = positionInBoard;
    }

    public int[] getPositionInBoard() {
        return this.positionInBoard;
    }

    public int getId() {
        return id;
    }

    public String getTypeAsString() {
        return "" + type;
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

    public String getNameOfEquipment() {
        return this.type == 0 ? "Escudo de madeira" : "Espada samurai";
    }
}
