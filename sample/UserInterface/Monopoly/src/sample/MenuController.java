package sample;


import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.text.html.ImageView;
import com.sun.glass.ui.Window;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController implements Initializable
{
	@FXML
	public Button exitButton;
	@FXML
	public Button settingsButton;
	@FXML
	public Button newGameButton;
	
	MediaPlayer mediaPlayer;
	
	public void newGameButtonClicked(ActionEvent event) throws IOException
	{	
		buttonClicked();
		Main.mediaPlayer.stop();
		Parent menuStage = FXMLLoader.load(getClass().getResource("newGame.fxml"));
		Scene newGameScene = new Scene(menuStage);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newGameScene);
        window.show();
	}
	
	public void buttonClicked()
	{
		String path = "src\\sample\\soundEffects\\buttonClick.mp3";
		Media h = new Media(Paths.get(path).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();	
	}

	public void settingsButtonClicked(ActionEvent event) throws IOException
	{	
		buttonClicked();
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
		Scene settingsScene = new Scene(root);
		window.setScene(settingsScene);
		window.showAndWait();
	}
	
//	public void settingsButtonClicked(ActionEvent event) throws IOException
//	{	
//		buttonClicked();
//		Parent menuStage = FXMLLoader.load(getClass().getResource("settings.fxml"));
//		Scene settingScene = new Scene(menuStage);
//      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//      window.setScene(settingScene);
//      window.show();
//	}
	
	public void exitButtonClicked()
	{
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// TODO Auto-generated method stub
		
	}


}