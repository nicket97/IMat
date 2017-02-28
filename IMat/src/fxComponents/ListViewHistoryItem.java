package fxComponents;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import main.backend.CustomDataHandler;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pontu on 2017-02-27.
 */
public class ListViewHistoryItem extends ListCell<ShoppingItem> implements Initializable {

    @FXML private Pane paneItem;
    @FXML private Label labelProduct;
    @FXML private Label labelAmount;
    @FXML private Label labelPriceEach;
    @FXML private Label labelSum;
    @FXML private Label labelAddToCart;

    ShoppingCart shoppingCart;

    public ListViewHistoryItem(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewHistoryItem.fxml"));

            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (IOException e){
            System.out.println("Whoops. Can't find fxml.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingCart = CustomDataHandler.getInstance().getShoppingCart();
        labelAddToCart.setOnMouseClicked(e -> shoppingCart.addItem(getItem()));
    }

    @Override
    protected void updateItem(ShoppingItem item, boolean empty){
        super.updateItem(item, empty);

        if(item == null)
            empty = true;

        setItem(item);

        if(empty) {
            setText(null);
            setGraphic(null);
            return;
        }

        double amount = item.getAmount();

        labelProduct.setText(item.getProduct().getName());
        labelAmount.setText((amount % 1) == 0 ? String.valueOf(((int)amount)) : String.valueOf(amount));
        labelPriceEach.setText(String.valueOf(item.getProduct().getPrice()));
        labelSum.setText(String.valueOf(item.getAmount()*item.getProduct().getPrice()));

        setGraphic(paneItem);
        setText(null);
    }
}
