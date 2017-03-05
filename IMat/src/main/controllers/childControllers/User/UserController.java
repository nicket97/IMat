package main.controllers.childControllers.User;

import fxComponents.SpemTextfield;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Created by pontu on 2017-02-15.
 */
public class UserController implements Initializable{
    
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private AnchorPane anchorRegister;
    @FXML private AnchorPane anchorLogin;
    @FXML private SpemTextfield txtRegEmail;
    @FXML private SpemTextfield txtRegPassword;
    @FXML private SpemTextfield txtRegPasswordSnd;

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
        anchorUser.setVisible(value);
        anchorUser.setManaged(value);
        setParentVisible(value);
        anchorRegister.setVisible(false);
        if(value){
            txtUsername.requestFocus();
            txtPassword.clear();
        }
    }


    public void setRegisterVisible(boolean value){
        anchorUser.setVisible(value);
        anchorUser.setManaged(value);
        setParentVisible(value);
        anchorRegister.setVisible(value);
        if(value) {
            anchorRegister.toFront();
            txtRegEmail.getTxtField().requestFocus();
        }
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
        if(txtRegEmail.getValid() && txtRegPassword.getValid() && txtRegPasswordSnd.getValid()){
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

        if(currentUser == null){
            labelErrorEmailAndPassword.setVisible(true);
            txtUsername.setId("txtError");
            txtPassword.setId("txtError");
        }else{
            txtUsername.setId(null);
            txtPassword.setId(null);
            labelErrorEmailAndPassword.setVisible(false);
            setLoginVisible(false);
        }
    }

    @FXML
    private void btnClose_onActionPerformed(javafx.event.ActionEvent e){
        setLoginVisible(false);
    }

    private void showError(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fel");
        error.setHeaderText("Ajdå!");
        error.setContentText("Någonting gick väldigt fel. Vi ber om ursäkt för detta!");
        error.showAndWait();
    }

    private boolean checkEmail(String email){
        boolean valid = email.contains("@") && email.contains(".");
        if (!valid){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Felaktig data");
            error.setHeaderText("Ogiltig e-postadress");
            error.showAndWait();

            //Gör röd
       //     txtRegEmail.clear();
        //    txtRegEmail.requestFocus();
        }

        return valid;
    }

    private void addListerners(){
        anchorLogin.setOnKeyPressed(e -> {if(e.getCode() == KeyCode.ENTER) login();});
        anchorRegister.setOnKeyPressed(e -> {if(e.getCode() == KeyCode.ENTER) registerNew();});

        txtUsername.focusedProperty().addListener(x -> {
            if(!txtUsername.isFocused()) {
                labelErrorEmail.setVisible(txtUsername.getText().isEmpty());
                txtUsername.setId(txtUsername.getText().isEmpty() ? "txtError" : null);
            }
            else
                txtUsername.setId(null);
        });
        txtPassword.focusedProperty().addListener(x -> {
            if(!txtPassword.isFocused()) {
                if(txtPassword.getText().isEmpty()){
                    labelErrorPassword.setVisible(true);
                    txtPassword.setId("txtError");
                }else {
                    labelErrorPassword.setVisible(false);
                    txtPassword.setId(null);
                }
            }
            else
                txtPassword.setId(null);
            
        });

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
