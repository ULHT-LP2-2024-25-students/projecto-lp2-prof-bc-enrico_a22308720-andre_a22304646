package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    int[][] board;
    ArrayList<Creature> creaturesOnBoard;
    ArrayList<Equipment> equipmentOnBoard;

    public Board() {
        this.creaturesOnBoard=new ArrayList<>();
        this.equipmentOnBoard=new ArrayList<>();
    }

    boolean createBoard (int rows, int columns){
        if (board==null){
            board = new int[rows][columns];
            return true;
        }
        return false;
    }

    boolean addCreature(Creature creature){
        if (creaturesOnBoard.contains(creature)){
            return false;
        }
        creaturesOnBoard.add(creature);
        return true;
    }

    void addEquipment(Equipment equipment){
        equipmentOnBoard.add(equipment);
    }
}