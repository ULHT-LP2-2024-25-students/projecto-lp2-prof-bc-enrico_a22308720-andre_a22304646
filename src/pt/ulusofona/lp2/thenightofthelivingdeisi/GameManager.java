package pt.ulusofona.lp2.thenightofthelivingdeisi;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.Equipment;
import pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameManager {
    int[] worldSize;
    int initialTeam;
    int currentTeam;
    boolean gameStatus;
    Board board;
    int turns;

    public GameManager() {
        this.turns=0;
    }

    public void loadGame(File file) throws FileNotFoundException  {
        ArrayList<String> info = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            info.add(scanner.nextLine());
        }

        int creatures = 0;
        int equipments = 0;

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
                positionInBoard[0] = Integer.parseInt(infoCreature[3]);
                positionInBoard[1] = Integer.parseInt(infoCreature[4]);

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
                    }
                    case 1:{
                         creature = new Adult(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                teamId == 10 ? State.DEAD : State.LIVE
                        );
                    }
                    case 2:{
                          creature = new Old(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                teamId == 10 ? State.DEAD : State.LIVE
                        );
                    }
                    case 3:{
                         creature = new Dog(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                State.LIVE
                        );
                    }
                    case 4:{
                         creature = new Vampire(
                                positionInBoard,
                                creatureId,
                                teamId,
                                creatureName,
                                State.LIVE
                        );
                    }
                    board.addCreature(creature);
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
                Equipment equipment = new Equipment(positionInBoard, equipmentId, equipmentType);
                board.addEquipment(equipment);
            } else if (index > 3 + creatures + equipments && index < 4 + creatures + equipments) {
                String[] infoDoor = info.get(index).split(" : ");
                if(infoDoor.length != 2){return;}                        // se nao passarem todas as informacaoes de algum equipamento
                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoDoor[0]);
                positionInBoard[1] = Integer.parseInt(infoDoor[1]);
                Door door = new Door(positionInBoard);
            }

        }
    }

    public int[] getWorldSize() {
        int[] size = new int[2];
        size[1] = board.getSizeX();
        size[0] = board.getSizeY();
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
        if(turns==0){
            gameStatus = getInitialTeamId() == 10 ? false : true;
        }

        if (((turns % 2) == 0) && turns != 0){
            if (gameStatus) {
                gameStatus = false;
            }else {
                gameStatus = true;
            }
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
        Creature creature = board.getCreatureById(id);
        String[] info = board.getCreatureById(id).getCreatureInfo();
        String result = "";
        switch (info[1]) {
            case "Zombie":
                result = info[0] + " | Zombie | " + info[2] + " | -" + creature.getPoints() + " @ (" + info[3] + ", " + info[4] + ")";
                break;
            case "Humano":
                result = info[0] + " | Humano | " + info[2] + " | +" + creature.getPoints() + " @ (" + info[3] + ", " + info[4] + ")";
                break;
            default:
                break;
        }
        return result;
    }

    public String[] getEquipmentInfo(int id) {
        return board.getEquipmentById(id).getEquipmentInfo();
    }

    public String getEquipmentInfoAsString(int id) {
        Equipment equipment = board.getEquipmentById(id);
        if(equipment == null){return null;}
        String[] info = equipment.getEquipmentInfo();
        return info[0] + " | " + equipment.getNameOfEquipment() + " @ (" + info[2] + "," + info[3] + ")";
    }

    public boolean hasEquipment(int creatureId, int equipmnentTypeId) {
        return board.getCreatureById(creatureId).hasEquipment(equipmnentTypeId);
    }

    public boolean move(int x0, int y0, int xD, int yD) {
        int originId = board.positionId(x0, y0);
        if (originId > 0 && board.getCreatureById(originId).getTeam() == getCurrentTeamId()) {
            if (board.move(x0, y0, xD, yD)) {
                increaseTurn();
                return true;
            }
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
            if (actualCreature.getTeam() == 1) {
                result.add(actualCreature.getIdAndName());
            }
        }
        result.add("");
        result.add("OS MORTOS");
        for (Creature actualCreature : creatures) {
            if (actualCreature.getTeam() == 10) {
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
}