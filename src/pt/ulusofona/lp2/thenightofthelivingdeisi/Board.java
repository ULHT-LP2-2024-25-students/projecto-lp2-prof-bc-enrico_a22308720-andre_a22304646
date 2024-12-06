//package pt.ulusofona.lp2.thenightofthelivingdeisi;
//
//import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.Creature;
//import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;
//
//import java.util.ArrayList;
//
//public class Board {
//    Piece[][] board;
//    ArrayList<Creature> creatures;
//    ArrayList<Equipment> equipments;
//    ArrayList<Creature> safeHeaven;
//
//
//    public Board(int rows, int columns){
//        this.board = new Piece[rows][columns];
//        this.creatures=new ArrayList<>();
//        this.equipments=new ArrayList<>();
//    }

//    public boolean addCreature(Creature creature){
//        int [] position = creature.getPositionInBoard();
//        if (!positionIsValid(position[1],position[0]) ){
//            return false;
//        }
//        if (board[position[1]][position[0]] != null){
//            return false;
//        }
//        board[position[1]][position[0]] = creature;
//        creatures.add(creature);
//        return true;
//    }

//    public void addEquipment(Equipment equipment){
//        int[] position = equipment.getPositionInBoard();
//        board[position[1]][position[0]] = equipment;
//        equipments.add(equipment);
//    }

//    public void addDoor(Door door){
//        board[door.getPositionInBoard()[1]][door.getPositionInBoard()[0]]=door;
//    }

//    public void removeEquipment (Equipment equipment){
//        equipments.remove(equipment);
//    }

//    public int getSizeX() {
//        return board[0].length;
//    }
//
//    public int getSizeY() {
//        return board.length;
//    }

//    public String getSquareInfo(int x, int y) {
//        if (board[y][x] == null){
//            return "";
//        }else{
//            return board[y][x].getSquareInfo();
//        }
//    }

//    public Creature getCreatureById(int id){
//        for(Creature atualCreature : creatures){
//            if(atualCreature.getId() == id) {
//                return atualCreature;
//            }
//        }
//        return  null;
//    }

//    public Equipment getEquipmentById(int id){
//        for(Equipment equipment : equipments){
//            if(equipment.getId() == id){
//                return equipment;
//            }
//        }
//        return null;
//    }

//todo ajuda na interacao entre pecas


//todo e se eu fizer um .toCast abstract na piece e no creature e equipments e assim ela receberia um piece e retornaria do tipo que ela e para cada uma


//    public boolean move(int x0, int y0, int xD, int yD){
//        if (positionIsValid(x0,y0) && positionIsValid(xD,yD)){
//            if(board[y0][x0] != null && board[y0][x0].canMove()){
//                Creature creature =  (Creature)board[y0][x0];
//                Piece pieceDestiny =  board[yD][xD];
//                if (creature.moveIsValid(x0, y0, xD, yD)){
//                    if (pieceDestiny == null){
//                        creature.changePositionInBoard(xD,yD);
//                        board[yD][xD] = creature;
//                        board[y0][x0] = null;
//                    }else if(pieceDestiny.canBeHolded()){
//                        if (creature.destroyEquipment(pieceDestiny)){
//                            board[yD][xD] = null;
//                        }
//                        creature.changePositionInBoard(xD,yD);
//                        board[yD][xD] = creature;
//                    }
//                }
//            }
//        }
//        return false;
//    }

//    public boolean positionIsValid(int x, int y) {
//        return x >= 0 && x < getSizeX() && y >= 0 && y < getSizeY();
//    }

//    public int positionId(int x, int y){
//        return board[y][x].getId();
//    }

//    public ArrayList<Creature> getCreatures (){return creatures;}

}
