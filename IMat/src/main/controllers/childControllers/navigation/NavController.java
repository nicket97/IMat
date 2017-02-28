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
import main.Main;
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
    private ProductViewController productViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridMain.getChildren().get(2).setOnMouseClicked(e -> productViewController.displayProducts(IMatDataHandler.getInstance().getProducts(ProductCategory.VEGETABLE_FRUIT), "Frukt & Grönt"));
        gridMain.getChildren().get(3).setOnMouseClicked(e -> productViewController.displayProducts(IMatDataHandler.getInstance().getProducts(ProductCategory.BREAD), "Bröd"));
        gridMain.getChildren().get(4).setOnMouseClicked(e -> productViewController.displayProducts(IMatDataHandler.getInstance().getProducts(ProductCategory.FLOUR_SUGAR_SALT), "Skafferi"));
        gridMain.getChildren().get(5).setOnMouseClicked(e -> productViewController.displayProducts(IMatDataHandler.getInstance().getProducts(ProductCategory.DAIRIES), "Mejeri"));
        gridMain.getChildren().get(6).setOnMouseClicked(e -> productViewController.displayProducts(IMatDataHandler.getInstance().getProducts(ProductCategory.MEAT), "Protein"));
        gridMain.getChildren().get(7).setOnMouseClicked(e -> productViewController.displayProducts(IMatDataHandler.getInstance().getProducts(ProductCategory.POD), "Kolonial"));
        addListeners();
    }

    public void injectControllers(StartpageController startpageController, CartController cartController, ProductViewController productViewController){
        this.cartController = cartController;
        this.startpageController = startpageController;
        this.productViewController = productViewController;
    }

    private void addListeners(){
        //Need animation here
        cartPane.setOnMouseClicked(e -> cartController.setVisible(!cartController.isVisible()));
        navHome.setOnMouseClicked(e -> {
            startpageController.setVisible(true);
            productViewController.setVisible(false);
        });
    }
}
