package sample;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;


public class Main extends Application {

    public Scene scene;
    static MediaPlayer mediaPlayer;

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
	

    public static void main(String[] args) 
    {	
        launch(args);
    }
}
