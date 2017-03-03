package fxComponents;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import main.controllers.childControllers.CartController;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pontus on 2017-02-23.
 */
public class ListViewCartItem extends ListCell<ShoppingItem> implements Initializable{

    @FXML AnchorPane anchorItem;
    @FXML private Label labelTitle;
    @FXML private Label labelUnit;
    @FXML private TextField txtAmount;
    @FXML private Label labelPrice;
    @FXML private Label labelDelete;
    @FXML private Label labelInc;
    @FXML private Label labelDec;

    private ShoppingCart cart;
    private SimpleIntegerProperty amount = new SimpleIntegerProperty();
    private CartController cartController;

    public ListViewCartItem(ShoppingCart cart, CartController c){
    	cartController = c;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewCartItem.fxml"));

            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (IOException e){
            System.out.println("Whoops. Can't find fxml.");
            e.printStackTrace();
        }

        this.cart = cart;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    public SimpleIntegerProperty getAmountProperty(){
        return amount;
    }

    @Override
    protected void updateItem(ShoppingItem item, boolean empty){
        super.updateItem(item, empty);

        if(item == null)
            empty = true;
        

        setItem(item);

        if(empty) {
            System.out.println("Size of list: ");
            labelTitle.setText("DELETED");
            setText(null);
            setGraphic(null);
            return;
        }
        if(item.getAmount() > 99){
        	item.setAmount(99);
        }
        labelTitle.setText(item.getProduct().getName());
        labelUnit.setText(item.getProduct().getUnitSuffix());
        amount.set((int)item.getAmount());
        txtAmount.setText(String.valueOf(amount.getValue()));
        labelPrice.setText(String.valueOf(item.getProduct().getPrice()*item.getAmount()) + " kr");

        setGraphic(anchorItem);
        setText(null);
        
    }

    private void addListeners() {
        labelInc.setOnMouseClicked(e -> {
            getItem().setAmount(getItem().getAmount() + 1);
            updateItem(getItem(), false);
            cartController.displaySum();
            
        });

        labelDec.setOnMouseClicked(e -> {
            if (amount.getValue() > 1){
            	cartController.displaySum();
                getItem().setAmount(getItem().getAmount() - 1);
                updateItem(getItem(), false);
            }
        });

        labelDelete.setOnMouseClicked(e -> {cart.removeItem(getItem()); setItem(null);});
    }
}
