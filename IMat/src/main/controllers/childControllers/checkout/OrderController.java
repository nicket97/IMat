/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.Controllable;

/**
 *
 * @author Felix
 */
public class OrderController implements Controllable {

    @FXML
    private AnchorPane order;
    @FXML
    private ListView<?> orderList;
    @FXML private Label labelGranska;

    private CustomDataHandler dataHandler;
    private String ordinaryTitle;

    @Override
    public void setVisible(boolean value) {
        order.setVisible(value);
        order.setManaged(value);
        order.toFront();

        if(dataHandler.getShoppingCart().getItems().size() == 0)
            labelGranska.setText("Oj, här var det visst tomt! Du har inte lagt några varor i varukorgen.");
        else
            labelGranska.setText(ordinaryTitle);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        ordinaryTitle = labelGranska.getText();
    }
    
}
