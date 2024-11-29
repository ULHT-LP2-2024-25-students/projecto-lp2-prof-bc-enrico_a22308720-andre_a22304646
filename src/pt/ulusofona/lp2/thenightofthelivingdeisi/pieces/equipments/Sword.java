package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.equipments;

public class Sword extends Equipment {
    public Sword(int[] positionInBoard, int id, int type, String name) {
        super(positionInBoard, id, type);
        this.name = "Espada samurai";

    }

    @Override
    public boolean canAttack(){return true;}

    @Override
    public boolean canDefend(){return false;}


}
