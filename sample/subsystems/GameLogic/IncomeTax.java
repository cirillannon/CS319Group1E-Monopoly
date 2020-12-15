package sample.subsystems.GameLogic;

public class IncomeTax extends TaxTile {

    public IncomeTax(int tLocation){
        super("Income Tax", tLocation);
    }

    @Override
    public void payTax(Player p) {
        System.out.println("income tax");
        p.updateBalance(Constants.TileConstants.INCOME_TAX);
    }
}
