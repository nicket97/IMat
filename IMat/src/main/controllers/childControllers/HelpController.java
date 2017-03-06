package main.controllers.childControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lumo on 06/03/17.
 */
public class HelpController implements Initializable {

    @FXML
    AnchorPane help;
    @FXML
    Button btnClose;
    @FXML
    Button buttonBack;

    Stage stage;
    Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();
        scene = new Scene(help);
        stage.setScene(scene);

        btnClose.setOnAction( e-> setVisible(false));
        buttonBack.setOnAction( e-> setVisible(false));
    }

    public void setVisible(boolean value) {
        help.setVisible(value);
        help.setManaged(value);
        if(value);
            help.toFront();
    }
}
