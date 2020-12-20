package UserInterface;

import java.awt.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.Slider;
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
	public AnchorPane volumePane;
	@FXML
	public Button exitButton;
	@FXML
	public Button newGameButton;
	@FXML
	public Slider volumeSlider;
	@FXML
	private TextField IPTextField;

	MediaPlayer mediaPlayer;
	private boolean  onVolumePane = false;

	public void newGameButtonClicked(ActionEvent event) throws IOException
	{
		buttonClicked();
		Monopoly.mediaPlayer.stop();
		try {
			Monopoly.initServer();
		} catch (Exception e) {
			System.out.println( "Exception");
			e.printStackTrace();
		}
	}

	public static void showMainScreen(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try{
					Parent menuStage = FXMLLoader.load(getClass().getResource("newGame.fxml"));
					Scene newGameScene = new Scene(menuStage);
					Stage window = Monopoly.window;
					window.setScene(newGameScene);
					window.show();
				}catch (Exception e){
					System.out.println("Exception");
					e.printStackTrace();
				}
			}
		});
	}

	public void buttonClicked()
	{
		String path = "src\\UserInterface\\soundEffects\\buttonClick.mp3";
		Media h = new Media(Paths.get(path).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
	}

	@FXML
	private void joinGameButtonClicked(ActionEvent event) throws IOException
	{
		if (/*IPTextField.getText().equals("")*/ false) {
			System.out.println("Enter a URL, dummy..");
			// Alert alert = new Alert(Alert.AlertType.NONE, "Enter a URL, dummy..", ButtonType.CLOSE);
			// alert.showAndWait();
			return;
		} else{
			String ip = "192.168.56.1";
			Monopoly.initClient( ip);
		}
	}

	public void openVolumeSetting()
	{
		onVolumePane = !onVolumePane;
		volumePane.setVisible(onVolumePane);
		volumePane.setDisable(!onVolumePane);
	}


	public void exitButtonClicked()
	{
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		volumeSlider.setValue(Monopoly.mediaPlayer.getVolume() * 100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				Monopoly.mediaPlayer.setVolume(volumeSlider.getValue() / 100);
			}
		});
	}

}