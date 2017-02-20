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
import java.util.ResourceBundle;

/**
 * Created by Pontus on 2017-02-20.
 */
public class UserStatusController implements Initializable {

    UserController userController;
    UserHandler userHandler;

    @FXML BorderPane borderLoggedOut;
    @FXML Text textLoginStatus;
    @FXML private ImageView imgUserSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userHandler = CustomDataHandler.getInstance().getUserHandler();

        setLoggedIn(userHandler.isLoggedIn());
        addListeners();
    }

    public void setUserController(UserController controller){
        userController = controller;
    }

    @FXML
    private void btnOpenLogin_onActionPerformed(ActionEvent e){
        userController.setLoginVisible(true);
    }

    private void setLoggedIn(boolean value){
        borderLoggedOut.setVisible(!value);

        if(value){
            textLoginStatus.setText(userHandler.getUser().getUserName());
        }
    }

    private void addListeners(){
        //Detta är trams
        imgUserSettings.setOnMouseClicked(e -> userHandler.logOut());

        //Lyssna efter ut- och inloggningar
        userHandler.getLoggedInProperty().addListener(x -> setLoggedIn(userHandler.isLoggedIn()));
    }
}
