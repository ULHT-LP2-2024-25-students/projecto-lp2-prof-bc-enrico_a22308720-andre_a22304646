package pt.ulusofona.lp2.thenightofthelivingdeisi.equipments;

public class Leach extends Equipment {
    int liters;


    public Leach(int[] positionInBoard, int id, int type, String name) {
        super(positionInBoard, id, type, name);
        this.liters = 10;
    }
}
