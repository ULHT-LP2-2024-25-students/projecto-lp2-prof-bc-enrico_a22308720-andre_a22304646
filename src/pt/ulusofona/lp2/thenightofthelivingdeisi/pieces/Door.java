package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces;

public class Door extends Piece {


    public Door(int[] positionInBoard) {
        super(positionInBoard,0);
    }


    @Override
    public String getSquareInfo() {
        return "SH";
    }
}
