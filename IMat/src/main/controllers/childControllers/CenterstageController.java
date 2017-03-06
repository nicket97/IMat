/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import main.controllers.childControllers.checkout.CheckoutController;
import main.controllers.childControllers.navigation.BottomBarController;
/**
 *
 * @author Felix
 */

public class CenterstageController implements Controllable{

    @FXML
    private AnchorPane centerstage;
    @FXML
    private StartpageController startpageController;
    @FXML
    private HistoryController historyController;
    @FXML
    private ProductViewController productViewController;
    @FXML
    private SearchController searchController;
    @FXML
    private BottomBarController bottomBarController;
    @FXML
    private CheckoutController checkoutController;
    @FXML
    private SearchViewController searchViewController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setVisible(boolean value) {

    }

    public StartpageController getStartpageController(){
        return startpageController;
    }

    public HistoryController getHistoryController(){
        return historyController;
    }
    
    public ProductViewController getProductViewController(){
        return productViewController;
    }
    public SearchController getSearchController(){
        return searchController;
    }
    public BottomBarController getBottomBarController(){
        return bottomBarController;
    }
    public CheckoutController getCheckoutController(){
        return checkoutController;
    }
    public SearchViewController getSearchViewController(){
        return searchViewController;
    }
    
    public AnchorPane getCenterstage(){
        return centerstage;
    }
}
