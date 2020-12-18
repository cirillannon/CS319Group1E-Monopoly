package GameLogic;

import java.util.ArrayList;

public class Player {

    private int id;
	private String name;
    private int balance;
    private int location;
    private boolean hasRentDebt;
    private boolean isBankrupt;
    private boolean isInJail;
    private ArrayList<Card> cards;
    private ArrayList<Property> properties;   
    private String color;

    public Player( String name, String color)
    {
    	this.name = name;
        balance = 1500; 
        location = 0;
        isBankrupt = false;
        isInJail = false;
        cards = new ArrayList<>();
        properties = new ArrayList<>();
        this.color = color;
        hasRentDebt = false;
        id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName()
    {
    	return name;
    }
   
    public String getColor()
    {
    	return color;
    }
    
    public int getBalance()
    {
        return balance;
    }
    
    public void addProperty(Property property)
    {
    	properties.add(property);
    }
    
    public void decrementBalance(int amount)
    {
    	balance = balance - amount;
    }
    
    public void incrementBalance(int amount)
    {
    	balance = balance + amount;
    }

    public void move(int movementAmount)
    {
        location = (location + movementAmount) % Constants.TileConstants.TILE_COUNT;
    }
    
    public int getLocation()
    {
        return location;
    }

    public boolean isBankrupt()
    {
        return isBankrupt;
    }

    public boolean isInJail()
    {
        return this.isInJail;
    }
    
    public void setHasRentDebt(boolean hasRentDebt)
    {
    	this.hasRentDebt = hasRentDebt;
    }
    
    public boolean hasRentDebt()
    {
    	return hasRentDebt;
    }    
}
