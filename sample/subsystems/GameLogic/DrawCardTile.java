package sample.subsystems.GameLogic;

import java.util.ArrayList;

public class DrawCardTile extends Tile{

    private ArrayList<Integer> chanceCards;
    private ArrayList<Integer> communityChestCards;


    public DrawCardTile( String tileName, int tileLocation){
        super(tileName, tileLocation);

    }

    @Override
    public void onLand(Player p) {
        //TBI

    }

    //IN PROGRESS

}
