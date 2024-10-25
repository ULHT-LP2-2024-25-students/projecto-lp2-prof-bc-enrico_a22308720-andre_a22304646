package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Equipment {
    int id;
    int type;
    String name;
    int[] positionInBoard;



    public Equipment(int id, int type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.positionInBoard=new int[2];
    }

    public Equipment(int id, int type, String name, int[] positionInBoard) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.positionInBoard = positionInBoard;
    }

    int[] getPositionInBoard(){return this.positionInBoard;}

    String getTypeAsString(){return "" + type;}

    String[] getEquipmentInfo(){
        String[] equipmentInfo = new String[5];
        equipmentInfo[0] = "" + id;
        equipmentInfo[1] = "" + type;
        equipmentInfo[2] = "" + positionInBoard[0];
        equipmentInfo[3] = "" + positionInBoard[1];
        equipmentInfo[4] = null;
        return equipmentInfo;
    }
}
