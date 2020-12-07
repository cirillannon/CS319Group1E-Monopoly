package sample.subsystems.GameLogic;

public abstract class Property extends Tile {

    private Player owner;
    private int baseRent;
    private boolean owned;
    private boolean mortgaged;


    public abstract void payRent(Player landed);

    public Property(int r, String tileName, int tileLocation){
        super(tileName, tileLocation);
        owner = null;
        baseRent = r;
        owned = false;
        mortgaged = false;
    }

    public boolean isOwned(){
        return owned;
    }

    public void setOwned(boolean b){
        owned = b;
    }

    public boolean isMortgaged(){
        return mortgaged;
    }

    public void setMortgaged(boolean b) {
        mortgaged = b;
    }

    public Player getOwner(){
        return owner;
    }

    public void setOwner(Player p){
        owner = p;
    }

    public int getRent(){
        return baseRent;
    }

    @Override
    public void onLand(Player landed) {
        this.payRent(landed);
    }
}
