package sample.subsystems.GameLogic;

public abstract class Property extends Tile {

    private int value;
    private Player owner;
    private int baseRent;
    private boolean owned;
    private boolean mortgaged;


    public abstract void payRent(Player landed);

    public Property(int val, int r, String tileName, int tileLocation){
        super(tileName, tileLocation);
        value = val;
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

    public void setRent(int rent){
        this.baseRent = rent;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    @Override
    public void onLand(Player landed) {
        this.payRent(landed);
    }
}
