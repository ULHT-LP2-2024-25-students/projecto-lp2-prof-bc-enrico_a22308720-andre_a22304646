package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    int[][] board;                  // board contains creature or equipmnet id. If empty 0


    public Board(int rows, int columns) {
        this.board = new int [rows][columns];
    }

    boolean addCreature(Creature creature){
        int [] position = creature.getPositionInBoard();
        if (position[0] < board.length && position[1] < board[0].length && board[position[0]][position[1]] != 0 ){
            return false;
        }
        board[position[0]][position[1]] = creature.getId();
        return true;
    }

    void addEquipment(Equipment equipment) {
        int[] position = equipment.getPositionInBoard();
        board[position[0]][position[1]] = equipment.getId();
    }

}