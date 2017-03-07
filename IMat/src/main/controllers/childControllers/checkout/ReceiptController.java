/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import fxComponents.ListViewReceiptItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import main.backend.CustomDataHandler;
import main.controllers.MainController;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Felix
 */
public class ReceiptController implements Initializable {
	public Order order;
    @FXML
    private AnchorPane receipt;
    @FXML
    private ListView listReceipt;
    @FXML
    private Button btnDone;
    @FXML private Label labelOrderTime;

    public void setVisible(boolean value) {
        receipt.setVisible(value);
        receipt.setManaged(value);
        receipt.toFront();
        
    }
    

    public void displayReceipt(Order order, LocalDate date, String time){
    	this.order = order;
    	listReceipt.getItems().addAll(order.getItems());
    	
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("dd MMMM");

        labelOrderTime.setText("Vi kommer att leverera dina varor den " + date.format(dFormat) + " klockan " + time + ".");
        listReceipt.setStyle("-fx-font-size: 2em;");

        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        
    }

    public void addListeners(){
    	listReceipt.setCellFactory(e -> new ListViewReceiptItem(this.order, this));
        //Living on the edge here
    	btnDone.setOnAction(e -> Main.requestStartpage());
    }
    
}
