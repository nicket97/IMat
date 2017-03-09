/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.backend.BackendWrapper;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import main.controllers.childControllers.Controllable;
import main.controllers.childControllers.User.UserController;
import se.chalmers.ait.dat215.project.Customer;

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
    
    @FXML private Label labelError;

    private int index = 0;
    private CustomDataHandler dataHandler;
    private UserController userController;

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
    	dataHandler = CustomDataHandler.getInstance();
        for(int i = 0; i < 5; i++){
            Node child = listBox.getChildren().get(i);
            int index = i;
            child.setOnMouseClicked(event -> openPage(index));
        }

        btnNext.setOnAction(x -> openPage(index+1));
        btnPrev.setOnAction(x -> openPage(index-1));
    }
    
    //Sidebar-navigation
    private void openPage(int index){
        //Quick fix
        if(this.index == 4)
            return;

        switch (index){
            case 0:
                labelError.setVisible(!orderController.validate());
                orderController.setVisible(true);
                break;
            case 1:
                labelError.setVisible(!orderController.validate());
                if(!labelError.isVisible()){
                    customerController.setVisible(true);
                    listBox.getChildren().get(index).setDisable(false);
                }
                break;
            case 2:
                labelError.setVisible(!customerController.validate());
                if(!labelError.isVisible()){
                    deliveryController.setVisible(true);
                    listBox.getChildren().get(index).setDisable(false);
                }
                break;
            case 3:
                labelError.setVisible(!deliveryController.validate());
                if(!labelError.isVisible()){
                    paymentController.setVisible(true);
                    listBox.getChildren().get(index).setDisable(false);
                }
                
                break;
            case 4:
                paymentController.placeOrder();
                labelError.setVisible(!paymentController.validate());
                if(!labelError.isVisible()) {
                    receiptController.setVisible(true);
                    receiptController.displayReceipt(paymentController.getOrder(), deliveryController.getDate(), deliveryController.getTime());
                    for(Node c : listBox.getChildren()){
                        c.setDisable(true);
                    }
                    listBox.getChildren().get(index).setDisable(false);
                    promptRegistration();
                }
                break;
        }

        if(!labelError.isVisible()) {
            btnPrev.setDisable(index == 0 || index == 4);
            btnNext.setDisable(index >= 4);
        }

        if(!labelError.isVisible()) this.index = index;

        customerController.saveChanges();
        deliveryController.saveChanges();

        if(this.index == 3)
            btnNext.setText("Beställ");
        else
            btnNext.setText("Nästa");

        System.out.println(index);
    }

    public void promptRegistration(){
        UserHandler uh = dataHandler.getUserHandler();
        if(uh.isLoggedIn()) return;

        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Registrera");
        closeAlert.setHeaderText("Du är ej inloggad!");
        closeAlert.setContentText("Vill du spara dina uppgifter till nästa köp?");

        closeAlert.getButtonTypes().clear();
        ButtonType btnYes = new ButtonType("Ja", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("Nej", ButtonBar.ButtonData.NO);

        closeAlert.getButtonTypes().addAll(btnYes, btnNo);

        Optional<ButtonType> result = closeAlert.showAndWait();
        if(result.get() == btnNo) return;

        userController.setRegisterVisible(true);
    }

    public void injectControllers(UserController uc){
        this.userController = uc;
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
    
    public Button getReturnButton(){
        return btnReturn;
    }
    
}
