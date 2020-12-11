package sample.subsystems.UserInterface.Monopoly.src.sample;

import sample.subsystems.Controller.*;
import sample.subsystems.communication.*;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
    public Scene scene;
    public static MediaPlayer mediaPlayer;

    public static int state = 0; //0 for client, 1 for server;
    public static ServerManager serverManager = null;
    public static GameManager gameManager = null;

    @Override
    public void start(Stage primaryStage) throws Exception
    {	
    	playMusic();
    	
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Monopoly");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	
	public void playMusic() 
	{
		String path = "src\\sample\\soundEffects\\mainMenuTheme.mp3";
		Media h = new Media(Paths.get(path).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();	
	}

    public static void initServer(){
        serverManager = new ServerManager();
        gameManager = new GameManager();
        state = 1;
        serverManager.initServer();
    }
    public static void initClient( String ip){
        gameManager = new GameManager();
        serverManager = null;
        state = 0;
        gameManager.initClient( "" + ip);

    }

    public static void main(String[] args) 
    {	
        launch(args);
    }
}
