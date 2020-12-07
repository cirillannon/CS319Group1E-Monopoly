package sample.subsystems.Controller;

import sample.subsystems.GameLogic.Card;
import sample.subsystems.GameLogic.GameBoard;
import sample.subsystems.GameLogic.Player;
import sample.subsystems.GameLogic.Tile;

import java.util.ArrayList;

// communicates with Server ?
public class GameManager {
    // properties

    private boolean gameStarted;
    private boolean gameOver;
    private static int numOfPlayers;
    private static ArrayList<Card> chanceCards;
    private static ArrayList<Card> communityChestCards;
    private static Tile[] tiles; // index 0 is empty, we have tiles between 1-40
    private static Player[] players; // max 4 players with playerID's between 0-3

    // constructor
    public GameManager() {







    }

    // methods
    public boolean initGame(int startMoney) {
        initBank();
        initCards();
        setPawnStartPosition();
        startPawnSelection();
        // static or instance???
        // initBoard();
    }

    // how to reach pawns?
    public void setPawnStartPosition() {

    }

    //
    public void startPawnSelection() {

    }

    public boolean initBank() {

    }

    public boolean startGame() {

    }

    public boolean endGame() {

    }

    public boolean isGameOver() {

    }

    public static ArrayList<Card> getChanceCards(){
        return chanceCards;
    }

    public static ArrayList<Card> getCommunityChestCards() {
        return communityChestCards;
    }

    public static Tile[] getTiles(){
        return tiles;
    }

    private void initCards(){

    }

    public static int getNumOfPlayers(){
        return numOfPlayers;
    }

    public static Player[] getPlayers(){
        return players;
    }
}
