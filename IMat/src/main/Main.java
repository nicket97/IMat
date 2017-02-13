package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
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

    public static String[] childControllerNames = {
            "Cart",
            "Centerstage",
            "Nav",
            "Sidebar",
            "Startpage"
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controllable[] childControllers = new Controllable[childControllerNames.length];

        //Ett äkta fulhack
        //Get the child controllers
        for(int i = 0; i < childControllerNames.length; i++ ) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/" + childControllerNames[i] + ".fxml"));
            loader.load();
            childControllers[i] = loader.getController();
        }

        //Create our main controller
        MainController mainController = new MainController(childControllers);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Main.fxml"));

        //Set the main controller programmatically
        loader.setController(mainController);
        Parent root = loader.load();

        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("iMat");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
