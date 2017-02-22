package main.backend;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.chalmers.ait.dat215.project.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pontus on 2017-02-18.
 */
public class CustomDataHandler extends BackendWrapper {
    private static CustomDataHandler instance = null;

    private Customer currentCustomer;
    private UserHandler userHandler;

    private ObservableList<Product> displayedProducts;

    public static CustomDataHandler getInstance(){
        if(instance == null)
            instance = new CustomDataHandler();

        return instance;
    }

    private CustomDataHandler(){
        super();
        displayedProducts = FXCollections.observableArrayList();
    }

    public UserHandler getUserHandler(){
        if(userHandler == null)
            userHandler = new UserHandler(getUser(), getCustomer(), imatDirectory());

        return userHandler;
    }

    public ObservableList<Product> getDisplayedProducts(){
        return displayedProducts;
    }

}
