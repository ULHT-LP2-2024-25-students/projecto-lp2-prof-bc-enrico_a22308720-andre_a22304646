package pt.ulusofona.lp2.thenightofthelivingdeisi.pieces.creatures;


public class Vampire extends Creature {
    public Vampire(int[] positionInBoard, int id, int team, String name, State state) {
        super(positionInBoard, id, team, name, state);
    }


    @Override
    public String[] getCreatureInfo(){
        String[] creatureInfo = new String[7];
        creatureInfo[0] = "" + id;
        creatureInfo[1] = "Vampiro";
        creatureInfo[2] = "Zombie";
        creatureInfo[3] = name;
        creatureInfo[4] = "" + positionInBoard[0];
        creatureInfo[5] = "" + positionInBoard[1];
        creatureInfo[6] = null;

        return creatureInfo;
    }

    @Override
    public String getCreatureInfoAsString() {
        return id + " | Vampiro | Zombie | " +  name + " | -" + points + " @(" + positionInBoard[0] + "," + positionInBoard[1]+")";
    }

    @Override
    public boolean hasEquipment(int equipmentTypeId) {
        return false;
    }

    @Override
    public String getIdAndName() {
            return id + " (antigamente conhecido como " + name +")";
    }
}
