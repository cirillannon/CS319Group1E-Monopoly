package UserInterface;

import Controller.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class NewGameController implements Initializable
{
	@FXML
	ImageView diceImage1;
	
	@FXML
	ImageView diceImage2;
	
	@FXML
	Button rollDiceButton;

    @FXML
    Button buildButton;

    @FXML
    Pane buildHousePane;
    
	@FXML
	ImageView orangePawnImage;	
	
	@FXML
	ImageView yellowPawnImage;	
	
	@FXML
	Label orangePlayersBalance;
	      
	@FXML
	Label yellowPlayersBalance;
	
	@FXML
    ImageView medView, balticView,orientalView,vermontView,connView,charlesView,statesView,virginiaView,jamesView,tennView,nyView,kenView,indianaView,illView,atlanticView,ventView,marvinView,pacificView,carolinaView,pennView,parkView,boardView;

    @FXML
    Label buildbtnPrompt;
	
	private Roller roller;
	private OrangePawnMover orangePawnMover;
	private YellowPawnMover yellowPawnMover;
	private GameManager gameManager;
	
	private double[] yellowPawnLocation;
	private int yellowPawnLocationIndex;
	private double[][] yellowPawnLocations;
	
	private double[] orangePawnLocation;
	private int orangePawnLocationIndex;
	private double[][] orangePawnLocations;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setAllPanesDisabled();
        buildHousePane.setVisible(false);
        buildbtnPrompt.setVisible(false);
        
		diceImage1.setImage(new Image("UserInterface/images/dice/dice1.png"));
		diceImage2.setImage(new Image("UserInterface/images/dice/dice2.png"));

		roller = new Roller();
		orangePawnMover = new OrangePawnMover();
		yellowPawnMover = new YellowPawnMover();
		
		initializePawnLocations();
		
		gameManager = new GameManager();
	} 

	private void initializePawnLocations()
	{
		// Initialize the initial x and y coordinates of the orange pawn.
		orangePawnLocation = new double[2];
		orangePawnLocations = new double[40][2];
		
		orangePawnLocation[0] = 801;
		orangePawnLocation[1] = 801;
		orangePawnLocationIndex = 0;
		
		for(int i = 0; i < 40; i++)
		{
			orangePawnLocations[i] = new double[2];
		
			if(i == 0)
			{
				orangePawnLocations[i][0] = 801;
				orangePawnLocations[i][1] = 801;
			}
			else if(i == 1)
			{
				orangePawnLocations[i][0] = 722;
				orangePawnLocations[i][1] = 801;
			}
			else if(i < 10)
			{
				orangePawnLocations[i][0] = 722 - (i - 1) * 79;
				orangePawnLocations[i][1] = 801;
			}
			else if (i == 10)
			{
				orangePawnLocations[i][0] = 1;
				orangePawnLocations[i][1] = 801;				
			}
			else if(i == 11)
			{
				orangePawnLocations[i][0] = 1;
				orangePawnLocations[i][1] = 721;					
			}
			else if(i < 20)
			{
				orangePawnLocations[i][0] = 1;
				orangePawnLocations[i][1] = 721 - (i - 11) * 78.875;					
			}
			else if(i == 20)
			{
				orangePawnLocations[i][0] = 1;
				orangePawnLocations[i][1] = 1;					
			}
			else if(i == 21)
			{
				orangePawnLocations[i][0] = 90;
				orangePawnLocations[i][1] = 1;					
			}
			else if(i < 30)
			{
				orangePawnLocations[i][0] = 90 + (i - 21) * 79;
				orangePawnLocations[i][1] = 1;					
			}
			else if(i == 30)
			{
				orangePawnLocations[i][0] = 801;
				orangePawnLocations[i][1] = 1;		
			}
			else if(i == 31)
			{
				orangePawnLocations[i][0] = 801;
				orangePawnLocations[i][1] = 90;		
			}
			else
			{
				orangePawnLocations[i][0] = 801;
				orangePawnLocations[i][1] = 90 + (i - 31) * 78.875;				
			}
			
		}
		
		orangePawnImage.setLayoutX(orangePawnLocations[0][0]);
		orangePawnImage.setLayoutY(orangePawnLocations[0][1]);
		
		// Initialize the initial x and y coordinates of the yellow pawn.
		yellowPawnLocation = new double[2];
		yellowPawnLocations = new double[40][2];
		
		yellowPawnLocation[0] = 801;
		yellowPawnLocation[1] = 801;
		yellowPawnLocationIndex = 0;
		
		for(int i = 0; i < 40; i++)
		{
			yellowPawnLocations[i] = new double[2];
		
			if(i == 0)
			{
				yellowPawnLocations[i][0] = 880;
				yellowPawnLocations[i][1] = 801;
			}
			else if(i == 1)
			{
				yellowPawnLocations[i][0] = 780;
				yellowPawnLocations[i][1] = 801;
			}
			else if(i < 10)
			{
				yellowPawnLocations[i][0] = 780 - (i - 1) * 79;
				yellowPawnLocations[i][1] = 801;
			}
			else if (i == 10)
			{
				yellowPawnLocations[i][0] = 68;
				yellowPawnLocations[i][1] = 801;				
			}
			else if(i == 11)
			{
				yellowPawnLocations[i][0] = 68;
				yellowPawnLocations[i][1] = 721;					
			}
			else if(i < 20)
			{
				yellowPawnLocations[i][0] = 68;
				yellowPawnLocations[i][1] = 721 - (i - 11) * 78.875;					
			}
			else if(i == 20)
			{
				yellowPawnLocations[i][0] = 68;
				yellowPawnLocations[i][1] = 1;					
			}
			else if(i == 21)
			{
				yellowPawnLocations[i][0] = 148;
				yellowPawnLocations[i][1] = 1;					
			}
			else if(i < 30)
			{
				yellowPawnLocations[i][0] = 148 + (i - 21) * 79;
				yellowPawnLocations[i][1] = 1;					
			}
			else if(i == 30)
			{
				yellowPawnLocations[i][0] = 880;
				yellowPawnLocations[i][1] = 1;		
			}
			else if(i == 31)
			{
				yellowPawnLocations[i][0] = 880;
				yellowPawnLocations[i][1] = 90;		
			}
			else
			{
				yellowPawnLocations[i][0] = 801;
				yellowPawnLocations[i][1] = 90 + (i - 31) * 78.875;				
			}
		}
		

		
		yellowPawnImage.setLayoutX(yellowPawnLocations[0][0]);
		yellowPawnImage.setLayoutY(yellowPawnLocations[0][1]);
	}
	
	public void movePawn(String color, int amount)
	{
		if(color.equals("orange"))
		{
			orangePawnMover.start();
			orangePawnLocationIndex = (orangePawnLocationIndex + amount) % 40;
			orangePawnLocation = orangePawnLocations[orangePawnLocationIndex];
		}
		else if(color.equals("yellow"))
		{
			yellowPawnMover.start();
			yellowPawnLocationIndex = (yellowPawnLocationIndex + amount) % 40;
			yellowPawnLocation = yellowPawnLocations[yellowPawnLocationIndex];	
		}
	}
	
	public void teleportPawn(String color, int teleportAmount)
	{
		if(color.equals("orange"))
		{
			orangePawnLocationIndex = (orangePawnLocationIndex + teleportAmount) % 40;
			orangePawnLocation = orangePawnLocations[orangePawnLocationIndex];
			orangePawnImage.setLayoutX(orangePawnLocation[0]);
			orangePawnImage.setLayoutY(orangePawnLocation[1]);

		}
		else if(color.equals("yellow"))
		{
			yellowPawnLocationIndex = (yellowPawnLocationIndex + teleportAmount) % 40;
			yellowPawnLocation = yellowPawnLocations[yellowPawnLocationIndex];
			yellowPawnImage.setLayoutX(yellowPawnLocation[0]);
			yellowPawnImage.setLayoutY(yellowPawnLocation[1]);
		}		
	}

	public void rollDice()
	{	
		int[] dice = new int[2];
		dice = gameManager.rollDice();
		
		if(dice == null)
		{
			return;
		}
		
		diceImage1.setImage(new Image("UserInterface/images/dice/dice" + dice[0] + ".png"));
		diceImage2.setImage(new Image("UserInterface/images/dice/dice" + dice[1] + ".png"));
		
		String color = gameManager.getTurnsColor();
		movePawn(color, dice[0] + dice[1]);
	}
	
	public void rollAnimation()
	{
		roller.start();
	}
	
	public void drawChanceCard()
	{
		String color = gameManager.getTurnsColor();
		teleportPawn(color, gameManager.drawChanceCard());
		updateBalances();
		
	}
	
	public void drawCommunityChestCard()
	{
		String color = gameManager.getTurnsColor();
		teleportPawn(color, gameManager.drawCommunityChestCard());
		updateBalances();
	}	
	
	public void payRent()
	{
		if(gameManager.payRent())
		{
			updateBalances();
		}
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
	
	public void updateBalances()
	{
		int balance = gameManager.getBalance("orange");
		
		if(balance != -1)
		{
			orangePlayersBalance.setText(balance + "$");
		}
		
		balance = gameManager.getBalance("yellow");

		if(balance != -1)
		{
			yellowPlayersBalance.setText(balance + "$");
		}
		
	}
	
	public void buyProperty()
	{
		if(gameManager.buyProperty())
		{
			updateBalances();
		}
	}

	public void sellProperty()
	{
		if(gameManager.sellProperty())
		{
			updateBalances();
		}
	}
	public void mortgageProperty()
	{
		gameManager.mortgage();
		updateBalances();
	}
	public void unMortageProperty(){
		gameManager.unmortgage();
		updateBalances();
	}
//    there are imageviews on each property so they will need to be altered depending on the state of the property
//    the user will tell us a string property
//    we need to get that imageview according to the string
//    public ImageView convertStringToPane(String propertyName){
//
//        if(propertyName == "MediteraneanAvenue" ){ return medView ;}
//
//        else
//        {
//            throw new IllegalArgumentException("String isnt correct");
//        }
//
//    }


	
	private class OrangePawnMover extends AnimationTimer
	{
		private long FRAMES_PER_SECOND = 50l;
		private long INTERVAL = 1000000000L / FRAMES_PER_SECOND;
		
		private long last = 0;
		
		@Override
		public void handle(long now) 
		{
			if(now - last > INTERVAL)
			{
				if( Math.abs(orangePawnImage.getLayoutY() - 801) < 3  || Math.abs(orangePawnImage.getLayoutY() - 1) < 3)
				{
					if(Math.abs(orangePawnImage.getLayoutX() - orangePawnLocation[0]) > 3)
					{
						if(orangePawnImage.getLayoutX() > orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() - 7.9);
						}
						else if(orangePawnImage.getLayoutX() < orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() + 7.9);
							
						}	
					}
					else
					{
						if(Math.abs(orangePawnImage.getLayoutY() - orangePawnLocation[1]) > 3)
						{
							if(orangePawnImage.getLayoutY() > orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() - 7.8875);
							}
							else if(orangePawnImage.getLayoutY() < orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() + 7.8875);
							}	
						}
					}
				}
				else
				{
					if(Math.abs(orangePawnImage.getLayoutY() - orangePawnLocation[1]) > 3)
					{
						if(Math.abs(orangePawnImage.getLayoutY() - orangePawnLocation[1]) > 3)
						{
							if(orangePawnImage.getLayoutY() > orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() - 7.8875);
							}
							else if(orangePawnImage.getLayoutY() < orangePawnLocation[1])
							{
								orangePawnImage.setLayoutY(orangePawnImage.getLayoutY() + 7.8875);
							}	
						}
					}
					else
					{
						if(orangePawnImage.getLayoutX() > orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() - 7.9);
						}
						else if(orangePawnImage.getLayoutX() < orangePawnLocation[0])
						{
							orangePawnImage.setLayoutX(orangePawnImage.getLayoutX() + 7.9);
						}	
					}	
				}


				last = now;
				
				if(Math.abs(orangePawnLocation[0] - orangePawnImage.getLayoutX()) <= 3 && Math.abs(orangePawnLocation[1] - orangePawnImage.getLayoutY()) <= 3)
				{
					orangePawnMover.stop();
				}
			}	
		}
	}
	
	private class YellowPawnMover extends AnimationTimer
	{
		private long FRAMES_PER_SECOND = 50l;
		private long INTERVAL = 1000000000L / FRAMES_PER_SECOND;
		
		private long last = 0;
		
		@Override
		public void handle(long now) 
		{
			if(now - last > INTERVAL)
			{
				if( Math.abs(yellowPawnImage.getLayoutY() - 801) < 5  || Math.abs(yellowPawnImage.getLayoutY() - 1) < 5)
				{
					if(Math.abs(yellowPawnImage.getLayoutX() - yellowPawnLocation[0]) > 5)
					{
						if(yellowPawnImage.getLayoutX() > yellowPawnLocation[0])
						{
							yellowPawnImage.setLayoutX(yellowPawnImage.getLayoutX() - 7.9);
						}
						else if(yellowPawnImage.getLayoutX() < yellowPawnLocation[0])
						{
							yellowPawnImage.setLayoutX(yellowPawnImage.getLayoutX() + 7.9);
							
						}	
					}
					else
					{
						if(Math.abs(yellowPawnImage.getLayoutY() - yellowPawnLocation[1]) > 5)
						{
							if(yellowPawnImage.getLayoutY() > yellowPawnLocation[1])
							{
								yellowPawnImage.setLayoutY(yellowPawnImage.getLayoutY() - 7.8875);
							}
							else if(yellowPawnImage.getLayoutY() < yellowPawnLocation[1])
							{
								yellowPawnImage.setLayoutY(yellowPawnImage.getLayoutY() + 7.8875);
							}	
						}
					}
				}
				else
				{
					if(Math.abs(yellowPawnImage.getLayoutY() - yellowPawnLocation[1]) >5)
					{
						if(Math.abs(yellowPawnImage.getLayoutY() - yellowPawnLocation[1]) > 3)
						{
							if(yellowPawnImage.getLayoutY() > yellowPawnLocation[1])
							{
								yellowPawnImage.setLayoutY(yellowPawnImage.getLayoutY() - 7.8875);
							}
							else if(yellowPawnImage.getLayoutY() < yellowPawnLocation[1])
							{
								yellowPawnImage.setLayoutY(yellowPawnImage.getLayoutY() + 7.8875);
							}	
						}
					}
					else
					{
						if(yellowPawnImage.getLayoutX() > yellowPawnLocation[0])
						{
							yellowPawnImage.setLayoutX(yellowPawnImage.getLayoutX() - 7.9);
						}
						else if(yellowPawnImage.getLayoutX() < yellowPawnLocation[0])
						{
							yellowPawnImage.setLayoutX(yellowPawnImage.getLayoutX() + 7.9);
						}	
					}	
				}


				last = now;
				
				if(Math.abs(yellowPawnLocation[0] - yellowPawnImage.getLayoutX()) <= 5 && Math.abs(yellowPawnLocation[1] - yellowPawnImage.getLayoutY()) <= 5)
				{
					yellowPawnMover.stop();
				}
			}	
		}
	}
	
	//method that runs when build button is pressed
	public void buildHouse()
	{
	    setAllPanesDisabled();
	
	    PauseTransition visiblePause = new PauseTransition(
	            Duration.seconds(5));
	     buildbtnPrompt.setVisible(true);
	    visiblePause.setOnFinished(
	            event -> buildbtnPrompt.setVisible(false)
	    );
	    visiblePause.play();
	}
	
	public void setAllPanesDisabled()
	{
	    medView.setDisable(true); balticView.setDisable(true);  orientalView.setDisable(true);  vermontView.setDisable(true);  connView.setDisable(true);
	    charlesView.setDisable(true);  statesView.setDisable(true);
	    virginiaView.setDisable(true);  jamesView.setDisable(true);  tennView.setDisable(true);
	    nyView.setDisable(true); kenView.setDisable(true); indianaView.setDisable(true); illView.setDisable(true); atlanticView.setDisable(true);
	    ventView.setDisable(true); marvinView.setDisable(true);
	    pacificView.setDisable(true); carolinaView.setDisable(true); pennView.setDisable(true); parkView.setDisable(true); boardView.setDisable(true);
	}
	
	public void setAllPanesEnabled()
	{
	    medView.setDisable(false); balticView.setDisable(false);  orientalView.setDisable(false);  vermontView.setDisable(false);  connView.setDisable(false);
	    charlesView.setDisable(false);  statesView.setDisable(false);
	    virginiaView.setDisable(false);  jamesView.setDisable(false);  tennView.setDisable(false);
	    nyView.setDisable(false); kenView.setDisable(false); indianaView.setDisable(false); illView.setDisable(true); atlanticView.setDisable(false);
	    ventView.setDisable(false); marvinView.setDisable(false);
	    pacificView.setDisable(false); carolinaView.setDisable(false); pennView.setDisable(false); parkView.setDisable(false); boardView.setDisable(false);
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
    public void changeState(ImageView imgView, int index)
    {
        int state = gameManager.buildHouse(index);
        if(state == 1)
        {
            imgView.setImage(new Image("UserInterface/images/housestates/state1.png"));
        }
        else if(state == 2)
        {
            imgView.setImage(new Image("UserInterface/images/housestates/state2.png"));
        }
        else if(state == 3)
        {
            imgView.setImage(new Image("UserInterface/images/housestates/state3.png"));
        }
        else if(state == 4)
        {
            imgView.setImage(new Image("UserInterface/images/housestates/state4.png"));
        }
        else if(state == 5)
        {
            imgView.setImage(new Image("UserInterface/images/housestates/state5.png"));
        }
        else
        {
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3));
            buildbtnPrompt.setText("Own or evenly distribute your houses on this property ");
            buildbtnPrompt.setVisible(true);
            visiblePause.setOnFinished(
                    event -> {
                        buildbtnPrompt.setVisible(false);
                        buildbtnPrompt.setText("Press on a property that you own on the board to build on it ");
                    });
            visiblePause.play();
        }
    }

    //brown colored property
    public void medPaneClicked() {
        changeState(medView, 0);
    }
    public void balticPaneClicked() {
        changeState(balticView, 1);
    }
    public void orientalPaneClicked() {
        changeState(orientalView, 2);
    }
    public void vermontPaneClicked() {
        changeState(vermontView, 3);
    }
    public void connPaneClicked() {
        changeState(connView, 4);
    }
    public void charlesPaneClicked() {
        changeState(charlesView, 5);
    }
    public void statesPaneClicked() {
        changeState(statesView, 6);
    }
    public void virginiaPaneClicked() {
        changeState(virginiaView, 7);
    }
    public void jamesPaneClicked() {
        changeState(jamesView, 8);
    }
    public void tennPaneClicked() {
        changeState(tennView, 9);
    }
    public void nyPaneClicked() {
        changeState(nyView, 10);
    }
    public void kenPaneClicked() {
        changeState(kenView, 11);
    }
    
    public void indianaPaneClicked() {
        changeState(indianaView, 12);
    }
    public void illPaneClicked() {
        changeState(illView, 13);
    }
    public void atlanticPaneClicked() {
        changeState(atlanticView, 14);
    }
    public void ventnorPaneClicked() {
        changeState(ventView, 15);
    }
    public void marvinPaneClicked() {
        changeState(marvinView, 16);
    }
    public void pacificPaneClicked() {
        changeState(pacificView, 17);
    }
    public void carolinaPaneClicked() {
        changeState(carolinaView, 18);
    }
    public void pennPaneClicked() {
        changeState(pennView, 19);
    }
    public void parkPaneClicked() {
        changeState(parkView, 20);
    }
    public void boardPaneClicked() {
        changeState(boardView, 21);
    }
}