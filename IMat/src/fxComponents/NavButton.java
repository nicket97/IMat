package fxComponents;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pontus on 2017-02-20.
 */
public class NavButton extends AnchorPane implements Initializable {

    @FXML ImageView imgCart;
    @FXML Label labelCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public NavButton(String label){

    }


}
