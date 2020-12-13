package sample.subsystems.GameLogic;

public class ColoredProperty extends Property{

    private int numberOfHouses;
    private int numberOfHotels;
    private String color;
    private int rentWithOne;
    private int rentWithTwo;
    private int rentWithThree;
    private int rentWithFour;
    private int rentWithHotel;
    private int houseCost;
    private int hotelCost;
    private int mortgageValue;

    public ColoredProperty(int value, int baseRent, String tName, int tLocation, String color,
                           int rentWithOne, int rentWithTwo, int rentWithThree,
                           int rentWithFour, int rentWithHotel, int houseCost,
                           int hotelCost, int mortgageValue){
        super(value, baseRent, tName, tLocation);
        this.color = color;
        numberOfHotels = 0;
        numberOfHouses = 0;
        this.rentWithOne = rentWithOne;
        this.rentWithTwo = rentWithTwo;
        this.rentWithThree = rentWithThree;
        this.rentWithFour = rentWithFour;
        this.rentWithHotel = rentWithHotel;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
        this.mortgageValue = mortgageValue;
    }

    @Override
    public void payRent(Player landed) {
        int finalRent;
        if (numberOfHouses == 1)
            finalRent = rentWithOne;
        else if (numberOfHouses == 2)
            finalRent = rentWithTwo;
        else if (numberOfHouses == 3)
            finalRent = rentWithThree;
        else if (numberOfHouses == 4)
            finalRent = rentWithFour;
        else if (numberOfHotels == 1)
            finalRent = rentWithHotel;
        else
            finalRent = super.getRent();

        landed.updateBalance(-finalRent);
        this.getOwner().updateBalance(finalRent);
        System.out.println(landed.getPlayerName() + " pays " + finalRent + " to " + this.getOwner().getPlayerName());
    }

    public int getNumberOfHotels() {
        return numberOfHotels;
    }

    public int getNumberOfHouses() {
        return numberOfHouses;
    }

    public int getRentWithOne() {
        return rentWithOne;
    }

    public int getRentWithTwo() {
        return rentWithTwo;
    }

    public int getRentWithThree() {
        return rentWithThree;
    }

    public int getRentWithFour() {
        return rentWithFour;
    }

    public int getRentWithHotel() {
        return rentWithHotel;
    }

    public int getMortgageValue(){
        return mortgageValue;
    }

    public int getHotelCost() {
        return hotelCost;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public String getColor() {
        return color;
    }

    public boolean updateHouses(int count){
        int result =  numberOfHouses + count;
        if (result <= 5) {
            numberOfHouses = result;
            return true;
        } else {
            return false;
        }
    }

    public boolean updateHotels(int count){
        int result =  numberOfHotels + count;
        if (result <= 5) {
            numberOfHotels = result;
            return true;
        } else {
            return false;
        }
    }

    public void applyEvent(int eventID){
        if (eventID == 1){
            this.mortgageValue = this.mortgageValue + this.mortgageValue/4;
            super.setValue(super.getValue() + super.getValue()/4);
            super.setRent(super.getRent() + super.getRent()/4);
            this.rentWithOne = this.rentWithOne + this.rentWithOne/4;
            this.rentWithTwo = this.rentWithTwo + this.rentWithTwo/4;
            this.rentWithThree = this.rentWithThree + this.rentWithThree/4;
            this.rentWithFour = this.rentWithFour + this.rentWithFour/4;
            this.rentWithHotel = this.rentWithHotel + this.rentWithHotel/4;
        }

        else if (eventID == 2){
            this.mortgageValue = this.mortgageValue + this.mortgageValue/2;
            super.setValue(super.getValue() + super.getValue()/2);
            super.setRent(super.getRent() + super.getRent()/2);
            this.rentWithOne = this.rentWithOne + this.rentWithOne/2;
            this.rentWithTwo = this.rentWithTwo + this.rentWithTwo/2;
            this.rentWithThree = this.rentWithThree + this.rentWithThree/2;
            this.rentWithFour = this.rentWithFour + this.rentWithFour/2;
            this.rentWithHotel = this.rentWithHotel + this.rentWithHotel/2;
        }

        else if (eventID == 3){
            this.mortgageValue = this.mortgageValue - this.mortgageValue/4;
            super.setValue(super.getValue() - super.getValue()/4);
            super.setRent(super.getRent() - super.getRent()/4);
            this.rentWithOne = this.rentWithOne - this.rentWithOne/4;
            this.rentWithTwo = this.rentWithTwo - this.rentWithTwo/4;
            this.rentWithThree = this.rentWithThree - this.rentWithThree/4;
            this.rentWithFour = this.rentWithFour - this.rentWithFour/4;
            this.rentWithHotel = this.rentWithHotel - this.rentWithHotel/4;
        }

        else if (eventID == 4){
            this.mortgageValue = this.mortgageValue - this.mortgageValue/2;
            super.setValue(super.getValue() - super.getValue()/2);
            super.setRent(super.getRent() - super.getRent()/2);
            this.rentWithOne = this.rentWithOne -this.rentWithOne/2;
            this.rentWithTwo = this.rentWithTwo - this.rentWithTwo/2;
            this.rentWithThree = this.rentWithThree - this.rentWithThree/2;
            this.rentWithFour = this.rentWithFour - this.rentWithFour/2;
            this.rentWithHotel = this.rentWithHotel - this.rentWithHotel/2;
        }
    }
}
