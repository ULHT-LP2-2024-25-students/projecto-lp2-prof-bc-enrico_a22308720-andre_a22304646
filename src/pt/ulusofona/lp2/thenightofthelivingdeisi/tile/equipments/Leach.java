package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Leach extends Equipment {
    private double liters;


    public Leach(int[] positionInBoard, int id, int type) {
        super(positionInBoard, id, type);
        this.name = "Lixívia";
        this.liters = 1.0;

    }

    @Override
    public boolean canAttack(){return false;}

    @Override
    public boolean canDefend(){return this.liters >= 0.3;}

    @Override
    public void atack() {}

    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + liters + " litros";
    }


}
