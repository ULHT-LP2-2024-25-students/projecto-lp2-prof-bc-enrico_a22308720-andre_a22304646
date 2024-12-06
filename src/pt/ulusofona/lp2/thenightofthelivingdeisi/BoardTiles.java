package pt.ulusofona.lp2.thenightofthelivingdeisi;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.State;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.TypeMove;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;

import java.util.ArrayList;

public class BoardTiles {
    Tile[][] boardTiles;
    ArrayList<Creature> safeHeaven;

    public BoardTiles(int rows, int columns) {
        this.boardTiles = new Tile[rows][columns];
    }


    public void addCreature(Creature creature, int y, int x) {
        boardTiles[y][x].addCreature(creature, y, x);
    }

    public void addEquipment(Equipment equipment, int  y, int x) {
        boardTiles[y][x].addEquipment(equipment, y, x);
    }

    public void removeEquipment(int y, int x) {
        boardTiles[y][x].removeEquipment();
    }

    public void removeCreature(int y, int x) {
        boardTiles[y][x].removeCreature();
    }
    public void addDoor(Door door, int y, int x) {
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
                        tile.removeCreature();
                        creature.addEquipment(tileD.getEquipment());
                        tileD.removeEquipment();
                        tileD.addCreature(creature, yD, xD);
                        return true;
                    case INFECT:
                        Creature newZombie = tileD.getCreature();
                        newZombie.setState(State.TRANSFORMED);
                        boardTiles[yD][xD].setCreature(newZombie);
                        return true;
                    case KILL:
                        tile.removeCreature();
                        tileD.removeCreature();
                        tileD.addCreature(creature, yD, xD);
                        return true;
                }
            }












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

            }
        }
        return false;
    }
}
