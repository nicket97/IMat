/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import main.backend.UserHandler;
import main.controllers.childControllers.User.UserStatusController;
/**
 *
 * @author Felix
 */

public class CenterstageController implements Controllable{

    @FXML
    private AnchorPane centerstage;
    
    @FXML
    private StartpageController startpageController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setVisible(boolean value) {

    }

    public StartpageController getStartpageController(){
        return startpageController;
    }
}
