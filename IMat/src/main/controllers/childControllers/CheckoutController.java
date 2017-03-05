/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private AnchorPane delivery;
    @FXML
    private AnchorPane payment;
    @FXML
    private AnchorPane receipt;
    @FXML
    private Label labelHeader;
    @FXML
    private VBox listBox;
    @FXML
    private AnchorPane checkout;

    /*Sidebar navigation*/
    @FXML
    private Label labelKundinformation, labelLevernas, labelBetalning, labelKvitto;
    
    /* Controllers for different forms*/
    @FXML
    private CustomerController customerController;
    @FXML
    private DeliveryController deliveryController;
    @FXML
    private OrderController orderController;
    @FXML
    private PaymentController paymentController;
    @FXML
    private ReceiptController receiptController;

    @Override
    public void setVisible(boolean value) {
        checkout.setVisible(value);
        checkout.setManaged(value);
        checkout.toFront();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < 5; i++){
            Node child = listBox.getChildren().get(i);
            child.setOnMouseClicked(event -> setClicked(event));
        }
    }

    //Sidebar-navigation
    private void setClicked(MouseEvent e){
        Label source = (Label) e.getSource();

        switch (source.getId()){
            case "labelKundinformation":
                customer.setVisible(true);
                break;
            case "labelLeverans":
                delivery.setVisible(true);
                break;
            case "labelBetalning":
                payment.setVisible(true);
                break;
            case "labelKvitto":
                receipt.setVisible(true);
                break;
            default:
                break;
        }
    }




    public CustomerController getCustomerController() {
        return customerController;
    }

    public DeliveryController getDeliveryController() {
        return deliveryController;
    }

    public OrderController getOrderController() {
        return orderController;
    }

    public PaymentController getPaymentController() {
        return paymentController;
    }

    public ReceiptController getReceiptController() {
        return receiptController;
    }
}
