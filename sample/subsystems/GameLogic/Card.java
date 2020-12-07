package sample.subsystems.GameLogic;

import sample.subsystems.Controller.GameManager;

public class Card {

    private String cardType;
    private String cardDescription;
    private int cardID;

    public Card( String cType, String cDescription, int cID ){
        this.cardType = cType;
        this.cardDescription = cDescription;
        this.cardID = cID;
    }

    public void cardFunction1(Player p){
        p.setLocation(1);
        GameManager.getTiles()[1].onLand(p);
    }

    public void cardFunction2(Player p){
        if (p.getLocation() > 25)
            GO.paySalary(p);
        p.setLocation(25);
        GameManager.getTiles()[25].onLand(p);
    }

    public void cardFunction3(Player p){
        if (p.getLocation() > 12)
            GO.paySalary(p);
        p.setLocation(12);
        GameManager.getTiles()[12].onLand(p);
    }

    public void cardFunction4(Player p){
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

        //It's always 10x dice roll so  we can't just call onLand here.
        if ((Utility)(GameManager.getTiles()[temp]).isOwned()){
            Dice.rollDice();
            p.updateBalance(-(Dice.getDiceTotal()*10));
            GameManager.getTiles()[temp].getOwner().updateBalance(Dice.getDiceTotal()*10);
        } else
            GameManager.getTiles()[temp].onLand(p);
    }

    public void cardFunction5(Player p){
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

        //It's always 2x the normal rent so we can't just call onLand here.
        if ((Station)(GameManager.getTiles()[temp]).isOwned()){
            int baseRent = (Station)(GameManager.getTiles()[temp].calculateRent());
            p.updateBalance(-(baseRent*2));
            GameManager.getTiles()[temp].getOwner().updateBalance((baseRent*2));
        } else
            GameManager.getTiles()[temp].onLand(p);
    }

    public void cardFunction6(Player p){
        p.updateBalance(50);
    }

    public void cardFunction7(Player p){
        p.addJailCard();
    }

    public void cardFunction8(Player p){
        int temp = p.getLocation();
        if (temp <= 3)
            temp += 40;

        temp -= 3;

        p.setLocation(temp);
        GameManager.getTiles()[temp].onLand(p);
    }

    public void cardFunction9(Player p){
        GoToJail.jailPlayer(p);
    }

    public void cardFunction10(Player p){
        int fee = (p.getHotelsOwned() * 100) + (p.getHousesOwned() * 25);
        p.updateBalance(-fee);

    }

    public void cardFunction11(Player p){
        p.updateBalance(-25);
    }

    public void cardFunction12(Player p){
        if (p.getLocation() > 6)
            GO.paySalary(p);
        p.setLocation(6);
        GameManager.getTiles()[6].onLand(p);
    }

    public void cardFunction13(Player p){
        p.setLocation(40);
        GameManager.getTiles()[40].onLand(p);
    }

    public void cardFunction14(Player p){
        for (int i = 0 ; i < GameManager.getNumOfPlayers(); i++){
            if ( (i != p.getPlayerID()) && (!p.getBankruptcy())){
                GameManager.getPlayers()[i].updateBalance(50);
                p.updateBalance(-50);
            }
        }

    }

    public void cardFunction15(Player p){
        p.updateBalance(150);
    }

    public void cardFunction16(Player p){
        p.updateBalance(100);
    }

    public void cardFunction17(Player p){
        // EVENT
    }

    public void cardFunction18(Player p){
        // EVENT
    }

    public void cardFunction19(Player p){
        // EVENT
    }

    public void cardFunction20(Player p){
        // EVENT
    }
}
