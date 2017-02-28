package fxComponents;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fxComponents.SpinBox;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

/**
 *
 * @author Nicket
 */

public class ProductViewNode extends AnchorPane implements Initializable {
    @FXML
    private Label labelProductName;
    @FXML
    private ImageView imgProduct;
    @FXML
    private Label labelPrice;
    @FXML
    private AnchorPane productPane;
    @FXML
    private SpinBox spinBox;

    private final Product product;
    private IMatDataHandler db = IMatDataHandler.getInstance();

    public ProductViewNode(Product product) throws IOException{
        this.product = product;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductViewNode.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
       
        
        imgProduct.setImage(IMatDataHandler.getInstance().getFXImage(product, 100, 100));
        labelProductName.setText(product.getName());
        labelPrice.setText((int) product.getPrice() + product.getUnit());
    }

    public ProductViewNode getPane () {
        return this;
    }
    /*public void ShowProducts(List<Product> toShowList){
    	for(Product p: toShowList){
    		labelProductName.setText(p.getName());
    		labelPrice.setText(""+ p.getPrice() + " Kr");
    		//imgProduct.setImage();
    	}
    }*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        
	}

   
}
