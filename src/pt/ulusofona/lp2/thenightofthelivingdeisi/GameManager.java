package pt.ulusofona.lp2.thenightofthelivingdeisi;
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
        this.turns=1;
        this.gameStatus = true;
    }

    public boolean loadGame(File file) {
        ArrayList<String> info = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                info.add(scanner.nextLine());
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return false;
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
                int creatureId = Integer.parseInt(infoCreature[0]);
                int teamId = Integer.parseInt(infoCreature[1]);
                String creatureName = infoCreature[2];
                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoCreature[3]);
                positionInBoard[1] = Integer.parseInt(infoCreature[4]);
                Creature creature = new Creature(creatureId, teamId, creatureName, positionInBoard);
                board.addCreature(creature);
            } else if (index == 3 + creatures) {
                equipments = Integer.parseInt(info.get(index));
            } else if (index > 3 + creatures && index < 4 + creatures + equipments) {
                String[] infoEquipment = info.get(index).split(" : ");
                int equipmentId = Integer.parseInt(infoEquipment[0]);
                int equipmentType = Integer.parseInt(infoEquipment[1]);
                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoEquipment[2]);
                positionInBoard[1] = Integer.parseInt(infoEquipment[3]);
                Equipment equipment = new Equipment(equipmentId, equipmentType, positionInBoard);
                board.addEquipment(equipment);
            }
        }
        return true;
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
        return turns%2 != 0 ? initialTeam : (initialTeam == 0 ? 0 : 1);
        //if turn is odd, return initial team, if not return the other team
    }

    public boolean isDay() {
        if (turns %2 == 0){
            gameStatus=false;
        }else{
            gameStatus=true;
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
            case "0":
                result = info[0] + " | Zombie | " + info[2] + " | -" + creature.points() + " @(" + info[3] + "," + info[4] + ")";
                break;
            case "1":
                result = info[0] + " | Humano | " + info[2] + " | +" + creature.points() + " @(" + info[3] + "," + info[4] + ")";
                break;
            default:
                break;
        }
        return result;
    }

    public String[] getEquipmentInfo(int id) {
        return board.getEquipment(id).getEquipmentInfo();
    }

    public String getEquipmentInfoAsString(int id) {
        Equipment equipment = board.getEquipment(id);
        String[] info = equipment.getEquipmentInfo();
        return info[0] + " | " + equipment.getNameOfEquipment() + " @ (" + info[2] + "," + info[3] + ")";
    }

    public boolean hasEquipment(int creatureId, int equipmnentTypeId) {
        return board.getCreatureById(creatureId).hasEquipment(equipmnentTypeId);
    }

    public boolean move(int x0, int y0, int xD, int yD) {
        boolean validation = board.move(x0, y0, xD, yD);
        if(validation){
            increaseTurn();
        }
        return validation;
    }

    public boolean gameIsOver() {
        return turns == 12;
    }

    public ArrayList<String> getSurvivors() {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Creature> creatures = board.getCreatures();
        result.add("Nr. de turnos terminados:");
        result.add(this.turns + "");
        result.add("OS VIVOS");
        for (Creature actualCreature : creatures) {
            if (actualCreature.getTeam() == 1) {
                result.add(actualCreature.getIdAndName(actualCreature));
            }
        }
        result.add("OS MORTOS");
        for (Creature actualCreature : creatures) {
            if (actualCreature.getTeam() == 0) {
                result.add(actualCreature.getIdAndName(actualCreature));
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



