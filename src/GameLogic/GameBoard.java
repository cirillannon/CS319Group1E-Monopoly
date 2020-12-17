package GameLogic;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard 
{
    private ArrayList<Card> chanceCards;
    private ArrayList<Card> communityChestCards;
    private ArrayList<Tile> tiles;
    private ArrayList<Pawn> pawns;
    private Dice dice;
    
    public GameBoard()
    {
    	initializeChanceCards();
    	initializeCommunityChestCards();
    	initializeTiles();   	
    	initializePawns();
    	dice = new Dice();
    	
    }

    private void initializeTiles()
    {
    	tiles = new ArrayList<Tile>();
    	tiles.add(new GO());
    	tiles.add(new ColoredProperty(60, 2, "Mediterranean Avenue", "brown", 50, 250));
    	tiles.add(new CommunityChestCardTile());
    	tiles.add(new ColoredProperty(60, 4, "Baltic Avenue", "brown", 50, 250));
    	tiles.add(new TaxTile("Income Tax", 200));
    	tiles.add(new Railroad(200, 25, "King's Cross Station"));
        tiles.add(new ColoredProperty(100, 6, "The Angel Islington", "light blue", 50, 250));
        tiles.add(new ChanceCardTile());
        tiles.add(new ColoredProperty(100, 6, "Euston Road", "light blue", 50, 250));
        tiles.add(new ColoredProperty(120, 8, "Pentonville Road\n", "light blue", 50, 250));
        tiles.add(new Jail("Jail"));
        tiles.add(new ColoredProperty(140, 10, "Pall Mall", "pink", 100, 500));
        tiles.add(new Utility(150, 1, "Electric Company"));
        tiles.add(new ColoredProperty(140, 10, "Whitehall", "pink", 100, 500));
        tiles.add(new ColoredProperty(160, 12, "Northumberland Avenue", "pink", 100, 500));
        tiles.add(new Railroad(200, 25, "Marylebone Station"));
        tiles.add(new ColoredProperty(180, 14, "Bow Street", "orange", 100, 500));
        tiles.add(new CommunityChestCardTile());
        tiles.add(new ColoredProperty(180,14, "Marlborough Street", "orange", 100, 500));
        tiles.add(new ColoredProperty(200, 16, "Vine Street", "orange", 100, 500));
        tiles.add(new FreeParking("Free Parking"));
        tiles.add(new ColoredProperty(220, 18, "Strand", "red", 150, 750));
        tiles.add(new ChanceCardTile());
        tiles.add(new ColoredProperty(220, 18, "Fleet Street", "red", 150, 750));
        tiles.add(new ColoredProperty(240, 20, "Trafalgar Square", "red", 150, 750));
        tiles.add(new Railroad(200, 25, "Fenchurch St. Station"));
        tiles.add(new ColoredProperty(260, 22, "Leicester Square", "yellow", 150, 750));
        tiles.add(new ColoredProperty(260, 22, "Coventry Street", "yellow", 150, 750));
        tiles.add(new Utility(150, 1, "Electric Company"));
        tiles.add(new ColoredProperty(280, 22, "Piccadilly", "yellow", 150, 750));
        tiles.add(new GoToJail("Go To Jail"));
        tiles.add(new ColoredProperty(300, 26, "Regent Street", "yellow", 150, 750));
        tiles.add(new ColoredProperty(300, 26, "Oxford Street", "yellow", 150, 750));
        tiles.add(new CommunityChestCardTile());
        tiles.add(new ColoredProperty(320, 28, "Bond Street", "yellow", 150, 750));
        tiles.add(new Railroad(200, 25, "Liverpool St. Station"));
        tiles.add(new ChanceCardTile());
        tiles.add(new ColoredProperty(350, 35, "Park Lane", "dark blue", 200, 1000));
        tiles.add(new TaxTile("Super Tax", 100));
        tiles.add(new ColoredProperty(400, 50, "Mayfair", "dark blue", 200, 1000));
    }
    
    private void initializeChanceCards()
    {
    	chanceCards = new ArrayList<Card>();
    	chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[0], new ChangeLocation(1,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[1], new ChangeLocation(25,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[2], new ChangeLocation(12,null)));

        //These two cards need nearest Utility and Railroad. They need to be calculated in controller classes.
        //Use Card.getStrategy().setTarget(); with calculated target location
        // -1 is placeholder, DON'T CALL AFFECT BEFORE USING setTarget();
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[3], new ChangeLocation(-1,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[4], new ChangeLocation(-1,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[5], new ChangeBalance( 50, null)));

        // JailCard Will be implemented
        chanceCards.add(null);

        //Use Card.getStrategy().setTarget(); with target as -3 of current location
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[7], new ChangeLocation(-1,null)));

        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[8], new ChangeLocation(31,null)));

        //Use Card.getStrategy().setAmount() with amount as house*25 + hotel*100
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[9], new ChangeBalance(-1, null)));

        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[10], new ChangeBalance(15, null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[11], new ChangeLocation(6,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[12], new ChangeLocation(40,null)));

        //Give every player 50$ after this is called
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[13], new ChangeBalance(-200,null)));

        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[14], new ChangeBalance(150,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[15], new ChangeBalance(100,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[15], new ChangePropertyRent(true,25,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[16], new ChangePropertyRent(true,50,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[17], new ChangePropertyRent(false,-25,null)));
        chanceCards.add(new ChanceCard(Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[18], new ChangePropertyRent(false,-25,null)));
    }

    private void initializeCommunityChestCards()
    {
        communityChestCards = new ArrayList<Card>();
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[0], new ChangeLocation(1,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[1], new ChangeBalance(200,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[2], new ChangeBalance(-50,null) ));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[3], new ChangeBalance(50,null) ));

        // JailCard Will be implemented
        chanceCards.add(null);

        communityChestCards.add(new ChanceCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[5], new ChangeLocation(31,null)));

        //Take 50$ from every player after this is called
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[6], new ChangeBalance(200,null)));

        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[7], new ChangeBalance(100,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[8], new ChangeBalance(20,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[9], new ChangeBalance(10,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[10], new ChangeBalance(100,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[11], new ChangeBalance(-100,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[12], new ChangeBalance(-150,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[13], new ChangeBalance(25,null)));

        //Use Card.getStrategy().setAmount() with amount as house*40 + hotel*115
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[14], new ChangeBalance(-1,null)));

        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[15], new ChangeBalance(10,null)));
        communityChestCards.add(new CommunityChestCard(Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[16], new ChangeBalance(100,null)));
    }
    
    private void initializePawns()
    {
    	pawns = new ArrayList<Pawn>();
    	pawns.add(new Pawn(null, "orange"));
    	pawns.add(new Pawn(null, "yellow"));
    	pawns.add(new Pawn(null, "green"));
    	pawns.add(new Pawn(null, "blue"));
    }
    
    public void rollDice()
    {
    	dice.rollDice();
    }
    
    public int getDice1()
    {
    	return dice.getDice1();
    }
   
    public int getDice2()
    {
    	return dice.getDice2();
    }
    
    public int getDiceSum()
    {
    	return dice.getDiceSum();
    }
    
    public Tile getTile(int index)
    {
		return tiles.get(index);	
    }
}