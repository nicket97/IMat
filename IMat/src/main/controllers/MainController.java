/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.controllers.childControllers.*;
import main.controllers.childControllers.User.UserController;
import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 *
 * @author Felix
 */
public class MainController implements Initializable{

    @FXML private Button btnTest;

    private UserController userController;

    @FXML
    AnchorPane mainPane;

    private IMatDataHandler dataHandler;
    @FXML
    private Pane navigation;
    @FXML
    private Pane sidebar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = IMatDataHandler.getInstance();
        userController = new UserController();
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

        dataHandler.shutDown();
    }

    @FXML
    private void btnTest_onAction(ActionEvent e){
        userController.setVisible(true);
    }

    private void addListeners(){
    }

}
