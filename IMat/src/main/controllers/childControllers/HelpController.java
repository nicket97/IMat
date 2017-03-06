package main.controllers.childControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lumo on 06/03/17.
 */
public class HelpController implements Initializable {

    @FXML
    AnchorPane helpPane;
    @FXML
    Button btnClose;
    @FXML
    Button buttonBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setHelpVisible(false);
        System.out.println("Help initialize");
        btnClose.setOnAction( e-> setHelpVisible(false));
        buttonBack.setOnAction( e-> setHelpVisible(false));
    }

    public void setHelpVisible(boolean value) {
        System.out.println("Help visibility enable");
        helpPane.setVisible(value);
        helpPane.setManaged(value);
        helpPane.toFront();
    }
}
