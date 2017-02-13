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
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Felix
 */
public class MainController implements Initializable{

    @FXML private Button btnTest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //At this point, the other controllers have not been initialized
        //However, they do not need to be constructed since the JavaFX creates them.
    }

    @FXML
    private void btnTest_onAction(ActionEvent e){
        System.out.println("Hello world");
    }


    
    
    
}
