/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.navigation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.backend.CustomDataHandler;
import main.controllers.MainController;
import main.controllers.childControllers.*;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 *
 * @author Felix
 */
public class NavController implements Initializable {
    @FXML GridPane gridMain;

    @FXML private StackPane cartPane;
    @FXML private StackPane navHome;

    private CartController cartController;
    private StartpageController startpageController;
    private ProductViewController prodCtrl;
    private BottomBarController bottomCtrl;

    private CustomDataHandler dataHandler;

    private int displayedIndex = 2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        
        addListeners();
    }

    public void injectControllers(StartpageController startpageController,
                                  CartController cartController,
                                  ProductViewController productViewController,
                                  BottomBarController bottomBarController,
                                  SearchController searchController){
        this.cartController = cartController;
        this.startpageController = startpageController;
        this.prodCtrl = productViewController;
        bottomCtrl = bottomBarController;
        searchController.setprodCtrl(prodCtrl);
        bottomCtrl.getBtnNext().setOnAction(x -> {displayedIndex++; displayCategory(displayedIndex);});
        bottomCtrl.getBtnPrev().setOnAction(x -> {displayedIndex--; displayCategory(displayedIndex);});
        
    }

    public void startShopping(){
        prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.VEGETABLE_FRUIT), "Frukt & Grönt");
        bottomCtrl.setButtonsVisible(false, true);
        displayedIndex = 1;
    }

    private void addListeners(){
        for(int i = 2; i <= 8; i++){
            //Endast för att lambda kräver det
            int index = i;
            gridMain.getChildren().get(i).setOnMouseClicked(e -> displayCategory(index));
        }

        //Need animation here
        cartPane.setOnMouseClicked(e -> cartController.setVisible(!cartController.isVisible()));
        navHome.setOnMouseClicked(e -> {
            startpageController.setVisible(true);
            prodCtrl.setVisible(false);
            bottomCtrl.setButtonsVisible(false, false);
        });
    }

    private void displayCategory(int index){

        switch (index){
            case 1:
                break;
            case 2:
                startShopping();
                break;
            case 3:
                prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.BREAD), "Bröd");
                bottomCtrl.setButtonsVisible(true, true);
                break;
            case 4:
                prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.FLOUR_SUGAR_SALT), "Skafferi");
                bottomCtrl.setButtonsVisible(true, true);
                break;
            case 5:
                prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.DAIRIES), "Mejeri");
                bottomCtrl.setButtonsVisible(true, true);
                break;
            case 6:
                prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.MEAT), "Protein");
                bottomCtrl.setButtonsVisible(true, true);
                break;
            case 7:
                prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.POD), "Kolonial");
                bottomCtrl.setButtonsVisible(true, true);
                break;
            case 8:
                Alert fu = new Alert(Alert.AlertType.INFORMATION);
                fu.setTitle("WOHOO");
                fu.setHeaderText("Du har kommit till en kassa som inte finns!");
                fu.setContentText("Sök på \"Order\" i sökfältet för att lägga en order direkt!");
                fu.showAndWait();
                break;
        }

        displayedIndex = index;
        System.out.println("Index: " + index);
    }
}
