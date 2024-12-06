package pt.ulusofona.lp2.thenightofthelivingdeisi;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.State;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.TypeMove;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;

import java.util.ArrayList;
import java.util.Comparator;

public class Board {
    Tile[][] boardTiles;
    ArrayList<Creature> safeHeaven;

    public Board(int rows, int columns) {
        this.boardTiles = new Tile[rows][columns];
        //Precisamos inicializar cada elemento
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                boardTiles[y][x] = new Tile();
            }
        }
    }
    public void addCreature(Creature creature, int x, int y) {
        boardTiles[y][x].addCreature(creature, x, y);
    }

    public void addEquipment(Equipment equipment, int  x, int y) {
        boardTiles[y][x].addEquipment(equipment, x, y);
    }

    public void removeEquipment(int x, int y) {
        boardTiles[y][x].removeEquipment();
    }
    public void removeCreature(int x, int y) {
        boardTiles[y][x].removeCreature();
    }
    public void addDoor(Door door, int x, int y) {
        boardTiles[y][x].addDoor(door);
    }

    public boolean positionIsValid(int x, int y) {
        return x >= 0 && x < boardTiles[0].length && y >= 0 && y < boardTiles.length;
    }


    public int getSizeX() {
        return boardTiles[0].length;
    }

    public int getSizeY() {
        return boardTiles.length;
    }

    public String getSquareInfo(int x, int y) {
        return boardTiles[y][x].getSquareInfo();
    }

    public Creature getCreatureById(int id) {
        for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[0].length; j++) {
                if (boardTiles[i][j].getCreature() != null && boardTiles[i][j].getCreature().getId() == id) {
                    return boardTiles[i][j].getCreature();
                }
            }
        }
        return null;
    }

    public Equipment getEquipmentById(int id) {
        for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[0].length; j++) {
                if (boardTiles[i][j].getEquipment() != null && boardTiles[i][j].getEquipment().getId() == id) {
                    return boardTiles[i][j].getEquipment();
                }
            }
        }
        return null;
    }

    public boolean move(int x0, int y0, int xD, int yD) {
        if (positionIsValid(xD, yD) && boardTiles[y0][x0].getCreature() != null) {
            Tile tile = boardTiles[y0][x0];
            Tile tileD = boardTiles[yD][xD];
            TypeMove typeMove = tile.getCreature().getTypeMove(tile, tileD);
            if (tile.getCreature().moveIsValid(x0, y0, xD, yD)) {
                Creature creature = boardTiles[y0][x0].getCreature();
                switch (typeMove) {
                    case INVALID:
                        return false;
                    case MOVE:
                        tile.removeCreature();
                        tileD.addCreature(creature, yD, xD);
                        return true;
                    case WEAPON:
                        if (creature.canHoldEquipment()){
                            tile.removeCreature();
                            creature.addEquipment(tileD.getEquipment());
                            tileD.removeEquipment();
                            tileD.addCreature(creature, yD, xD);
                            return true;
                        }else if(creature.canDestroyEquipment()){
                            tileD.removeEquipment();
                            creature.increasePoints();
                            return true;
                        }else{
                            return false;
                        }
                    case INFECT:
                        Creature newZombie = tileD.getCreature();
                        newZombie.setState(State.TRANSFORMED);
                        newZombie.setTeam(10);
                        boardTiles[yD][xD].setCreature(newZombie);
                        return true;
                    case KILL:
                        tile.removeCreature();
                        tileD.removeCreature();
                        tileD.addCreature(creature, yD, xD);
                        return true;
                    case SAFEHEAVEN:
                        tile.removeCreature();
                        safeHeaven.add(creature);
                        return true;
                }
            }

            /*
            //move to empty space
            if (boardTiles[yD][xD].getCreature() == null && boardTiles[yD][xD].getCreature().moveIsValid(x0, y0, xD, yD)) {
                Creature creature = boardTiles[y0][x0].getCreature();
                Tile tile = boardTiles[y0][x0];
                Tile tileD = boardTiles[yD][xD];
                tile.removeCreature();
                tileD.addCreature(creature, yD, xD);
            }
            //move to equipment
            else if (boardTiles[yD][xD].getEquipment() != null && boardTiles[yD][xD].getCreature() == null) {
                Creature creature = boardTiles[y0][x0].getCreature();
                Equipment equipment = boardTiles[yD][xD].getEquipment();
                Tile tile = boardTiles[y0][x0];
                Tile tileD = boardTiles[yD][xD];
                tile.removeCreature();
                if(!creature.canHoldEquipment()) {
                    tileD.removeEquipment();
                    tileD.addCreature(creature, yD, xD);
                    tile.addEquipment(equipment, yD, xD);
                } else {
                    tileD.removeEquipment();
                    tileD.addCreature(creature, yD, xD);
                    tile.addEquipment(equipment, yD, xD);
                }

            }*/
        }
        return false;
    }

    public ArrayList<Creature> getCreatures (){
        ArrayList<Creature> creatures = new ArrayList<>();
        for (int y=0; y<getSizeY(); y++) {
            for (int x = 0; x < getSizeX(); x++) {
                if (boardTiles[y][x].getCreature() != null) {
                    creatures.add(boardTiles[y][x].getCreature());
                }
            }
        }
        creatures.sort(Comparator.comparingInt(Creature::getId));
        return creatures;
    }
}
