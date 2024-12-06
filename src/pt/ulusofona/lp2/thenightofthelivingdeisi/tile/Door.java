package pt.ulusofona.lp2.thenightofthelivingdeisi.tile;

import pt.ulusofona.lp2.thenightofthelivingdeisi.Piece;

public class Door extends Piece {


    public Door(int[] positionInBoard) {
        super(positionInBoard,0);
    }


    @Override
    public String getSquareInfo() {
        return "SH";
    }

    public boolean canMove(){
        return false;
    }

    @Override
    public boolean canBeHolded() {
        return false;
    }

    public boolean moveIsValid (int x0, int y0, int xD, int yD){
        return false;
    }

    public boolean canTransform(){return false;}

    public boolean canBeTransformed(){return false;}
}
