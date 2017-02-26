/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.navigation;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.Main;
import main.controllers.MainController;
import main.controllers.childControllers.*;

/**
 *
 * @author Felix
 */
public class NavController implements Initializable {
    @FXML GridPane gridMain;

    @FXML private StackPane cartPane;
    @FXML private StackPane navHome;

    private CartController cartController;
    private StartpageController startpageController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i < Main.NUMBER_OF_CATEGORIES; i++){
            gridMain.getChildren().get(i).setOnMouseClicked(e -> pressButton());
        }

        addListeners();
    }

    public void injectControllers(StartpageController startpageController, CartController cartController){
        this.cartController = cartController;
        this.startpageController = startpageController;
    }

    private void pressButton(){
        System.out.println("Category chosen");
    }

    private void addListeners(){
        //Need animation here
        cartPane.setOnMouseClicked(e -> cartController.setVisible(!cartController.isVisible()));
        navHome.setOnMouseClicked(e -> startpageController.setVisible(true));
    }
}
