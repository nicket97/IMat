package main.controllers.childControllers;

import fxComponents.ProductViewNode;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 * Created by Tekniker on 2017-02-27.
 */
public class ProductViewController implements Initializable{
    @FXML
    private FlowPane productViewFlowPane;
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

        if (productViewNodePane != null) {
            productViewNodePane.getChildren().clear();
        }
        for(int i = 0;i<productList.size();i++) {
            try {
                ProductViewNode node = new ProductViewNode(productList.get(i));
                productViewNodePane.getChildren().add(node);
                labelHeader.setText(label);
                
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

}
