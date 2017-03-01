package main.controllers.childControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.backend.CustomDataHandler;

import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by lumo on 01/03/17.
 */
public class KvittoController implements Initializable {
    @FXML
    ScrollPane kvittoList;

    private CustomDataHandler dataHandler;
    private SimpleDateFormat dateFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataHandler = CustomDataHandler.getInstance();
        dateFormat = new SimpleDateFormat("dd MMMMMMMMM yyyy, HH:mm");
    }
}
