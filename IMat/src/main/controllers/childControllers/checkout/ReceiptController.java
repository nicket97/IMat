/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.controllers.childControllers.Controllable;

/**
 *
 * @author Felix
 */
public class ReceiptController implements Controllable {

    @FXML
    private AnchorPane receipt;
    @FXML
    private ListView<?> listReceipt;
    @FXML
    private Button btnDone;

    
    @Override
    public void setVisible(boolean value) {
        receipt.setVisible(value);
        receipt.setManaged(value);
        receipt.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listReceipt.getItems();
        addListeners();
    }
    public void addListeners(){
    	btnDone.setOnAction(e ->{
    		// lägg till logik
    	});
    }
    
}
