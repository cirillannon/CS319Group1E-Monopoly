package sample;

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
	private int orangePawnLocation;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		diceImage1.setImage(new Image("sample/images/dice/dice1.png"));
		diceImage2.setImage(new Image("sample/images/dice/dice2.png"));
		
		roller = new Roller();
		pawnMover = new PawnMover();
		
		
		// This is the initial x position of the pawn. I got it from screen builder.
		orangePawnLocation = 740;
	} 
	
	public int rollDice()
	{
		int top1 = (int) (Math.random()*6 + 1);
		int top2 = (int) (Math.random()*6 + 1);
		
		diceImage1.setImage(new Image("sample/images/dice/dice" + top1 + ".png"));
		diceImage2.setImage(new Image("sample/images/dice/dice" + top2 + ".png"));
		
		pawnMover.start();
		orangePawnLocation = orangePawnLocation - (top1 + top2) * 71;
		
		return top1 + top2;
	}
	
	public void setDiceImage(int top1, int top2)
	{
		diceImage1.setImage(new Image("sample/images/dice/dice" + top1 + ".png"));
		diceImage2.setImage(new Image("sample/images/dice/dice" + top2 + ".png"));
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
				orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() - 5);
				last = now;
				
				if(orangePawnImage.getLayoutX() < orangePawnLocation)
				{
					pawnMover.stop();
				}
			}
			
		}
		
	}
}
