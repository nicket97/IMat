package main;

import fxComponents.SpinBox;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import main.ProductView;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.util.List;

/**
 * Created by Tekniker on 2017-02-27.
 */
public class ProductViewController {
    @FXML
    private FlowPane productViewFlowPane;

    public void displayProducts (List<Product> productList) {
        if (productViewFlowPane != null) {
            productViewFlowPane.getChildren().clear();
        }
        for(int i = 0;i<productList.size();i++) {
            try {
                ProductView test = new ProductView(productList.get(i),new SpinBox());
                productViewFlowPane.getChildren().add(test.getPane());
            } catch (IOException e) {
                System.out.println("Men för i helvete");
            }

        }
    }

}
