package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.Tile;
import pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments.Equipment;

public class Adult extends Creature {
    protected Equipment equipment;

    public Adult(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
        this.equipment = null;
    }


    @Override
    public boolean moveIsValid(int x0, int y0, int xD, int yD) {
        if(x0 != xD || y0!=yD) {                                        // evitar que nao se possoa mover para a casa onde está
            if ((Math.abs(xD - x0) <= 2) && (Math.abs(yD - y0) <= 2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEquipment(Equipment equipment) {
        if (this.state == State.LIVE){
            this.equipment = equipment;
        }
        this.points ++;
    }


    @Override
    public boolean canHoldEquipment() {
        return this.state == State.LIVE;
    }




//    @Override
//    public String[] getCreatureInfo(){
//        String[] creatureInfo = new String[7];
//        creatureInfo[0] = "" + id;
//        creatureInfo[1] = "Adulto";
//        creatureInfo[2] = this.state == State.LIVE ? "Humano" : this.state == State.DEAD ? "Zombie" : "Zombie (Transformado)";
//        creatureInfo[3] = name;
//        creatureInfo[4] = "" + positionInBoard[0];
//        creatureInfo[5] = "" + positionInBoard[1];
//        creatureInfo[6] = null;
//
//        return creatureInfo;
//    }
//
//    @Override
//    public String getCreatureInfoAsString() {
//        String adultType= this.state == State.LIVE ? "Humano" : this.state == State.DEAD ? "Zombie" : "Zombie (Transformado)";
//        String adultPoints = this.state == State.LIVE ? "+" : "-";
//        String result = id + " | Adulto | " + adultType + " | " + name + " | " + adultPoints + points + " @(" + positionInBoard[0] + "," + positionInBoard[1]+")";
//        if(equipment == null){
//            return result;
//        }else{   // se tiver equipamento concatenar strings
//            return result + equipment.getEquipmentInfoAsString();
//        }
//    }
//
//    @Override
//    public boolean hasEquipment(int equipmentTypeId) {
//        if (equipment==null){
//            return false;
//        }
//        return equipment.getType() == equipmentTypeId;
//    }
//
//    @Override
//    public String getIdAndName() {
//        if(state ==State.LIVE){
//            return id + " " + name;
//        }else{
//            return id + " (antigamente conhecido como " + name +")";
//        }
//    }
//
//    @Override
//    public Creature interactCreature(Creature piece) {
//        if (this.state == State.LIVE) {
//
//            if (piece.canBeTransformed()){
//                return piece;
//            }
//            if (piece.canTransform() && this.equipment.canAttack()){
//                equipment.atack();
//            }
//
//
//
//        }else{
//
//        }
//    }
//
//    @Override
//    public boolean destroyEquipment(Piece piece) {
//        if(this.state == State.LIVE){
//            this.equipment = (Equipment) piece;
//            return false;
//        }else {
//            return true;
//        }
//    }


    public boolean canTransform(){return this.state != State.LIVE;}

    public boolean canBeTransformed(){return this.state == State.LIVE;}
}
