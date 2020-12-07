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

    public static void drawChanceCard( Player p){
        Card c = GameManager.getChanceCards().get(GameManager.getChanceCards().size()-1);
        c.onDraw(p);
    }

    public static void drawCommunityChestCard( Player p){
        Card c = GameManager.getCommunityChestCards().get(GameManager.getCommunityChestCards().size()-1);
        c.onDraw(p);
    }

}
