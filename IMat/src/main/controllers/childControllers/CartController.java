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
import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import main.backend.CustomDataHandler;
import main.controllers.MainController;
import main.controllers.childControllers.navigation.NavController;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Felix
 */
public class CartController implements Initializable{

    @FXML 
    ListView listViewCartItems;
    @FXML 
    Label labelSum;
    @FXML 
    Button btnCheckout;
    
    @FXML private Pane cart;

    private NavController nav;
    private ShoppingCart shoppingCart;
    private DecimalFormat df = new DecimalFormat("#.00");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingCart = CustomDataHandler.getInstance().getShoppingCart();
        addListeners();
    }

    public void setNavStateListener(NavController nav){
        this.nav = nav;
    }
    
    public void setVisible(boolean value){
        //Animation here
        cart.setVisible(value);
        cart.setManaged(value);
        nav.setCartBtn(value);
    }

    public boolean isVisible(){
        return cart.isVisible();
    }

    private void addListeners(){
        shoppingCart.addShoppingCartListener(x -> {updateChart(x.getShoppingItem(), x.isAddEvent());
        });
        listViewCartItems.setCellFactory(x -> new ListViewCartItem(shoppingCart, this));
        btnCheckout.setOnAction(e -> {
        	nav.toCheckOut();
        });
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
        if(add && viewedItems.stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName())).findAny().isPresent()) {
            ShoppingItem existingItem =
                    viewedItems.stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName()))
                    .findAny().get();

            existingItem.setAmount(existingItem.getAmount() + item.getAmount());
            viewedItems.set(viewedItems.indexOf(existingItem), existingItem);

        } else if(add) {
            viewedItems.add(item);
        }
        else
        {
            viewedItems.remove(item);
        }
        this.displaySum();
    }
    public void displaySum(){
    	List<ShoppingItem> viewedItems = listViewCartItems.getItems();
        double sum = 0;
        for(ShoppingItem i :viewedItems){
        	sum += i.getAmount()*i.getProduct().getPrice();
        }
        labelSum.setText("Totalsumma " + df.format(sum) + ":-");
    }

    public void injectControllers(NavController navController) {
        nav = navController;
    }
    
    
}
