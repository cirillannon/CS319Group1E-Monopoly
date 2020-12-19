package Controller;

import UserInterface.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import GameLogic.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.JFrame;

public class GameManager 
{
	GameBoard gameBoard;
	private ArrayList<Player> players; 
	private int turn;
	private boolean isGameOver;
	private int countOfPlayers;
	
	public GameManager()
    {
	    gameBoard = new GameBoard();
        players = new ArrayList<Player>();
        initializePlayers();
        isGameOver = false;
        turn = 0;
    }

	public String getTurnsColor()
	{
		return players.get(turn).getColor();
	}
	
	private void initializePlayers()
	{
        players = new ArrayList<Player>();
        players.add(new Player("John", "orange"));
        players.add(new Player("Ann", "yellow"));
        countOfPlayers = 2;
	}
	
	public int[] rollDice()
	{
		Player player = players.get(turn);
		
		if(player.hasRolledDice())
		{
			return null;
		}
		
		gameBoard.rollDice();
		int[] dice = new int[2];
		dice[0] = gameBoard.getDice1();
		dice[1] = gameBoard.getDice2();
		player.move(gameBoard.getDiceSum());
		Tile tile = gameBoard.getTile(player.getLocation());
		System.out.println(player.getName() + " has landed on " + tile.getName());
		
		if(tile instanceof Property)
		{
			if(((Property) tile).isOwned() && ((Property) tile).getOwner() != player && !((Property) tile).isMortgaged() )
			{
				player.setHasRentDebt(true);
			}
		}
		
		player.setHasRolledDice(true);
		return dice;	
	}

	public boolean payRent()
	{
		Player player = players.get(turn);
		Tile tile = gameBoard.getTile(player.getLocation());
		
		if(!(tile instanceof Property) || !(player.hasRentDebt()))
		{
			return false;
		}
		
		int rentAmount = ((Property) tile).getRent();
		
		if(player.getBalance() >= rentAmount)
		{
			player.decrementBalance(rentAmount);
			((Property) tile).getOwner().incrementBalance(rentAmount);
			player.setHasRentDebt(false);
			
			return true;
		}
		
		return false;
	}
	
	public boolean buyProperty()
	{
		Player player = players.get(turn);
		Tile tile = gameBoard.getTile(player.getLocation());
		
		if(tile instanceof Property)
		{
			if(!((Property) tile).isOwned() && ((Property) tile).getOwner() != player)
			{
				if(player.getBalance() > ((Property) tile).getValue())
				{
					player.addProperty((Property) tile);
					player.decrementBalance(((Property) tile).getValue());
					((Property) tile).setOwner(player);
					return true;
				}
			}
		}
	
		return false;
	}

	public boolean sellProperty()
	{
		Player player = players.get(turn);
		Tile tile = gameBoard.getTile(player.getLocation());

		if(tile instanceof Property)
		{
			if(((Property) tile).isOwned() && ((Property) tile).getOwner() == player)
			{
				if(player.getBalance() > ((Property) tile).getValue())
				{
					player.removeProperty((Property) tile);
					player.incrementBalance(((Property) tile).getValue());
					((Property) tile).setOwner(null);
					return true;
				}
			}
		}

		return false;
	}

	public void mortgage() {
			Player player = players.get(turn);
			Tile tile = gameBoard.getTile(player.getLocation());
			if(tile instanceof Property){
				if (((Property) tile).getOwner()==player)
				{
					if (((Property) tile).isMortgaged())
					{
						return;
					}
					else {
						player.incrementBalance(((Property)tile).getValue() / 2);
						((Property)tile).setMortgaged(true);
					}

				}
				else
					return;

			}
	}

	public void unmortgage() {
		Player player = players.get(turn);
		Tile tile = gameBoard.getTile(player.getLocation());
		if(tile instanceof Property){
			if (((Property) tile).getOwner()==player)
			{
				if (((Property) tile).isMortgaged())
				{
					player.decrementBalance(((Property)tile).getValue() / 2);
					((Property)tile).setMortgaged(false);
				}
				else {
					return;
				}

			}
			else
				return;

		}
	}




	public int getBalance(String color)
	{
		for(int i = 0; i < countOfPlayers; i++)
		{
			if(players.get(i).getColor() == color)
			{
				return players.get(i).getBalance();
			}
		}
		
		return -1;
	}
	
	private int calculateMovementAmount(int previousLocation, int newLocation)
    {
		if(previousLocation == newLocation)
		{
			return 0;
		}
		
		int movementAmount = 0;
		
    	for(int i = 0; i < 40; i++)
    	{
    		previousLocation = (previousLocation + 1) % 40;
    		movementAmount++;
    		
    		if(previousLocation == newLocation)
    		{
    			return movementAmount;
    		}
    	}   	
    	
    	return 0;
    }
	
	private int getNearestUtility(int location)
	{
		location = (location + 1) % 40;
		
		for(int i = 0; i < 39; i++)
		{
			if(gameBoard.getTile(location) instanceof Utility)
			{
				return location;
			}
			
			location = (location + 1) % 40;
		}
		
		return -1;
	}
	
