/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Felix
 */
public class OrderController implements Controllable {

    @FXML
    private AnchorPane order;
    @FXML
    private ListView<?> orderList;

    @Override
    public void setVisible(boolean value) {
     
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
    }
    
}
