/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.navigation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.*;
import main.controllers.childControllers.checkout.CheckoutController;

/**
 *
 * @author Felix
 */
public class NavController implements Initializable {
    @FXML 
    GridPane gridMain;

    @FXML 
    private StackPane cartPane;
    @FXML 
    private Label navHome;
    
    private CenterstageController centerstageController;
    private CartController cartController;
    private StartpageController startpageController;
    private ProductViewController prodCtrl;
    private BottomBarController bottomCtrl;
    private CheckoutController checkoutController;
    private SearchViewController searchCtrl;

    private CustomDataHandler dataHandler;

    private int displayedIndex = 1;
    @FXML
    private AnchorPane nav;
    @FXML
    private Label navGreens;
    @FXML
    private Label navBread;
    @FXML
    private Label navPantry;
    @FXML
    private Label navDairy;
    @FXML
    private Label navProtein;
    @FXML
    private Label navColonial;
    @FXML
    private Label navCheckout;

    private ImageView imgHome;
   
    private navigationSearchState searchState;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        navHome.setId("navActive");
        addListeners();
    }

    public void injectControllers(CenterstageController centerstageController, 
        CartController cartController, SearchController searchController, BottomBarController btmBarCtrl, HelpController helpCtrl){
            this.cartController = cartController;
            this.startpageController = centerstageController.getStartpageController();
            this.prodCtrl = centerstageController.getProductViewController();
            this.checkoutController = centerstageController.getCheckoutController();
            this.searchCtrl = centerstageController.getSearchViewController();
            
            bottomCtrl = btmBarCtrl;
            searchController.setControllers(searchCtrl, bottomCtrl);
            searchCtrl.getSearchView().visibleProperty().addListener(e -> setSearchView());
            
            checkoutController.getReturnButton().setOnAction(e -> { checkoutController.setVisible(false); 
                displayedIndex--; clearIds(); forceCart(true); displayCategory(displayedIndex); gridMain.getChildren().get(displayedIndex).setId("navActive");});
            bottomCtrl.getBtnNext().setOnAction(x -> {displayedIndex++; clearIds(); displayCategory(displayedIndex); 
                gridMain.getChildren().get(displayedIndex).setId("navActive");});
            
            bottomCtrl.getBtnPrev().setOnAction(x -> {displayedIndex--; clearIds(); displayCategory(displayedIndex); 
                gridMain.getChildren().get(displayedIndex).setId("navActive");});
            
            bottomCtrl.setHelpController(helpCtrl);
            imgHome = (ImageView) nav.getParent().getChildrenUnmodifiable().get(4);
            setHomeHatch();
            searchCtrl.getBackButton().setOnAction(e -> {searchCtrl.moveBack(); forceCart(displayedIndex < 8 && displayedIndex > 1); bottomCtrl.setButtonsVisible(searchCtrl.getReturnValues()[0], searchCtrl.getReturnValues()[1]);});
            
    }

    public void startShopping(){
        prodCtrl.displayProducts((ProductCategories.getVeg()), "Frukt & Grönt");
        bottomCtrl.setButtonsVisible(false, true);
        //cartController.setVisible(true);
        displayedIndex = 2;
        clearIds();
        gridMain.getChildren().get(displayedIndex).setId("navActive");
        forceCart(true);
    }
    
    private void addListeners(){
    	
        for(int i = 2; i <= 8; i++){
            //Endast för att lambda kräver det
            int index = i;
            gridMain.getChildren().get(i).setOnMouseClicked(e -> {
            	clearIds();            	
            	gridMain.getChildren().get(index).setId("navActive");
            	displayCategory(index);
            });
        } 
        
        
        //Need animation here
        cartPane.setOnMouseClicked(e -> {
            cartController.setVisible(!cartController.isVisible());
        });
        
        
        
        navHome.setOnMouseClicked(e -> displayStartPage());
    }
    
    public void setCartBtn(boolean value){

        if(value)
            cartPane.setId("navCartActive");
        else
            cartPane.setId("navCart");
    }

    public void displayStartPage(){
        startpageController.setVisible(true);
        clearIds();
        navHome.setId("navActive");
        prodCtrl.setVisible(false);
        bottomCtrl.setButtonsVisible(false, false);
        forceCart(false);
    }

    private void displayCategory(int index){
        switch (index){
            case 1:
                break;
            case 2:
                startShopping();
                break;
            case 3:
                prodCtrl.displayProducts(ProductCategories.getBrd(), "Bröd");
                bottomCtrl.setButtonsVisible(true, true);          
                forceCart(true);
                break;
            case 4:
                prodCtrl.displayProducts(ProductCategories.getPtry(), "Skafferi");
                bottomCtrl.setButtonsVisible(true, true);
                forceCart(true);
                break;
            case 5:
                prodCtrl.displayProducts(ProductCategories.getDry(), "Mejeri");
                bottomCtrl.setButtonsVisible(true, true);
                forceCart(true);
                break;
            case 6:
                prodCtrl.displayProducts(ProductCategories.getProt(), "Protein");
                bottomCtrl.setButtonsVisible(true, true);
                forceCart(true);
                break;
            case 7:
                prodCtrl.displayProducts(ProductCategories.getCol(), "Kolonial");
                bottomCtrl.setButtonsVisible(true, true);
                forceCart(true);
                break;
            case 8:
                checkoutController.setVisible(true);
                bottomCtrl.setButtonsVisible(true, false); //Ändra om här kanske, disabla eller dölja?
                forceCart(false);
                break;
        }
    
        displayedIndex = index;
        System.out.println("Index: " + index);
    }
    private void forceCart(boolean value){
        cartController.setVisibleOnAdd(value);
        cartPane.setDisable(value);
        cartController.setVisible(value);
        setCartBtn(value);
    }

    private void clearIds() {
        for(Node n: gridMain.getChildren())
            n.setId(null);            	     
    }
    private void setHomeHatch() {
        imgHome.setOnMouseClicked(e -> {
            startpageController.setVisible(true);
            clearIds();
            navHome.setId("navActive");
            prodCtrl.setVisible(false);
            bottomCtrl.setButtonsVisible(false, false);
            forceCart(false);
        });
        imgHome.setStyle("-fx-cursor: hand;");
    }    
    public void toCheckOut(){
    	checkoutController.setVisible(true);
        bottomCtrl.setButtonsVisible(true, false); //Ändra om här kanske, disabla eller dölja?
        forceCart(false);
        cartController.setVisibleOnAdd(false);
        displayedIndex = 8;
        clearIds();
        gridMain.getChildren().get(8).setId("navActive");
    }

    private void setSearchView() {
        if(searchCtrl.getSearchView().isVisible()){
            searchState = new navigationSearchState(displayedIndex, cartController.isVisible());
            clearIds();
            forceCart(true);
        }
        else{
            gridMain.getChildren().get(searchState.getIndex()).setId("navActive");
            forceCart(searchState.getCartState());
        }
    }
    
    private class navigationSearchState{
        private int currentIndex;
        private boolean currentCartState;
        
        public navigationSearchState(int currentIndex, boolean currentCartState){
            this.currentIndex = currentIndex;
            this.currentCartState = currentCartState;
        }
        
        public int getIndex(){
            return currentIndex;
        }
        public boolean getCartState(){
        return currentCartState;
        }
    }
}
