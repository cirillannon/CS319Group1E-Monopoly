package sample.subsystems.GameLogic;

public class CommunityChestCard extends Card{

    public CommunityChestCard(String cDescription, int cID){
        super("Community Chest Card", cDescription, cID);
    }

    @Override
    public void onDraw(Player p, ColoredProperty property){
        if (this.getCardID() == 21) super.communityChestCardFunction1(p);
        else if (this.getCardID() == 22) super.communityChestCardFunction2(p);
        else if (this.getCardID() == 23) super.communityChestCardFunction3(p);
        else if (this.getCardID() == 24) super.communityChestCardFunction4(p);
        else if (this.getCardID() == 25) super.communityChestCardFunction5(p);
        else if (this.getCardID() == 26) super.communityChestCardFunction6(p);
        else if (this.getCardID() == 27) super.communityChestCardFunction7(p);
        else if (this.getCardID() == 28) super.communityChestCardFunction8(p);
        else if (this.getCardID() == 29) super.communityChestCardFunction9(p);
        else if (this.getCardID() == 30) super.communityChestCardFunction10(p);
        else if (this.getCardID() == 31) super.communityChestCardFunction11(p);
        else if (this.getCardID() == 32) super.communityChestCardFunction12(p);
        else if (this.getCardID() == 33) super.communityChestCardFunction13(p);
        else if (this.getCardID() == 34) super.communityChestCardFunction14(p);
        else if (this.getCardID() == 35) super.communityChestCardFunction15(p);
        else if (this.getCardID() == 36) super.communityChestCardFunction16(p);
        else if (this.getCardID() == 37) super.communityChestCardFunction17(p);

    }
}
