package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures;

public class Dog extends Creature {

    public Dog(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
    }


    @Override
    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[7];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "Cão";
        creatureInfo[2] = "Humano";
        creatureInfo[3] = name;
        creatureInfo[4] = "" + positionInBoard[0];
        creatureInfo[5] = "" + positionInBoard[1];
        creatureInfo[6] = null;

        return creatureInfo;
    }

    @Override
    public String getCreatureInfoAsString() {
        return id + " | Cão | " + name + " @(" + positionInBoard[0] + "," + positionInBoard[1]+")";
    }

}
