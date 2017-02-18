package main.backend;

import se.chalmers.ait.dat215.project.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Pontus on 2017-02-18.
 */
public class CustomDataHandler extends BackendWrapper {
    private static CustomDataHandler instance = null;

    private User currentUser;
    private Customer currentCustomer;

    public static CustomDataHandler getInstance(){
        if(instance == null)
            instance = new CustomDataHandler();

        return instance;
    }

    private CustomDataHandler(){
        super();
    }

    @Override
    public User getUser(){
        return currentUser;
    }

    public User createNewUser(){
        System.out.println(imatDirectory());
        return currentUser;
    }
}
