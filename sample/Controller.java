package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller implements Initializable{

    private boolean settings_visible = false;
    private boolean help_visible = false;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
