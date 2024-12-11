package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;


import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

public class Dog extends Creature {


    public Dog(int[] positionInBoard, int id, int team, String name, State state, int type) {
        super(positionInBoard, id, team, name, state, type);
    }

    @Override
    public void addEquipment(Equipment equipment) {}


    @Override
    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[7];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "Cão";
        creatureInfo[2] = "Humano";
        creatureInfo[3] = name;
        creatureInfo[4] = positionInBoard[0] == -1 ? null : "" + positionInBoard[0];
        creatureInfo[5] = positionInBoard[1] == -1 ? null : "" + positionInBoard[1];
        creatureInfo[6] = null;

        return creatureInfo;
    }

    @Override
    public String getCreatureInfoAsString() {
        return id + " | Cão | " + name + " @ (" + positionInBoard[0] + ", " + positionInBoard[1]+")";
    }

    @Override
    public boolean hasEquipment(int equipmentTypeId) {
        return false;
    }

    @Override
    public String getIdAndName() {
            return id + " " + name;
    }

    @Override
    public boolean moveIsValid(int x0, int y0, int xD, int yD) {
        if(x0 != xD || y0!=yD) {
            if ((Math.abs(xD - x0) <= 2) && (Math.abs(yD - y0) == 0)) {
                return true;
            }
            if ((Math.abs(xD - x0) == 0 ) && (Math.abs(yD - y0) <= 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean canTransform(){return false;}

    @Override
    public boolean canHoldEquipment(Equipment equipment) {
        return false;
    }
    @Override
    public boolean canDestroyEquipment() {return false;}

    public boolean canBeTransformed(){return false;}

    @Override
    public boolean canMoveAtNight() {
        return true;
    }
    @Override
    public boolean canMoveAtDay() {
        return true;
    }

    @Override
    public TypeMove getTypeMove(Tile tile, Tile tileDestiny) {
        if (tileDestiny.getCreature() == null) {
            if (tileDestiny.getEquipment() == null) {
                if (tileDestiny.getDoor() == null) {
                    //empty tile
                    return TypeMove.MOVE;
                } else if (tileDestiny.getDoor() != null) {
                    return TypeMove.SAFEHEAVEN;
                }
            }
        }
        return TypeMove.INVALID;
    }
}
