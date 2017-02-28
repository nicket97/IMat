/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
    
    
    private ProductViewNode parent;
    private int count = 1;
    
    public SpinBox() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpinBox.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
    
    private void updateTxtVal(){
        txtVal.setText("" + count);
        if(count>1){
            decPane.setDisable(false);
        }
        else{
            decPane.setDisable(true);
        }
    }
    
   
    
    private void addToCartAnimation(){
        Node checkMark = addPane.getChildren().get(1);
        Node cart = addPane.getChildren().get(0);
        
/*        
        FadeTransition fadeCart = new FadeTransition(Duration.millis(200), cart);
        fadeCart.setFromValue(1.0);
        fadeCart.setToValue(0.0);
        
        FadeTransition fillCart = new FadeTransition(Duration.millis(1000), cart);
        fillCart.setFromValue(0.0);
        fillCart.setToValue(1.0);
  */      
        FadeTransition fillMark = new FadeTransition(Duration.millis(600), checkMark);
        fillMark.setFromValue(0.0);
        fillMark.setToValue(1.0);
        
        FadeTransition fadeMark = new FadeTransition(Duration.millis(1000), checkMark);
        fadeMark.setFromValue(1.0);
        fadeMark.setToValue(0.0);
        
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        
        SequentialTransition st = new SequentialTransition(fillMark, pause, fadeMark);
        st.setInterpolator(Interpolator.LINEAR);
        st.setOnFinished(e -> addPane.setDisable(false));
        st.play();
        
       
    }
    
    public void addListener(ProductViewNode parent){
        this.parent = parent;
    }
    private void notifyParent(){
        parent.addToCart(count);
    }
    
    @FXML
    protected void incVal(MouseEvent e){
        count++;
        updateTxtVal();
        
    }
      @FXML
    protected void decVal(MouseEvent e){
        count--;
        updateTxtVal();
    }
    
    @FXML
    protected void controlCheck(MouseEvent event){
        System.out.println("Spinner clicked");
    }

    @FXML
    private void addToCart(MouseEvent event) {
        addPane.setDisable(true);
        addToCartAnimation();
        notifyParent();
        count = 1;
        updateTxtVal();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtVal.setText(""+ count);
        incPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> incVal(event));
        decPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> decVal(event));
        decPane.setDisable(true);
        addPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addToCart(event));
        
    }
}
