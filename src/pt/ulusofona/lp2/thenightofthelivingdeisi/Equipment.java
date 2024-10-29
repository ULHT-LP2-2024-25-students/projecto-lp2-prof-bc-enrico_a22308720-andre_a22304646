package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Equipment {
    private int id;
    private int type;
    private String name;
    private int[] positionInBoard;

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
        return String.valueOf(type);
    }

    public String[] getEquipmentInfo() {
        return new String[]{String.valueOf(id), String.valueOf(type), String.valueOf(positionInBoard[0]), String.valueOf(positionInBoard[1]), null};
    }

    public String getNameOfEquipment() {
        return this.type == 0 ? "Escudo de Madeira" : "Espada samurai";
    }
}
