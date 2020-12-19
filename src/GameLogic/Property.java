package GameLogic;

public abstract class Property extends Tile 
{
    private int value;
    private Player owner;
    protected int rent;
    private boolean isMortgaged;
    
    public Property(int value, int rent, String tileName)
    {
        super(tileName);
        this.value = value;
        this.rent = rent;
        owner = null;
        isMortgaged = false;
    }

    public boolean isOwned()
    {
        return !(owner == null);
    }

    public boolean isMortgaged()
    {
        return isMortgaged;
    }

    public void setMortgaged(boolean isMortgaged)
    {
    	this.isMortgaged = isMortgaged;
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setOwner(Player player)
    {
        owner = player;
    }

    public int getRent()
    {
        return rent;
    }

    public void setRent(int rent)
    {
        this.rent = rent;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

}
