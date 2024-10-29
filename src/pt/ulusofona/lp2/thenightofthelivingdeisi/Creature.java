package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Creature {
    private int id;
    private int team;
    private String name;
    private int[] positionInBoard;
    private ArrayList<Equipment> equipments;

    public Creature(int id, int team, String name) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.positionInBoard = new int[2];
        this.equipments = new ArrayList<>();
    }

    public Creature(int id, int team, String name, int[] positionInBoard) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.positionInBoard = positionInBoard;
        this.equipments = new ArrayList<>();
    }

    public String[] getCreatureInfo() {
        return new String[]{String.valueOf(this.id), String.valueOf(this.team), this.name, String.valueOf(this.positionInBoard[0]), String.valueOf(this.positionInBoard[1]), null};
    }

    public int getId() {
        return this.id;
    }

    public boolean hasEquipment(int type) {
        for (Equipment equipment : equipments) {
            if (equipment.getTypeAsString().equals(String.valueOf(type))) {
                return true;
            }
        }
        return false;
    }

    public int[] getPositionInBoard() {
        return this.positionInBoard;
    }

    public int points() {
        return equipments.size();
    }

    public boolean move(int[] position) {
        this.positionInBoard = position;
        return true;
    }
}
