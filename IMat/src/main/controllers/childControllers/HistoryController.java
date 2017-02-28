package main.controllers.childControllers;

import fxComponents.ListViewHistoryItem;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.backend.CustomDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-26.
 */
public class HistoryController implements Initializable {

    @FXML Label labelDate;
    @FXML Label labelTotal;
    @FXML Button btnAddAll;
    @FXML ListView<String> listDates;
    @FXML ListView<ShoppingItem> listItems;
    @FXML AnchorPane history;
    @FXML AnchorPane anchorHistoryDetails;

    private CustomDataHandler dataHandler;
    private SimpleDateFormat dateFormat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        dateFormat = new SimpleDateFormat("dd MMMMMMMMM yyyy, HH:mm");

        listDates.getSelectionModel().selectedItemProperty().addListener((ChangeListener) -> selectDate());
        listItems.setCellFactory(x -> new ListViewHistoryItem());
        btnAddAll.setOnMouseClicked(e -> listItems.getItems().stream().forEach(i ->
                dataHandler.getShoppingCart().addItem(i)));
    }

    public void setVisible(boolean value){
        history.setVisible(value);
        history.setManaged(value);
        history.toFront();

        if(value)
            dataHandler.getOrders().stream().forEach(o -> listDates.getItems().add(dateFormat.format(o.getDate())));

        if(listDates.getItems().size() > 0)
            listDates.getSelectionModel().select(0);
    }

    private void selectDate(){
        String selected = listDates.getSelectionModel().getSelectedItem();

        labelDate.setText(selected);

        Order selectedOrder = dataHandler.getOrders().stream()
                .filter(o -> dateFormat.format(o.getDate()).equals(selected)).findFirst().get();

        listItems.getItems().clear();
        selectedOrder.getItems().stream().forEach(i -> listItems.getItems().add(i));

        double total = 0;
        for(ShoppingItem item : selectedOrder.getItems())
            total += item.getAmount()*item.getProduct().getPrice();

        labelTotal.setText(String.valueOf(total));
    }

}
