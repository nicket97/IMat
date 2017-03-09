package fxComponents;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.controllers.childControllers.CartController;
import main.controllers.childControllers.checkout.OrderController;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class ListViewOrderItem extends ListCell<ShoppingItem> implements Initializable{
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
    private OrderController orderController;
    private DecimalFormat df = new DecimalFormat("#.00");

    private int count = 0;
    
    public ListViewOrderItem(ShoppingCart cart, OrderController c){
    	orderController = c;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewOrderItem.fxml"));

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
    
    private void updateTxtVal(){
        txtAmount.setText("" + count);
        
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
                count = 99;
                
        }
        labelTitle.setText(item.getProduct().getName());
        labelUnit.setText(item.getProduct().getUnitSuffix());
        amount.set((int)item.getAmount());
        txtAmount.setText(String.valueOf(amount.getValue()));
        labelPrice.setText(String.valueOf(df.format(item.getProduct().getPrice()*item.getAmount())) + " kr");
        if(amount.get() < 2)
            labelDec.setDisable(true);
        
        else
            labelDec.setDisable(false);
       
        setGraphic(anchorItem);
        setText(null);
        
    }

    private void addListeners() {
        labelInc.setOnMouseClicked(e -> {
            getItem().setAmount(getItem().getAmount() + 1);
            updateItem(getItem(), false);
            orderController.displaySum();
        });

        labelDec.setOnMouseClicked(e -> {
            if (amount.getValue() > 1){
                getItem().setAmount(getItem().getAmount() - 1);
                updateItem(getItem(), false);
                orderController.displaySum();
            }
        });
        
        labelDelete.setOnMouseClicked(e -> {cart.removeItem(getItem()); setItem(null);});
    }
}


