package main.controllers.childControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxComponents.ProductViewSubCategory;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.Product;
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
    
    private int OFFSET = 420 + 280;
    private List<ProductViewSubCategory> nodes = new ArrayList<>();
    
    public void addListener(ProductViewSubCategory node){
        nodes.add(node);
    }
    
    public void displayProducts (List<Product> productList, String label) {
        productView.getScene().setCursor(Cursor.WAIT);

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
        
        productView.getScene().setCursor(Cursor.DEFAULT);
        
    }
    public void displayProducts (ProductCategory[] section, String label) {
        Platform.runLater(() -> productView.getScene().setCursor(Cursor.WAIT));

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
                subSection.setWrappWidth(productView.getWidth() - OFFSET);
                nodes.add(subSection);
                //System.out.println(subSection.getBoundsInParent().getWidth());
                
                labelHeader.setText(label);
                listBox.getChildren().add(createSubcategoryButton(subSection.getSubCatLabel()));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        
        double scrollPaneLength = scrollIndex[scrollIndex.length-1];
        double pxEqu = 1/scrollPaneLength;
        double[] scrollValues = new double[section.length + 1];
        // Generate theoretical offset, based on the amount of elements and the loss of element height.
        for(int i = 0; i < scrollValues.length; i++){
        	scrollValues[i] = ((scrollIndex[i] + offset[i])*pxEqu);
        }
       
       scrollValues[scrollValues.length-2] = 1.0;
       scrollValues[0] = 0;
       //// Loop through each list-category, adding a listener with their index + offset divided by the whole window.
        // The interval needed is 0 - 1, problem is that 1 pane is "lost" as Vvalue = 1 is bottom of scrollbar at bottom
        // and Vvalue = 0 is the top of the scrollbar at the top.
        for(int index = 0; index < listBox.getChildren().size(); index++){
            int i = index;
            
            //System.out.println(scrollValues[i] + "  " + i);
            
            listBox.getChildren().get(i).setOnMouseClicked(e -> {
            	final Timeline timeline = new Timeline();
                final KeyValue kv = new KeyValue(scrollPane.vvalueProperty(), scrollValues[i]);
                final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
            	//scrollPane.setVvalue(scrollValues[i]);
            	
            });
        }

        Platform.runLater(() -> productView.getScene().setCursor(Cursor.DEFAULT));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productView.widthProperty().addListener(e -> resizeNodes());
        
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

    private void resizeNodes() {
        for(ProductViewSubCategory node: nodes){
            node.setWrappWidth(productView.getWidth() - OFFSET);
        }
    }
}
