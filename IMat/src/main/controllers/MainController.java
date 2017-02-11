/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Felix
 */
public class MainController implements Initializable{
    
    @FXML private CartController cartController;
    @FXML private CenterstageController centerstageController;
    @FXML private NavController navController;
    @FXML private SidebarController sidebarController;
    @FXML private StartpageController startpageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartController.injectMainController(this);
        centerstageController.injectMainController(this);
        navController.injectMainController(this);
        sidebarController.injectMainController(this);
        startpageController.injectMainController(this);
        
    }
    
    
    
}
