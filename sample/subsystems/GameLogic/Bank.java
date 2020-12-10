package sample.subsystems.GameLogic;

public class Bank {
    private static Bank instance = null;
    private int numberOfHouses;
    private int numberOfHotels;

    private Bank()
    {
        numberOfHouses = 32;
        numberOfHotels = 12;
    }

    // static method to create instance of Singleton class
    public static Bank initBank()
    {
        if (instance == null)
            instance = new Bank();

        return instance;
    }

    // methods
    public boolean buyProperty(Property prp ,Tile t , Player p  ) {
        if (!prp.isOwned() && p.getLocation() == t.getTileLocation() && p.getBalance() >= prp.getRent())
        {
            prp.setOwner(p);
            p.updateBalance(-prp.getRent());
            return true;
        }
        else
            return false;
    }
    public void auctionProperty(Property prp ,Player p , int playerOfferAmount)  {
        if (p.getBalance () >= playerOfferAmount && !prp.isOwned()){
            p.updateBalance(-playerOfferAmount);
            prp.setOwner(p);
        }
        else
            return;
    }

    public boolean tradeProperty(Property prp, Player owner, Player target , int tradeAmount) {
        if (target.getBalance() >= tradeAmount)
        {
            prp.setOwner(target);
            target.updateBalance(-tradeAmount);
            owner.updateBalance(tradeAmount);
            return true;
        }
        else
            return false;
    }


    public boolean mortgageProperty(Property prp, Player p) {
        if (!prp.isMortgaged()) {
            prp.setMortgaged(true);
            p.updateBalance(prp.mortgageAmount()) ;
            return true;
        }
        else
            return false;
    }


    public boolean unMortgageProperty(Property prp, Player p) {
        if (prp.isMortgaged() && p.getBalance() >= prp.mortgageAmount()) {
            prp.setMortgaged(false);
            p.updateBalance(-prp.mortgageAmount());
            return true;
        }
        else
            return false;
    }


    public boolean buyBuilding(Player p, String type, ColoredProperty prp ) {
        boolean flag = false;
        if (type == "House") {
            if (p.hasMonopoly() && p.getBalance() >= (  prp.getHouseCost()))
            {
                p.updateBalance(-prp.getHouseCost());
                numberOfHouses = numberOfHouses - 1;
                setNumberOfHouses(numberOfHouses);
                prp.addHouse();
                p.addHouse();
                flag = true;
                return flag;
            }
            else{
                flag = false;
                return flag;
            }
        }
        if (type == "Hotel") {
            if (p.hasMonopoly() && (p.getBalance() >= prp.getHotelCost()) && prp.getNumberOfHouses () == 4)
            {
                p.updateBalance(-prp.getHotelCost());
                numberOfHotels = numberOfHotels - 1;
                setNumberOfHotels(numberOfHotels);
                prp.addHotel ();
                p.addHotels();
                flag = true;
                return flag;
            }
            else {
                flag=false;
                return flag;
            }
        }
        return flag;
    }
    public boolean removeBuilding(Player p, String type, ColoredProperty prp ) {
        boolean flag = false;
        if (type == "House") {
            if (p.getHousesOwned() > 0 )
            {
                p.updateBalance(prp.getHouseCost());
                numberOfHouses = numberOfHouses + 1;
                setNumberOfHouses(numberOfHouses);
                prp.removeHouse();
                flag = true;
                return flag;
            }
            else{
                flag = false;
                return flag;
            }
        }
        if (type == "Hotel") {
            if (p.getHotelsOwned() > 0)
            {
                p.updateBalance(prp.getHotelCost());
                numberOfHotels = numberOfHotels + 1;
                setNumberOfHotels(numberOfHotels);
                prp.removeHotel();
                flag = true;
                return flag;
            }
            else {
                flag=false;
                return flag;
            }
        }
        return flag;
    }


    public boolean tradeJailCard( Player owner, Player target, int moneyAmount) {
        if(owner.hasOutOfJailFreeCard() & target.getBalance () >= moneyAmount) {
            owner.removeJailCard();
            target.addJailCard();
            owner.updateBalance(moneyAmount);
            target.updateBalance(-moneyAmount);
            return true;
        }
        else
            return false;

    }

    public boolean tradeMoney(int moneyAmount, Player owner, Player target) {
        if (owner.getBalance () >= moneyAmount) {
            owner.updateBalance(-moneyAmount);
            target.updateBalance(moneyAmount);
            return true;
        }
        else
            return false;
    }

    public int getNumberOfHouses(){
        return this.numberOfHouses;
    }
    public void setNumberOfHouses(int numberOfHouses){
        this.numberOfHouses = numberOfHouses;
    }
    public int getNumberOfHotels(){
        return this.numberOfHotels;
    }
    public void setNumberOfHotels(int numberOfHotels){
        this.numberOfHotels = numberOfHotels;
    }

}
