package sample.subsystems.Controller;

import sample.subsystems.GameLogic.GameBoard;
import sample.subsystems.GameLogic.Tile;

import java.util.ArrayList;

// communicates with Server ?
public class GameManager {
    // properties

    private int playerStartMoney;
    private boolean gameStarted;
    private boolean gameOver;
    private ArrayList<Integer> chanceCards;
    private ArrayList<Integer> communityChestCards;
    private Tile[] tiles; // index 0 is empty, we have tiles between 1-40

    // constructor
    public GameManager() {







    }

    // methods
    public boolean initGame(int startMoney) {
        setPlayersStartMoney(startMoney);
        initBank();
        initCards();
        setPawnStartPosition();
        startPawnSelection();
        // static or instance???
        // initBoard();
    }

    // give players money - through gameBoard?
    public void setPlayersStartMoney(int startMoney) {
        this.playerStartMoney = startMoney;
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

    public static ArrayList<Integer> getChanceCards(){
        return chanceCards;
    }

    public static ArrayList<Integer> getCommunityChestCards() {
        return communityChestCards;
    }

    public static Tile[] getTiles(){
        return tiles;
    }

    private void initCards(){

    }
}
