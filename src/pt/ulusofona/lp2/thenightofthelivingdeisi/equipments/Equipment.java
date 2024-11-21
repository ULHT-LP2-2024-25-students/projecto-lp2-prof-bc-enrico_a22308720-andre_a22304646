package pt.ulusofona.lp2.thenightofthelivingdeisi.equipments;
import pt.ulusofona.lp2.thenightofthelivingdeisi.Piece;

public class Equipment extends Piece {
    int id;
    int type;
    String name;

    public Equipment(int[] positionInBoard, int id, int type, String name) {
        super(positionInBoard);
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
