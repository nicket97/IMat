package main.controllers.childControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by lumo on 06/03/17.
 */
public class HelpController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button buttonBack;
    @FXML
    private AnchorPane help;
    @FXML
    private Label labelKontakt;
    @FXML
    private Text textHelp;
    @FXML
    private BorderPane navPane;
    @FXML
    private Label labelHeader;
    @FXML
    private VBox listBox;
    @FXML
    private Label navUsr;
    
    StackPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setHelpVisible(false);
        System.out.println("Help initialize");
        btnClose.setOnAction( e-> close());
        buttonBack.setOnAction( e-> close());
    }

    public void setVisible(boolean value) {
        System.out.println("Help visibility enable");
        help.setVisible(value);
        help.setManaged(value);
        
        if(value){
            parent.setVisible(value);
            parent.setManaged(value);
            help.getParent().getParent().toFront();
        }
        else
            help.getParent().getParent().toBack();
        
    }
    public void close(){
        parent.setVisible(false);
        parent.setManaged(false);
        setVisible(false);
    }
    public void setParentPane(StackPane anchorUser) {
        parent = anchorUser;
    }
}
