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
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.Product;

/**
 *
 * @author Nicket
 */

public class ProductView extends Pane implements Initializable {
    @FXML
    private Label labelProductName;
    @FXML
    private ImageView imgProduct;
    @FXML
    private Label labelPrice;


    public ProductView() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpinBox.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
    public void ShowProducts(List<Product> toShowList){
    	for(Product p: toShowList){
    		labelProductName.setText(p.getName());
    		labelPrice.setText(""+ p.getPrice() + " Kr");
    		//imgProduct.setImage();
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

   
}
