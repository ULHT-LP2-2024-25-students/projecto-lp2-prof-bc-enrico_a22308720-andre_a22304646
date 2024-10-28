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

    public GameManager() {
    }

    public boolean loadGame(File file) {
        ArrayList<String> info = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                info.add(line);
            }
            scanner.close();
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
            }
            if (index == 1) {
                this.initialTeam = Integer.parseInt(info.get(index));
            }
            if (index == 2) {
                creatures = Integer.parseInt(info.get(index));
            }
            if (index > 2 && index < 3 + creatures) {
                String[] infoCreature = info.get(index).split(":");
                String creatureId = infoCreature[0].substring(0, 1);
                String teamId = infoCreature[1].substring(0, 1);
                String creatureName = infoCreature[2].substring(1, infoCreature[2].length() - 2);
                int[] positionInBoard = new int[2];
                positionInBoard[0] = Integer.parseInt(infoCreature[3].substring(1, 2));
                positionInBoard[1] = Integer.parseInt(infoCreature[4].substring(1, 2));
                Creature creature = new Creature(
                        Integer.parseInt(creatureId),
                        Integer.parseInt(teamId),
                        creatureName,
                        positionInBoard
                );
                board.addCreature(creature);
            }
            if (index == 3 + creatures) {
                {
                    equipments = Integer.parseInt(info.get(index));
                }
                if (index > 3 + creatures && index < info.size()) {
                    String[] infoEquipment = info.get(index).split(":");
                    String equipmentId = infoEquipment[0].substring(0, 1);
                    String equipmentType = infoEquipment[1].substring(0, 1);
                    int[] positionInBoard = new int[2];
                    positionInBoard[0] = Integer.parseInt(infoEquipment[2].substring(1, 2));
                    positionInBoard[1] = Integer.parseInt(infoEquipment[3].substring(1, 2));
                    Equipment equipment = new Equipment(
                            Integer.parseInt(equipmentId),
                            Integer.parseInt(equipmentType),
                            positionInBoard
                    );
                    board.addEquipment(equipment);
                }
            }
            return false;
        }
        return true;
    }

    public int[] getWorldSize (){

        return null;
    }

    public int getInitialTeamID (){
        return 0;
    }

    public int getCurrentlTeamID (){
        return 0;
    }
     public boolean isDay (){
        return false;
     }

     public String getSquareInfo ( int x, int y){
        return "";
     }

     public String getCreatureInfo (int id){
        return "";
     }

     public String getCreatureInfoAsString (int id){
        return null;
     }

     public String [] getEquipment (){
        return null;
     }

     public boolean hasEquipmnent (int creatureId, int equipmnentTypeId){
        return false;
     }

     public boolean move(int x0, int y0, int xD, int yD){
        return false;
     }

     public boolean gameIsOver (){
        return false;
     }

     public ArrayList<String> getSurvivors(){
        return null;
     }

     public JPanel getCreditsPanel(){
        return null;
     }





    public HashMap<String,String> customizeBoard (){
        HashMap<String,String> hash= new HashMap<>();

        return hash;
    }
}



