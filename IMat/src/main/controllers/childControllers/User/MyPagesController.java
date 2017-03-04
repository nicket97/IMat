package main.controllers.childControllers.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import main.controllers.childControllers.Controllable;

/**
 * Created by pontu on 2017-03-03.
 */
public class MyPagesController implements Controllable{

    Label labelTitle;
    @FXML
    private AnchorPane myPage;
    @FXML
    private StackPane stackPages;
    @FXML
    private AnchorPane anchorUserDetails;
    @FXML
    private Button btnEditUser;
    @FXML
    private AnchorPane anchorPaymentDetails;
    @FXML
    private Button btnEditDelivery;
    @FXML
    private Button btnEditPayment;
    @FXML
    private AnchorPane anchorPwrd;
    @FXML
    private Button btnUpdatePw;
    @FXML
    private CheckBox cBoxInfo;
    @FXML
    private Button saveChanges;
    @FXML
    private BorderPane navPane;
    @FXML
    private Label labelHeader;
    @FXML
    private VBox listBox;
    @FXML
    private ChoiceBox<String> choicePayment;
    @FXML
    private Label navUsr;
    @FXML
    private Label navPay;
    @FXML
    private Label navPwrd;
    @FXML
    private Button btnLogout;
    @FXML
    private Group usrGroup;
    @FXML
    private Group delGroup;
    
    
    UserHandler uh = CustomDataHandler.getInstance().getUserHandler();
    @FXML
    private Button btnClose;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] paymentOptions = CustomDataHandler.getInstance().getPaymentOptions(); 
        for(int i = 0; i < paymentOptions.length; i++){
            choicePayment.getItems().add(paymentOptions[i]);
        }
        
        addListeners();
        
    }
        @Override
    public void setVisible(boolean value) {
        
    }
    public Button getCloseBtn(){
        return btnClose;
    }
    
    private void addListeners(){
        navUsr.setOnMouseClicked(e -> showUserDetails());
        navPay.setOnMouseClicked(e -> showPaymentDetails());
        navPwrd.setOnMouseClicked(e -> showPasswordSettings());
        btnLogout.setOnAction(e -> uh.logOut());
        btnEditUser.setOnAction(e -> usrGroup.setDisable(!usrGroup.isDisabled()));
        btnEditPayment.setOnAction(e -> choicePayment.setDisable(!choicePayment.isDisabled()));
        btnEditDelivery.setOnAction(e -> delGroup.setDisable(!delGroup.isDisabled()));
        /** Implement this
        btnSaveChanges.setOnAction(e -> )
        * */
    }

    private void showPasswordSettings() {
        clearIds();
        navPwrd.setId("navActive");
        anchorPwrd.toFront();
    }

    private void showPaymentDetails() {
        clearIds();
        navPay.setId("navActive");
        anchorPaymentDetails.toFront();
    }

    private void showUserDetails() {
        clearIds();
        navUsr.setId("navActive");
        anchorUserDetails.toFront();
    }


    
    private void clearIds(){
        navPwrd.setId(null);
        navPay.setId(null);
        navUsr.setId(null);
    }

    @FXML
    private void btnClose_onActionPerformed(ActionEvent event) {
    }
}
