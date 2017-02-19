package main.controllers.childControllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-15.
 */
public class UserController implements Controllable {
    
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private TextField txtRegEmail;
    @FXML private TextField txtRegPassword;
    @FXML private TextField txtRegPasswordRepeat;
    @FXML private AnchorPane anchorRegister;

    private AnchorPane anchorUser;

    private CustomDataHandler dataHandler;
    private UserHandler userHandler;
    private User currentUser;
    private Customer currentCustomer;

    private Stage userStage;
    private Scene loginScene;
    private Parent root = null;


    public UserController(){
        dataHandler = CustomDataHandler.getInstance();
        userHandler = dataHandler.getUserHandler();
        currentUser = dataHandler.getUser();
        currentCustomer = dataHandler.getCustomer();
    }

    @Override
    public void setVisible(boolean value) {
        anchorUser.setVisible(value);
        anchorUser.setManaged(value);
    }
    
    public void setParentPane(AnchorPane pane){
        anchorUser = pane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = dataHandler.getUser();
    }

    @FXML
    private void btnLogin_onActionPerformed(javafx.event.ActionEvent e){
            User loginUser = new User();
            loginUser.setUserName(txtUsername.getText());
            loginUser.setPassword(txtPassword.getText());

            currentUser = userHandler.logIn(loginUser);

            if(currentUser == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Fel");
                error.setHeaderText("Fel användarnamn eller lösenord.");
                error.showAndWait();
            }else{
                setVisible(false);
            }
    }

    @FXML
    private void btnRegister_onActionPerformed(ActionEvent e){
        anchorRegister.setVisible(true);
    }

    @FXML
    private void btnRegisterNew_onActionPerformed(ActionEvent e){
        if(checkEmail(txtRegEmail.getText()) && checkPass(txtRegPassword.getText(), txtRegPasswordRepeat.getText())){
            User newUser = userHandler.createNewUser(txtRegEmail.getText(), txtRegPassword.getText());

            if(newUser == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Fel");
                error.setHeaderText("E-postadressen finns redan!");
                error.showAndWait();

                txtRegEmail.clear();
                return;
            }

            userHandler.logIn(newUser);
            setVisible(false);
            anchorRegister.setVisible(false);
        }
    }

    @FXML
    private void btnClose_onActionPerformed(javafx.event.ActionEvent e){
        anchorRegister.setVisible(false);
        setVisible(false);
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
            txtRegEmail.clear();
            txtRegEmail.requestFocus();
        }

        return valid;
    }

    private boolean checkPass(String passwordFst, String passwordSnd){
        boolean valid = passwordFst.equals(passwordSnd);
        if(!valid){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Felaktig data");
            error.setHeaderText("Dina lösenord matchar inte!");
            error.showAndWait();

            //Gör röd
            txtRegPassword.clear();
            txtRegPassword.requestFocus();
            txtRegPasswordRepeat.clear();
        }
        return valid;
    }
}
