package main.controllers.childControllers.navigation;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import main.controllers.childControllers.ProductView;
import main.controllers.childControllers.ProductViewController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-28.
 */
public class BottomBarController implements Initializable {

    @FXML private Button btnPrev;
    @FXML private Button btnNext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
