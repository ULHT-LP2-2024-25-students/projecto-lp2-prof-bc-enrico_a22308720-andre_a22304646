package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Board {
    private int[][] board; // Board contains creature or equipment id. If empty, 0
    private ArrayList<Equipment> equipments = new ArrayList<>();
    private ArrayList<Creature> creatures = new ArrayList<>();

    public Board(int rows, int columns) {
        this.board = new int[rows][columns];
    }

    public boolean addCreature(Creature creature) {
        int[] position = creature.getPositionInBoard();
        if (position[0] < board.length && position[1] < board[0].length && board[position[0]][position[1]] != 0) {
            return false;
        }
        board[position[0]][position[1]] = creature.getId();
        creatures.add(creature);
        return true;
    }

    public boolean addEquipment(Equipment equipment) {
        int[] position = equipment.getPositionInBoard();
        if (position[0] < board.length && position[1] < board[0].length && board[position[0]][position[1]] != 0) {
            return false;
        }
        board[position[0]][position[1]] = equipment.getId();
        equipments.add(equipment);
        return true;
    }

    public String[] getCreatureInfo(int id) {
        for (Creature creature : creatures) {
            if (creature.getId() == id) {
                return creature.getCreatureInfo();
            }
        }
        return null;
    }

    public String typeOfElement(int id) {
        if (id > 0) {
            String[] elements = getCreatureInfo(id);
            return elements[1].equals("0") ? "Zombie" : "Human";
        } else {
            for (Equipment equipment : equipments) {
                if (equipment.getId() == id) {
                    return equipment.getNameOfEquipment();
                }
            }
        }
        return null;
    }

    public Creature getCreature(int id) {
        for (Creature creature : creatures) {
            if (creature.getId() == id) {
                return creature;
            }
        }
        return null;
    }

    public Equipment getEquipment(int id) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == id) {
                return equipment;
            }
        }
        return null;
    }

    public boolean moveCreature(int x0, int y0, int xD, int yD) {
        if ((xD > x0 + 1 || xD < x0 - 1) || (yD > y0 + 1 || yD < y0 - 1) || board[xD][yD] > 0 || xD > board.length || xD < 0 || yD < 0 || yD > board[0].length) {
            return false;
        } else if (board[xD][yD] >= 0) {
            for (Creature creature : creatures) {
                if (creature.getId() == board[x0][y0]) {
                    creature.move(new int[]{xD, yD});
                    board[x0][y0] = 0;
                }
            }
        }
        return true;
    }

    public int getSizeX() {
        return board.length;
    }

    public int getSizeY() {
        return board[0].length;
    }
}
