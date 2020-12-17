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
        players.add(new Player("John"));
        players.add(new Player("Ann"));
        countOfPlayers = 2;
	}
	
	public int[] diceRolled()
	{
		Player player = players.get(turn);
		
		gameBoard.rollDice();
		int[] dice = new int[2];
		dice[0] = gameBoard.getDice1();
		dice[1] = gameBoard.getDice2();
		player.move(gameBoard.getDiceSum());
		Tile tile = gameBoard.getTile(player.getLocation());
		System.out.println(player.getName() + " has landed on " + tile.getName());
		
		return dice;	
	}
	
	public void turnEnded()
	{
		turn = (turn + 1) % countOfPlayers;
	}
	
}
