package sample.subsystems.GameLogic;

public abstract class Utility extends Property{

    public Utility(int value,int rent, String tName, int tLocation){
        super(value, rent, tName, tLocation);
    }

    @Override
    public void payRent(Player landed) {
        int finalRent;
        int d = Dice.getDiceTotal();
        if( this.getOwner().hasBothUtilities())
            finalRent = d * this.getRent() * 10;
        else
            finalRent = d * this.getRent() * 4;

        landed.updateBalance(-finalRent);
        this.getOwner().updateBalance(finalRent);

    }
}
