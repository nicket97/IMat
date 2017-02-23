package fxComponents;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import se.chalmers.ait.dat215.project.Product;
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

    private ShoppingItem item;

    public ListViewCartItem(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewCartItem.fxml"));

            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (IOException e){
            System.out.println("Whoops. Can't find fxml.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    protected void updateItem(ShoppingItem item, boolean empty){
        if(item == null)
            empty = true;

        setVisible(empty);
        setManaged(empty);

        if(empty) return;

        labelTitle.setText(item.getProduct().getName());
        labelUnit.setText(item.getProduct().getUnitSuffix());
        txtAmount.setText(String.valueOf((int)item.getAmount()));
        labelPrice.setText(String.valueOf(item.getProduct().getPrice()*item.getAmount()) + ":-");

        setGraphic(anchorItem);
        setText(null);
    }
}
