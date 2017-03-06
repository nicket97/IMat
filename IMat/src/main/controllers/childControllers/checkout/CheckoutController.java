/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.controllers.childControllers.Controllable;

/**
 *
 * @author Felix
 */
public class CheckoutController implements Controllable {

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

    private int index = 0;

    @Override
    public void setVisible(boolean value) {
        checkout.setVisible(value);
        checkout.setManaged(value);
        if(value) checkout.toFront();
        else checkout.toBack();

        openPage(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < 5; i++){
            Node child = listBox.getChildren().get(i);
            int index = i;
            child.setOnMouseClicked(event -> openPage(index));
        }

        btnNext.setOnAction(x -> {index++; openPage(index); });
        btnPrev.setOnAction(x -> {index--; openPage(index); });
        btnReturn.setOnAction(x -> setVisible(false));
    }

    //Sidebar-navigation
    private void openPage(int index){
        switch (index){
            case 0:
                orderController.setVisible(true);
                break;
            case 1:
                customerController.setVisible(true);
                break;
            case 2:
                deliveryController.setVisible(true);
                break;
            case 3:
                paymentController.setVisible(true);
                break;
            case 4:
                receiptController.setVisible(true);
                receiptController.displayReceipt(paymentController.getOrder(), deliveryController.getDate(), deliveryController.getTime());
                break;
            default:
                break;
        }

        this.index = index;

        btnPrev.setDisable(index == 0);
        btnNext.setDisable(index == 4);
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
