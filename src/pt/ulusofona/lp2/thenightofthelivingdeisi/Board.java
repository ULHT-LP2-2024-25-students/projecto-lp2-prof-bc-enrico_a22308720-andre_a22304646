package pt.ulusofona.lp2.thenightofthelivingdeisi;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.Creature;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.State;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.TypeMove;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board {
    private Tile[][] boardTiles;
    private ArrayList<Creature> safeHeaven;
    private TypeMove lastTypeMove;


    public Board(int rows, int columns) {
        this.boardTiles = new Tile[rows][columns];
        this.safeHeaven = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                boardTiles[y][x] = new Tile();
            }
        }
        this.lastTypeMove=null;
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
        for (Creature creature : safeHeaven){
            if (creature.getId() == id){
                return creature;
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
                if(boardTiles[i][j].getCreature() != null && boardTiles[i][j].getCreature().getEquipment() != null){
                    if (boardTiles[i][j].getCreature().getEquipment().getId() == id){
                        return boardTiles[i][j].getCreature().getEquipment();
                    }
                }
            }
        }
        return null;
    }

    public boolean move(int x0, int y0, int xD, int yD, boolean isDay,int currentTeam) {
        if (positionIsValid(xD, yD) && boardTiles[y0][x0].getCreature() != null) {    //verificar se a posicao de destino e valida e se esta uma criatura na posicao de origem
            Tile tile = boardTiles[y0][x0];
            Tile tileD = boardTiles[yD][xD];
            TypeMove typeMove = tile.getCreature().getTypeMove(tile, tileD);
            if (tile.getCreature().moveIsValid(x0, y0, xD, yD)) {                   //verificar se o movimento é valido pelo tipo de criatura
                Creature creature = boardTiles[y0][x0].getCreature();
                int creatureTeam = creature.getTeam();
                if(((isDay && creature.canMoveAtDay()) || (!isDay && creature.canMoveAtNight())) && creatureTeam == currentTeam){            //verificar se a criatura se pode mover consoante ser dia ou noite e consoante ser o seu turno
                    switch (typeMove) {
                        case INVALID:
                            this.lastTypeMove = TypeMove.INVALID;
                            return false;
                        case MOVE:
                            //Se for velho deixa a arma para trás
                            if (!creature.canMoveAtNight() && creature.getEquipment() != null){
                                tile.addEquipment(creature.getEquipment(),x0,y0);
                                tile.removeCreature();
                                tileD.addCreature(creature, xD, yD);
                                tileD.getCreature().removeEquipment();
                                this.lastTypeMove = TypeMove.MOVE;
                                return true;
                            }
                            tile.removeCreature();
                            tileD.addCreature(creature, xD, yD);
                            if (tileD.getCreature().getEquipment() != null){
                                tileD.getCreature().getEquipment().setPositionInBoard(xD,yD);
                            }
                            this.lastTypeMove = TypeMove.MOVE;
                            return true;
                        case WEAPON:
                            if (creature.canHoldEquipment(tileD.getEquipment())){
                                if (creature.getEquipment() != null){
                                    tile.addEquipment(creature.getEquipment(),x0,y0);
                                    creature.removeEquipment();
                                }
                                tile.removeCreature();
                                creature.addEquipment(tileD.getEquipment());
                                creature.increasePoints();
                                tileD.removeEquipment();
                                tileD.addCreature(creature, xD, yD);
                                this.lastTypeMove=TypeMove.WEAPON;
                                return true;
                            }else if(creature.canDestroyEquipment()){
                                tileD.removeEquipment();
                                creature.increasePoints();
                                tile.removeCreature();
                                tileD.addCreature(creature, xD, yD);
                                this.lastTypeMove=TypeMove.WEAPON;
                                return true;
                            }else{
                                this.lastTypeMove = TypeMove.INVALID;
                                return false;
                            }
                        case INFECT:
                            Creature newZombie = tileD.getCreature();
                            newZombie.setState(State.TRANSFORMED);
                            newZombie.setTeam(10);
                            newZombie.removeEquipment();
                            boardTiles[yD][xD].setCreature(newZombie);
                            this.lastTypeMove=TypeMove.INFECT;
                            return true;
                        case KILL:
                            tile.removeCreature();
                            tileD.removeCreature();
                            tileD.addCreature(creature, xD, yD);
                            //  Alterar posicao da arma quando criatura se move com ela
                            if (tileD.getCreature().getEquipment() != null){
                                tileD.getCreature().getEquipment().setPositionInBoard(xD,yD);
                            }
                            this.lastTypeMove=TypeMove.KILL;
                            return true;
                        case SAFEHEAVEN:
                            tile.removeCreature();
                            creature.setPositionNull();
                            safeHeaven.add(creature);
                            this.lastTypeMove=TypeMove.SAFEHEAVEN;
                            return true;
                        case DEFENDED:
                            if (tileD.getEquipment()!= null){
                                tileD.getEquipment().defend();
                            }
                            if (tileD.getCreature().getEquipment()!= null){
                                tileD.getCreature().getEquipment().defend();
                            }
                            this.lastTypeMove=TypeMove.DEFENDED;
                            return true;
                        case PASS:
                            tile.getCreature().getEquipment().defend();
                            this.lastTypeMove=TypeMove.PASS;
                            return true;
                    }
                }else{
                    this.lastTypeMove = TypeMove.INVALID;
                    return false;
                }
            }
        }
        this.lastTypeMove = TypeMove.INVALID;
        return false;
    }

    public ArrayList<Creature> getCreatures (){
        ArrayList<Creature> creatures = new ArrayList<>();
        for (int y=0; y < getSizeY(); y++) {
            for (int x = 0; x < getSizeX(); x++) {
                if (boardTiles[y][x].getCreature() != null) {
                    creatures.add(boardTiles[y][x].getCreature());
                }
            }
        }
        creatures.sort(Comparator.comparingInt(Creature::getId));
        return creatures;
    }

    public List<Integer> getIdsInSafeHaven(){
        List<Integer> ids = new ArrayList<>();
        for (Creature creature : safeHeaven){
            ids.add(creature.getId());
        }
        return ids;
    }

    public ArrayList<Creature> getSafeHeaven() {
        return safeHeaven;
    }

    public ArrayList<Equipment> getEquipments(){
        ArrayList<Equipment> equipments = new ArrayList<>();
        for (int y=0; y < getSizeY(); y++) {
            for (int x = 0; x < getSizeX(); x++) {
                if (boardTiles[y][x].getEquipment() != null) {
                    equipments.add(boardTiles[y][x].getEquipment());
                }
            }
        }
        equipments.sort(Comparator.comparingInt(Equipment::getId));
        return equipments;
    }


    public ArrayList<Door> getDoors(){
        ArrayList<Door> doors = new ArrayList<>();
        for (int y=0; y < getSizeY(); y++) {
            for (int x = 0; x < getSizeX(); x++) {
                if (boardTiles[y][x].getDoor() != null) {
                    doors.add(boardTiles[y][x].getDoor());
                }
            }
        }
        return doors;
    }

    public TypeMove getLastTypeMove() {
        return lastTypeMove;
    }
}
