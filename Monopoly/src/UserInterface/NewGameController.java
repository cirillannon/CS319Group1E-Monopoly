package UserInterface;

import Controller.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NewGameController implements Initializable
{
	@FXML
	ImageView diceImage1;
	@FXML
	ImageView diceImage2;
	@FXML
	Button rollDiceButton;
	@FXML
	ImageView orangePawnImage;	
	
	private Roller roller;
	private PawnMover pawnMover;
	private GameManager gameManager;
	
	private double[] orangePawnLocation;
	private int orangePawnLocationIndex;
	private double[][] orangePawnLocations;
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		diceImage1.setImage(new Image("UserInterface/images/dice/dice1.png"));
		diceImage2.setImage(new Image("UserInterface/images/dice/dice2.png"));

		roller = new Roller();
		pawnMover = new PawnMover();
		
		initializePawnLocations();
		
		gameManager = new GameManager();
	} 

	private void initializePawnLocations()
	{
		// Initialize the initial x and y coordinates of the orange pawn.
		orangePawnLocation = new double[2];
		orangePawnLocation[0] = 805;
		orangePawnLocation[1] = 790;
		orangePawnLocationIndex = 0;
		
		orangePawnLocations = new double[40][2];
		
		for(int i = 0; i < 40; i++)
		{
			orangePawnLocations[i] = new double[2];
			
			if(i <= 10)
			{
				orangePawnLocations[i][0] = 805 - i * 80.4;
				orangePawnLocations[i][1] = 790;
			}
			else if( i > 10 && i <= 20)
			{
				orangePawnLocations[i][0] = 1;
				orangePawnLocations[i][1] = 790 - (i - 10) * 78.9;
			}
			else if( i > 20 && i <= 30)
			{
				orangePawnLocations[i][0] = 1 + (i - 20) * 80.4;
				orangePawnLocations[i][1] = 1;
			}
			else 
			{
				orangePawnLocations[i][0] = 805;
				orangePawnLocations[i][1] = 1 + (i - 30) * 78.9;
			}
		}
		
		orangePawnImage.setLayoutX(orangePawnLocations[0][0]);
		orangePawnImage.setLayoutY(orangePawnLocations[0][1]);
	}
	
	public void rollDice()
	{
		int[] dice = new int[2];
		dice = gameManager.diceRolled();
		
		diceImage1.setImage(new Image("UserInterface/images/dice/dice" + dice[0] + ".png"));
		diceImage2.setImage(new Image("UserInterface/images/dice/dice" + dice[1] + ".png"));
		
		pawnMover.start();
		orangePawnLocationIndex = (orangePawnLocationIndex + dice[0] + dice[1]) % 39;
		orangePawnLocation = orangePawnLocations[orangePawnLocationIndex];
	}
	
	public void endTurn()
	{
		gameManager.turnEnded();
	}
	
	public void setDiceImage(int top1, int top2)
	{
		diceImage1.setImage(new Image("UserInterface/images/dice/dice" + top1 + ".png"));
		diceImage2.setImage(new Image("UserInterface/images/dice/dice" + top2 + ".png"));
	}
	
	
	// This function is called when a player clicks the roll dice button.
	public void rollAnimation()
	{
		// Do the animation
		roller.start();
	}

	
	private class Roller extends AnimationTimer
	{
		private long FRAMES_PER_SECOND = 50l;
		private long INTERVAL = 1000000000L / FRAMES_PER_SECOND;
		
		// Roll it 20 times. 
		private int MAX_ROLLS = 20;
		
		private long last = 0;
		private int count = 0;

		@Override
		public void handle(long now) 
		{
			if(now - last > INTERVAL)
			{
				int top1 = 1 + (int)(Math.random() * 6);
				int top2 = 1 + (int)(Math.random() * 6);
				setDiceImage(top1, top2);
				last = now;
				count++;
				
				if(count > MAX_ROLLS)
				{
					roller.stop();
					
					// Roll 20 times for the animation then roll for the last time.
					rollDice();
					count = 0;
				}
			}	
		}	
	}
	
	private class PawnMover extends AnimationTimer
	{
		private long FRAMES_PER_SECOND = 50l;
		private long INTERVAL = 1000000000L / FRAMES_PER_SECOND;
		
		private long last = 0;
		
		@Override
		public void handle(long now) 
		{
			if(now - last > INTERVAL)
			{
				if( Math.abs(orangePawnImage.getLayoutY() - 790) < 0.5  || Math.abs(orangePawnImage.getLayoutY() - 1) < 0.5)
				{
					if(Math.abs(orangePawnImage.getLayoutX() - orangePawnLocation[0]) > 0.5)
					{
						if(orangePawnImage.getLayoutX() > orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() - 8.04);
						}
						else if(orangePawnImage.getLayoutX() < orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() + 8.04);
							
						}	
					}
					else
					{
						if(Math.abs(orangePawnImage.getLayoutY() - orangePawnLocation[1]) > 0.5)
						{
							if(orangePawnImage.getLayoutY() > orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() - 7.89);
							}
							else if(orangePawnImage.getLayoutY() < orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() + 7.89);
							}	
						}
					}
				}
				else
				{
					if(Math.abs(orangePawnImage.getLayoutY() - orangePawnLocation[1]) > 0.5)
					{
						if(Math.abs(orangePawnImage.getLayoutY() - orangePawnLocation[1]) > 0.5)
						{
							if(orangePawnImage.getLayoutY() > orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() - 7.89);
							}
							else if(orangePawnImage.getLayoutY() < orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() + 7.89);
							}	
						}
					}
					else
					{
						if(orangePawnImage.getLayoutX() > orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() - 8.04);
						}
						else if(orangePawnImage.getLayoutX() < orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() + 8.04);
						}	
					}	
				}


				last = now;
				
				if(Math.abs(orangePawnLocation[0] - orangePawnImage.getLayoutX()) <= 0.5 && Math.abs(orangePawnLocation[1] - orangePawnImage.getLayoutY()) <= 0.5)
				{
					pawnMover.stop();
				}
			}
			
		}
		
	}
}
