/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.backend.CustomDataHandler;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Order;


public class PaymentController implements Controllable {
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */

	    @FXML
	    private AnchorPane payment;
	    @FXML
	    private StackPane radioBtnDoor;
	    @FXML
	    private StackPane radioBtnDebit;
	    @FXML
	    private StackPane radioBtnMail;
	    @FXML
	    private AnchorPane panePayChoice;
	    @FXML
	    private Label labelPayMethod;
	    @FXML
	    private AnchorPane paneDebit;
	    @FXML
	    private ComboBox<?> cbCard;
	    @FXML
	    private TextField txtCardNumber;
	    @FXML
	    private TextField txtMM;
	    @FXML
	    private TextField txtYY;
	    @FXML
	    private TextField txtCVV;
	    @FXML
	    private AnchorPane paneRest;
	    @FXML
	    private Button btnPay;

	    private Order order = null;
	    private CustomDataHandler dataHandler;
	    private boolean ordered = false;
		private Group radioGroup;

	    @Override
	    public void setVisible(boolean value) {
	        payment.setVisible(value);
	        payment.setManaged(value);
	        payment.toFront();

	        //Just for testing, delete this when you want
	        btnPay.setDisable(false);
	    }

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	       dataHandler = CustomDataHandler.getInstance();
	        btnPay.setOnAction(x -> {
	            order = dataHandler.placeOrder(true);
	            btnPay.setDisable(true);
	            ordered = true;
	        });
	    }

	    public boolean validate(){
	        return ordered;
	    }

	    public Order getOrder(){
	        return order;
	    }
	    
	
    



    private void setActive(MouseEvent e) {
        Node radioBtn = (Node) e.getSource();
        clearIds();
        radioBtn.setId("radioButtonChecked");
    }

    private void clearIds() {
        for( Node radioBtn : radioGroup.getChildren()){
            radioBtn.setId(null);
        }
    }

	public Object getBtnPay() {
		return btnPay;
	}

    
}
