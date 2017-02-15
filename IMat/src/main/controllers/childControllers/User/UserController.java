package main.controllers.childControllers.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.controllers.childControllers.Controllable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-15.
 */
public class UserController implements Controllable {

    @FXML
    TextField txtUsername;
    //More textfield

    @FXML
    Button btnLogin;

    @Override
    public void setVisible(boolean value) {

    }

    @Override
    public void testMe() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