	private int getNearestRailroad(int location)
	{
		location = (location + 1) % 40;
		
		for(int i = 0; i < 39; i++)
		{
			if(gameBoard.getTile(location) instanceof Railroad)
			{
				return location;
			}
			
			location = (location + 1) % 40;
		}
		
		return -1;
	}
	
	public int drawChanceCard()
	{
		Player player = players.get(turn);
		Tile tile = gameBoard.getTile(player.getLocation());
		
//		if(!(tile instanceof ChanceCardTile))
//		{
//			return 0;
//		}
		
		ChanceCard cardDrawn = gameBoard.drawChanceCard();
		System.out.println(cardDrawn.getCardDescription());
		int playersFirstLocation = player.getLocation();
		
		if(cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[3])
		{
			cardDrawn.setEffectStrategy(new ChangeLocation(getNearestUtility(playersFirstLocation), true));
			
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[4])
		{
			cardDrawn.setEffectStrategy(new ChangeLocation(getNearestRailroad(playersFirstLocation), true));
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[7])
		{
			cardDrawn.setEffectStrategy(new ChangeLocation((playersFirstLocation + 37) % 40, false));
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[9])
		{
			cardDrawn.setEffectStrategy(new ChangeBalance(-(player.getHouseCount() * 25 + player.getHotelCount() * 100)));
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[13])
		{
			int debt = -((countOfPlayers - 1) * 50);
			cardDrawn.setEffectStrategy(new ChangeBalance(debt));
			
			for(int i = 0; i < countOfPlayers; i++)
			{
				if(players.get(i) != player)
				{
					players.get(i).incrementBalance(50);
				}
			}
		}		
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[15])
		{
			int propertyCount = player.getPropertyCount();
			
			if(propertyCount != 0)
			{
				int selectedPropertyIndex = (int)(Math.random()*propertyCount + 1) - 1;
				cardDrawn.setEffectStrategy(new ChangePropertyRent(true, 25, player.getProperty(selectedPropertyIndex)));
			}
			else
			{
				return 0;
			}
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[16])
		{
			int propertyCount = player.getPropertyCount();
			
			if(propertyCount != 0)
			{
				int selectedPropertyIndex = (int)(Math.random()*propertyCount + 1) - 1;
				cardDrawn.setEffectStrategy(new ChangePropertyRent(true, 50, player.getProperty(selectedPropertyIndex)));
			}	
			else
			{
				return 0;
			}
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[17])
		{
			int propertyCount = player.getPropertyCount();
			
			if(propertyCount != 0)
			{
				int selectedPropertyIndex = (int)(Math.random()*propertyCount + 1) - 1;
				cardDrawn.setEffectStrategy(new ChangePropertyRent(false, 25, player.getProperty(selectedPropertyIndex)));
			}
			else
			{
				return 0;
			}
		}
		else if (cardDrawn.getCardDescription() == Constants.CardConstants.CHANCE_CARD_DESCRIPTIONS[18])
		{
			int propertyCount = player.getPropertyCount();
			
			if(propertyCount != 0)
			{
				int selectedPropertyIndex = (int)(Math.random()*propertyCount + 1) - 1;
				cardDrawn.setEffectStrategy(new ChangePropertyRent(false, 50, player.getProperty(selectedPropertyIndex)));
			}
			else
			{
				return 0;
			}
		}
			
