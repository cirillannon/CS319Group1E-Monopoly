package Controller;

import UserInterface.*;
import GameLogic.*;
import java.util.*;

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
		
		gameBoard.rollDice();
		int[] dice = new int[2];
		dice[0] = gameBoard.getDice1();
		dice[1] = gameBoard.getDice2();
		player.move(gameBoard.getDiceSum());
		Tile tile = gameBoard.getTile(player.getLocation());
		System.out.println(player.getName() + " has landed on " + tile.getName());
		
		if(tile instanceof Property)
		{
			if(((Property) tile).isOwned() && ((Property) tile).getOwner() != player )
			{
				player.setHasRentDebt(true);
			}
		}
		
		return dice;	
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
	
	public void turnEnded()
	{
		if(players.get(turn).hasRentDebt())
		{
			return;
		}
		
		turn = (turn + 1) % countOfPlayers;
	}
	
}
