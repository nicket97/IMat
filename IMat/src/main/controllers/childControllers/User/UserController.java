package main.controllers.childControllers.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.User;

import javax.xml.bind.annotation.XmlAccessorOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-15.
 */
public class UserController implements Controllable {
    
    @FXML
    AnchorPane anchorUser;
    
    @FXML
    TextField txtUsername;
    //More textfield

    @FXML
    Button btnLogin;

    private IMatDataHandler dataHandler;
    private User currentUser;
    private Customer currentCustomer;

    private Stage userStage;
    private Scene loginScene;
    private Parent root = null;


    public UserController(){
      
        
        /*try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Login.fxml"));
            userStage = new Stage();
            userStage.setAlwaysOnTop(true);
         
            loader.setController(this);
            root = loader.load();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();

            showError();
        }
        */
        
        

        dataHandler = IMatDataHandler.getInstance();
        currentUser = dataHandler.getUser();
        currentUser.setUserName("arne");
        currentCustomer = dataHandler.getCustomer();
    }

    @Override
    public void setVisible(boolean value) {
       //anchorUser.setVisible(value);
       
       System.out.println("WOho");
    }
    
    public void setParentPane(AnchorPane pane){
        anchorUser = pane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnLogin_onAction(javafx.event.ActionEvent e){

    }

    private void showError(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fel");
        error.setHeaderText("Ajdå!");
        error.setContentText("Någonting gick väldigt fel. Vi ber om ursäkt för detta!");
        error.showAndWait();
    }
}
