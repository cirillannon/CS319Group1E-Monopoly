package UserInterface;
import Controller.*;

import java.io.File;

import communication.ServerManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    	playMusic();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Monopoly");
        scene = new Scene(root);
        primaryStage.setScene(scene);
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
