package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Pistol extends Equipment {
    int bullets;

    public Pistol(int[] positionInBoard, int id, int type) {
        super(positionInBoard, id, type);
        this.bullets = 3;
        this.name = "Pistola Walter PPK";

    }

    @Override
    public boolean canAttack(){return this.bullets > 0;}

    @Override
    public boolean canDefend(){return false;}

    @Override
    public void atack(){
        this.bullets--;
    }


    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + bullets + " balas";
    }


}
