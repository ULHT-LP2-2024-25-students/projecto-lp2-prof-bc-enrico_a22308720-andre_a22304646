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
        if (this.liters >= 0.1 ){
            return true;
        }
        return false;
    }

    public void defend(){
        if(liters < 0.3){
            liters=0.0;
        }
        liters -= 0.3;
        liters = Math.round(liters * 10.0) / 10.0;

    }




    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + liters + " litros";
    }


}
