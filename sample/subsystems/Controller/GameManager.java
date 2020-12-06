package sample.subsystems.Controller;

import sample.subsystems.GameLogic.GameBoard;
// communicates with Server ?
public class GameManager {
    // properties

    int playerStartMoney;
    boolean gameStarted;
    boolean gameOver;

    // constructor
    public Controller() {

    }

    // methods
    public boolean initGame(int startMoney) {
        setPlayersStartMoney(startMoney);
        initBank();
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
}
