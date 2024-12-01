package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments;

public class Leach extends Equipment {
    private int liters;


    public Leach(int[] positionInBoard, int id, int type) {
        super(positionInBoard, id, type);
        this.name = "Lixívia";
        this.liters = 1;

    }

    @Override
    public boolean canAttack(){return false;}

    @Override
    public boolean canDefend(){return true;}

    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @ (" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + liters + "litros";
    }


}
