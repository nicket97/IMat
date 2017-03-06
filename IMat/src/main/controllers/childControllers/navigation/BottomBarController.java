package main.controllers.childControllers.navigation;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnHelp.setOnAction(x -> {
            Alert closeAlert = new Alert(Alert.AlertType.INFORMATION);
            closeAlert.setTitle("Hjälp ska vara här");
            closeAlert.setHeaderText("Men hur svårt kan det vara egentligen..?");

            closeAlert.showAndWait();
        });
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
