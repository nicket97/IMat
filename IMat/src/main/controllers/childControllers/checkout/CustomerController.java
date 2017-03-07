/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.ResourceBundle;

import fxComponents.SpemTextfield;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Customer;

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
    private SpemTextfield txtMail;
    @FXML
    private Label labelMail;
    @FXML
    private TextField txtPhone;
    @FXML
    private Label labelPhone;

    private UserHandler userHandler;

    @Override
    public void setVisible(boolean value) {
        customer.setVisible(value);
        customer.setManaged(value);
        customer.toFront();

        loadDefaults();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtMail.setOnValidation(x -> txtMail.setValid(txtMail.getText().contains("@") && txtMail.getText().contains(".")));

        userHandler = CustomDataHandler.getInstance().getUserHandler();
    }

    private void loadDefaults(){
        Customer currCustomer = userHandler.getCustomer();
        txtFName.setText(currCustomer.getFirstName());
        txtLName.setText(currCustomer.getLastName());
        txtMail.setText(currCustomer.getEmail());
        txtPhone.setText(currCustomer.getPhoneNumber());
    }
    
}
