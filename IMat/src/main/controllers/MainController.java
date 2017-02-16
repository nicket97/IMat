/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.controllers.childControllers.*;

/**
 *
 * @author Felix
 */
public class MainController implements Initializable{

    @FXML private Button btnTest;

    @FXML
    private CartController cartCtrl;
    @FXML
    private NavController navController;
    @FXML
    private SidebarController sidebarController;
    @FXML
    private StartpageController startpageController;
    @FXML
    private CenterstageController centerstageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnTest_onAction(ActionEvent e){
      //  System.out.println(cartCtrl.toString());
        centerstageController.testMe();
        System.out.println(navController.toString());

      //  System.out.println(sidebarController.toString());
      //  System.out.println(startpageController.toString());
        //controllers[1].testMe();

    }
}
