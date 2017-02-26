package main.controllers.childControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.backend.CustomDataHandler;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-26.
 */
public class HistoryController implements Initializable {

    @FXML Label labelDate;
    @FXML Label labelTotal;
    @FXML Button btnAddAll;
    @FXML ListView<String> listDates;
    @FXML ListView<String> listItems;
    @FXML AnchorPane history;
    @FXML AnchorPane anchorHistoryDetails;

    private CustomDataHandler dataHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();


    }

    public void setVisible(boolean value){
        history.setVisible(value);
        history.setManaged(value);
    }


}
