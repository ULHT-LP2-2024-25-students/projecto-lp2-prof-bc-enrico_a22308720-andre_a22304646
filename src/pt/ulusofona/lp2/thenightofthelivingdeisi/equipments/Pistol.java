package pt.ulusofona.lp2.thenightofthelivingdeisi.equipments;

public class Pistol extends Equipment {
    int bullets;

    public Pistol(int[] positionInBoard, int id, int type, String name, int bullets) {
        super(positionInBoard, id, type, name);
        this.bullets = 3;
    }
}
