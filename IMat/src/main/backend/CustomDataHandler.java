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

    public static CustomDataHandler getInstance() {
        if (instance == null)
            instance = new CustomDataHandler();

        return instance;
    }

    private CustomDataHandler() {
        super();
        displayedProducts = FXCollections.observableArrayList();

        if (!getUserHandler().isLoggedIn()) {
            getShoppingCart().getItems().clear();
        }
    }

    public UserHandler getUserHandler() {
        if (userHandler == null)
            userHandler = new UserHandler(getUser(), getCustomer(), imatDirectory());

        return userHandler;
    }

    public ObservableList<Product> getDisplayedProducts() {
        return displayedProducts;
    }
    @Override
    public List<Product> findProducts(String s){
    	List<Product> products = super.getProducts();
    	List<Product> result = new ArrayList<>();
    	for(Product p: products){
    		if(p.getName().equalsIgnoreCase(s)){
    			result.add(p);
    		}
    		
    	}
    	for(Product p: products){
    		if(p.getName().startsWith(s)){
    			if(!result.contains(p)){
    				result.add(p);
    			}
    			
    		}
    		
    	}
    	for(Product p: products){
    		String[] words = p.getName().split(" ");
    		for(String word: words){
    			if(word.equalsIgnoreCase(s)){
    				if(!result.contains(p)){
        				result.add(p);
        			}
    			}
    			
    		
    		if(word.toLowerCase().startsWith(s.toLowerCase())){
    			System.out.println(p.getName());
    			if(!result.contains(p)){
    				result.add(p);
    			}
    			
    		}
    		if(word.toLowerCase().endsWith(s.toLowerCase())){
    			if(!result.contains(p)){
    				result.add(p);
    			}
    		}
    		}
    	}
    	
    	
    	return result;
    	//return super.findProducts(s);
    }

    @Override
    public List<Product> findProducts(String s) {
        List<Product> products = super.getProducts();
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(s)) {
                result.add(p);
            }

        }
        for (Product p : products) {
            if (p.getName().startsWith(s)) {
                if (!result.contains(p)) {
                    result.add(p);
                }

            }

        }
        for (Product p : products) {
            String[] words = p.getName().split("\\s+");
            for (String word : words) {
                if (word.equalsIgnoreCase(s)) {
                    if (!result.contains(p)) {
                        result.add(p);
                    }
                }


                if (word.toLowerCase().startsWith(s.toLowerCase())) {
                    System.out.println(p.getName());
                    if (!result.contains(p)) {
                        result.add(p);
                    }

                }
                if (word.toLowerCase().endsWith(s.toLowerCase())) {
                    if (!result.contains(p)) {
                        result.add(p);
                    }
                }
            }
        }


        return result;
        //return super.findProducts(s);
    }

    @Override
    public List<Order> getOrders(){
        return super.getOrders();
    }

}
