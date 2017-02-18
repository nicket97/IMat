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
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-15.
 */
public class UserController implements Controllable {
    
    @FXML
    AnchorPane anchorUser;
    
    @FXML TextField txtUsername;
    @FXML TextField txtPassword;


    @FXML
    Button btnLogin;

    private CustomDataHandler dataHandler;
    private User currentUser;
    private Customer currentCustomer;

    private Stage userStage;
    private Scene loginScene;
    private Parent root = null;


    public UserController(){
        dataHandler = CustomDataHandler.getInstance();
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

    public void logOut(){
        currentUser.setUserName("");
        currentUser.setPassword("");
    }

    @FXML
    private void btnLogin_onActionPerformed(javafx.event.ActionEvent e){
            User loginUser = new User();
            loginUser.setUserName(txtUsername.getText());
            loginUser.setPassword(txtPassword.getText());
            dataHandler.logIn(loginUser);

            currentUser = dataHandler.getUser();

            if(currentUser == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Fel");
                error.setHeaderText("Fel användarnamn eller lösenord.");
                error.showAndWait();
            }else{
                System.out.println("Logged in as: " + currentUser.getUserName());
                setVisible(false);
            }
    }

    @FXML
    private void btnRegister_onActionPerformed(ActionEvent e){
        dataHandler.createNewUser(txtUsername.getText(), txtPassword.getText());
    }

    @FXML
    private void btnClose_onActionPerformed(javafx.event.ActionEvent e){
        setVisible(false);
    }

    private void showError(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fel");
        error.setHeaderText("Ajdå!");
        error.setContentText("Någonting gick väldigt fel. Vi ber om ursäkt för detta!");
        error.showAndWait();
    }
}
