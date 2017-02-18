package main.backend;

import se.chalmers.ait.dat215.project.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by Pontus on 2017-02-18.
 */
public class CustomDataHandler extends BackendWrapper {
    private static CustomDataHandler instance = null;

    private User currentUser = null;
    private Customer currentCustomer;

    public static CustomDataHandler getInstance(){
        if(instance == null)
            instance = new CustomDataHandler();

        return instance;
    }

    private CustomDataHandler(){
        super();
    }

    public User createNewUser(String email, String password){
        User newUser = new User();
        newUser.setUserName(email);
        newUser.setPassword(password);

        if(email.isEmpty() || password.isEmpty())
            return newUser;

        try {
            File users = new File(imatDirectory() + "/users.txt");
            users.createNewFile();

            FileWriter fw = new FileWriter(users, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            out.println(email + " " + password);
            out.flush();
        } catch (IOException e) {
            System.out.println("Couldn't find user file!");
        }

        return newUser;
    }
}
