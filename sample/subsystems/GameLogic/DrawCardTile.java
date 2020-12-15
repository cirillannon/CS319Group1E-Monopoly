package sample.subsystems.GameLogic;

import sample.subsystems.Controller.GameManager;

import java.util.ArrayList;
import java.util.Scanner;

public class DrawCardTile extends Tile{

    private static int chanceOrder;
    private static int communityOrder;
    public DrawCardTile( String tileName, int tileLocation){
        super(tileName, tileLocation);
        chanceOrder = 0;
        communityOrder = 0;
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

    public static Property askForTargetProperty(Player p){
        //THIS METHOD WILL RETURN THE TARGET PROPERTY FOR EVENT
        //NEED UI FOR THIS
        Scanner scan = new Scanner(System.in);
        String allCards = "";
        Player[] players = GameManager.getPlayers();
        ArrayList<Property> properties;
        for (Player player : players) {
            properties = player.getProperties();
            for (Property property: properties ) {
                allCards = allCards + property.getOwner().getPlayerName() + ":  " + property.toString() + "\n";
            }
        }
        System.out.println(allCards);
        if (allCards != "") {
            System.out.println("Enter a property location to use the card on");
            int location = scan.nextInt();
            return (Property) GameBoard.getTile(location); // PLACEHOLDER
        }
        return null;
    }

    public static Card drawChanceCard( Player p){
        Card c = GameBoard.getChanceCards().get(chanceOrder % 20);
        chanceOrder++;
        if (c.getCardID()  == 17 || c.getCardID() == 18
            || c.getCardID() == 19 || c.getCardID() == 20){
            System.out.println("Chance Card: " + c.getCardDescription());
            Property property = DrawCardTile.askForTargetProperty(p);
            if (property == null) {
                System.out.println("no property found");
                return c;
            }
            c.onDraw(p,property);
            return c;
        }
        else
            c.onDraw(p,null);
        return c;
    }

    public static Card drawCommunityChestCard( Player p){
        Card c = GameBoard.getCommunityChestCards().get(communityOrder % GameBoard.getCommunityChestCards().size());
        communityOrder++;
        System.out.println("Community Card: " + c.getCardDescription());
        c.onDraw(p,null);
        return c;
    }

    @Override
    public String toString() {
        return ("draw card tile");
    }
}
