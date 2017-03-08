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
import javafx.scene.control.ChoiceBox;
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
	    private AnchorPane panePayChoice;
	    @FXML
	    private Label labelPayMethod;
	    @FXML
	    private AnchorPane paneDebit;
	    @FXML
	    private ChoiceBox<String> cbCard;
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
            @FXML
            private Group radioGroup;
            @FXML
            private Label radioDel;
            @FXML
            private Label radioDebit;
            @FXML
            private Label radioCheck;
            @FXML
            private Label labelRest;
            
            private static final String[] DEBIT_CARDS = {"MasterCard", "Visa"};

	    private Order order = null;
	    private CustomDataHandler dataHandler;
	    private boolean ordered = false;

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
            for(Node radioBtn : radioGroup.getChildren()){
                radioBtn.setOnMouseClicked(e -> {setActive(e); showPaymentOption();});
            }
            cbCard.getItems().addAll(DEBIT_CARDS);
            cbCard.setValue("Välj korttyp");
        }
        
        public void placeOrder(){
            order = dataHandler.placeOrder(true);
            btnPay.setDisable(true);
            ordered = true;
            setVisible(false);
        }
        public Button getPayBtn(){
            return btnPay;
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

    private void showPaymentOption() {
        int index = 0;
        for(int i = 0; i < radioGroup.getChildren().size(); i++){
            if(radioGroup.getChildren().get(i).getId()==null)
            continue;
            index = i;
            panePayChoice.setVisible(true);
            break;
        }
        switch(index){
            case 0:
                paneRest.toFront();
                labelPayMethod.setText("Betala vid leverans");
                labelRest.setText("Tack, då löser vi betalningen vid leverans!");
                setPane(true);
                break;
            case 1:
                paneDebit.toFront();
                labelPayMethod.setText("Kredit-/Kontokort");
                setPane(false);
                break;
            case 2:
                paneRest.toFront();
                labelPayMethod.setText("Faktura");
                labelRest.setText("Tack, en faktura kommer att ges i samband med leverans?!");
                setPane(true);
        }
    }
    private void setPane(boolean value){
        paneRest.setVisible(value);
        paneRest.setManaged(value);
        
        paneDebit.setVisible(!value);
        paneDebit.setManaged(!value);
        
    }

    

    
}
