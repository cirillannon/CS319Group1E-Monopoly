package sample.subsystems.GameLogic;

public abstract class Utility extends Property{
    int rent;
    int rentBoth;

    public Utility(int value, int rent, String tName, int tLocation){
        super(value, rent, tName, tLocation);
        rent = 4;
        rentBoth = 10;
    }

    @Override
    public void applyEvent(int eventID){
        if (eventID == 1){
            super.setValue(super.getValue() + super.getValue()/4);
            super.setRent(super.getRent() + super.getRent()/4);
            rent += rent / 4;
            rentBoth += rentBoth / 4;
        }

        else if (eventID == 2){
            super.setValue(super.getValue() + super.getValue()/2);
            super.setRent(super.getRent() + super.getRent()/2);
            rent += rent / 2;
            rentBoth += rentBoth / 2;
        }

        else if (eventID == 3){
            super.setValue(super.getValue() - super.getValue()/4);
            super.setRent(super.getRent() - super.getRent()/4);
            rent -= rent / 4;
            rentBoth -= rentBoth / 4;
        }

        else if (eventID == 4){
            super.setValue(super.getValue() - super.getValue()/2);
            super.setRent(super.getRent() - super.getRent()/2);
            rent -= rent / 2;
            rentBoth -= rentBoth / 2;
        }
    }

    @Override
    public void payRent(Player landed) {
        int finalRent;
        int d = Dice.getDiceTotal();
        if( this.getOwner().hasBothUtilities())
            finalRent = d * this.getRent() * rentBoth;
        else
            finalRent = d * this.getRent() * rent;

        landed.updateBalance(-finalRent);
        this.getOwner().updateBalance(finalRent);
    }
}
