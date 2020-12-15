package sample.subsystems.GameLogic;

public class FreeParking extends Tile {
    public FreeParking(){
        super("Free Parking", 21 ); //FreeParking is unique so I hardcoded the name and location in constructor
    }

    @Override
    public void onLand(Player p) {
        //Do nothing
    }
}
