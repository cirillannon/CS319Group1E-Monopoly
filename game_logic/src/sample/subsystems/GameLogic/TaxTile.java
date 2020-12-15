package sample.subsystems.GameLogic;

public abstract class TaxTile extends Tile {
    public TaxTile( String tName, int tLocation) {
            super( tName, tLocation);
    }
    public abstract void payTax(Player p);

    public void onLand(Player p){
        payTax(p);
    }
}
