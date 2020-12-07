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
            p.updateBalance(-prp.getPrice());
            return true;
        }
        else
            return false;
    }
    // Add player and playerOfferAmount parameters.
    public void auctionProperty(Property prp ,Player p , int playerOfferAmount)  {
        if (p.getBalance () >= playerOfferAmount && !prp.isOwned()){
            p.updateBalance(-playerOfferAmount);
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
         target.updateBalance(-tradeAmount);
         owner.updateBalance(tradeAmount);
         return true;
        }
        else
            return false;
    }

    // Add mortgageAmount() for property
    public boolean mortgageProperty(Property prp, Player p) {
        if (!prp.isMortgaged()) {
            prp.setMortgaged(true);
            p.updateBalance(prp.mortgageAmount()) ;
            return true;
        }
        else
            return false;
    }

    // Add mortgageAmount() for property
    public boolean unMortgageProperty(Property prp, Player p) {
        if (prp.isMortgaged() && p.getBalance() >= prp.mortgageAmount()) {
            prp.setMortgaged(false);
            p.updateBalance(-prp.mortgageAmount());
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
                p.updateBalance(-(buildingCount * prp.gethouseCost()));
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
                p.updateBalance(-(buildingCount * prp.gethotelCost()));
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
            owner.updateBalance(moneyAmount);
            target.updateBalance(moneyAmount);
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
