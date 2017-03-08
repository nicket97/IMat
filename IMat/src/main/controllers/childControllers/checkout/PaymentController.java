/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers.checkout;

import java.net.URL;
import java.util.ResourceBundle;

import fxComponents.SpemTextfield;
import javafx.collections.ObservableList;
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
import main.backend.UserHandler;
import main.controllers.childControllers.Controllable;
import se.chalmers.ait.dat215.project.Order;


public class PaymentController implements Controllable {
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */

	    @FXML private AnchorPane payment;
	    @FXML private AnchorPane panePayChoice;
	    @FXML
	    private Label labelPayMethod;
	    @FXML
	    private AnchorPane paneDebit;
	    @FXML
	    private ChoiceBox<String> cbCard;
	    @FXML
	    private SpemTextfield txtCardNumber;
	    @FXML
	    private SpemTextfield txtMM;
	    @FXML
	    private SpemTextfield txtYY;
	    @FXML
	    private SpemTextfield txtCVV;
	    @FXML
	    private AnchorPane paneRest;
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

        UserHandler uh = dataHandler.getUserHandler();
        if(uh.getCustomerPayment() != null)
            selectPaymentOption(uh.getCustomerPayment().ordinal());
    }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            dataHandler = CustomDataHandler.getInstance();
            for(Node radioBtn : radioGroup.getChildren()){
                radioBtn.setOnMouseClicked(e -> selectPaymentOption(radioGroup.getChildren().indexOf(e.getSource())));
            }
            cbCard.getItems().addAll(DEBIT_CARDS);
            cbCard.setValue("Välj korttyp");
            setValidations();
        }
        
        public void placeOrder(){
            if(labelPayMethod.getText().equals("Kredit-/Kontokort")){
                txtCardNumber.validate();
                txtYY.validate();
                txtMM.validate();
                txtCVV.validate();

                if(!txtCardNumber.isValid() || !txtCVV.isValid() || !txtMM.isValid() || !txtYY.isValid() || cbCard.getSelectionModel().isEmpty())
                    return;
            }

            if(!panePayChoice.isVisible())
                return;

            order = dataHandler.placeOrder(true);
            ordered = true;
            setVisible(false);
        }

        public boolean validate(){
            return ordered;
        }

        public Order getOrder(){
            return order;
        }

    private void selectPaymentOption(int index){
        panePayChoice.setVisible(true);
        radioGroup.getChildren().get(index).setId("radioButtonChecked");
        radioGroup.getChildren().stream().filter(x -> radioGroup.getChildren().indexOf(x) != index).forEach(x -> x.setId(null));

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
                labelRest.setText("Tack, en faktura kommer att skickas hem till dig inom kort.");
                setPane(true);
        }
    }

    private void setValidations(){
        txtCardNumber.setOnValidation(x -> txtCardNumber.setValid(!txtCardNumber.getText().isEmpty() &&
        txtCardNumber.getText().matches("[0-9]+") && txtCardNumber.getText().length() == 16));

        txtCVV.setOnValidation(x -> txtCVV.setValid(!txtCVV.getText().isEmpty() &&
                txtCVV.getText().matches("[0-9]+")&& txtCVV.getText().length() == 3));

        txtMM.setOnValidation(x -> txtMM.setValid(!txtMM.getText().isEmpty() &&
                txtMM.getText().matches("[0-9]+")&& txtMM.getText().length() == 2));

        txtYY.setOnValidation(x -> txtYY.setValid(!txtYY.getText().isEmpty() &&
                txtYY.getText().matches("[0-9]+")&& txtYY.getText().length() == 2));
    }

    private void setPane(boolean value){
        paneRest.setVisible(value);
        paneRest.setManaged(value);
        
        paneDebit.setVisible(!value);
        paneDebit.setManaged(!value);
    }

    

    
}
