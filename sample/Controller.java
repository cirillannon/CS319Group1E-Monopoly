package sample;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;


import javax.swing.text.html.ImageView;

public class Controller implements Initializable{

    private boolean onGame;
    private boolean settings_visible;
    private boolean help_visible;

    @FXML
    private AnchorPane menu_window;

    @FXML
    private AnchorPane game_window;


    @FXML
    private ComboBox languages;

    @FXML
    private AnchorPane settings_pane;

    @FXML
    private AnchorPane help_pane;

    @FXML
    private void exitApplication()
    {
        System.exit(0);
    }

    @FXML
    private void settings()
    {
        settings_visible = !settings_visible;
        settings_pane.setDisable(!settings_visible);
        settings_pane.setVisible(settings_visible);
    }

    @FXML
    private void help()
    {
        help_visible = !help_visible;
        help_pane.setDisable(!help_visible);
        help_pane.setVisible(help_visible);

    }

    @FXML 
    private void hideCursor(){
        settings_pane.getScene().setCursor(Cursor.NONE);
    }

    @FXML
    private void showCursor(){
        settings_pane.getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void createGame(){
        onGame = true;
        menu_window.setVisible(!onGame);
        menu_window.setDisable(onGame);
        game_window.setVisible(onGame);
        game_window.setDisable(!onGame);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onGame = false;
        settings_visible = false;
        help_visible = false;
        languages.getItems().addAll("Türkçe", "English");
        languages.setValue("English");
    }
}
