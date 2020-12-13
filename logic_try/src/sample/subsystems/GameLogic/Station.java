package sample.subsystems.GameLogic;

public class Station extends Property {

    public Station(int value, int rent, String tName, int tLocation){
        super(value, rent, tName, tLocation);
    }

    @Override
    public void payRent(Player landed) {
        int n = this.getOwner().getNumberOfStations();
        int finalRent = this.getRent() * n;

        landed.updateBalance(-finalRent);
        this.getOwner().updateBalance(finalRent);

    }
}
