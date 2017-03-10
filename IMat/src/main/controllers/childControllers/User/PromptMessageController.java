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
import main.controllers.childControllers.checkout.CheckoutController;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

/**
 * Created by pontu on 2017-03-03.
 */
public class PromptMessageController implements Controllable {

    
    @FXML
    private Button btnClose;
    @FXML
    private AnchorPane promptMessage;
    @FXML
    private AnchorPane anchorPrompt;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnDecline;
    
    private UserHandler uh = CustomDataHandler.getInstance().getUserHandler();
    private StackPane parent;
    private UserController uc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        
    }

    public void setParentPane(StackPane anchorUser) {
        parent = anchorUser;
    }
     
    
    @Override
    public void setVisible(boolean value) {
        promptMessage.setVisible(value);
        promptMessage.setManaged(value);

        if (value) {
            parent.setVisible(value);
            parent.setManaged(value);
            //Hehe
            promptMessage.getParent().getParent().toFront();
        } else
            promptMessage.getParent().getParent().toBack();
    }

    public void close() {
        parent.setVisible(false);
        parent.setManaged(false);
        promptMessage.getParent().getParent().toBack();
    }
    public Button getBtnAccept(){
        return btnAccept;
    }
  
    public boolean isVisible() {
        return promptMessage.isVisible();
    }

    private void addListeners() {
        btnClose.setOnAction(e -> close());
        btnDecline.setOnAction(e -> close());
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
