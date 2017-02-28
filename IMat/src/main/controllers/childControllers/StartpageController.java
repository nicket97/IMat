/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package main.controllers.childControllers;
 import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 import java.util.ResourceBundle;
import java.util.TreeMap;

 import javafx.beans.value.ChangeListener;
 import javafx.fxml.FXML;
 import javafx.scene.Node;
 import javafx.scene.control.Label;
 import javafx.scene.image.ImageView;
 import javafx.scene.input.MouseEvent;
 import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
 import main.backend.CustomDataHandler;
 import main.backend.UserHandler;
 import main.controllers.MainController;
 import main.controllers.childControllers.User.UserController;
 import main.controllers.childControllers.navigation.NavController;
 import se.chalmers.ait.dat215.project.IMatDataHandler;
 import se.chalmers.ait.dat215.project.ProductCategory;

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
    @FXML
    private FlowPane flowPane;
    
    private boolean loginStatus = false;
    private final DataPair btn1 = new DataPair(new DataNode("Börja Handla ", "img1"), new DataNode("Börja Handla ", "img1In"));
    private final DataPair btn2 = new DataPair(new DataNode("Logga in ", "img2"), new DataNode("Historik ", "img2In"));
    private final DataPair btn3 = new DataPair(new DataNode("Sök vara ", "img3"), new DataNode("Sök vara ", "img3In"));
    private final DataPair btn4 = new DataPair(new DataNode("Registrera konto ", "img4"), new DataNode("Verktyg ", "img4In"));
    private final Map<Node, DataPair<DataNode>> btnDataMap = new HashMap(); 

    private UserController usrCtrl;
    private HistoryController historyCtrl;
    private SearchController srchCtrl;
    private NavController navController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDataMap.put(btnChoice1, btn1);
        btnDataMap.put(btnChoice2, btn2);
        btnDataMap.put(btnChoice3, btn3);
        btnDataMap.put(btnChoice4, btn4);
        
        for(int i = 0; i < 4; i++){
            Node child = flowPane.getChildren().get(i);
            child.setOnMouseEntered(event -> setBtnHover(event));
            child.setOnMouseExited(event -> setBtnDefault(event));
            child.setOnMouseClicked(event -> setClicked(event));
        }

        addListeners();
    }
    
    @Override
    public void setVisible(boolean value) {
        startpage.setVisible(value);
        startpage.setManaged(value);
       
        if(value){
            startpage.toFront();
        }

    }

    public void injectControllers(UserController userController, HistoryController hController,
                                  SearchController srchController, NavController navCtrl){
        usrCtrl = userController;
        historyCtrl = hController;
        srchCtrl = srchController;
        navController = navCtrl;
    }

    private void setLoginStatus(boolean value){
       if(loginStatus != value){
           loginStatus = value;
           updateFrameData();
       }
    }
    
    private DataNode getFrameData(Node source){
        return btnDataMap.get(source).getData();
    }

    private void setBtnHover(MouseEvent e){
        AnchorPane source = (AnchorPane) e.getSource();
        DataNode data = getFrameData(source);
        source.getChildren().get(0).setId("txtHover");
        source.getChildren().get(1).setId(data.getImg()+"Hover");
    }

    private void setBtnDefault(MouseEvent e) {
        AnchorPane source = (AnchorPane) e.getSource();
        DataNode data = getFrameData(source);
        Label txt = (Label) source.getChildren().get(0);
        Label img = (Label) source.getChildren().get(1);
        txt.setId(null);
        img.setId(data.getImg());
    }

    private void setClicked(MouseEvent e){
        AnchorPane source = (AnchorPane) e.getSource();
        Label txt = (Label) source.getChildren().get(0);


        //LÄGG TILL SKIT HÄR
        switch (txt.getText().toLowerCase().trim()){
            case "börja handla":
                navController.startShopping();
                break;
            case "historik":
                historyCtrl.setVisible(true);
                break;
            case "logga in":
                usrCtrl.setLoginVisible(true);
                break;
            case "sök vara":
                srchCtrl.focus();
                break;
            case "registrera konto":
                usrCtrl.setRegisterVisible(true);
                break;
            case "verktyg":
                System.out.println("Verktyg nu");
                //TODO
            default:
                break;
        }
    }

    private void updateFrameData() {
        for(int i = 0; i < 4; i++){
            AnchorPane child = (AnchorPane) flowPane.getChildren().get(i);
            DataNode data = getFrameData(child);
            Label txt = (Label) child.getChildren().get(0);
            Label img = (Label) child.getChildren().get(1);
            txt.setText(data.getTxt());
            img.setId(data.getImg());
        }
    }

    private void addListeners(){
        UserHandler uh = CustomDataHandler.getInstance().getUserHandler();
        uh.getLoggedInProperty().addListener((ChangeListener) -> setLoginStatus(uh.isLoggedIn()));
    }
        
    private class DataPair<D>{
        private final D left;
        private final D right;
        
        DataPair(D left, D right){
            this.left = left;
            this.right = right;
        }
        
        public D getData(){
            if(loginStatus){
                return right;
            }
            else{
                return left;
            }
        }
    }
     
     private class DataNode{
         private String txt;
         private String img;
         
         public DataNode(String txt, String img){
             this.txt = txt;
             this.img = img;
         }
         
         public String getTxt(){
             return txt;
         }
         public String getImg(){
             return img;
         }
     }
}
