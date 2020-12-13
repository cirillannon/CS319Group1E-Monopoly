package sample.subsystems.GameLogic;

public class SuperTax extends TaxTile {

    public SuperTax(String tName, int tLocation){
        super(tName, tLocation);
    }

    @Override
    public void payTax(Player p) {
        System.out.println("Super tax");
        p.updateBalance(Constants.TileConstants.SUPER_TAX);
    }
}
