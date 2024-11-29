package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces;

abstract public class Piece {
    protected int[] positionInBoard;


    public Piece(int[] positionInBoard) {
        this.positionInBoard = positionInBoard;
    }

    public int[] getPositionInBoard(){
        return this.positionInBoard;
    }


}
