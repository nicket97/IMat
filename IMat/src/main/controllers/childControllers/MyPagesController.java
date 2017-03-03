package main.controllers.childControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-03-03.
 */
public class MyPagesController implements Initializable {

    @FXML Label labelTitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelTitle.setText("MASK");
    }
}
