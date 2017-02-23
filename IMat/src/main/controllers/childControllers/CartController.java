/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fxComponents.ListViewCartItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.backend.CustomDataHandler;
import main.controllers.MainController;
import se.chalmers.ait.dat215.project.ShoppingCart;

/**
 *
 * @author Felix
 */
public class CartController implements Initializable{

    @FXML ListView listViewCartItems;
    @FXML Label labelSum;


    private ShoppingCart shoppingCart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingCart = CustomDataHandler.getInstance().getShoppingCart();
        addListeners();
    }

    public void setVisible(boolean value){

    }

    private void addListeners(){
        shoppingCart.addShoppingCartListener(x -> updateChart());
        listViewCartItems.setCellFactory(x -> new ListViewCartItem());
    }

    private void updateChart(){
        listViewCartItems.getItems().clear();
        listViewCartItems.getItems().addAll(shoppingCart.getItems());

        labelSum.setText(shoppingCart.getTotal() + " kr");
    }
}
