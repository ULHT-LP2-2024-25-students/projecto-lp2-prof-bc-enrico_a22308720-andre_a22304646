package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Creature {
    int id;
    int team;
    String name;
    int[] positionInBoard;
    ArrayList<Equipment> equipments;

    public Creature(int id, int team, String name, int[] positionInBoard, ArrayList<Equipment> equipments) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.positionInBoard= new int[2];
        this.equipments = new ArrayList<Equipment>();
    }

    public Creature(int id, int team, String name, int[] positionInBoard) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.positionInBoard = positionInBoard;
        this.equipments=new ArrayList<>();
    }

    String[] getCreatureInfo(){
        String[] creatureInfo = new String[6];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "" + team;
        creatureInfo[2] = name;
        creatureInfo[3] = "" + positionInBoard[0];
        creatureInfo[4] = "" + positionInBoard[1];
        creatureInfo[5] = null;

        return creatureInfo;
    }

    boolean hasEquipment(int type){
        for (Equipment actualEquipmet: equipments){
            if (actualEquipmet.getTypeAsString().equals("" + type)){
                return true;
            }
        }
        return false;
    }

    int[] getPositionInBoard(){
        return this.positionInBoard;
    }


}
