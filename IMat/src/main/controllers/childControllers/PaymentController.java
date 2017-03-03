/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Felix
 */
public class PaymentController implements Controllable {

    @FXML
    private AnchorPane payment;
    @FXML
    private StackPane radioBtnDoor;
    @FXML
    private StackPane radioBtnDebit;
    @FXML
    private StackPane radioBtnMail;
    @FXML
    private AnchorPane panePayChoice;
    @FXML
    private Label labelPayMethod;
    @FXML
    private AnchorPane paneDebit;
    @FXML
    private ComboBox<?> cbCard;
    @FXML
    private TextField txtCardNumber;
    @FXML
    private TextField txtMM;
    @FXML
    private TextField txtYY;
    @FXML
    private TextField txtCVV;
    @FXML
    private AnchorPane paneRest;
    @FXML
    private Button btnPay;

    @Override
    public void setVisible(boolean value) {
   
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
