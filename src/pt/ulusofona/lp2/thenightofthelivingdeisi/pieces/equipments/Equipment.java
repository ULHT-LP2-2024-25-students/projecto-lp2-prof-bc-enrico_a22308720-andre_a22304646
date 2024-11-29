package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Piece;

public class Equipment extends Piece {
    protected int id;
    protected int type;
    protected String name;

    public Equipment(int[] positionInBoard, int id, int type, String name) {
        super(positionInBoard);
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
