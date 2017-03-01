package main.controllers.childControllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.navigation.BottomBarController;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Pontus on 2017-02-22.
 */
public class SearchController implements Initializable {
    @FXML TextField txtSearch;
    @FXML Button btnSearch;
    @FXML CenterstageController centerStageController;
    
    private ProductViewController prodCtrl;
    private BottomBarController bottomCtrl;

    CustomDataHandler dataHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        addListeners();
    }

    public void focus(){
        //TODO
        //Animation here
        txtSearch.requestFocus();
    }

    @FXML
    private void btnSearch_onActionPerformed(ActionEvent e){
        startSearch();
        txtSearch.setText("");
    }

    private void startSearch(){
        if(txtSearch.getText().isEmpty())
            return;

        //Just optional for quick testing
        if(txtSearch.getText().equals("add")) {
            dataHandler.getDisplayedProducts().stream().forEach(p -> dataHandler.getShoppingCart().addItem(new ShoppingItem(p, 1)));
            return;
        }

        if(txtSearch.getText().equals("order")){
            dataHandler.placeOrder(true);
            return;
        }
        if(txtSearch.getText().equalsIgnoreCase("test")){
        	List<Product> result = dataHandler.getProducts();
        	 prodCtrl.displayProducts(result, txtSearch.getText());
        	return;
        }

        List<Product> result = search(txtSearch.getText());
        dataHandler.getDisplayedProducts().clear();
        dataHandler.getDisplayedProducts().addAll(result);

        //Optional printing
        prodCtrl.displayProducts(result, txtSearch.getText());
        //bottomCtrl.setButtonsVisible(false, false);
        //printSearchResult(result);
    }

    private List<Product> search(String search){
        //Add more search logic here

        return dataHandler.findProducts(search);
    }

    private void addListeners(){
        dataHandler.getDisplayedProducts().addListener((ListChangeListener<? super Product>)  x -> System.out.println("List changed."));
        txtSearch.setOnKeyPressed(e -> {
        	if(e.getCode() == KeyCode.ENTER){ startSearch();
        	txtSearch.setText("");
        }});
    }

    private void printSearchResult(List<Product> result){
        System.out.println("---------------------------------------------");
        System.out.println("----------------SÖKRESULTAT------------------");
        System.out.println("---------------------------------------------");
        System.out.println("Antal resultat: " + result.size());
        System.out.println("---------------------------------------------");

        result.stream().forEach(p ->
                System.out.println(p.getProductId() + " " + p.getName() + " " + p.getPrice() + "kr/" + p.getUnitSuffix()));

        System.out.println("---------------------------------------------");
    }
    public void setprodCtrl(ProductViewController p){
    	prodCtrl = p;
    }
}
