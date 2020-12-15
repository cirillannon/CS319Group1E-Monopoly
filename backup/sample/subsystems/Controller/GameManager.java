package sample.subsystems.Controller;

import sample.subsystems.GameLogic.*;

import java.util.ArrayList;
import java.util.Scanner;

// communicates with Server ?
public class GameManager {

    // properties
    private boolean gameStarted;
    private static int turnOf;
    private boolean gameOver;
    private static int numOfPlayers;
    private static Player[] players; // max 4 players with playerID's between 0-3
    private Card drawnCard; // The latest drawn card is held here
    Scanner scan = new Scanner(System.in);

    // constructor
    public GameManager() {
        gameStarted = false;
        gameOver = false;
        System.out.println("GameManager initialized");
    }

    // methods
    public void initGame(int playerNumber, String playerName1, String playerName2,
                         String playerName3, String playerName4) {
        String[] playerNames = {playerName1, playerName2, playerName3, playerName4};
        Bank bank = initBank();
        System.out.println("Bank initialized");
        //setPawnStartPosition();
        //startPawnSelection();
        GameBoard.initBoard();
        System.out.println("Board initialized");
        numOfPlayers = playerNumber;
        players = new Player[playerNumber];
        for (int i = 0; i < numOfPlayers; i++){
            players[i] = new Player(playerNames[i], i, Constants.PlayerConstants.PAWN_COLORS[i]);
        }
        System.out.println("Players and pawns initialized");
    }

    public Bank initBank() {
        Bank bank = Bank.initBank();
        return bank;
    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        turnOf = 0;
        while(!isGameOver()) {
            if (players[turnOf].getBankruptcy() || players[turnOf].hasLeft()) {
                deletePlayer(turnOf);
            }
            passTurn();
        }
        endGame();
    }

    public void endGame() {

    }

    public boolean isGameOver() {
        if (getNumOfPlayers() < 2) {
            return true;
        }
        return false;
    }

    public void deletePlayer(int playerIndex) {
        for (int i = playerIndex; i < numOfPlayers; i++) {
            players[i] = players[i+1];
        }
        players[numOfPlayers-1] = null;
        numOfPlayers--;
    }

    private void passTurn(){
        players[turnOf].playTurn();
        players[turnOf].setPlayerHasTurn(false);
        turnOf++;
        turnOf = turnOf % numOfPlayers;
        players[turnOf].setPlayerHasTurn(true);
    }

    public static int getNumOfPlayers(){
        return numOfPlayers;
    }

    public static Player[] getPlayers(){
        return players;
    }

    public static Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getPlayerName().equals(name))
                return player;
        }
        return null;
    }

    public static Player currentTurn(){
        return players[turnOf];
    }

}
