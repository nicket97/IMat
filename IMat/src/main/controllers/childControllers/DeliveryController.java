/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TextField txtPostal;
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
    private ChoiceBox<?> choiceTime;
    @FXML
    private TextArea txtInfo;
    @FXML
    private Label labelInfo;

    @Override
    public void setVisible(boolean value) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
