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

    @Override
    public void applyEvent(int eventID){
        if (eventID == 1){
            super.setValue(super.getValue() + super.getValue()/4);
            super.setRent(super.getRent() + super.getRent()/4);
        }

        else if (eventID == 2){
            super.setValue(super.getValue() + super.getValue()/2);
            super.setRent(super.getRent() + super.getRent()/2);
        }

        else if (eventID == 3){
            super.setValue(super.getValue() - super.getValue()/4);
            super.setRent(super.getRent() - super.getRent()/4);
        }

        else if (eventID == 4){
            super.setValue(super.getValue() - super.getValue()/2);
            super.setRent(super.getRent() - super.getRent()/2);
        }
    }
}
