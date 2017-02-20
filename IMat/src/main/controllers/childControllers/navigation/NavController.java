/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.navigation;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import main.Main;
import main.controllers.MainController;
import main.controllers.childControllers.CenterstageController;
import main.controllers.childControllers.Controllable;
import main.controllers.childControllers.SidebarController;

/**
 *
 * @author Felix
 */
public class NavController implements Initializable {

    private CenterstageController centerstageController;
    private SidebarController sidebarController;

    @FXML GridPane gridMain;

    AnchorPane anchorFruit;
    AnchorPane anchorBread;
    AnchorPane anchorColonial;
    @FXML
    private AnchorPane nav;
    @FXML
    private StackPane navHome;
    @FXML
    private StackPane navGreens;
    @FXML
    private StackPane navBread;
    @FXML
    private StackPane navPantry;
    @FXML
    private StackPane navDairy;
    @FXML
    private StackPane navProtein;
    @FXML
    private StackPane navColonial;
    @FXML
    private StackPane navCheckout;
    @FXML
    private Region navDivider;
    @FXML
    private StackPane cartPane;


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //Added 1 extra for Checkout
        for(int i = 0; i < Main.NUMBER_OF_CATEGORIES + 1; i++){
            gridMain.getChildren().get(i).setOnMouseClicked(e -> pressButton());
            
        }
    }

    private void pressButton(){
        System.out.println("Category chosen");
    }
    
}
