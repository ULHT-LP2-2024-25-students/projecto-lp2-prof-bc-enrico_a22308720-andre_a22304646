package pt.ulusofona.lp2.thenightofthelivingdeisi;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.*;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    private int[] worldSize;
    private int initialTeam;
    private int currentTeam;
    private boolean gameStatus;
    private Board board;
    private int turns;

    public GameManager() {
        this.turns=0;
    }

    public void loadGame(File file) throws InvalidFileException, FileNotFoundException{
        ArrayList<String> info = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            info.add(scanner.nextLine());
        }

        int creatures = 0;
        int equipments = 0;
        int doors =0;

        for (int index = 0; index < info.size(); index++) {
            if (index == 0) {
                String[] size = info.get(index).split(" ");
                this.board = new Board(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
            } else if (index == 1) {
                this.initialTeam = Integer.parseInt(info.get(index));
            } else if (index == 2) {
                creatures = Integer.parseInt(info.get(index));
            } else if (index > 2 && index < 3 + creatures) {
                String[] infoCreature = info.get(index).split(" : ");
                if(infoCreature.length != 6){return;}                         //se nao passarem todas as informacoes de alguma criatura
                int creatureId = Integer.parseInt(infoCreature[0]);
                int teamId = Integer.parseInt(infoCreature[1]);
                int creatureType = Integer.parseInt(infoCreature[2]);
                String creatureName = infoCreature[3];

                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoCreature[4]);
                positionInBoard[1] = Integer.parseInt(infoCreature[5]);

                Creature creature;
                switch (creatureType) {
                    case 0:{
                          creature = new Child(
                                 positionInBoard,
                                 creatureId,
                                 teamId,
                                 creatureName,
                                 teamId == 10 ? State.DEAD : State.LIVE
                             );
                          board.addCreature(creature,positionInBoard[0],positionInBoard[1]);
                          break;
                    }
                    case 1:{
                         creature = new Adult(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                teamId == 10 ? State.DEAD : State.LIVE
                        );
                         board.addCreature(creature,positionInBoard[0],positionInBoard[1]);
                         break;
                    }
                    case 2:{
                          creature = new Old(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                teamId == 10 ? State.DEAD : State.LIVE
                        );
                          board.addCreature(creature,positionInBoard[0],positionInBoard[1]);
                          break;
                    }
                    case 3:{
                         creature = new Dog(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                State.LIVE
                        );
                         board.addCreature(creature,positionInBoard[0],positionInBoard[1]);
                         break;
                    }
                    case 4:{
                         creature = new Vampire(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                State.DEAD
                        );
                         board.addCreature(creature,positionInBoard[0],positionInBoard[1]);
                         break;
                    }
                }

            } else if (index == 3 + creatures) {
                equipments = Integer.parseInt(info.get(index));
            } else if (index > 3 + creatures && index < 4 + creatures + equipments) {
                String[] infoEquipment = info.get(index).split(" : ");
                if(infoEquipment.length != 4){return;}                        // se nao passarem todas as informacaoes de algum equipamento
                int equipmentId = Integer.parseInt(infoEquipment[0]);
                int equipmentType = Integer.parseInt(infoEquipment[1]);
                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoEquipment[2]);
                positionInBoard[1] = Integer.parseInt(infoEquipment[3]);
                Equipment equipment;
                switch (equipmentType){
                    case 0:{
                        equipment = new Shield(equipmentType,positionInBoard,equipmentId);
                        board.addEquipment(equipment,positionInBoard[0],positionInBoard[1]);
                        break;
                    }
                    case 1:{
                        equipment = new Sword(equipmentType,positionInBoard,equipmentId);
                        board.addEquipment(equipment,positionInBoard[0],positionInBoard[1]);
                        break;
                    }
                    case 2:{
                        equipment = new Pistol(equipmentType,positionInBoard,equipmentId);
                        board.addEquipment(equipment,positionInBoard[0],positionInBoard[1]);
                        break;
                    }
                    case 3:{
                        equipment = new Leach(equipmentType,positionInBoard,equipmentId);
                        board.addEquipment(equipment,positionInBoard[0],positionInBoard[1]);
                        break;
                    }
                }
            }else if(index == 4 + creatures + equipments){
                doors = Integer.parseInt(info.get(index));
            } else if (index > 4 + creatures + equipments && index < 5 + creatures + equipments + doors) {
                String[] infoDoor = info.get(index).split(" : ");
                if(infoDoor.length != 2){return;}                        // se nao passarem todas as informacaoes de algum equipamento
                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoDoor[0]);
                positionInBoard[1] = Integer.parseInt(infoDoor[1]);
                Door door = new Door(positionInBoard);
                board.addDoor(door,positionInBoard[0],positionInBoard[1]);
            }

        }
    }

    public int[] getWorldSize() {
        int[] size = new int[2];
        size[0] = board.getSizeX();
        size[1] = board.getSizeY();
        return size;
    }

    public int getInitialTeamId() {
        return initialTeam;
    }

    public int getCurrentTeamId() {
        if (turns%2 ==0){
            currentTeam = initialTeam;
        }
        else {
            currentTeam = initialTeam == 20 ? 10 : 20;
        }
        return currentTeam;
        //if turn is odd, return initial team, if not return the other team
    }

public boolean isDay() {
    if (turns % 4 < 2) {
        gameStatus = true;
    } else {
        gameStatus = false;
    }
    return gameStatus;
}

    public void increaseTurn(){
        turns++;
    }

    public String getSquareInfo(int x, int y) {
        if (board.positionIsValid(x, y)){
            return board.getSquareInfo(x, y);
        }
        return null;
    }

    public String[] getCreatureInfo(int id) {
        return board.getCreatureById(id).getCreatureInfo();
    }

    public String getCreatureInfoAsString(int id) {
        return board.getCreatureById(id).getCreatureInfoAsString();
    }

    public String[] getEquipmentInfo(int id) {
        return board.getEquipmentById(id).getEquipmentInfo();
    }

    public String getEquipmentInfoAsString(int id) {
        return board.getEquipmentById(id).getEquipmentInfoAsString();
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        return board.getCreatureById(creatureId).hasEquipment(equipmentTypeId);
    }

    public boolean move(int x0, int y0, int xD, int yD) {
        int currentTeam = getCurrentTeamId();
        if (board.move(x0,y0,xD,yD,isDay(),currentTeam)){
            turns++;
            return true;
        }
        return false;
    }

    public boolean gameIsOver() {
        return turns == 12;
    }

    public ArrayList<String> getSurvivors() {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Creature> creatures = board.getCreatures();
        result.add("Nr. de turnos terminados:");
        result.add(this.turns + "");
        result.add("");
        result.add("OS VIVOS");
        for (Creature actualCreature : creatures) {
            if (actualCreature.getState() == State.LIVE) {
                result.add(actualCreature.getIdAndName());
            }
        }
        result.add("");
        result.add("OS OUTROS");
        for (Creature actualCreature : creatures) {
            if (actualCreature.getState() != State.LIVE) {
                result.add(actualCreature.getIdAndName());
            }
        }
        result.add("-----");
        return result;
    }

    public JPanel getCreditsPanel() {
        return null;
    }

    public HashMap<String, String> customizeBoard() {
        HashMap<String, String> hash = new HashMap<>();

        return hash;
    }

    public List<Integer> getIdsInSafeHaven() {
        List<Integer> ids = new ArrayList<>();
        for (Creature creature : board.safeHeaven) {
            ids.add(creature.getId());
        }
        return ids;
    }

    public void saveGame(File file) throws IOException {

    }
}