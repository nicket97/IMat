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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import main.controllers.childControllers.Controllable;

/**
 *
 * @author Felix
 */
public class MainController implements Initializable{

    @FXML private Button btnTest;

    private Controllable[] controllers;

    public MainController(Controllable[] controllers){
        //Can assign to private variables with more readable names
        this.controllers = controllers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnTest_onAction(ActionEvent e){
        controllers[1].testMe();
    }
}
