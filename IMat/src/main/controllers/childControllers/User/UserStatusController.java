package main.controllers.childControllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import main.backend.CustomDataHandler;
import main.backend.UserHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.controllers.childControllers.Controllable;
import main.controllers.childControllers.LoginStatusListener;

/**
 * Created by Pontus on 2017-02-20.
 */
public class UserStatusController implements Initializable {

    UserController userController;
    UserHandler userHandler;

    @FXML StackPane userStatus;
    @FXML AnchorPane paneLoggedOut;
    @FXML AnchorPane paneLoggedIn;
    @FXML Label textLoginStatus;
    
    private List<LoginStatusListener> listeners = new ArrayList<>();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userHandler = CustomDataHandler.getInstance().getUserHandler();

        setLoggedIn(userHandler.isLoggedIn());
        addListeners();
    }

    public void setUserController(UserController controller){
        userController = controller;
    }

    public void addListener(LoginStatusListener controller){
        listeners.add(controller);
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
        notifyLoginStatusListeners(value);
    }

    private void addListeners(){

        //Lyssna efter ut- och inloggningar
        userHandler.getLoggedInProperty().addListener(x -> setLoggedIn(userHandler.isLoggedIn()));
    }
    
    private void notifyLoginStatusListeners(boolean value){
        if(!listeners.isEmpty()){
        for(LoginStatusListener listener:listeners){
            listener.loginStatusChanged(value);
        }}
    }
    
}
