package sample.subsystems.GameLogic;

public class DrawCardTile extends Tile{

    public DrawCardTile( String tileName, int tileLocation){
        super(tileName, tileLocation);
    }

    @Override
    public void onLand(Player p) {
        if(this.getTileLocation() == 8
        || this.getTileLocation() == 23
        || this.getTileLocation() == 37)
            drawChanceCard(p);

        else if(this.getTileLocation() == 3
        || this.getTileLocation() == 18
        || this.getTileLocation() == 34)
            drawCommunityChestCard(p);
    }

    public static ColoredProperty askForTargetProperty(Player p){
        //THIS METHOD WILL RETURN THE TARGET PROPERTY FOR EVENT
        //NEED UI FOR THIS
        return null; // PLACEHOLDER
    }

    public static Card drawChanceCard( Player p){
        Card c = GameBoard.getChanceCards().get(GameBoard.getChanceCards().size()-1);
        if (c.getCardID()  == 17 || c.getCardID() == 18
            || c.getCardID() == 19 || c.getCardID() == 20){
            ColoredProperty property = DrawCardTile.askForTargetProperty(p);
            c.onDraw(p,property);
            return c;
        }
        else
            c.onDraw(p,null);
        return c;


    }

    public static Card drawCommunityChestCard( Player p){
        Card c = GameBoard.getCommunityChestCards().get(GameBoard.getCommunityChestCards().size()-1);
        c.onDraw(p,null);
        return c;
    }

}
