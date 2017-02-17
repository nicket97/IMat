/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Felix
 */

public class SpinBox extends Pane implements Initializable {
    @FXML
    private TextField txtVal;
    @FXML
    private StackPane incPane;
    @FXML
    private StackPane decPane;
    @FXML
    private StackPane addPane;
    
    public SpinBox(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpinBox.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try  {
            fxmlLoader.load();
        } catch (IOException exception)  {
            throw new RuntimeException(exception);
        }
        

    }
    
    @FXML
    protected void incVal(MouseEvent e){
        
    }
    
    @FXML
    protected void controlCheck(MouseEvent event){
        System.out.println("Spinner clicked");
    }

    @FXML
    private void addToCart(MouseEvent event) {
        System.out.print("Woho");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtVal.setText("1");
        incPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> controlCheck(event));
        
    }
}
