package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameManager {
    private int[] worldSize;
    private int initialTeam;
    private int currentTeam;
    private boolean gameStatus;
    private Board board;
    private int turns;

    public GameManager() {
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
        return new int[]{board.getSizeX(), board.getSizeY()};
    }

    public int getInitialTeamID() {
        return initialTeam;
    }

    public int getCurrentTeamID() {
        return turns % 2 != 0 ? initialTeam : (initialTeam == 0 ? 1 : 0);
    }

    public boolean isDay() {
        return gameStatus;
    }

    public String getSquareInfo(int x, int y) {
        return "";
    }

    public String[] getCreatureInfo(int id) {
        return board.getCreatureInfo(id);
    }

    public String getCreatureInfoAsString(int id) {
        Creature creature = board.getCreature(id);
        String[] info = board.getCreatureInfo(id);
        String type = board.typeOfElement(id);
        String result;
        switch (type) {
            case "Zombie":
                result = info[0] + " | Zombie | " + info[2] + " | -" + creature.points() + " @(" + info[3] + "," + info[4] + ")";
                break;
            case "Human":
                result = info[0] + " | Human | " + info[2] + " | +" + creature.points() + " @(" + info[3] + "," + info[4] + ")";
                break;
            default:
                result = info[0] + " | " + type + " | " + info[2] + " | +" + creature.points() + " @(" + info[3] + "," + info[4] + ")";
                break;
        }
        return result;
    }

    public String[] getEquipment(int id) {
        return board.getEquipment(id).getEquipmentInfo();
    }

    public String getEquipmentAsString(int id) {
        Equipment equipment = board.getEquipment(id);
        String[] info = equipment.getEquipmentInfo();
        return info[0] + " | " + equipment.getNameOfEquipment() + " @ (" + info[2] + "," + info[3] + ")";
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        return board.getCreature(creatureId).hasEquipment(equipmentTypeId);
    }

    public boolean move(int x0, int y0, int xD, int yD) {
        return board.moveCreature(x0, y0, xD, yD);
    }

    public boolean gameIsOver() {
        return turns == 12;
    }

    public ArrayList<String> getSurvivors() {
        return null;
    }

    public JPanel getCreditsPanel() {
        return null;
    }

    public HashMap<String, String> customizeBoard() {
        return new HashMap<>();
    }
}
