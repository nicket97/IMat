package main.controllers.childControllers.User;

import fxComponents.SpemTextfield;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

/**
 * Created by pontu on 2017-03-03.
 */
public class MyPagesController implements Controllable {

    Label labelTitle;
    @FXML private AnchorPane myPages;
    @FXML private StackPane stackPages;
    @FXML private AnchorPane anchorUserDetails;
    @FXML private Button btnEditUser;
    @FXML private AnchorPane anchorPaymentDetails;
    @FXML private Button btnEditDelivery;
    @FXML private Button btnEditPayment;
    @FXML private AnchorPane anchorPwrd;
    @FXML private Button btnUpdatePw;
    @FXML private CheckBox cBoxInfo;
    @FXML private Button saveChanges;
    @FXML private BorderPane navPane;
    @FXML private Label labelHeader;
    @FXML private VBox listBox;
    @FXML private ChoiceBox<String> choicePayment;
    @FXML private Label navUsr;
    @FXML private Label navPay;
    @FXML private Label navPwrd;
    @FXML private Button btnLogout;
    @FXML private Group usrGroup;
    @FXML private Group delGroup;
    @FXML private Button btnClose;

    @FXML private TextField txtFirstname;
    @FXML private TextField txtLastname;
    @FXML private SpemTextfield txtEmail;
    @FXML private SpemTextfield txtPhone;

    private UserHandler uh = CustomDataHandler.getInstance().getUserHandler();
    private StackPane parent;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] paymentOptions = CustomDataHandler.getInstance().getPaymentOptions();
        for (int i = 0; i < paymentOptions.length; i++) {
            choicePayment.getItems().add(paymentOptions[i]);
        }

        addListeners();
        addValidators();
    }

    @Override
    public void setVisible(boolean value) {
        myPages.setVisible(value);
        myPages.setManaged(value);

        if(value) {
            parent.setVisible(value);
            parent.setManaged(value);
            //Hehe
            myPages.getParent().getParent().toFront();
            loadUser();
        }else
            myPages.getParent().getParent().toBack();
    }

    public void close(){
        parent.setVisible(false);
        parent.setManaged(false);
        myPages.getParent().getParent().toBack();
    }

    public boolean isVisible(){
        return myPages.isVisible();
    }

    public void setParentPane(StackPane anchorUser) {
        parent = anchorUser;
    }

    private void addListeners() {
        navUsr.setOnMouseClicked(e -> showUserDetails());
        navPay.setOnMouseClicked(e -> showPaymentDetails());
        navPwrd.setOnMouseClicked(e -> showPasswordSettings());
        btnLogout.setOnAction(e -> {uh.logOut();close();});
        btnEditUser.setOnAction(e -> {if(usrGroup.isDisabled() || (txtEmail.isValid() && txtPhone.isValid()))setUserFields(!usrGroup.isDisabled());});
        btnEditPayment.setOnAction(e -> setPaymentFields(!choicePayment.isDisabled()));
        btnEditDelivery.setOnAction(e -> setDelFields(!delGroup.isDisabled()));
        btnClose.setOnMouseClicked(e -> close());
    }

    private void addValidators(){
        txtEmail.setOnValidation(x -> {
            boolean valid = true;
            if(!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".")){
                txtEmail.setErrorText("Felaktig e-postadress!");
                valid = false;
            }
            if(valid && !uh.getCustomer().getEmail().equals(txtEmail.getText()) && uh.emailExists(txtEmail.getText())){
                txtEmail.setErrorText("E-postadressen finns redan!");
                valid = false;
            }
            txtEmail.setValid(valid);
        });

        txtPhone.setOnValidation(x -> txtPhone.setValid(txtPhone.getText().matches("[0-9]+") || txtPhone.getText().isEmpty()));
    }

    private void loadUser(){
        Customer currCust = uh.getCustomer();
        User currUser = uh.getUser();
        txtFirstname.setText(currCust.getFirstName());
        txtLastname.setText(currCust.getLastName());
        txtEmail.setText(currCust.getEmail());
        txtPhone.setText(currCust.getPhoneNumber());
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

    private void clearIds() {
        navPwrd.setId(null);
        navPay.setId(null);
        navUsr.setId(null);
    }

    private void setUserFields(boolean b) {
        usrGroup.setDisable(b);
        btnEditUser.setText(getBtnString(b));
    }

    private void setPaymentFields(boolean b) {
        choicePayment.setDisable(b);
        if (b)
            btnEditPayment.setText("Spara");
        else
            btnEditPayment.setText("Ändra");
    }

    private void setDelFields(boolean b) {
        delGroup.setDisable(b);
        btnEditDelivery.setText(getBtnString(b));
    }

    private String getBtnString(boolean b) {
        if (b)
            return "Redigera";
        else
            return "Spara";
    }


}
