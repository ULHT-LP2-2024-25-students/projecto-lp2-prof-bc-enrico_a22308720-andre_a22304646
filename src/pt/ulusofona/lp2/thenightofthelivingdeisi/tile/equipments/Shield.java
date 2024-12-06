package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.equipments;

public class Shield extends Equipment {



    public Shield(int type, int[] positionInBoard, int id) {
        super(type, positionInBoard, id);
        this.name = "Escudo madeira";
    }

    @Override
    public boolean canAttack(){return false;}

    @Override
    public boolean canDefend(){return true;}




}
