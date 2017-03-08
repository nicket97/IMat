package main.controllers.childControllers.User;

import fxComponents.SpemPasswordfield;
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
    @FXML
    private AnchorPane myPages;
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
    @FXML
    private Button btnClose;

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private SpemTextfield txtEmail;
    @FXML
    private SpemTextfield txtPhone;
    @FXML
    private TextField txtAddress;
    @FXML
    private SpemTextfield txtPostcode;
    @FXML
    private TextField txtPostaddress;
    @FXML
    private SpemPasswordfield txtPasswordOld;
    @FXML
    private SpemPasswordfield txtPassword;
    @FXML
    private SpemPasswordfield txtPasswordSnd;
    @FXML
    private Label labelPwSuccess;
    @FXML
    private Label labelPassPrompt;
    @FXML
    private Label labelPassSndPrompt;

    private UserHandler uh = CustomDataHandler.getInstance().getUserHandler();
    private StackPane parent;

    private String newPassword = null;

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

        if (value) {
            parent.setVisible(value);
            parent.setManaged(value);
            //Hehe
            myPages.getParent().getParent().toFront();
            loadUser();
        } else
            myPages.getParent().getParent().toBack();
    }

    public void close() {
        parent.setVisible(false);
        parent.setManaged(false);
        myPages.getParent().getParent().toBack();
    }

    public boolean isVisible() {
        return myPages.isVisible();
    }

    public void setParentPane(StackPane anchorUser) {
        parent = anchorUser;
    }

    private void addListeners() {
        navUsr.setOnMouseClicked(e -> showUserDetails());
        navPay.setOnMouseClicked(e -> showPaymentDetails());
        navPwrd.setOnMouseClicked(e -> showPasswordSettings());
        btnLogout.setOnAction(e -> {
            uh.logOut();
            close();
        });
        btnEditUser.setOnAction(e -> {
            txtEmail.validate();
            txtPhone.validate();
            if (usrGroup.isDisabled() || (txtEmail.isValid() && txtPhone.isValid()))
                setUserFields(!usrGroup.isDisabled());
        });
        btnEditPayment.setOnAction(e -> setPaymentFields(!choicePayment.isDisabled()));
        btnEditDelivery.setOnAction(e ->{
            txtPostcode.validate();
            if(delGroup.isDisabled() || isAllDeliveryValid()) setDelFields(!delGroup.isDisabled());
        });

        btnClose.setOnMouseClicked(e -> close());
        btnUpdatePw.setOnAction(x -> updatePassword());
        saveChanges.setOnAction(x -> saveChanges());
    }

    private void addValidators() {
        txtEmail.setOnValidation(x -> {
            boolean valid = true;
            if (!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".")) {
                txtEmail.setErrorText("Felaktig e-postadress!");
                valid = false;
            }
            if (valid && !uh.getCustomer().getEmail().equals(txtEmail.getText()) && uh.emailExists(txtEmail.getText())) {
                txtEmail.setErrorText("E-postadressen finns redan!");
                valid = false;
            }
            txtEmail.setValid(valid);

            btnEditUser.setDisable(!isAllUserValid());
        });

        txtPhone.setOnValidation(x -> {
            txtPhone.setValid(txtPhone.getText().matches("[0-9]+") || txtPhone.getText().isEmpty());
            btnEditUser.setDisable(!isAllUserValid());
        });

        txtPostcode.setOnValidation(x -> {
            txtPostcode.setValid(txtPostcode.getText().isEmpty() || (txtPostcode.getText().trim().length() == 6 && txtPostcode.getText().matches("[0-9]+")));
            btnEditDelivery.setDisable(!isAllDeliveryValid());
        });

        txtPasswordOld.setOnValidation(x -> {
            boolean valid = txtPasswordOld.getText().isEmpty() || txtPasswordOld.getText().equals(uh.getUser().getPassword());
            txtPasswordOld.setValid(valid);
            btnUpdatePw.setDisable(!isAllPassValid());
        });

        txtPassword.setOnValidation(x -> {
            boolean valid = txtPassword.getText().isEmpty() || txtPassword.getText().length() >= 6;
            txtPassword.setValid(valid);
            btnUpdatePw.setDisable(!isAllPassValid());
            labelPassPrompt.setVisible(valid);
        });

        txtPasswordSnd.setOnValidation(x -> {
            boolean valid = txtPasswordSnd.getText().equals(txtPassword.getText());
            txtPasswordSnd.setValid(valid);
            btnUpdatePw.setDisable(!isAllPassValid());
            labelPassSndPrompt.setVisible(valid);
        });
    }

    private void loadUser() {
        Customer currCust = uh.getCustomer();
        txtFirstname.setText(currCust.getFirstName());
        txtLastname.setText(currCust.getLastName());
        txtEmail.setText(currCust.getEmail());
        txtPhone.setText(currCust.getPhoneNumber());
        txtAddress.setText(currCust.getAddress());
        txtPostcode.setText(currCust.getPostCode());
        txtPostaddress.setText(currCust.getPostAddress());
        if(uh.getCustomerPayment() != null){
            switch (uh.getCustomerPayment()){
                case CASH:
                    choicePayment.setValue("Vid leverans");
                    break;
                case CREDIT_CARD:
                    choicePayment.setValue("Kredit-/Kontokort");
                    break;
                case INVOICE:
                    choicePayment.setValue("Faktura");
                    break;
            }
        }

    }

    private void saveChanges(){
        Customer currCust = uh.getCustomer();
        User currUser = uh.getUser();

        currUser.setUserName(txtEmail.getText());
        if(newPassword != null) currUser.setPassword(newPassword);
        currCust.setEmail(txtEmail.getText());
        currCust.setFirstName(txtFirstname.getText());
        currCust.setLastName(txtLastname.getText());
        currCust.setAddress(txtAddress.getText());
        currCust.setPostCode(txtPostcode.getText());
        currCust.setPostAddress(txtPostaddress.getText());
        currCust.setPhoneNumber(txtPhone.getText());
        savePayment();
        uh.saveCurrentCustomer();

    }

    private void savePayment(){
        if(choicePayment.getValue() == null)
            return;

        switch(choicePayment.getValue()){
            case "Vid leverans":
                uh.setCustomerPayment(UserHandler.Payments.CASH);
                break;
            case "Kredit-/Kontokort":
                uh.setCustomerPayment(UserHandler.Payments.CREDIT_CARD);
                break;
            case "Faktura":
                uh.setCustomerPayment(UserHandler.Payments.INVOICE);
                break;
        }
    }

    private void showPasswordSettings() {
        clearIds();
        navPwrd.setId("navActive");
        labelPwSuccess.setVisible(false);
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

    private void updatePassword() {
        newPassword = txtPassword.getText();

        txtPasswordOld.clearText();
        txtPassword.clearText();
        txtPasswordSnd.clearText();
        labelPwSuccess.setVisible(true);
    }

    //Bästa metodnamnet nånsin
    private boolean isAllPassValid() {
        return txtPasswordOld.isValid() && txtPassword.isValid() && txtPasswordSnd.isValid() &&
                !txtPassword.getText().isEmpty() && !txtPasswordOld.getText().isEmpty()
                && !txtPasswordSnd.getText().isEmpty();
    }

    private boolean isAllUserValid(){
        return txtEmail.isValid() && txtPhone.isValid();
    }

    private boolean isAllDeliveryValid(){
        return txtPostcode.isValid();
    }

/*
private SpemTextfield txtEmail;
    @FXML
    private SpemTextfield txtPhone;

    @FXML
    private SpemTextfield txtPostcode;

    @FXML
    private SpemTextfield txtPasswordOld;
    @FXML
    private SpemTextfield txtPassword;
    @FXML
    private SpemTextfield txtPasswordSnd;
 */

}
