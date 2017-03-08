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

import javax.naming.directory.SearchResult;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.DataPair;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * Created by Nicket on 2017-02-27.
 */
public class SearchViewController implements Controllable{
    @FXML
    private VBox productViewFlowPane;
    @FXML
    private AnchorPane searchView;
    @FXML
    private Label labelHeader;
    @FXML
    private VBox listBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnBack;
    @FXML
    private Label labelResult;
    

    private boolean[] btnNavRtrnValues = new boolean[2];
    private int OFFSET = 420 + 280;
    private List<ProductViewSubCategory> nodes = new ArrayList<>();
    
    public void addListener(ProductViewSubCategory node){
        nodes.add(node);
    }
    public void displayProducts (List<DataPair<ProductCategory, List<Product>>> sortedResults, String label) {
        setVisible(true);
        if (productViewFlowPane != null) {
            productViewFlowPane.getChildren().clear();
            listBox.getChildren().clear();      
        }
        // Array with +1 length for setting 0 as starting point and also getting full height of nodes
        double[] scrollIndex = new double[sortedResults.size() + 1];
        double[] offset = new double[sortedResults.size() + 1];
        int i = 0;
        if(sortedResults.size() == 0){
        	labelResult.setText("Det finns inga produkter som matchar din sökning för: " + label);
        }
        else{
        labelResult.setText("Sökresultat för: " + label);
        }
        for(DataPair<ProductCategory, List<Product>> dp : sortedResults) {
            try {
                ProductViewSubCategory searchResult = new ProductViewSubCategory(dp.getRight(), dp.getLeft().toString());
                productViewFlowPane.getChildren().add(searchResult);
                searchResult.setWrappWidth(searchView.getWidth() - OFFSET);
                nodes.add(searchResult);
                listBox.getChildren().add(createSubcategoryButton(ProductCategories.getProdCatName(dp.getLeft())));
                
                // Add each height to their respective index, starting from 1 to max, adding each previous height.
                scrollIndex[i+1] = scrollIndex[i] + productViewFlowPane.getChildren().get(i).getBoundsInParent().getHeight();
                offset[i] = offset[i] + scrollIndex[i]/(scrollIndex.length) ;
                System.out.println(searchResult.getBoundsInParent().getHeight());
            	i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            

        }
        for(int index = 0; index < listBox.getChildren().size(); index++){
            int k = index;
            listBox.getChildren().get(k).setOnMouseClicked(e -> scrollPane.setVvalue((scrollIndex[k] + offset[k])/scrollIndex[scrollIndex.length-1]));
        }
        
        
    }
    public void setReturnValues(boolean prev, boolean next){
        btnNavRtrnValues[0] = prev;
        btnNavRtrnValues[1] = next;
    }
    public boolean[] getReturnValues(){
        return btnNavRtrnValues;
    }
/*    public void displayProducts (ProductCategory[] section, String label) {
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
                offset[i] = offset[i] + scrollIndex[i]/(scrollIndex.length) ;
                System.out.println(subSection.getBoundsInParent().getHeight());
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
*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction(e -> moveBack());
        searchView.widthProperty().addListener(e -> resizeNodes());
    }

    public void setVisible(boolean value){
        searchView.setVisible(value);
        searchView.setManaged(value);

        if(value)
            searchView.toFront();
    }
    
    public boolean isVisible(){
        return searchView.isVisible();
    }
    
    public AnchorPane getSearchView(){
        return searchView;
    }
    public Label createSubcategoryButton(String label){
        Label category = new Label(label);
        category.setAlignment(Pos.CENTER);
        category.setPrefSize(160, 40);
        return category;
    }

    public void moveBack() {
        searchView.setVisible(false);
        searchView.toBack();
    }
    
    public Button getBackButton(){
        return btnBack;
    }
    
    private void resizeNodes() {
        for(ProductViewSubCategory node: nodes){
            node.setWrappWidth(searchView.getWidth() - OFFSET);
        }
    }
}
