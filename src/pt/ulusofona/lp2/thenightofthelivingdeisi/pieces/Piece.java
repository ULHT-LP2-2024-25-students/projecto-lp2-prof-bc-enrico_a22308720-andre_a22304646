package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces;

abstract public class Piece {
    protected int[] positionInBoard;
    protected int id;



    public Piece(int[] positionInBoard, int id) {
        this.positionInBoard = positionInBoard;
        this.id = id;
    }

    public int[] getPositionInBoard(){
        return this.positionInBoard;
    }

    public int getId() {
        return id;
    }
}
