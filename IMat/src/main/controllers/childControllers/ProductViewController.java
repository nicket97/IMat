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
import javafx.scene.control.ScrollPane;
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
    private Label labelHeader;
    @FXML
    private VBox listBox;
    @FXML
    private ScrollPane scrollPane;
    
    public void displayProducts (List<Product> productList, String label) {
        setVisible(true);
        List<ProductCategory> usedCat = new ArrayList<>();
        if (productViewFlowPane != null) {
            productViewFlowPane.getChildren().clear();
            listBox.getChildren().clear();      
        }
        for(int i = 0;i<productList.size();i++) {
            try {
            	if(!usedCat.contains(productList.get(i).getCategory())){
            		ProductViewSubCategory searchResult = new ProductViewSubCategory(productList, label);
            		productViewFlowPane.getChildren().add(searchResult);
            		
                        labelHeader.setText(label);
            		usedCat.add(productList.get(i).getCategory());
            		System.out.println(usedCat.toString());
            	}
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
            scrollPane.setVvalue(0);
        }
        
        // Array with +1 length for setting 0 as starting point and also getting full height of nodes
        double[] scrollIndex = new double[section.length + 1];
        double[] offset = new double[section.length + 1];
        
        for(int i = 0;i<section.length;i++) {
            try {
                ProductViewSubCategory subSection = new ProductViewSubCategory(section[i]);
                productViewFlowPane.getChildren().add(subSection);
                
                // Add each height to their respective index, starting from 1 to max, adding each previous height.
                scrollIndex[i+1] = scrollIndex[i] + productViewFlowPane.getChildren().get(i).getBoundsInParent().getHeight();
                offset[i] = offset[i] + scrollIndex[i]/(scrollIndex.length - 4) ;
                System.out.println(subSection.getBoundsInParent().getWidth());
                labelHeader.setText(label);
                listBox.getChildren().add(createSubcategoryButton(subSection.getSubCatLabel()));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        
        double scrollPaneLength = scrollIndex[scrollIndex.length-1];
        double pxEqu = 1/scrollPaneLength;
        // Generate theoretical offset, based on the amount of elements and the loss of element height.
        
       
        // Loop through each list-category, adding a listener with their index + offset divided by the whole window.
        // The interval needed is 0 - 1, problem is that 1 pane is "lost" as Vvalue = 1 is bottom of scrollbar at bottom
        // and Vvalue = 0 is the top of the scrollbar at the top.
        for(int index = 0; index < listBox.getChildren().size(); index++){
            int i = index;
            listBox.getChildren().get(i).setOnMouseClicked(e -> scrollPane.setVvalue((scrollIndex[i] + offset[i])*pxEqu));
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
