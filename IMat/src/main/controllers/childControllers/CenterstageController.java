/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers.childControllers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Felix
 */
public class CenterstageController implements Controllable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setVisible(boolean value) {

    }

    @Override
    public void testMe() {
        System.out.println("Woho!");
    }
}