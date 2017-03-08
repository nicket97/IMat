package fxComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Pontus on 2017-03-05.
 */
public class SpemTextfield extends AnchorPane implements Initializable {
    @FXML protected TextField txtField;
    @FXML protected Label labelError;

    private EventHandler<? super ActionEvent> validationHandler;
    private List<EventHandler<? super ActionEvent>> validationHandlers;
    private boolean valid = true;

    public SpemTextfield() throws Exception{
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpemTextfield.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();

        validationHandlers = new ArrayList<>();
    }

    public String getErrorText(){
        return labelError.getText();
    }

    public void setErrorText(String errorText){
        labelError.setText(errorText);
    }

    public void setOnValidation(EventHandler<? super ActionEvent> value){
        validationHandlers.add(value);
    }

    public String getText(){
        return txtField.getText();
    }

    public void setText(String text){
        txtField.setText(text);
    }

    public void setPromptText(String promptText){
        txtField.setPromptText(promptText);
    }

    public String getPromptText(){
        return txtField.getPromptText();
    }

    public boolean isValid(){
        return valid;
    }

    public void setValid(boolean isValid){
        if(isValid) {
            txtField.setId(null);
        }else
            txtField.setId("txtError");

        labelError.setVisible(!isValid);

        valid = isValid;
    }

    public void setRed(boolean isRed){
        if(!isRed) {
            txtField.setId(null);
        }else
            txtField.setId("txtError");
    }

    public void clearText(){
        txtField.clear();
    }

    public TextField getTxtField(){
        return txtField;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtField.focusedProperty().addListener(x -> {
            if(!txtField.isFocused()){
                validate();
            }
        });

        labelError.setVisible(false);
    }

    public void validate(){
        for(EventHandler<? super ActionEvent> handler : validationHandlers)
            handler.handle(new ActionEvent(this, txtField));
    }
}
