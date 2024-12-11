package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;


import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

public class Vampire extends Creature {

    public Vampire(int[] positionInBoard, int id, int team, String name, State state, int type) {
        super(positionInBoard, id, team, name, state, type);
    }
    @Override
    public void addEquipment(Equipment equipment) {}

    @Override
    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[7];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "Vampiro";
        creatureInfo[2] = "Zombie";
        creatureInfo[3] = name;
        creatureInfo[4] = positionInBoard[0] == -1 ? null : "" + positionInBoard[0];
        creatureInfo[5] = positionInBoard[1] == -1 ? null : "" + positionInBoard[1];
        creatureInfo[6] = null;

        return creatureInfo;
    }

    @Override
    public String getCreatureInfoAsString() {
        String result;
        if(positionInBoard[0] == -1) {
            return id + " | Vampiro | " + name + " | -" + points + " @ Safe Haven";
        }
        return id + " | Vampiro | " +  name + " | -" + points + " @ (" + positionInBoard[0] + ", " + positionInBoard[1]+")";
    }

    @Override
    public boolean hasEquipment(int equipmentTypeId) {
        return false;
    }

    @Override
    public boolean canMoveAtNight() {return true;}

    @Override
    public String getIdAndName() {
            return id + " (antigamente conhecido como " + name +")";
    }

    @Override
    public boolean moveIsValid(int x0, int y0, int xD, int yD) {
        if(x0 != xD || y0 != yD) {                                        // evitar que nao se possoa mover para a casa onde está
            if ((Math.abs(xD - x0) <= 1) && (Math.abs(yD - y0) <= 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean canTransform(){return true;}

    @Override
    public boolean canHoldEquipment(Equipment equipment) {
        return false;
    }
    @Override
    public boolean canDestroyEquipment() {return true;}

    public boolean canBeTransformed(){return false;}
    @Override
    public boolean canMoveAtDay() {
        return false;
    }


    @Override
    public TypeMove getTypeMove(Tile tile, Tile tileDestiny){
        if(tileDestiny.getCreature() == null){
            if(tileDestiny.getEquipment() == null) {
                if(tileDestiny.getDoor() == null){
                    //empty tile
                    return TypeMove.MOVE;
                }
            } else if(tileDestiny.getEquipment() != null){
                //tile with equipment
                return TypeMove.WEAPON;
            }
        }
        if (tileDestiny.getCreature().canBeTransformed()){
            if (tileDestiny.getCreature().getEquipment() != null &&
                    (tileDestiny.getCreature().getEquipment().canDefend() || tileDestiny.getCreature().getEquipment().canAttack())){
                //tile with creature DEAD and destination tile with creature LIVE with weapon to defend
                return TypeMove.DEFENDED;
            }
            else{
                //tile with creature DEAD and destination tile with creature LIVE with no defend weapon
                return TypeMove.INFECT;
            }

        }
        return TypeMove.INVALID;
    }
}
