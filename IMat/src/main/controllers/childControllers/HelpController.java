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
import javafx.scene.text.TextFlow;

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
    private Label sidebarNavigation, sidebarKontakt;
    @FXML
    private TextFlow flowNavigation, flowKontakt;
    @FXML
    private Text textNavigation, textKontakt;

    
    StackPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnClose.setOnAction( e-> close());
        buttonBack.setOnAction( e-> close());
        sidebarNavigation.setOnMouseClicked(e -> initText());
        initText();
    }

    public void setVisible(boolean value) {
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

    public void initText(){
        textNavigation.setText("\nNavigera mellan olika kategorier av varor genom navigationspanelen längst upp på sidan. Du kan även röra dig genom hela butiken steg för steg genom att trycka på nästa-knappen längst ned till höger på sidan.\n" +
                "När du valt en kategori kan du navigera genom underkategorier I panelen till vänster eller bara scrolla genom kategorin för att få en överblick över allt som finns.\n" +
                "Använd sökrutan längst upp på sidan för att söka på en sorts vara du letar efter.\n" +
                "När du hittat en vara du vill köpa justerar du hur stor mängd av varan du vill ha med hjälp av plusknappen och minusknappen under bilden på varan. När du är nöjd lägger du till den I varukorgen genom att klicka på varukorgsikonen.");
        textKontakt.setText("\nHar du några synpunkter eller frågor om något vill vi jättegärna att du kontaktar oss. Skriv till oss på imatsupport@dat216.se.");
    }
}
