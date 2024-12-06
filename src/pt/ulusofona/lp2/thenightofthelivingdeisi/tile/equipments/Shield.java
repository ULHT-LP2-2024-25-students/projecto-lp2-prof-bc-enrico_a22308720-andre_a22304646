package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Shield extends Equipment {
    public Shield(int[] positionInBoard, int id, int type) {
        super(positionInBoard, id, type);
        this.name = "Escudo madeira";

    }

    @Override
    public boolean canAttack(){return false;}

    @Override
    public boolean canDefend(){return true;}

    @Override
    public void atack() {

    }


}
