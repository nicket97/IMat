/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import fxComponents.SpemTextfield;
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
    private TextField txtAddress;
    @FXML
    private Label labelAddress;
    @FXML
    private TextField txtCity;
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

    private final String[] pickupHours = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00"};

    @Override
    public void setVisible(boolean value) {
        delivery.setVisible(value);
        delivery.setManaged(value);
        delivery.toFront();
        loadDefaults();
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

        txtPostal.setOnValidation(x -> txtPostal.setValid(txtPostal.getText().length() == 5));
        choiceTime.getItems().addAll(pickupHours);
        choiceTime.getSelectionModel().select(4);

        //Default to tomorrow
        datePicker.setValue(LocalDate.now().plusDays(1));
    }

    private void loadDefaults(){
        Customer currCust = userHandler.getCustomer();
        txtAddress.setText(currCust.getAddress());
        txtPostal.setText(currCust.getPostCode());
        txtCity.setText(currCust.getPostAddress());
    }
}