package main.controllers.childControllers.User;

import fxComponents.SpemTextfield;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.StackPane;

/**
 * Created by pontu on 2017-02-15.
 */
public class UserController implements Initializable{
    
    @FXML private SpemTextfield txtUsername;
    @FXML private SpemTextfield txtPassword;
    @FXML private AnchorPane anchorRegister;
    @FXML private AnchorPane anchorLogin;
    @FXML private SpemTextfield txtRegEmail;
    @FXML private SpemTextfield txtRegPassword;
    @FXML private SpemTextfield txtRegPasswordSnd;
    @FXML private AnchorPane user;

    //Error stuff
    @FXML private Label labelErrorEmailAndPassword;
    @FXML private Label labelErrorEmail;
    @FXML private Label labelErrorPassword;


    private StackPane anchorUser;

    private CustomDataHandler dataHandler;
    private UserHandler userHandler;
    private User currentUser;
    private Customer currentCustomer;
    private MyPagesController myPagesController;

    private Stage userStage;
    private Scene loginScene;
    private Parent root = null;


    public UserController(){
        dataHandler = CustomDataHandler.getInstance();
        userHandler = dataHandler.getUserHandler();
        currentUser = dataHandler.getUser();
        currentCustomer = dataHandler.getCustomer();
    }
    
    public void setParentVisible(boolean value){
        anchorUser.setVisible(value);
        anchorUser.setManaged(value);
    }

    public void setLoginVisible(boolean value) {
        setParentVisible(value);
        anchorRegister.setVisible(false);
        anchorLogin.setVisible(value);
        myPagesController.setVisible(false);
        if(value){
            txtUsername.getTxtField().requestFocus();
            txtPassword.getTxtField().clear();
        }
    }


    public void setRegisterVisible(boolean value){
        setParentVisible(value);
        anchorRegister.setVisible(value);
        myPagesController.setVisible(false);
        if(value) {
            anchorRegister.toFront();
            txtRegEmail.getTxtField().requestFocus();
        }
    }

    public void setMyPagesVisible(boolean value){
        anchorRegister.setVisible(false);
        anchorLogin.setVisible(false);
        myPagesController.setVisible(value);
    }

    public void injectControllers(MyPagesController myPagesCtrl){
        myPagesController = myPagesCtrl;
    }

    public void setParentPane(StackPane pane){
        anchorUser = pane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = dataHandler.getUser();
        addListerners();
    }

    @FXML
    private void btnLogin_onActionPerformed(javafx.event.ActionEvent e){
            login();
    }

    @FXML
    private void btnRegister_onActionPerformed(ActionEvent e){
        setRegisterVisible(true);
    }

    @FXML
    private void btnRegisterNew_onActionPerformed(ActionEvent e){
        registerNew();
    }

    private void registerNew(){
        if(txtRegEmail.isValid() && txtRegPassword.isValid() && txtRegPasswordSnd.isValid()){
            User newUser = userHandler.createNewUser(txtRegEmail.getText(), txtRegPassword.getText());

            userHandler.logIn(newUser);
            setLoginVisible(false);
        }
    }

    private void login(){
        User loginUser = new User();
        loginUser.setUserName(txtUsername.getText());
        loginUser.setPassword(txtPassword.getText());

        currentUser = userHandler.logIn(loginUser);

        txtUsername.setRed(currentUser == null);
        txtPassword.setRed(currentUser == null);
        labelErrorEmailAndPassword.setVisible(currentUser == null);
        if(currentUser != null) setLoginVisible(false);
    }

    @FXML
    private void btnClose_onActionPerformed(javafx.event.ActionEvent e){
        setLoginVisible(false);
    }

    private void addListerners(){
        anchorLogin.setOnKeyPressed(e -> {
        	if(e.getCode() == KeyCode.ENTER) login();
        	if(e.getCode() == KeyCode.ESCAPE) setLoginVisible(false);
        });
        anchorRegister.setOnKeyPressed(e -> {
        	if(e.getCode() == KeyCode.ENTER) registerNew();
        	if(e.getCode() == KeyCode.ESCAPE) setLoginVisible(false);
        });
        
        txtUsername.setOnValidation(x -> txtUsername.setValid(!txtUsername.getText().isEmpty()));
        txtPassword.setOnValidation(x -> txtPassword.setValid(!txtPassword.getText().isEmpty()));

        txtRegEmail.setOnValidation(x -> {
            boolean valid = true;
            if(!txtRegEmail.getText().contains("@") || !txtRegEmail.getText().contains(".")){
                txtRegEmail.setErrorText("Felaktig e-postadress!");
                valid = false;
            }
            if(valid && userHandler.emailExists(txtRegEmail.getText())){
                txtRegEmail.setErrorText("E-postadressen finns redan!");
                valid = false;
            }
            txtRegEmail.setValid(valid);
        });

        txtRegPassword.setOnValidation(x -> {
            if(txtRegPassword.getText().length() < 6) txtRegPassword.setValid(false);
            else txtRegPassword.setValid(true);
        });

        txtRegPasswordSnd.setOnValidation(x -> {
            if(!txtRegPasswordSnd.getText().equals(txtRegPassword.getText())) txtRegPasswordSnd.setValid(false);
            else txtRegPasswordSnd.setValid(true);
        });
    }
}
