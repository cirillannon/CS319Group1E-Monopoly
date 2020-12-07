package sample.subsystems.GameLogic;
public class Bank {
    private int numberOfHouses;
    private int numberOfHotels;
    public Bank(){
        numberOfHouses= 32;
        numberOfHotels=12;
    }
    // Add price variable for property.
    public boolean buyProperty(Property prp ,Tile t , Player p  ) {
        if (!prp.isOwned() && p.location == t.tileLocation && p.getBalance() >= prp.price)  
        {
            prp.setOwner(p);
            return true; 
        }
        else
            return false;

    }
    // Add player and playerOfferAmount parameters. 
    public void auctionProperty(Property prp ,Player p , int playerOfferAmount)  {
        if (p.getBalance >= playerOfferAmount){
            p.balance = p.balance- playerOfferAmount;
            prp.setOwner(p);
            
        }
        

    }
    // Add tradeAmount parameter and removeOwner() for property
    public boolean tradeProperty(Property prp, Player owner, Player target , int tradeAmount) {
        if (target.getBalance() >= tradeAmount)
        {
         prp.setOwner(target);
         prp.removeOwner(owner);
         target.balance = target.balance - tradeAmount;
         owner.balance = owner.balance + tradeAmount;
         return true;

        }
        else
            return false;

    }
    // Add mortgageAmount() for property
    public boolean mortgageProperty(Property prp, Player p) {
        if (!prp.isMortgaged()) {
            prp.setMortgaged(true);
            p.balance =  p.balance + prp.mortgageAmount() ;
            return true;
        }
        else
            return false;


    }
    // Add mortgageAmount() for property
    public boolean unMortgageProperty(Property prp, Player p) {
        if (prp.isMortgaged() && p.getBalance() >= prp.mortgageAmount()) {
            prp.setMortgaged(false);
            p.balance =  p.balance - prp.mortgageAmount() ;
            return true;
        }
        else
            return false;

    }
    // Add buildingCount parameter. HouseCost, hotelCost ,setRentAmount() for each property.
    public boolean buyBuilding(Player p, Building type, Property prp , int buildingCount) {
        if (type == "House") {
            if (p.hasMonopoly() && p.getBalance() >= (buildingCount * prp.houseCost))
            {
                p.balance = p.balance - (buildingCount * prp.houseCost);
                numberOfHouses = numberOfHouses - buildingCount;
                prp.setNumOfHouse (buildingCount);
                prp.setRentAmount();
                return true;

            }
            else
                return false;
        }
        if (type=="Hotel") {
            if (p.hasMonopoly() && p.getBalance() >= (buildingCount * prp.hotelCost) && prp.getNumOfHouse () = 4)
            {
                p.balance = p.balance - (buildingCount * prp.hotelCost);
                numberOfHotels = numberOfHotels - buildingCount;
                prp.setNumOfHotel (buildingCount);
                prp.setRentAmount(); // Add setRentAmount function to Property class.
                return true;

            }
            else
                return false;

        }



    }
    public boolean tradeCard(int cardID, Player owner, Player target) {
        boolean flag = false;
        for (int i = 0 ; i< owner.cardsOwned.size(); i++) {
            if (cardsOwned[i] == cardID){
                owner.cardsOwned[i].remove();
                target.cardsOwned.add(cardID);
                flag= true;
                break;
            }
            else
                flag = false;
        }
        return flag;


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
    public int getNumberOfHouses(){
        return this.numberOfHouses;

    }
    public void setNumberOfHouses(int numberOfHouses){

        this.numberOfHouses= numberOfHouses;
    }
    public int getNumberOfHotels(){
        return this.numberOfHotels;

    }
    public void setNumberOfHotels(int numberOfHotels){
        this.numberOfHotels= numberOfHotels;
    }


}
