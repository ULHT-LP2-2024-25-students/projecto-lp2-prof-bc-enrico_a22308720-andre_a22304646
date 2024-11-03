package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Creature {
    int id;
    int team;
    String name;
    int[] positionInBoard;
    ArrayList<Equipment> equipments;
    int points;


    public Creature(int id, int team, String name, int[] positionInBoard) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.positionInBoard = positionInBoard;
        this.equipments=new ArrayList<>();
    }

    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[6];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = team == 0? "Zombie" : "Humano";
        creatureInfo[2] = name;
        creatureInfo[3] = "" + positionInBoard[0];
        creatureInfo[4] = "" + positionInBoard[1];
        creatureInfo[5] = null;

        return creatureInfo;
    }

    public void increasePoint(){
        points++;
    }
    public int getPoints(){
        return points;
    }

    public int getId (){
        return this.id;
    }

    public int getTeam (){return this.team;}

    public boolean hasEquipment(int type){
        for (Equipment actualEquipment: equipments){
            if (actualEquipment.getTypeAsString().equals("" + type)){
                return true;
            }
        }
        return false;
    }

    public int[] getPositionInBoard(){
        return this.positionInBoard;
    }

    public void move(int x, int y) {
        this.positionInBoard = new int[]{x, y};
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public String getIdAndName() {
        if (team == 1) {
            return id + " " + name;
        }
        return id + " (antigamente conhecido como" + name + ")";
    }

    public boolean isHuman(){
        return team == 0 ? false : true;
    }


}
