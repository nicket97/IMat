package main.controllers.childControllers.navigation;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.controllers.childControllers.CenterstageController;
import main.controllers.childControllers.HelpController;
import main.controllers.childControllers.ProductView;
import main.controllers.childControllers.ProductViewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-28.
 */
public class BottomBarController implements Initializable {

    @FXML private Button btnPrev;
    @FXML private Button btnNext;
    @FXML private Button btnHelp;

    private HelpController helpController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnHelp.setOnAction(e-> helpController.setVisible(true));
    }

    public void setButtonsVisible(boolean btnPrevVisible, boolean btnNextVisible){
        btnPrev.setVisible(btnPrevVisible);
        btnPrev.setManaged(btnPrevVisible);
        btnNext.setVisible(btnNextVisible);
        btnNext.setManaged(btnNextVisible);
    }

    public Button getBtnPrev(){
        return btnPrev;
    }

    public Button getBtnNext(){
        return btnNext;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public HelpController getHelpController() {
        return helpController;
    }
    
    public void setHelpController(HelpController helpCtrl){
        helpController = helpCtrl;
        btnHelp.setOnAction(e -> helpController.setVisible(true));
    }
    
    
}
