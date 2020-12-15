package sample.subsystems.GameLogic;

public class SuperTax extends TaxTile {

    public SuperTax(int tLocation){
        super("Super Tax", tLocation);
    }

    @Override
    public void payTax(Player p) {
        System.out.println("Super tax");
        p.updateBalance(Constants.TileConstants.SUPER_TAX);
    }
}
