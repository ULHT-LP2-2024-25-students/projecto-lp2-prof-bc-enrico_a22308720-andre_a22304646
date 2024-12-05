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

    abstract public boolean canMove();

    abstract public boolean canBeHolded();

    abstract public String getSquareInfo();

    abstract public boolean moveIsValid (int x0, int y0, int xD, int yD);

    abstract public boolean canTransform();

    abstract public boolean canBeTransformed();

    public void changePositionInBoard(int x, int y){
        this.positionInBoard[0] = x;
        this.positionInBoard[1] = y;
    }
}
