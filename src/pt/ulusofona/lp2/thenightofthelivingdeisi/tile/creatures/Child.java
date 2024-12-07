package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

public class Child extends Creature {
    protected Equipment equipment;

    public Child(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
        this.equipment = null;
    }

    @Override
    public void addEquipment(Equipment equipment) {
        if (this.state == State.LIVE && equipment.canDefend()){    // Crianças só podem apanhar equipamentos defensivos
            this.equipment = equipment;
        }
        this.points ++;
    }

    @Override
    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[7];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "Criança";
        creatureInfo[2] = this.state == State.LIVE ? "Humano" : this.state == State.DEAD ? "Zombie" : "Zombie (Transformado)";
        creatureInfo[3] = name;
        creatureInfo[4] = "" + positionInBoard[0];
        creatureInfo[5] = "" + positionInBoard[1];
        creatureInfo[6] = null;

        return creatureInfo;
    }

    @Override
    public String getCreatureInfoAsString() {
        String childType= this.state == State.LIVE ? "Humano" : this.state == State.DEAD ? "Zombie" : "Zombie (Transformado)";
        String childPoints = this.state == State.LIVE ? "+" : "-";
        String result= id + " | Criança | " + childType + " | " + name + " | " + childPoints + points + " @(" + positionInBoard[0] + "," + positionInBoard[1]+")";
        if(equipment == null){
            return result;
        }else{   // se tiver equipamento concatenar strings
            return result + " " + equipment.getEquipmentInfoAsString();
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
    public String getIdAndName() {
        if(state ==State.LIVE){
            return id + " " + name;
        }else{
            return id + " (antigamente conhecido como " + name +")";
        }
    }

    @Override
    public boolean moveIsValid(int x0, int y0, int xD, int yD) {
        if ((Math.abs(xD - x0) == 1) && (Math.abs(yD - y0) == 0)) {
            return true;
        }
        if ((Math.abs(xD - x0) == 0) && (Math.abs(yD - y0) == 1)) {
            return true;
        }
        return false;
    }

    public boolean canTransform(){return this.state != State.LIVE;}

    @Override
    public boolean canHoldEquipment() {
        return false;
    }
    @Override
    public boolean canDestroyEquipment() {return this.state != State.LIVE;}

    public boolean canBeTransformed(){return this.state == State.LIVE;}

    @Override
    public boolean canMoveAtNight() {
        return true;
    }
    @Override
    public boolean canMoveAtDay() {
        return true;
    }

}
