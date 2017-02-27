package main.controllers.childControllers;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.backend.CustomDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

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

        listDates.getSelectionModel().selectedItemProperty().addListener((ChangeListener) -> selectDate());
    }

    public void setVisible(boolean value){
        history.setVisible(value);
        history.setManaged(value);

        if(value)
            dataHandler.getOrders().stream().forEach(o -> listDates.getItems().add(o.getDate().toString()));
    }

    private void selectDate(){
        String selected = listDates.getSelectionModel().getSelectedItem();

        labelDate.setText(selected);

        Order selectedOrder = dataHandler.getOrders().stream()
                .filter(o -> o.getDate().toString().equals(selected)).findFirst().get();

        listItems.getItems().clear();
        selectedOrder.getItems().stream().forEach(i -> listItems.getItems().add(i.getProduct().getName()));

        double total = 0;
        for(ShoppingItem item : selectedOrder.getItems())
            total += item.getAmount()*item.getProduct().getPrice();

        labelTotal.setText(String.valueOf(total));
    }

}
