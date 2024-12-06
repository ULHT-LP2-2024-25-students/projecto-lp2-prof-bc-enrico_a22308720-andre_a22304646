package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Sword extends Equipment {


    public Sword(int type, int[] positionInBoard, int id) {
        super(type, positionInBoard, id);
        this.name = "Espada samurai";
    }

    @Override
    public boolean canAttack(){return true;}

    @Override
    public boolean canDefend(){return false;}




}
