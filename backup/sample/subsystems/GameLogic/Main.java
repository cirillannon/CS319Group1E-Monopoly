package sample.subsystems.GameLogic;

import sample.subsystems.Controller.*;

public class Main {
  public static void main(String[] args) 
  { 
    GameManager gameManager = new GameManager();
    gameManager.initGame(4, "a", "b", "c", "d");
    gameManager.startGame();
  }
}