		cardDrawn.affect(player);
		return calculateMovementAmount(playersFirstLocation, player.getLocation());
	}
	
	public int drawCommunityChestCard()
	{
		Player player = players.get(turn);
		Tile tile = gameBoard.getTile(player.getLocation());
		
//		if(!(tile instanceof CommunityChestCardTile))
//		{
//			return 0;
//		}
		
		CommunityChestCard cardDrawn = gameBoard.drawCommunityChestCard();
		System.out.println(cardDrawn.getCardDescription());
		int playersFirstLocation = player.getLocation();
		
		if(cardDrawn.getCardDescription() == Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[6])
		{
			int income = (countOfPlayers - 1) * 50;
			cardDrawn.setEffectStrategy(new ChangeBalance(income));
			
			for(int i = 0; i < countOfPlayers; i++)
			{
				if(players.get(i) != player)
				{
					players.get(i).decrementBalance(50);
				}
			}
		}
		else if(cardDrawn.getCardDescription() == Constants.CardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[14])
		{
			cardDrawn.setEffectStrategy(new ChangeBalance(-(player.getHouseCount() * 40 + player.getHotelCount() * 115)));
		}

		cardDrawn.affect(player);
		return calculateMovementAmount(playersFirstLocation, player.getLocation());
	}
	
	public void turnEnded()
	{
		if(players.get(turn).hasRentDebt())
		{
			return;
		}
		
		turn = (turn + 1) % countOfPlayers;
		players.get(turn).setHasRolledDice(false);
	}
	 //helper method for buildHouse
    public ArrayList<Property> getSameColor(ArrayList<Property> list, String color) {
        ArrayList<Property> storeSameColor = new ArrayList<Property>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof ColoredProperty) {
                if (((ColoredProperty) (list.get(i))).getColor() == color) ;
                {
                    storeSameColor.add(list.get(i));
                }
            }
        }

        return storeSameColor;
    }
    public int buildSpecificTile(ArrayList<Property> playerProperties, ArrayList<Property> storeSameColor, String name, String color)
    {
        int result = 0;
        storeSameColor = getSameColor(playerProperties,color);

        int storeHouse = 0;
        int arrIndex = 0;

        for(int i = 0; i < storeSameColor.size(); i++)
        {

            if(((ColoredProperty)storeSameColor.get(i)).getName().equals(name))
            {
                storeHouse= ((ColoredProperty)storeSameColor.get(i)).getNumberOfHouses(); // get the number of houses on that property
                arrIndex = i;
            }

        }

        System.out.println("value of store is" + storeHouse);
        //remove that house from the storeSameColor array because it needs to be compared to the other properties not by itself again
        storeSameColor.remove(arrIndex);
        for(int i = 0; i < storeSameColor.size();i++)
        {
            System.out.println("comparted to " + ((ColoredProperty)storeSameColor.get(i)).getNumberOfHouses());
            //if the difference of houses in the same color set is more than 1
            if(storeHouse-((ColoredProperty)storeSameColor.get(i)).getNumberOfHouses() >= 1)
            {
                System.out.println("disparity");
                return 0;
            }
            else
            {
                //increment the house on that property by updating it on the arraylist of the players property list
                for (Property playerProperty : playerProperties) {
                    if (playerProperty instanceof ColoredProperty) {
                        if (((ColoredProperty) playerProperty).getName().equals(name)) {
                            ((ColoredProperty) playerProperty).setHouses(storeHouse + 1);
                        }
                    }
                }
                result = storeHouse + 1;

            }
        }
        return result;
    }

    //returns a stage i.e stage 0 do nothing and stage 5 build a hotel
    public int buildHouse(int index)
    {
        int result = 0;
        int count = 0;
        boolean hasMonopoly = false;
        Player curPlayer = players.get(turn);
        ArrayList<Property> playerProperties = curPlayer.getProperties();
        ArrayList<Property> storeSameColor = new ArrayList<Property>();

        //if the property is first brown tile or second brown tile on the board
        if(index == 0 || index == 1 ) {
           hasMonopoly = curPlayer.hasMonopoly("brown");
        }

        else if (index == 2 || index == 3 || index == 4)
        {
            hasMonopoly = curPlayer.hasMonopoly("light blue");
        }

        else if (index == 5 || index == 6 || index == 7)
        {
            hasMonopoly = curPlayer.hasMonopoly("pink");
        }
        else if (index == 8 || index == 9 || index == 10)
        {
            hasMonopoly = curPlayer.hasMonopoly("orange");
        }
        else if (index == 11 || index == 12 || index == 13)
        {
            hasMonopoly = curPlayer.hasMonopoly("red");
        }
        else if (index == 14 || index == 15 || index == 16)
        {
            hasMonopoly = curPlayer.hasMonopoly("pink");
        }
        else if (index == 17 || index == 18 || index == 19)
        {
            hasMonopoly = curPlayer.hasMonopoly("green");
        }
        else
        {
            hasMonopoly = curPlayer.hasMonopoly("dark blue");
        }

        //helper method



            //if he wants to build house on first brown tile and has a monopoly of that color then
        if(index == 0 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Mediterranean Avenue", "brown");
        }
        else if(index == 1 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Baltic Avenue", "brown");
        }
        else if(index == 2 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Oriental Avenue", "light blue");
        }
        else if(index == 3 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Vermont Avenue", "light blue");
        }
        else if(index == 4 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Connecticut Avenue", "light blue");
        }
        else if(index == 5 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"St. Charles Place", "pink");
        }
        else if(index == 6 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"States Avenue", "pink");
        }
        else if(index == 7 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Virginia Avenue", "pink");
        }
        else if(index == 8 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"St. James Place", "orange");
        }
        else if(index == 9 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Tennessee Avenue", "orange");
        }
        else if(index == 10 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"New York Avenue", "orange");
        }
        else if(index == 11 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Kentucky Avenue", "red");
        }
        else if(index == 12 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Indiana Avenue", "red");
        }
        else if(index == 13 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Illinois Avenue", "red");
        }
        else if(index == 14 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Atlantic Avenue", "yellow");
        }
        else if(index == 15 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Ventnor Avenue", "yellow");
        } else if(index == 16 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Marven Gardens", "yellow");
        } else if(index == 17 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Pacific Avenue", "green");
        } else if(index == 18 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"North Carolina Avenue", "green");
        } else if(index == 19 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Pennysylvania Avenue", "green");
        }
        else if(index == 20 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Park Place", "dark blue");
        }
        else if(index == 21 && hasMonopoly)
        {
            result = buildSpecificTile(playerProperties, storeSameColor,"Board Walk", "dark blue");
        }
        else
            {
                return 0;
            }

        return result;
    }
}
