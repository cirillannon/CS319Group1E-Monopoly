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
    }

    // methods
    public void initGame(int playerNumber, String playerName1, String playerName2,
                         String playerName3, String playerName4) {
        String[] playerNames = {playerName1, playerName2, playerName3, playerName4};
        Bank bank = initBank();
        //setPawnStartPosition();
        //startPawnSelection();
        GameBoard.initBoard();
        numOfPlayers = playerNumber;
        players = new Player[playerNumber];
        for (int i = 0; i < numOfPlayers; i++){
            players[i] = new Player(playerNames[i], i);
        }
    }

    // how to reach pawns?
    public void setPawnStartPosition() {

    }

    //
    public void startPawnSelection() {

    }

    public Bank initBank() {
        Bank bank = Bank.initBank();
        return bank;
    }

    public void startGame() {
        turnOf = 0;
        char input;
        while(!isGameOver()){
            if(GameManager.currentTurn().isInJail()){
                Dice.rollDice();
                if(Dice.getDoubles())
                    players[turnOf].move(Dice.getDiceTotal());
                else if (players[turnOf].hasOutOfJailFreeCard()) {
                    System.out.println("Use out of jail card ? Y/N");
                    input = scan.next().charAt(0);
                    if ( input == 'Y' || input == 'y'){
                        players[turnOf].removeJailCard();
                        GoToJail.releasePlayer(players[turnOf]);
                        Dice.rollDice();
                        players[turnOf].move(Dice.getDiceTotal());
                        if (Dice.getDoubles()){
                            Dice.rollDice();
                            players[turnOf].move(Dice.getDiceTotal());
                            if (Dice.getDoubles()){
                                Dice.rollDice();
                                players[turnOf].move(Dice.getDiceTotal());
                                if(Dice.getDoubles()){
                                    GoToJail.jailPlayer(players[turnOf]);
                                    passTurn();
                                    continue;
                                }
                            }
                        }
                    }
                }
                else if(players[turnOf].getBalance() >= 50){
                    System.out.println("Pay $50 to leave jail ? Y/N");
                    input = scan.next().charAt(0);
                    if ( input == 'Y' || input == 'y'){
                        players[turnOf].updateBalance(-50);
                        GoToJail.releasePlayer(players[turnOf]);
                        Dice.rollDice();
                        players[turnOf].move(Dice.getDiceTotal());
                        GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
                        if (Dice.getDoubles()){
                            Dice.rollDice();
                            players[turnOf].move(Dice.getDiceTotal());
                            GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
                            if (Dice.getDoubles()){
                                Dice.rollDice();
                                players[turnOf].move(Dice.getDiceTotal());
                                GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
                                if(Dice.getDoubles()){
                                    GoToJail.jailPlayer(players[turnOf]);
                                    passTurn();
                                    continue;
                                }
                            }
                        }
                    }
                    else passTurn();
                    continue;
                }
            }
            Dice.rollDice();
            players[turnOf].move(Dice.getDiceTotal());
            GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
            if (Dice.getDoubles()){
                Dice.rollDice();
                players[turnOf].move(Dice.getDiceTotal());
                GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
                if (Dice.getDoubles()){
                    Dice.rollDice();
                    players[turnOf].move(Dice.getDiceTotal());
                    GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
                    if(Dice.getDoubles()){
                        GoToJail.jailPlayer(players[turnOf]);
                        passTurn();
                        continue;
                    }
                }
            }
        }
    }

    public void startBank(){

    }

    public void endGame() {

    }

    public boolean isGameOver() {
        return false;
    }

    private void initCards(){

    }

    private void passTurn(){
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

    public static Player currentTurn(){
        return players[turnOf];
    }

    public Card getDrawnCard() {
        return drawnCard;
    }
}
