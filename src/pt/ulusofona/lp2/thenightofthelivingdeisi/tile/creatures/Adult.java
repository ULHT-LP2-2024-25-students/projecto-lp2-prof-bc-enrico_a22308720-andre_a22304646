package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

public class Adult extends Creature {


    public Adult(int[] positionInBoard, int id, int team, String name, State state, int type) {
        super(positionInBoard, id, team, name, state, type);
    }

    @Override
    public boolean moveIsValid(int x0, int y0, int xD, int yD) {
        if (x0 != xD || y0 != yD) { // evitar que nao se possa mover para a casa onde está
            if (Math.abs(xD - x0) <= 2 && Math.abs(yD - y0) == 0) { // horizontal
                return true;
            }
            if (Math.abs(xD - x0) == 0 && Math.abs(yD - y0) <= 2) { // vertical
                return true;
            }
            if (Math.abs(xD - x0) == 2 && Math.abs(yD - y0) == 2) { // diagonal
                return true;
            }
            if (Math.abs(xD - x0) == 1 && Math.abs(yD - y0) == 1) { // diagonal
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEquipment(Equipment equipment) {
        if (this.state == State.LIVE){
            this.equipment = equipment;
            equipment.hold();
        }
    }

    @Override
    public boolean canHoldEquipment(Equipment equipment) {
        return this.state == State.LIVE;
    }

    @Override
    public boolean canDestroyEquipment() {return this.state != State.LIVE;}


    @Override
    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[7];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "Adulto";
        creatureInfo[2] = this.state == State.LIVE ? "Humano" : this.state == State.DEAD ? "Zombie" : "Zombie (Transformado)";
        creatureInfo[3] = name;
        creatureInfo[4] = positionInBoard[0] == -1 ? null : "" + positionInBoard[0];
        creatureInfo[5] = positionInBoard[1] == -1 ? null : "" + positionInBoard[1];
        creatureInfo[6] = null;

        return creatureInfo;
   }

    @Override
    public String getCreatureInfoAsString() {
        String adultType= this.state == State.LIVE ? "Humano" : this.state == State.DEAD ? "Zombie" : "Zombie (Transformado)";
        String adultPoints = this.state == State.LIVE ? "+" : "-";
        String result;
        if(positionInBoard[0] == -1){
            result = id + " | Adulto | " + adultType + " | " + name + " | " + adultPoints + points + " @ Safe Haven";
        }else {
            result = id + " | Adulto | " + adultType + " | " + name + " | " + adultPoints + points + " @ (" + positionInBoard[0] + ", " + positionInBoard[1] + ")";
        }
        if(equipment == null){
            return result;
        }else{   // se tiver equipamento concatenar strings
            return result + " | " + equipment.getEquipmentInfoAsString();
        }
    }

    @Override
    public boolean hasEquipment(int equipmentTypeId) {
        if (equipment==null){
            return false;
        }
        return equipment.getType() == equipmentTypeId;
    }


    @Override
    public boolean canMoveAtNight() {
        return true;
    }
    @Override
    public boolean canMoveAtDay() {
        return true;
    }

    @Override
    public String getIdAndName() {
        if(state ==State.LIVE){
            return id + " " + name;
        }else{
            return id + " (antigamente conhecido como " + name +")";
        }
    }

    public boolean canTransform(){return this.state != State.LIVE;}

    public boolean canBeTransformed(){return this.state == State.LIVE;}
    @Override
    public Equipment getEquipment() {
        return equipment;
    }

    @Override
    public TypeMove getTypeMove(Tile tile, Tile tileDestiny){
        if(tileDestiny.getCreature() == null){
            if(tileDestiny.getEquipment() == null) {
                if(tileDestiny.getDoor() == null){
                    //empty tile
                    return TypeMove.MOVE;
                }else if(tileDestiny.getDoor() != null){
                    if (this.state == State.LIVE){
                        //tile with door
                        return TypeMove.SAFEHEAVEN;
                    }
                }
            } else if(tileDestiny.getEquipment() != null){
                //tile with equipment
                return TypeMove.WEAPON;
            }
        } else if(this.state == State.LIVE ){
            if(tileDestiny.getCreature().canTransform() && this.equipment != null){
                if(this.equipment.canAttack()){
                    //tile with creature LIVE with atack weapon and destination tile with creature DEAD or TRANSFORMED
                    return TypeMove.KILL;
                }else if(this.equipment.canDefend()){
                    //tile with creature LIVE with DEFEND weapon and destination tile with creature DEAD or TRANSFORMED
                    return TypeMove.PASS;
                }else{
                    return TypeMove.INVALID;
                }

            }
        } else if (this.state != State.LIVE){
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

            }else {
                //tile with creature DEAD and destination tile with creature DEAD or TRANSFORMED
                return TypeMove.INVALID;
            }
        }
        return TypeMove.INVALID;
    }

}
