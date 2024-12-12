package pt.ulusofona.lp2.thenightofthelivingdeisi;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Door;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures.*;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GameManager {
    private int[] worldSize;
    private int initialTeam;
    private int currentTeam;
    private boolean gameStatus;
    private Board board;
    private int turns;
    private int lastTransformationOrDead;
    private ArrayList<Creature> livesInBoard;
    private ArrayList<Creature> deadsInBoard;

    private static final int CREATURE_COMPONENTS_NR = 6;
    private static final int EQUIPMENT_COMPONENTS_NR = 4;

    public GameManager() {
        this.turns=0;
        this.lastTransformationOrDead=0;
        this.livesInBoard= new ArrayList<>();
        this.deadsInBoard= new ArrayList<>();

    }

public void loadGame(File file) throws InvalidFileException, FileNotFoundException {
    ArrayList<String> info = new ArrayList<>();
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {info.add(scanner.nextLine());}
    int creatures = 0, equipments = 0, doors = 0;
    for (int index = 0; index < info.size(); index++) {
        if (index == 0) {
            String[] size = info.get(index).split(" ");
            if (size.length != 2) {
                throw new InvalidFileException(index + 1);
            }
            this.board = new Board(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
        } else if (index == 1) {
            this.initialTeam = Integer.parseInt(info.get(index));
        } else if (index == 2) {
            creatures = Integer.parseInt(info.get(index));
        } else if (index > 2 && index < 3 + creatures) {
            String[] infoCreature = info.get(index).split(" : ");
            if (infoCreature.length != CREATURE_COMPONENTS_NR) {throw new InvalidFileException(index + 1);}

            int creatureId = Integer.parseInt(infoCreature[0]);
            int teamId = Integer.parseInt(infoCreature[1]);
            int creatureType = Integer.parseInt(infoCreature[2]);
            String creatureName = infoCreature[3];
            int[] positionInBoard = new int[2];
            positionInBoard[0] = Integer.parseInt(infoCreature[4]);
            positionInBoard[1] = Integer.parseInt(infoCreature[5]);

            Creature creature = Creature.createCreature(creatureType, positionInBoard, creatureId, teamId, creatureName);
            if(creature == null){
                throw new InvalidFileException(index + 1);
            }

            board.addCreature(creature, positionInBoard[0], positionInBoard[1]);
        } else if (index == 3 + creatures) {
            equipments = Integer.parseInt(info.get(index));
        } else if (index > 3 + creatures && index < 4 + creatures + equipments) {
            String[] infoEquipment = info.get(index).split(" : ");
            if (infoEquipment.length != EQUIPMENT_COMPONENTS_NR) {
                throw new InvalidFileException(index + 1);
            }
            int equipmentId = Integer.parseInt(infoEquipment[0]);
            int equipmentType = Integer.parseInt(infoEquipment[1]);
            int[] positionInBoard = new int[2];
            positionInBoard[0] = Integer.parseInt(infoEquipment[2]);
            positionInBoard[1] = Integer.parseInt(infoEquipment[3]);

            Equipment equipment = Equipment.createEquipment(equipmentType, positionInBoard, equipmentId);
            if (equipment == null) {
                throw new InvalidFileException(index + 1);
            }
            board.addEquipment(equipment, positionInBoard[0], positionInBoard[1]);
        } else if (index == 4 + creatures + equipments) {
            doors = Integer.parseInt(info.get(index));
        } else if (index > 4 + creatures + equipments && index < 5 + creatures + equipments + doors) {
            String[] infoDoor = info.get(index).split(" : ");
            if (infoDoor.length != 2) {
                throw new InvalidFileException(index + 1);
            }
            int[] positionInBoard = new int[2];
            positionInBoard[0] = Integer.parseInt(infoDoor[0]);
            positionInBoard[1] = Integer.parseInt(infoDoor[1]);
            Door door = new Door(positionInBoard);
            board.addDoor(door, positionInBoard[0], positionInBoard[1]);
        }
    }
}

    public int[] getWorldSize() {
        int[] size = new int[2];
        size[0] = board.getSizeY();
        size[1] = board.getSizeX();
        return size;
    }

    public int getInitialTeamId() {
        return initialTeam;    }

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
        if (board.getCreatureById(id) == null){
            return null;
        }
        return board.getCreatureById(id).getCreatureInfo();
    }

    public String getCreatureInfoAsString(int id) {
        if (board.getCreatureById(id) == null){
            return null;
        }
        return board.getCreatureById(id).getCreatureInfoAsString();
    }

    public String[] getEquipmentInfo(int id) {
        Equipment equipment = board.getEquipmentById(id);
        if (equipment == null ||  equipment.isHolded()){
            return null;
        }
        return board.getEquipmentById(id).getEquipmentInfo();
    }

    public String getEquipmentInfoAsString(int id) {
        Equipment equipment = board.getEquipmentById(id);
        if (equipment==null){
            return null;
        }
        if (equipment.isHolded()){
            return null;
        }
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
        ArrayList<Creature> creaturesInGame = board.getCreatures();
        int livesInBoard=this.livesInBoard.size();
        int deadsInBoard=this.deadsInBoard.size();
        this.livesInBoard= new ArrayList<>();
        this.deadsInBoard= new ArrayList<>();
        for(Creature atualCreature : creaturesInGame){
            if (atualCreature.getState() == State.LIVE){
                this.livesInBoard.add(atualCreature);
            }else{
                this.deadsInBoard.add(atualCreature);
            }
        }
        if(this.turns==1){
            return false;
        }
        if (livesInBoard != this.livesInBoard.size() || deadsInBoard != this.deadsInBoard.size()){
            this.lastTransformationOrDead=this.turns;
        }

        ArrayList<Creature> creaturesInvalid = new ArrayList<>();

        if (!isDay() && this.currentTeam == 20){
            for (Creature creature : this.livesInBoard){
                if (!creature.canMoveAtNight()){
                    creaturesInvalid.add(creature);
                }
            }
            if (creaturesInvalid.size() == this.livesInBoard.size()){
                return true;
            }
        } else if (isDay() && this.currentTeam ==10){
            for (Creature creature : this.deadsInBoard){
                if (!creature.canMoveAtDay()){
                    creaturesInvalid.add(creature);
                }
            }
            if (creaturesInvalid.size() == this.deadsInBoard.size()){
                return true;
            }
        }

        return this.livesInBoard.isEmpty() || this.deadsInBoard.isEmpty() || this.turns - this.lastTransformationOrDead==8;
    }

    public ArrayList<String> getSurvivors() {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Creature> creatures = board.getCreatures();
        ArrayList<Creature> creaturesSafeHaven = board.getSafeHeaven();
        creatures.addAll(creaturesSafeHaven);
        creatures.sort(Comparator.comparingInt(Creature::getId));

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
        JPanel res = new JPanel();
        return res;
    }

    public HashMap<String, String> customizeBoard() {
        HashMap<String, String> hash = new HashMap<>();

        return hash;
    }

    public List<Integer> getIdsInSafeHaven() {
        return board.getIdsInSafeHaven();
    }

    public void saveGame(File file) throws IOException {
        ArrayList<Equipment> equipments = board.getEquipments();
        ArrayList<Creature> creatures = board.getCreatures();
        ArrayList<Door> doors = board.getDoors();

        try (PrintWriter writer = new PrintWriter(file)) {
            // Save board size
            writer.println(board.getSizeY() + " " + board.getSizeX());

            // Save initial team
            writer.println(initialTeam);

            // Save creatures
            writer.println(creatures.size());
            for (Creature creature : creatures) {
                writer.println(creature.getSave());
            }

            // Save equipments
            writer.println(equipments.size());
            for (Equipment equipment : equipments) {
                writer.println(equipment.getSave());
            }

            // Save doors
            writer.println(doors.size());
            for (Door door : doors) {
                writer.println(door.getPositionInBoard()[0] + " : " + door.getPositionInBoard()[1]);
            }
        }
    }


}