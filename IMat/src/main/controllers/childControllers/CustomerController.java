/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Felix
 */
public class CustomerController implements Controllable {

    @FXML
    private AnchorPane customer;
    @FXML
    private TextField txtFName;
    @FXML
    private Label labelFName;
    @FXML
    private TextField txtLName;
    @FXML
    private Label labelLName;
    @FXML
    private TextField txtMail;
    @FXML
    private Label labelMail;
    @FXML
    private TextField txtPhone;
    @FXML
    private Label labelPhone;

    @Override
    public void setVisible(boolean value) {
        customer.setVisible(value);
        customer.setManaged(value);
        customer.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
