package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Pistol extends Equipment {
    int bullets;

    public Pistol(int type, int[] positionInBoard, int id) {
        super(type, positionInBoard, id);
        this.bullets = 3;
        this.name = "Pistola Walther PPK";
    }

    @Override
    public boolean canAttack(){
        if (this.bullets > 0){
            bullets--;
            return true;

        }
        return false;
    }

    @Override
    public boolean canDefend(){
        if (this.bullets > 0 ){
            return true;
        }
        return false;
    }

    public void defend(){
        bullets--;
    }

    @Override
    public String getEquipmentInfoAsString() {
        return id + " | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1] + ") | " + bullets + " balas";
    }


}
