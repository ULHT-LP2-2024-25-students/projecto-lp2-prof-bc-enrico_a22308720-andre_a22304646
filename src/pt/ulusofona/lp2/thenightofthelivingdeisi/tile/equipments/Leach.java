package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Leach extends Equipment {
    private double liters;


    public Leach(int type, int[] positionInBoard, int id) {
        super(type, positionInBoard, id);
        this.liters = 1.0;
        this.name = "Lixívia";
    }

    @Override
    public boolean canAttack(){return false;}

    @Override
    public boolean canDefend(){
        if (this.liters >= 0.3 ){
            liters -= 0.3;
            return true;
        }
        liters=0;
        return false;
    }




    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + liters + " litros";
    }


}
