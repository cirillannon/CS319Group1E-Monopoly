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
    	tiles.add(new ColoredProperty(60, 2, "Mediterranean Avenue", "brown", 50, 50));
    	tiles.add(new CommunityChestCardTile());
    	tiles.add(new ColoredProperty(60, 4, "Baltic Avenue", "brown", 50, 50));
    	tiles.add(new TaxTile("Income Tax", 200));
    }
    
    private void initializeChanceCards()
    {
    	chanceCards = new ArrayList<Card>();
    	
    	
    }
    
    private void initializeCommunityChestCards()
    {
    	communityChestCards = new ArrayList<Card>();
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