package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.childControllers.Controllable;
import main.controllers.MainController;

/**
 *
 * @author Felix
 */
public class Main extends Application {

    public static final int NUMBER_OF_CATEGORIES = 7;

    private static MainController mCtrl;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	System.out.println(System.getProperty("user.home"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1920, 1080);
        
        primaryStage.setTitle("iMat");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1280);
        primaryStage.setMaximized(true);
        
        
        mCtrl = loader.getController();

        //Add listener for closing the stage
        primaryStage.setOnCloseRequest(windowEvent -> loader.<MainController>getController().shutdown(windowEvent));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        launch(args);
    }

    public static void requestStartpage(){
        //The most terrible way of solving problems ever :D
        mCtrl.requestStartPage();
    }
}
