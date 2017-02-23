/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package main.controllers.childControllers;
 import java.net.URL;
 import java.util.ResourceBundle;
 import javafx.fxml.FXML;
 import javafx.scene.Node;
 import javafx.scene.control.Label;
 import javafx.scene.image.ImageView;
 import javafx.scene.input.MouseEvent;
 import javafx.scene.layout.AnchorPane;
 import main.controllers.MainController;

/**
 *
 * @author Felix
 */
public class StartpageController implements Controllable{

    @FXML
    private AnchorPane startpage;
    @FXML
    private AnchorPane btnChoice1;
    @FXML
    private Label txt1;
    @FXML
    private Label img1;
    @FXML
    private AnchorPane btnChoice2;
    @FXML
    private Label txt2;
    @FXML
    private Label img2;
    @FXML
    private AnchorPane btnChoice3;
    @FXML
    private Label txt3;
    @FXML
    private Label img3;
    @FXML
    private AnchorPane btnChoice4;
    @FXML
    private Label txt4;
    @FXML
    private Label img4;
    @FXML
    private Label txtWelcome;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i < 4; i++){
            Node child = startpage.getChildren().get(i);
            child.setOnMouseEntered(event -> setBtnHover(event));
            child.setOnMouseExited(event -> setBtnDefault(event));

        }
    }

    @Override
    public void setVisible(boolean value) {

    }

    private void setBtnHover(MouseEvent e){
        AnchorPane source = (AnchorPane) e.getSource();
        source.getChildren().get(0).setId("txtHover");
        source.getChildren().get(1).setId("imgHover");
    }

    private void setBtnDefault(MouseEvent e) {
        AnchorPane source = (AnchorPane) e.getSource();
        source.getChildren().get(0).setId(null);
        source.getChildren().get(1).setId(null);
    }

}
