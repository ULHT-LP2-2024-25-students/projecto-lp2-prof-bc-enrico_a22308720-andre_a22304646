package pt.ulusofona.lp2.thenightofthelivingdeisi.tile;



public class Door {

    protected int[] positionInBoard;


    public Door(int[] positionInBoard) {
        this.positionInBoard = positionInBoard;
    }

    public String getSquareInfo(){
        return "SH";
    }



}
