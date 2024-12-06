package pt.ulusofona.lp2.thenightofthelivingdeisi.tile.creatures;


import pt.ulusofona.lp2.thenightofthelivingdeisi.Piece;

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

    @Override
    public boolean moveIsValid(int x0, int y0, int xD, int yD) {
        if(x0 != xD || y0 != yD) {                                        // evitar que nao se possoa mover para a casa onde está
            if ((Math.abs(xD - x0) <= 1) && (Math.abs(yD - y0) <= 1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void interact(Piece piece) {

    }

    public boolean canTransform(){return true;}

    public boolean canBeTransformed(){return false;}
}
