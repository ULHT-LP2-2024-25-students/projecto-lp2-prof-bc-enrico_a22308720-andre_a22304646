package pt.ulusofona.lp2.thenightofthelivingdeisi;

import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.LegacyEquipment;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Piece;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments.Equipment;

import java.util.ArrayList;

public class Board {
    Piece[][] board;                  // board contains creature or equipmnet id. If empty 0
    ArrayList<Creature> creatures;
    ArrayList<Equipment> equipments;
    ArrayList<Creature> safeHeaven;


    public Board(int rows, int columns){
        this.board = new Piece[rows][columns];
        this.creatures=new ArrayList<>();
        this.equipments=new ArrayList<>();
    }

    public boolean addCreature(Creature creature){
        int [] position = creature.getPositionInBoard();
        if (!positionIsValid(position[0],position[1]) ){
            return false;
        }
        if (board[position[0]][position[1]] != null){
            return false;
        }
        board[position[0]][position[1]] = creature;
        creatures.add(creature);
        return true;
    }

    public void addEquipment(Equipment equipment){
        int[] position = equipment.getPositionInBoard();
        board[position[0]][position[1]] = equipment;
        equipments.add(equipment);
    }

    public void addDoor(Door door){
        board[door.getPositionInBoard()[1]][door.getPositionInBoard()[0]]=door;
    }

    public void removeEquipment (Equipment equipment){
        equipments.remove(equipment);
    }

    public int getSizeX() {
        return board[0].length;
    }

    public int getSizeY() {
        return board.length;
    }

    public String getSquareInfo(int x, int y) {
        String result="";
        if (board[y][x] == null){
            result+="";
        }
        if (board[y][x] < 0 ){
            result+="E:" + board[x][y];
        }
        if (board[y][x] > 0 ){
            LegacyCreature creature = getCreatureById(board[x][y]);
            if(creature.getTeam() == 0) {
                result += "Z:"+ board[x][y];
            }
            if(creature.getTeam() == 1){
                result += "H:" + board[x][y];
            }
        }

        return result;
    }

    public Creature getCreatureById(int id){
        for(Creature atualCreature : creatures){
            if(atualCreature.getId() == id) {
                return atualCreature;
            }
        }
        return  null;
    }

    public Equipment getEquipmentById(int id){
        for(Equipment equipment : equipments){
            if(equipment.getId() == id){
                return equipment;
            }
        }
        return null;
    }

    public boolean move(int x0, int y0, int xD, int yD){
        LegacyCreature creature;
        if (positionIsValid(x0, y0)){               // verificar se a posicao de origem e valida
            if(positionId(x0, y0) > 0){             // verificar se na posicao de origem esta uma criatura
                creature = getCreatureById(positionId(x0, y0));         //obter a criatura a mover

                // verificar se posicao de destino e valida, se o movimento e valido e se a posicao de destino esta vazia
                if ((positionIsValid(xD, yD) && moveIsValid(x0,y0,xD,yD)) && (positionId(xD, yD) <= 0)){
                    if (positionId(xD, yD) < 0){
                        if (creature.isHuman()){
                            creature.increasePoint();
                            creature.addEquipment(getEquipmentById(positionId(xD, yD)));  //se for equipamento fica com a criatura
                        }else{
                            creature.increasePoint();

                        }
                        removeEquipment(getEquipmentById(positionId(xD, yD)));                  //elimina equipamento da lisat
                    }
                    board[xD][yD]=positionId(x0,y0);                                    // posicao destino recebe id da origem
                    board[x0][y0]=0;                                                    //posicao da origem recebe 0
                    creature.move(xD, yD);

                    return true;
                }
            }
        }
        return false;
    }

    public boolean positionIsValid(int x, int y) {
        return x >= 0 && x < getSizeX() && y >= 0 && y < getSizeY();
    }

    public boolean moveIsValid (int x0, int y0, int xD, int yD){
        if((Math.abs(xD-x0) == 1) && (yD-y0 == 0)){
            return true;
        }
        if((Math.abs(yD-y0) == 1) && (xD-x0 == 0)){
            return true;
        }

        return false;
    }

    public int positionId(int x, int y){
        return board[x][y];
    }

    public ArrayList<Creature> getCreatures (){return creatures;}



}
