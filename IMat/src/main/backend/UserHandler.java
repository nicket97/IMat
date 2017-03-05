package main.backend;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Pontus on 2017-02-18.
 */
public class UserHandler {
    User currentUser;
    Customer currentCustomer;
    String iMatDir;
    List<String> customerLines;
    String customerOrderDates;

    private BooleanProperty loggedInProperty = new SimpleBooleanProperty(false);

    public UserHandler(User cUser, Customer cCustomer, String iMatDir) {
        currentCustomer = cCustomer;
        currentUser = cUser;
        this.iMatDir = iMatDir;

        customerLines = loadCustomerLines();
        customerOrderDates = loadCustomerOrderDates(currentCustomer);
        clearCustomer(currentCustomer);
        currentUser.setUserName("");
        currentUser.setPassword("");
    }

    public User getUser() {
        return currentUser;
    }

    public Customer getCustomer() {return currentCustomer;}

    public User createNewUser(String email, String password) {
        User newUser = new User();
        newUser.setUserName(email);
        newUser.setPassword(password);

        //Empty
        if (email.isEmpty() || password.isEmpty())
            return new User(); //Return empty user = not logged in

        if (emailExists(newUser.getUserName())) {
            System.out.println("User already exists!");
            return null;
        }

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

    public User logIn(User user) {
        if (userExists(user)) {
            currentUser.setUserName(user.getUserName());
            currentUser.setPassword(user.getPassword());
            clearCustomer(currentCustomer);

            System.out.println("Logged in as: " + currentUser.getUserName());
            currentCustomer = getCustomerFromUser(currentUser);

            loggedInProperty.set(true);
            return user;
        }

        System.out.println("User not found!");
        return null;
    }

    public void logOut() {
        saveCustomer(currentCustomer);

        currentUser.setUserName("");
        currentUser.setPassword("");
        clearCustomer(currentCustomer);
        loggedInProperty.set(false);
        System.out.println("Logged out");
    }

    public boolean isLoggedIn() {
        return loggedInProperty.getValue();
    }

    public BooleanProperty getLoggedInProperty() {
        return loggedInProperty;
    }

    public String getCustomerOrderDates(){
        return customerOrderDates;
    }

    void addOrderDate(Date date){
        customerOrderDates += date.toString();
    }

    private boolean userExists(User user) {
        List<String> users = new ArrayList<>();

        try {
            File usersfile = new File(iMatDir + "/users.txt");
            usersfile.createNewFile();

            //This is seriously hacking. Java... sigh.
            BufferedReader reader = new BufferedReader(new FileReader(usersfile));

            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            System.out.println("Couldn't find user file!");
        }

        for (String s : users) {
            if (s.equals(user.getUserName() + " " + user.getPassword()))
                return true;
        }

        return false;
    }

    public boolean emailExists(String email) {
        List<String> emails = new ArrayList<>();

        try {
            File usersfile = new File(iMatDir + "/users.txt");
            usersfile.createNewFile();

            //This is seriously hacking. Java... sigh.
            BufferedReader reader = new BufferedReader(new FileReader(usersfile));

            String line;
            while ((line = reader.readLine()) != null) {
                emails.add(line);
            }
        } catch (IOException e) {
            System.out.println("Couldn't find user file!");
        }

        for (String s : emails) {
            if (s.split("\\s+")[0].equals(email))
                return true;
        }

        return false;
    }

    public void saveCurrentCustomer(){
        saveCustomer(currentCustomer);
    }

    private Customer getCustomerFromUser(User user) {
        Optional<String> maybeCustomer = customerLines.stream().filter(x -> x.contains(user.getUserName())).findFirst();
        if(maybeCustomer.isPresent()) {
            String[] properties = maybeCustomer.get().split(";");
            for(int i = 0; i < properties.length; i++){
                switch(i) {
                    case 0:
                        currentCustomer.setFirstName(properties[i]);
                        break;
                    case 1:
                        currentCustomer.setLastName(properties[i]);
                        break;
                    case 2:
                        currentCustomer.setPhoneNumber(properties[i]);
                        break;
                    case 3:
                        currentCustomer.setMobilePhoneNumber(properties[i]);
                        break;
                    case 5:
                        currentCustomer.setAddress(properties[i]);
                        break;
                    case 6:
                        currentCustomer.setPostCode(properties[i]);
                        break;
                    case 7:
                        currentCustomer.setPostAddress(properties[i]);
                        break;
                    case 8:
                        customerOrderDates = properties[i];
                }
            }
        }

        currentCustomer.setEmail(currentUser.getUserName());


        return currentCustomer;
    }

    private void clearCustomer(Customer customer) {
        customer.setAddress("");
        customer.setEmail("");
        customer.setFirstName("");
        customer.setLastName("");
        customer.setMobilePhoneNumber("");
        customer.setPhoneNumber("");
        customer.setPostAddress("");
        customer.setPostCode("");
        customerOrderDates = "";
    }

    private void saveCustomer(Customer customer) {
        if(!isLoggedIn())
            return;

        try {
            File users = new File(iMatDir + "/customers.txt");
            FileOutputStream exc = new FileOutputStream(users);
            OutputStreamWriter osw = new OutputStreamWriter(exc, "ISO-8859-1");

            String newLine = customer.getFirstName() + ";" +
                    customer.getLastName() + ";" +
                    customer.getPhoneNumber() + ";" +
                    customer.getMobilePhoneNumber() + ";" +
                    customer.getEmail() + ";" +
                    customer.getAddress() + ";" +
                    customer.getPostCode() + ";" +
                    customer.getPostAddress() + ";" +
                    customerOrderDates + ";" +
                    "end";

            boolean addNew = true;
            for (int i = 0; i < customerLines.size(); i++) {
                if (customerLines.get(i).contains(currentUser.getUserName())) {
                    customerLines.remove(i);
                    customerLines.add(newLine);
                    addNew = false;
                }
            }

            if (addNew)
                customerLines.add(newLine);

            System.out.println("saveCustomers()");

            for(String line : customerLines)
                osw.write(line + "\n");

            osw.flush();
            osw.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    private List<String> loadCustomerLines() {
        List<String> lines = new ArrayList<>();

        try {
            File customersFile = new File(iMatDir + "/customers.txt");
            customersFile.createNewFile();

            //This is seriously hacking. Java... sigh.
            BufferedReader reader = new BufferedReader(new FileReader(customersFile));

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Couldn't find user file!");
        }

        return lines;
    }

    private String loadCustomerOrderDates(Customer customer){
        String orderDates = "";
        Optional<String> maybeCustomer = customerLines.stream().filter(x -> x.contains(customer.getEmail())).findFirst();
        if(maybeCustomer.isPresent())
            orderDates = maybeCustomer.get().split(";")[8];

        return orderDates;
    }

}
