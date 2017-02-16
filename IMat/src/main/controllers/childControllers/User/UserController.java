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
    TextField txtUsername;
    //More textfield

    @FXML
    Button btnLogin;


    Stage userStage;
    Scene loginScene;
    Parent root = null;

    @Override
    public void setVisible(boolean value) {
        if(root == null){
            showError();
            return;
        }
        loginScene = new Scene(root, 440, 540);
        userStage.setScene(loginScene);
        userStage.show();
    }

    @Override
    public void testMe() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try{
            userStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/resources/fxml/Login.fxml"));
        }catch (IOException e){
            System.out.println(e);
            e.printStackTrace();

            showError();
        }
    }

    @FXML
    private void btnLogin_onAction(javafx.event.ActionEvent e){

    }

    private void showError(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fel");
        error.setHeaderText("Ajdå!");
        error.setContentText("Någonting gick väldigt fel. Vi ber om ursäkt för detta!");
    }
}
