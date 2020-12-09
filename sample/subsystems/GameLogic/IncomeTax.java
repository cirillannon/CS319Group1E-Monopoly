package sample.subsystems.GameLogic;

public class IncomeTax extends TaxTile {

    public IncomeTax(int tLocation){
        super("Income Tax", tLocation);
    }

    @Override
    public void payTax(Player p) {
        p.updateBalance(-200);
    }
}
