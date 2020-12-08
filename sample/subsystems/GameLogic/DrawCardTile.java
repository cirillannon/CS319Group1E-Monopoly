package sample.subsystems.GameLogic;

import sample.subsystems.Controller.GameManager;

import java.util.ArrayList;

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

    public static void drawChanceCard( Player p){
        Card c = GameManager.getChanceCards().get(GameManager.getChanceCards().size()-1);
        if (c.getCardID()  == 17 || c.getCardID() == 18
            || c.getCardID() == 19 || c.getCardID() == 20){
            ColoredProperty property = DrawCardTile.askForTargetProperty(p);
            c.onDraw(p,property);
        }
        else
            c.onDraw(p,null);

    }

    public static void drawCommunityChestCard( Player p){
        Card c = GameManager.getCommunityChestCards().get(GameManager.getCommunityChestCards().size()-1);
        c.onDraw(p,null);
    }

}
