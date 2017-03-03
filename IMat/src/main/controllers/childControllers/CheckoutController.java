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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Felix
 */
public class CheckoutController implements Controllable{

    @FXML
    private AnchorPane paneBtns;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnReturn;
    @FXML
    private StackPane checkoutStack;
    @FXML
    private AnchorPane order;
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
    @FXML private DatePicker datePicker;
    @FXML private ChoiceBox<?> choiceTime;
    @FXML private TextArea txtInfo;
    @FXML private Label labelInfo;
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
    @FXML
    private AnchorPane receipt;
    @FXML
    private ListView<?> listReceipt;
    @FXML
    private Label labelHeader;
    @FXML
    private VBox listBox;
    @FXML
    private AnchorPane checkout;

    @Override
    public void setVisible(boolean value) {
        checkout.setVisible(value);
        checkout.setManaged(value);
        checkout.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
