package main.controllers.childControllers;

import fxComponents.ProductViewNode;
import fxComponents.ProductViewSubCategory;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import main.controllers.childControllers.navigation.BottomBarController;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * Created by Nicket on 2017-02-27.
 */
public class ProductViewController implements Controllable{
    @FXML
    private VBox productViewFlowPane;
    @FXML
    private AnchorPane productView;
    @FXML
    private Label subcatLabel;
    @FXML
    private FlowPane productViewNodePane;
    @FXML
    private Label labelHeader;
    @FXML
    private VBox listBox;
    
    public void displayProducts (List<Product> productList, String label) {
        setVisible(true);

        if (productViewFlowPane != null) {
            productViewFlowPane.getChildren().clear();
            listBox.getChildren().clear();      
        }
        for(int i = 0;i<productList.size();i++) {
            try {
                ProductViewSubCategory searchResult = new ProductViewSubCategory(productList, label);
                productViewFlowPane.getChildren().add(searchResult);
                labelHeader.setText(label);
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
    }
    public void displayProducts (ProductCategory[] section, String label) {
        setVisible(true);

        if (productViewFlowPane != null) {
            productViewFlowPane.getChildren().clear();
            listBox.getChildren().clear();
        }
        for(int i = 0;i<section.length;i++) {
            try {
                ProductViewSubCategory subSection = new ProductViewSubCategory(section[i]);
                productViewFlowPane.getChildren().add(subSection);
                labelHeader.setText(label);
                listBox.getChildren().add(createSubcategoryButton(subSection.getSubCatLabel()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

    public void setVisible(boolean value){
        productView.setVisible(value);
        productView.setManaged(value);

        if(value)
            productView.toFront();
    }
    
    public Label createSubcategoryButton(String label){
        Label category = new Label(label);
        category.setAlignment(Pos.CENTER);
        category.setPrefSize(160, 40);
        return category;
    }
}
