package main.controllers.childControllers;

import javafx.fxml.Initializable;

/**
 * Created by Pontus on 2017-02-13.
 */
public interface Controllable extends Initializable {

    //This is supposed to set all the controller's underlying panes' visibility
    void setVisible(boolean value);
}
