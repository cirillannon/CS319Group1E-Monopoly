package sample.subsystems.GameLogic;

public class IncomeTax extends TaxTile {

    public IncomeTax(String tName, int tLocation){
        super(tName, tLocation);
    }

    @Override
    public void payTax(Player p) {
        p.updateBalance(-200);
    }
}
