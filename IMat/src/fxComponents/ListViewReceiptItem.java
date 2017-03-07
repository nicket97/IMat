package fxComponents;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import main.controllers.childControllers.checkout.ReceiptController;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class ListViewReceiptItem extends ListCell<ShoppingItem> implements Initializable {
	private ReceiptController receiptController;
	private ShoppingCart cart;
    private DecimalFormat df = new DecimalFormat("#.00");
	@FXML
	private Label labelName;
	@FXML
	private Label labelPrice;
	@FXML
	private Label labelPriceUnit;
	@FXML
	private Label labelAmount;
	@FXML
	private Label labelUnit;
	@FXML
	private Label labelSum;
	@FXML
	private AnchorPane anchorItem;

	public ListViewReceiptItem(Order order, ReceiptController c){
    	receiptController = c;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewReceiptItem.fxml"));

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
        
        labelName.setText(item.getProduct().getName());
        labelPrice.setText("" + item.getProduct().getPrice());
        labelPriceUnit.setText("" + item.getProduct().getUnit());
        labelAmount.setText("" +(int)item.getAmount());
        labelUnit.setText(item.getProduct().getUnitSuffix());
        labelSum.setText("Totalt : " + String.valueOf(df.format(item.getProduct().getPrice()*item.getAmount())));

        setGraphic(anchorItem);
        setText(null);
        
    }

    private void addListeners() {
        
    }
}
