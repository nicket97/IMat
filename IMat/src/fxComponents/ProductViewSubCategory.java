/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxComponents;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.ProductCategories;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 *
 * @author Felix
 */
public class ProductViewSubCategory extends FlowPane implements Initializable{
    @FXML
    private FlowPane productViewFlowPane;
    @FXML
    private Label subcatLabel;
    @FXML
    private FlowPane productViewNodePane;
    
    private CustomDataHandler dh = CustomDataHandler.getInstance();
    private List<Product> products;

    public ProductViewSubCategory(ProductCategory section) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductViewSubCategory.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        

        products = dh.getProducts(section);
        subcatLabel.setText(ProductCategories.getProdCatName(section));
        for(Product p : products){
            productViewNodePane.getChildren().add(new ProductViewNode(p));
        }
        
    }
    
    public ProductViewSubCategory(List<Product> result, String header) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductViewSubCategory.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        
        subcatLabel.setText(header);
        for(Product p : result){
            productViewNodePane.getChildren().add(new ProductViewNode(p));
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    
    public void setFilter(boolean value){
        
    }

    public String getSubCatLabel(){
        return subcatLabel.getText();
    }
}
