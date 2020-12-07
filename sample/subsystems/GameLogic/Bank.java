package sample.subsystems.GameLogic;

public class Bank {
    private int numberOfHouses;
    private int numberOfHotels;
    public Bank(){
        numberOfHouses = 32;
        numberOfHotels = 12;
    }
    // Add getprice() function for property.
    public boolean buyProperty(Property prp ,Tile t , Player p  ) {
        if (!prp.isOwned() && p.getLocation() == t.getTileLocation() && p.getBalance() >= prp.getPrice())
        {
            prp.setOwner(p);
            return true;
        }
        else
            return false;
    }
    // Add player and playerOfferAmount parameters.
    public void auctionProperty(Property prp ,Player p , int playerOfferAmount)  {
        if (p.getBalance () >= playerOfferAmount && !prp.isOwned()){
            p.setBalance(p.getBalance()- playerOfferAmount);
            prp.setOwner(p);
        }
        else
            return;
    }
    // Add tradeAmount parameter 
    public boolean tradeProperty(Property prp, Player owner, Player target , int tradeAmount) {
        if (target.getBalance() >= tradeAmount)
        {
         prp.setOwner(target);
         target.setBalance(target.getBalance() - tradeAmount);
         owner.setBalance = (owner.getBalance() + tradeAmount);
         return true;
        }
        else
            return false;
    }

    // Add mortgageAmount() for property
    public boolean mortgageProperty(Property prp, Player p) {
        if (!prp.isMortgaged()) {
            prp.setMortgaged(true);
            p.setBalance(p.getBalance + prp.mortgageAmount()) ;
            return true;
        }
        else
            return false;
    }

    // Add mortgageAmount() for property
    public boolean unMortgageProperty(Property prp, Player p) {
        if (prp.isMortgaged() && p.getBalance() >= prp.mortgageAmount()) {
            prp.setMortgaged(false);
            p.setBalance(p.getBalance() - prp.mortgageAmount());
            return true;
        }
        else
            return false;
    }

    // Add buildingCount parameter. getHouseCost(), gethotelCost() ,setRentAmount() for each property.
    public boolean buyBuilding(Player p, Building type, Property prp , int buildingCount) {
        if (type == "House") {
            if (p.hasMonopoly() && p.getBalance() >= (buildingCount * prp.gethouseCost()))
            {
                p.setBalance(p.getBalance() - (buildingCount * prp.gethouseCost()));
                numberOfHouses = numberOfHouses - buildingCount;
                setNumberOfHouses(numberOfHouses);
                prp.setNumOfHouse (buildingCount);
                prp.setRentAmount(); // Add setRentAmount function to Property class.
                return true;
            }
            else
                return false;
        }
        if (type == "Hotel") {
            if (p.hasMonopoly() && p.getBalance() >= (buildingCount * prp.gethotelCost()) && prp.getNumOfHouse () = 4)
            {
                p.setBalance(p.getBalance() - (buildingCount * prp.gethotelCost()));
                numberOfHotels = numberOfHotels - buildingCount;
                setNumberOfHotels(numberOfHotels);
                prp.setNumOfHotel (buildingCount);
                prp.setRentAmount(); // Add setRentAmount function to Property class.
                return true;
            }
            else
                return false;
        }
    }
    // Add getNumOfJailCards()  hasJailCard()
    
    public boolean tradeJailCard( Player owner, Player target, int moneyAmount) {
        if(owners.hasJailCard() & target.getBalance () >= moneyAmount) {
            owner.removeJailCard();
            target.addJailCard();
            owner.balance = owner.balance + moneyAmount;
            target.balance = target.balance - moneyAmount;
            return true;
        }
        else
            return false;

    }

    public boolean tradeMoney(int moneyAmount, Player owner, Player target) {
        if (owner.getBalance () >= moneyAmount) {
            owner.balance = owner.balance - moneyAmount;
            target.balance = target.balance + moneyAmount;
            return true;
        }
        else
            return false;
    }
    // Add new function tradeProperty() to bank class.
    public boolean tradeProperty(int tradeMoneyAmount, Player owner, Player buyer , Property prp)
    {
        if (buyer.getBalance() >= tradeMoneyAmount && prp.isOwned()) {
            prp.removeOwner(owner);
            prp.setOwner(buyer);
            owner.setBalance(owner.getBalance() + tradeMoneyAmount);
            buyer.setBalance(buyer.getBalance() - tradeMoneyAmount);
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
