package GameLogic;

public class Pawn 
{
    private int location;
    private Player owner;
    private String color;

    public Pawn(Player player, String color) 
    {
    	location = 0;
        this.owner = player;
        this.color = color;
    }

    public int getLocation() 
    {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Player getOwner() 
    {
        return owner;
    }
    
    public void setOwner(Player player) 
    {
        owner = player;
    }
}
