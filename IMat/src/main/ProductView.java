package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fxComponents.SpinBox;
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

public class ProductView implements Initializable {
    @FXML
    private Label labelProductName;
    @FXML
    private ImageView imgProduct;
    @FXML
    private Label labelPrice;
    @FXML
    private AnchorPane productPane;

    private SpinBox spinBox;

    private Product product;


    public ProductView(Product product, SpinBox spinBox) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/ProductView.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Detta gick inte");
        }

        this.product = product;
        imgProduct.setImage(IMatDataHandler.getInstance().getFXImage(product));
        labelProductName.setText(product.getName());
        labelPrice.setText((int) product.getPrice() + product.getUnit());
        this.spinBox = spinBox;
    }

    public AnchorPane getPane () {
        return productPane;
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
