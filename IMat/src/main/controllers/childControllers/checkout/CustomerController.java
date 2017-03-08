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
    private SpemTextfield txtFName;
    @FXML
    private Label labelFName;
    @FXML
    private SpemTextfield txtLName;
    @FXML
    private Label labelLName;
    @FXML
    private SpemTextfield txtMail;
    @FXML
    private Label labelMail;
    @FXML
    private SpemTextfield txtPhone;
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
        txtMail.setOnValidation(x -> txtMail.setValid(txtMail.getText().contains("@") && txtMail.getText().contains(".")
                && !txtFName.getText().isEmpty()));

                txtPhone.setOnValidation(x -> txtPhone.setValid(txtPhone.getText().matches("[0-9]+") && !txtPhone.getText().isEmpty()));
                txtFName.setOnValidation(x -> txtFName.setValid(!txtFName.getText().isEmpty()));
                txtLName.setOnValidation(x -> txtLName.setValid(!txtLName.getText().isEmpty()));

                userHandler = CustomDataHandler.getInstance().getUserHandler();
            }


            public boolean validate(){
                txtMail.validate();
                txtPhone.validate();
                txtFName.validate();
                txtLName.validate();

                return txtFName.isValid() && txtLName.isValid() && txtMail.isValid() && txtPhone.isValid();
            }
    

    private void loadDefaults(){
        Customer currCustomer = userHandler.getCustomer();
        txtFName.setText(currCustomer.getFirstName());
        txtLName.setText(currCustomer.getLastName());
        txtMail.setText(currCustomer.getEmail());
        txtPhone.setText(currCustomer.getPhoneNumber());
    }
    
    
}
