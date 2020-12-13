package sample.subsystems.GameLogic;

public class ChanceCard extends Card {
    public ChanceCard(String cDescription, int cID){
        super("Chance Card", cDescription, cID);
    }

    @Override
    public void onDraw(Player p, ColoredProperty property){
        if (this.getCardID() == 1) super.chanceCardFunction1(p);
        else if (this.getCardID() == 2) super.chanceCardFunction2(p);
        else if (this.getCardID() == 3) super.chanceCardFunction3(p);
        else if (this.getCardID() == 4) super.chanceCardFunction4(p);
        else if (this.getCardID() == 5) super.chanceCardFunction5(p);
        else if (this.getCardID() == 6) super.chanceCardFunction6(p);
        else if (this.getCardID() == 7) super.chanceCardFunction7(p);
        else if (this.getCardID() == 8) super.chanceCardFunction8(p);
        else if (this.getCardID() == 9) super.chanceCardFunction9(p);
        else if (this.getCardID() == 10) super.chanceCardFunction10(p);
        else if (this.getCardID() == 11) super.chanceCardFunction11(p);
        else if (this.getCardID() == 12) super.chanceCardFunction12(p);
        else if (this.getCardID() == 13) super.chanceCardFunction13(p);
        else if (this.getCardID() == 14) super.chanceCardFunction14(p);
        else if (this.getCardID() == 15) super.chanceCardFunction15(p);
        else if (this.getCardID() == 16) super.chanceCardFunction16(p);
        else if (this.getCardID() == 17) super.chanceCardFunction17(p,property);
        else if (this.getCardID() == 18) super.chanceCardFunction18(p,property);
        else if (this.getCardID() == 19) super.chanceCardFunction19(p,property);
        else if (this.getCardID() == 20) super.chanceCardFunction20(p,property);
    }
}
