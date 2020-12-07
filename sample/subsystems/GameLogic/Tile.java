package sample.subsystems.GameLogic;

public abstract class Tile {

    private String tileName;
    private int tileLocation;

    public Tile ( String tName, int tLocation){
        tileName = tName;
        tileLocation = tLocation;
    }

    public String getTileName(){
        return tileName;
    }

    public int getTileLocation() {
        return tileLocation;
    }
}
