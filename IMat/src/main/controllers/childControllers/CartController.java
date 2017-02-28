/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fxComponents.ListViewCartItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import main.backend.CustomDataHandler;
import main.controllers.MainController;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Felix
 */
public class CartController implements Initializable{

    @FXML ListView listViewCartItems;
    @FXML Label labelSum;

    @FXML private Pane cart;

    private ShoppingCart shoppingCart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingCart = CustomDataHandler.getInstance().getShoppingCart();
        addListeners();
    }

    public void setVisible(boolean value){
        //Animation here
        cart.setVisible(value);
        cart.setManaged(value);
    }

    public boolean isVisible(){
        return cart.isVisible();
    }

    private void addListeners(){
        shoppingCart.addShoppingCartListener(x -> updateChart(x.getShoppingItem(), x.isAddEvent()));
        listViewCartItems.setCellFactory(x -> new ListViewCartItem(shoppingCart));
    }

    private void updateChart(ShoppingItem item, boolean add){
        setVisible(true);
        List<ShoppingItem> viewedItems = listViewCartItems.getItems();

        if(item == null){
            viewedItems.clear();
            return;
        }

        System.out.println("Adding " + item.getProduct().getName());

        //Den här if-satsen alltså hahahaha
        if(viewedItems.stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName())).findAny().isPresent()) {
            ShoppingItem existingItem =
                    viewedItems.stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName()))
                    .findAny().get();

            existingItem.setAmount(existingItem.getAmount() + item.getAmount());
            viewedItems.set(viewedItems.indexOf(existingItem), existingItem);

        } else if(add) {
            viewedItems.add(item);
        }
        else
            viewedItems.remove(item);

        labelSum.setText(shoppingCart.getTotal() + " :-");
    }
}
