package sample.subsystems.GameLogic;

public class SuperTax extends TaxTile {

    public SuperTax(String tName, int tLocation){
        super(tName, tLocation);
    }

    @Override
    public void payTax(Player p) {
        p.updateBalance(-100);
    }
}
