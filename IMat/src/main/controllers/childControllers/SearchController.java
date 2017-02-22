package main.controllers.childControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.backend.CustomDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Pontus on 2017-02-22.
 */
public class SearchController implements Initializable {
    @FXML TextField txtSearch;
    @FXML Button btnSearch;

    CustomDataHandler dataHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
    }

    @FXML
    private void btnSearch_onActionPerformed(ActionEvent e){
        System.out.println("Searching for " + txtSearch.getText() + "...");

        dataHandler.getDisplayedProducts().addAll(search(txtSearch.getText()));
    }

    private List<Product> search(String search){
        List<Product> result = new ArrayList<>();

        return result;
    }
}
