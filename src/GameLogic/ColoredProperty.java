package GameLogic;

public class ColoredProperty extends Property{

    private int numberOfHouses;
    private int numberOfHotels;
    private String color;
    private int houseCost;
    private int hotelCost;

    public ColoredProperty(int value, int rent, String tileName, String color, int houseCost, int hotelCost)
    {
        super(value, rent, tileName);
        this.color = color;
        numberOfHotels = 0;
        numberOfHouses = 0;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
    }

    public int getNumberOfHotels() 
    {
        return numberOfHotels;
    }

    public int getNumberOfHouses() 
    {
        return numberOfHouses;
    }

    public int getHouseCost() 
    {
        return houseCost;
    }
    
    public int getHotelCost() 
    {
        return hotelCost;
    }

    public String getColor() 
    {
        return color;
    }

    public void setHouses(int setNo){numberOfHouses = setNo;}
}
