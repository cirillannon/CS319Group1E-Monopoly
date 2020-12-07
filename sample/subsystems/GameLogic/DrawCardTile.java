package sample.subsystems.GameLogic;

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

    }

    //IN PROGRESS

}
