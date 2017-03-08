package main.controllers.childControllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Created by Pontus on 2017-02-20.
 */
public class UserStatusController implements Initializable {

    private UserController userController;
    private UserHandler userHandler;

    @FXML StackPane userStatus;
    @FXML AnchorPane paneLoggedOut;
    @FXML AnchorPane paneLoggedIn;
    @FXML Label textLoginStatus;
    @FXML Region imgUserSettings;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userHandler = CustomDataHandler.getInstance().getUserHandler();

        setLoggedIn(userHandler.isLoggedIn());
        addListeners();
    }

    public void injectControllers(UserController controller){
        userController = controller;
    }
    
    @FXML
    private void btnOpenLogin_onActionPerformed(ActionEvent e){
        userController.setLoginVisible(true);
    }

    @FXML
    private void btnOpenRegister_onActionPerformed(ActionEvent e){
        userController.setRegisterVisible(true);
    }
    
    private void setLoggedIn(boolean value){
        userStatus.getChildren().get(1).setVisible(!value);
        userStatus.getChildren().get(1).setMouseTransparent(value);
        if(value){
            textLoginStatus.setText(userHandler.getUser().getUserName());
        }
    }

    private void addListeners(){

        //Lyssna efter ut- och inloggningar
        userHandler.getLoggedInProperty().addListener(x -> setLoggedIn(userHandler.isLoggedIn()));
        paneLoggedIn.setOnMouseClicked(e -> userController.setMyPagesVisible(true));
    }
    
}
