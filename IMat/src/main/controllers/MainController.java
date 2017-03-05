/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.*;
import main.controllers.childControllers.User.MyPagesController;
import main.controllers.childControllers.User.UserController;
import main.controllers.childControllers.User.UserStatusController;
import main.controllers.childControllers.navigation.BottomBarController;
import main.controllers.childControllers.navigation.NavController;

/**
 *
 * @author Felix
 */
public class MainController implements Initializable{

    @FXML
    private UserController userController;
    @FXML
    private UserStatusController userStatusController;
    @FXML
    private CenterstageController centerstageController;
    @FXML
    private NavController navController;
    @FXML
    private CartController cartController;
    @FXML
    private SearchController searchController;
    @FXML
    private BottomBarController bottomBarController;
    @FXML
    private MyPagesController myPagesController;

    @FXML
    StackPane mainPane;
    
    @FXML StackPane anchorUser;

    private CustomDataHandler dataHandler;
    @FXML
    private Pane leftBorder;
    @FXML
    private Pane rightBorder;
    @FXML
    private AnchorPane centerStage;
    @FXML
    private Pane topBorder;
    @FXML
    private Pane bottomBorder;
    @FXML
    private Pane Mallar;
    @FXML
    private GridPane DONOTREMOVE;
    @FXML
    private HBox paneLogin;
    @FXML
    private HBox panePages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = CustomDataHandler.getInstance();

        //Give controllers their stuff
        injectControllers();
        addListeners();
    }

    public void shutdown(WindowEvent e){
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Bekräfta");
        closeAlert.setHeaderText("Vill du verkligen avsluta?");

        closeAlert.getButtonTypes().clear();
        ButtonType btnYes = new ButtonType("Ja", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("Nej", ButtonBar.ButtonData.NO);

        closeAlert.getButtonTypes().addAll(btnYes, btnNo);

        Optional<ButtonType> result = closeAlert.showAndWait();
        if(result.get() == btnNo){
            e.consume();
            return;
        }

        //Automatic log out
        dataHandler.getUserHandler().logOut();
        dataHandler.shutDown();
    }

    private void addListeners(){
        anchorUser.setOnMouseClicked(e -> {
            if(false) {
                userController.setLoginVisible(false);
                userController.setRegisterVisible(false);
            }
        });
        mainPane.setOnMouseClicked(e -> {
        	System.out.println(e.getTarget().toString());
        	if(e.getTarget().equals(paneLogin)){
        	userController.setLoginVisible(false);
            userController.setRegisterVisible(false);}
        	if(e.getTarget().equals(panePages)){
        		myPagesController.close();
        	}
        });
    }

    private void injectControllers(){
        userController.setParentPane(anchorUser);
        myPagesController.setParentPane(anchorUser);

        userStatusController.injectControllers(userController);

        navController.injectControllers(centerstageController.getStartpageController(), cartController,
                centerstageController.getProductViewController(), centerstageController.getCheckoutController(), bottomBarController, searchController);

        centerstageController.getStartpageController().
                injectControllers(userController, centerstageController.getHistoryController(), searchController,
                        navController);
        
        cartController.injectControllers(navController);
        userController.injectControllers(myPagesController);
    }

}
