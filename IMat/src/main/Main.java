package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("iMat");
        primaryStage.setScene(scene);
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
}
