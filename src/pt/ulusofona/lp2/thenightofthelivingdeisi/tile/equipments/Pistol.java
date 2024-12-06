package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Pistol extends Equipment {
    int bullets;

    public Pistol(int type, int[] positionInBoard, int id) {
        super(type, positionInBoard, id);
        this.bullets = 3;
        this.name = "Pistola Walter PPK";
    }

    @Override
    public boolean canAttack(){return this.bullets > 0;}

    @Override
    public boolean canDefend(){return false;}

    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + bullets + " balas";
    }


}
