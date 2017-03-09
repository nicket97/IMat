/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import fxComponents.ListViewOrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Nicket
 */
public class OrderController implements Controllable {

    @FXML
    private AnchorPane order;
    @FXML
    private ListView<ShoppingItem> orderList;
    @FXML
    private VBox orderVBox;

    @FXML 
    private Label labelGranska;
    @FXML
    private Label labelSum;

    private CustomDataHandler dataHandler;
    private String ordinaryTitle;
    private ShoppingCart shoppingCart;
    private DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void setVisible(boolean value) {
        order.setVisible(value);
        order.setManaged(value);
        order.toFront();
        
        if(dataHandler.getShoppingCart().getItems().size() == 0)
            labelGranska.setText("Oj, här var det visst tomt! Du har inte lagt några varor i varukorgen.");
        else
            labelGranska.setText(ordinaryTitle);

        shoppingCart = CustomDataHandler.getInstance().getShoppingCart();
        orderList.getItems().clear();

        for(ShoppingItem item : shoppingCart.getItems()){
            if(orderList.getItems().stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName())).findAny().isPresent()) {
                ShoppingItem existingItem =
                        orderList.getItems().stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName()))
                                .findAny().get();

                existingItem.setAmount(Math.max(existingItem.getAmount(),item.getAmount()));
                orderList.getItems().set(orderList.getItems().indexOf(existingItem), existingItem);

            } else orderList.getItems().add(item);
        }

        this.displaySum();
    }
    public boolean isVisible(){
    	return order.isVisible() && order.isManaged();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        ordinaryTitle = labelGranska.getText();
        shoppingCart = CustomDataHandler.getInstance().getShoppingCart();
        //orderList.setMouseTransparent( true );
        //orderList.setFocusTraversable( false );
        //orderList.getChildrenUnmodifiable().get(0).setFocusTraversable(true);
        //orderVBox.setManaged(true);
        
        addListeners();
    }
    
    private void addListeners(){
    	orderList.setCellFactory(x -> new ListViewOrderItem(shoppingCart, this));
    	 shoppingCart.addShoppingCartListener(x -> {updateOrder();
         });
    }
    private void updateOrder() {
		// TODO Auto-generated method stub
		orderList.getItems().clear();
		for(ShoppingItem item : shoppingCart.getItems()){
            if(orderList.getItems().stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName())).findAny().isPresent()) {
                ShoppingItem existingItem =
                        orderList.getItems().stream().filter(i -> i.getProduct().getName().equals(item.getProduct().getName()))
                                .findAny().get();

                existingItem.setAmount(Math.max(existingItem.getAmount(),item.getAmount()));
                orderList.getItems().set(orderList.getItems().indexOf(existingItem), existingItem);

            } else orderList.getItems().add(item);
        }
	}
	public void displaySum(){
    	List<ShoppingItem> viewedItems = orderList.getItems();
        double sum = 0;
        for(ShoppingItem i :viewedItems){
        	sum += i.getAmount()*i.getProduct().getPrice();
        }
        labelSum.setText("Totalsumma " + df.format(sum) + ":-");
    }
    public boolean validate(){
        return dataHandler.getShoppingCart().getItems().size() > 0;
    }
    
}
