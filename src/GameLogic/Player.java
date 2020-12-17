package GameLogic;

import java.util.ArrayList;

public class Player {

	private String name;
    private int balance;
    private int location;
    private boolean isBankrupt;
    private boolean isInJail;
    private ArrayList<Card> cards;
    private ArrayList<Property> properties;   
    private Pawn pawn;

    public Player( String name)
    {
    	this.name = name;
        balance = 1500; 
        location = 0;
        isBankrupt = false;
        isInJail = false;
        cards = new ArrayList<>();
        properties = new ArrayList<>();
        pawn = new Pawn(this, "");
    }
    
    public String getName()
    {
    	return name;
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

    public void setLocation(int location){
        this.location = location;
    }

    public boolean isBankrupt()
    {
        return isBankrupt;
    }

    public boolean isInJail()
    {
        return this.isInJail;
    }

    public void setInJail(boolean b){
        this.isInJail = b;
    }
}
