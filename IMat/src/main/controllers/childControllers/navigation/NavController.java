/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.navigation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.ProductViewController;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();
        addListeners();
    }

    public void injectControllers(StartpageController startpageController,
                                  CartController cartController,
                                  ProductViewController productViewController,
                                  BottomBarController bottomBarController){
        this.cartController = cartController;
        this.startpageController = startpageController;
        this.prodCtrl = productViewController;
        bottomCtrl = bottomBarController;
    }

    public void startShopping(){
        prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.VEGETABLE_FRUIT), "Frukt & Grönt");
        bottomCtrl.setButtonsVisible(false, true);
    }

    private void addListeners(){
        gridMain.getChildren().get(2).setOnMouseClicked(e -> startShopping());

        gridMain.getChildren().get(3).setOnMouseClicked(e -> {
            prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.BREAD), "Bröd");
            bottomCtrl.setButtonsVisible(true, true);
        });

        gridMain.getChildren().get(4).setOnMouseClicked(e -> {
            prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.FLOUR_SUGAR_SALT), "Skafferi");
            bottomCtrl.setButtonsVisible(true, true);
        });

        gridMain.getChildren().get(5).setOnMouseClicked(e -> {
            prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.DAIRIES), "Mejeri");
            bottomCtrl.setButtonsVisible(true, true);
        });

        gridMain.getChildren().get(6).setOnMouseClicked(e -> {
            prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.MEAT), "Protein");
            bottomCtrl.setButtonsVisible(true, true);
        });

        gridMain.getChildren().get(7).setOnMouseClicked(e -> {
            prodCtrl.displayProducts(dataHandler.getProducts(ProductCategory.POD), "Kolonial");
            bottomCtrl.setButtonsVisible(true, true);
        });


        //Need animation here
        cartPane.setOnMouseClicked(e -> cartController.setVisible(!cartController.isVisible()));
        navHome.setOnMouseClicked(e -> {
            startpageController.setVisible(true);
            prodCtrl.setVisible(false);
            bottomCtrl.setButtonsVisible(false, false);
        });
    }
}
