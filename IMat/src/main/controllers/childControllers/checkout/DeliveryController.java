/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import fxComponents.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class DeliveryController implements Controllable {

		@FXML
	    private AnchorPane delivery;
	    @FXML
	    private SpemTextfield txtAddress;
	    @FXML
	    private Label labelAddress;
	    @FXML
	    private SpemTextfield txtCity;
	    @FXML
	    private Label labelCity;
	    @FXML
	    private SpemTextfield txtPostal;
	    @FXML
	    private Label labelPostal;
	    @FXML
	    private Label labelDate;
	    @FXML
	    private Label and;
	    @FXML
	    private Label labelTime;
	    @FXML
	    private DatePicker datePicker;
	    @FXML
	    private ChoiceBox<String> choiceTime;
	    @FXML
	    private TextArea txtInfo;
	    @FXML
	    private Label labelInfo;

    private UserHandler userHandler;
    private boolean defaultsLoaded = false;

    private final String[] pickupHours = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00"};

    @Override
    public void setVisible(boolean value) {
        delivery.setVisible(value);
        delivery.setManaged(value);
        delivery.toFront();
        loadDefaults();
    }

    public void saveChanges(){
        Customer currCust = userHandler.getCustomer();
        currCust.setAddress(txtAddress.getText());
        currCust.setPostCode(txtPostal.getText());
        currCust.setPostAddress(txtCity.getText());
    }

    public String getTime(){
        return choiceTime.getSelectionModel().getSelectedItem();
    }

    public LocalDate getDate(){
        return datePicker.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	userHandler = CustomDataHandler.getInstance().getUserHandler();

        txtAddress.setOnValidation(x -> txtAddress.setValid(!txtAddress.getText().isEmpty()));
        txtCity.setOnValidation(x -> txtCity.setValid(!txtCity.getText().isEmpty()));
        txtPostal.setOnValidation(x -> txtPostal.setValid(txtPostal.getText().length() == 5));

        choiceTime.getItems().addAll(pickupHours);
        choiceTime.getSelectionModel().select(4);

        //Default to tomorrow
        datePicker.setValue(LocalDate.now().plusDays(1));
    }

    public boolean validate(){
        txtAddress.validate();
        txtCity.validate();
        txtPostal.validate();

        return txtAddress.isValid() && txtCity.isValid() && txtPostal.isValid();
    }

    private void loadDefaults(){
        if(!defaultsLoaded) {
            Customer currCust = userHandler.getCustomer();
            txtAddress.setText(currCust.getAddress());
            txtPostal.setText(currCust.getPostCode());
            txtCity.setText(currCust.getPostAddress());
            defaultsLoaded = true;
        }
    }

    public void reset(){
        txtAddress.clearText();
        txtCity.clearText();
        txtPostal.clearText();
        txtInfo.clear();
        defaultsLoaded = false;
        //setVisible(false);
    }
}