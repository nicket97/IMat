package main.backend;

import com.sun.istack.internal.Nullable;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pontus on 2017-02-18.
 */
public class UserHandler {
    User currentUser;
    Customer currentCustomer;
    String iMatDir;

    public UserHandler(User cUser, Customer cCustomer, String iMatDir){
        currentCustomer = cCustomer;
        currentUser = cUser;
        this.iMatDir = iMatDir;
    }

    public User createNewUser(String email, String password){
        User newUser = new User();
        newUser.setUserName(email);
        newUser.setPassword(password);

        if(email.isEmpty() || password.isEmpty())
            return newUser;

        try {
            File users = new File(iMatDir + "/users.txt");
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

    @Nullable
    public User logIn(User user) {
        List<String> users = new ArrayList<>();

        try {
            File usersfile = new File(iMatDir + "/users.txt");
            usersfile.createNewFile();

            //This is seriously hacking. Java... sigh.
            BufferedReader reader = new BufferedReader(new FileReader(usersfile));

            String line;
            while((line = reader.readLine()) != null){
                users.add(line);
            }
        } catch (IOException e) {
            System.out.println("Couldn't find user file!");
        }

        boolean exists = false;
        for(String s : users){
            if(s.equals(user.getUserName() + " " + user.getPassword()))
                exists = true;
        }

        if(!exists)
            return null;

        currentUser.setUserName(user.getUserName());
        currentUser.setPassword(user.getPassword());

        return user;
    }

    public void logOut(){
        currentUser.setUserName("");
        currentUser.setPassword("");
    }

}
