package UserInterface;
import Controller.*;

import java.io.File;

import communication.ServerManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.nio.file.Paths;

public class Monopoly extends Application 
{
	public Scene scene;
    static MediaPlayer mediaPlayer;

    public static Stage window;

    public static int state = 0; //0 for client, 1 for server;
    public static ServerManager serverManager = null;
    public static GameManager gameManager = null;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        try {
            playMusic();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Monopoly");

        scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(250);
        primaryStage.setMinWidth(500);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

	
	public void playMusic() 
	{
		String path = "src\\UserInterface\\soundEffects\\mainMenuTheme.mp3";
        try {
            Media h = new Media(Paths.get(path).toUri().toString());
            mediaPlayer = new MediaPlayer(h);
            mediaPlayer.play();
        } catch (Exception exception) {
            System.out.println(exception);
        }
	}

    public static void initServer(){
        System.out.println("initializing client");
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
