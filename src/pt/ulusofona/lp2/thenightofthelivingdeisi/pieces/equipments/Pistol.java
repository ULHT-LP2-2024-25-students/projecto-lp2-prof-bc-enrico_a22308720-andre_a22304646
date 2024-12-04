package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments;

public class Pistol extends Equipment {
    int bullets;

    public Pistol(int[] positionInBoard, int id, int type) {
        super(positionInBoard, id, type);
        this.bullets = 3;
        this.name = "Pistola Walter PPK";

    }

    @Override
    public boolean canAttack(){return true;}

    @Override
    public boolean canDefend(){return false;}

    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + bullets + " balas";
    }


}
