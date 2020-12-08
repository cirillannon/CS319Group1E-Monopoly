package sample.subsystems.GameLogic;

import sample.subsystems.Controller.GameManager;

public abstract class Card {

    private String cardType;
    private String cardDescription;
    private int cardID;

    public Card( String cType, String cDescription, int cID ){
        this.cardType = cType;
        this.cardDescription = cDescription;
        this.cardID = cID;
    }

    public abstract void onDraw(Player p, ColoredProperty property);

    public int getCardID(){
        return cardID;
    }

    public void chanceCardFunction1(Player p){
        p.setLocation(1);
        GameManager.getTiles()[1].onLand(p);
    }

    public void chanceCardFunction2(Player p){
        if (p.getLocation() > 25)
            GO.paySalary(p);
        p.setLocation(25);
        GameManager.getTiles()[25].onLand(p);
    }

    public void chanceCardFunction3(Player p){
        if (p.getLocation() > 12)
            GO.paySalary(p);
        p.setLocation(12);
        GameManager.getTiles()[12].onLand(p);
    }

    public void chanceCardFunction4(Player p){
        int temp = p.getLocation();

        // This loop finds the next utility and stores it in temp
        boolean flag = false;
        do {
            temp++;
            if (temp == 41) // max of 40 tiles
                temp = 1;
            if(GameManager.getTiles()[temp].getClass().equals(Utility.class))
                flag = true;
        }while (!flag) ;

        Utility u = (Utility)GameManager.getTiles()[temp];
        //It's always 10x dice roll so  we can't just call onLand here.
        if (u.isOwned()){
            Dice.rollDice();
            p.updateBalance(-(Dice.getDiceTotal()*10));
            u.getOwner().updateBalance(Dice.getDiceTotal()*10);
        } else
            GameManager.getTiles()[temp].onLand(p);

    }

    public void chanceCardFunction5(Player p){
        int temp = p.getLocation();

        // This loop finds the next utility and stores it in temp
        boolean flag = false;
        do {
            temp++;
            if (temp == 41) // max of 40 tiles
                temp = 1;
            if(GameManager.getTiles()[temp].getClass().equals(Station.class))
                flag = true;
        }while (!flag) ;

        Station s = (Station)GameManager.getTiles()[temp];
        //It's always 2x the normal rent so we can't just call onLand here.
        if (s.isOwned()){
            int n = s.getOwner().getNumberOfStations();
            int finalRent = s.getRent() * n;
            p.updateBalance(-(finalRent*2));
            s.getOwner().updateBalance((finalRent*2));
        } else
            GameManager.getTiles()[temp].onLand(p);
    }

    public void chanceCardFunction6(Player p){
        p.updateBalance(50);
    }

    public void chanceCardFunction7(Player p){
        p.addJailCard();
    }

    public void chanceCardFunction8(Player p){
        int temp = p.getLocation();
        if (temp <= 3)
            temp += 40;

        temp -= 3;

        p.setLocation(temp);
        GameManager.getTiles()[temp].onLand(p);
    }

    public void chanceCardFunction9(Player p){
        GoToJail.jailPlayer(p);
    }

    public void chanceCardFunction10(Player p){
        int fee = (p.getHotelsOwned() * 100) + (p.getHousesOwned() * 25);
        p.updateBalance(-fee);

    }

    public void chanceCardFunction11(Player p){
        p.updateBalance(-25);
    }

    public void chanceCardFunction12(Player p){
        if (p.getLocation() > 6)
            GO.paySalary(p);
        p.setLocation(6);
        GameManager.getTiles()[6].onLand(p);
    }

    public void chanceCardFunction13(Player p){
        p.setLocation(40);
        GameManager.getTiles()[40].onLand(p);
    }

    public void chanceCardFunction14(Player p){
        for (int i = 0 ; i < GameManager.getNumOfPlayers(); i++){
            if ( (i != p.getPlayerID()) && (!p.getBankruptcy())){
                GameManager.getPlayers()[i].updateBalance(50);
                p.updateBalance(-50);
            }
        }

    }

    public void chanceCardFunction15(Player p){
        p.updateBalance(150);
    }

    public void chanceCardFunction16(Player p){
        p.updateBalance(100);
    }

    public void chanceCardFunction17(Player p, ColoredProperty property){
        property.applyEvent(1);
    }

    public void chanceCardFunction18(Player p, ColoredProperty property){
        property.applyEvent(2);
    }

    public void chanceCardFunction19(Player p,ColoredProperty property){
        property.applyEvent(3);
    }

    public void chanceCardFunction20(Player p, ColoredProperty property){
        property.applyEvent(4);
    }

    public void communityChestCardFunction1(Player p){
        p.setLocation(1);
        GameManager.getTiles()[1].onLand(p);
    }

    public void communityChestCardFunction2(Player p){
        p.updateBalance(200);
    }

    public void communityChestCardFunction3(Player p){
        p.updateBalance(50);
    }

    public void communityChestCardFunction4(Player p){
        p.updateBalance(-50);
    }

    public void communityChestCardFunction5(Player p){
        p.addJailCard();
    }

    public void communityChestCardFunction6(Player p){
        GoToJail.jailPlayer(p);
    }

    public void communityChestCardFunction7(Player p){
        for (int i = 0 ; i < GameManager.getNumOfPlayers(); i++){
            if ( (i != p.getPlayerID()) && !(GameManager.getPlayers()[i].getBankruptcy())){
                GameManager.getPlayers()[i].updateBalance(-50);
                p.updateBalance(50);
            }
        }
    }

    public void communityChestCardFunction8(Player p){
        p.updateBalance(100);
    }

    public void communityChestCardFunction9(Player p){
        p.updateBalance(20);
    }

    public void communityChestCardFunction10(Player p){
        p.updateBalance(10);
    }

    public void communityChestCardFunction11(Player p){
        p.updateBalance(100);
    }

    public void communityChestCardFunction12(Player p){
        p.updateBalance(-100);
    }

    public void communityChestCardFunction13(Player p){
        p.updateBalance(-150);
    }

    public void communityChestCardFunction14(Player p){
        p.updateBalance(25);
    }

    public void communityChestCardFunction15(Player p){
        int fee = (p.getHotelsOwned() * 115) + (p.getHousesOwned() * 40);
        p.updateBalance(-fee);
    }

    public void communityChestCardFunction16(Player p){
        p.updateBalance(10);
    }

    public void communityChestCardFunction17(Player p){
        p.updateBalance(100);
    }

}
