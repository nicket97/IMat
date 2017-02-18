package main.backend;

import se.chalmers.ait.dat215.project.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Pontus on 2017-02-18.
 */
public abstract class BackendWrapper {
    private static CustomDataHandler instance = null;

    private IMatDataHandler dataHandler;

    protected BackendWrapper() {
        dataHandler = IMatDataHandler.getInstance();
    }

    public User getUser() {
        return dataHandler.getUser();
    }

    public void addFavorite(int idNbr) {
        dataHandler.addFavorite(idNbr);
    }

    public void addFavorite(Product p) {
        dataHandler.addFavorite(p);
    }

    public void addProduct(Product p) {
        dataHandler.addProduct(p);
    }

    public List<Product> favorites() {
        return dataHandler.favorites();
    }

    public List<Product> findProducts(String s) {
        return dataHandler.findProducts(s);
    }

    public CreditCard getCreditCard() {
        return dataHandler.getCreditCard();
    }

    public Customer getCustomer() {
        return dataHandler.getCustomer();
    }

    public ImageIcon getImageIcon(Product p) {
        return dataHandler.getImageIcon(p);
    }

    public ImageIcon getImageIcon(Product p, Dimension d) {
        return dataHandler.getImageIcon(p, d);
    }

    public ImageIcon getImageIcon(Product p, int width, int height) {
        return dataHandler.getImageIcon(p, width, height);
    }

    public List<Order> getOrders() {
        return dataHandler.getOrders();
    }

    public Product getProduct(int idNbr) {
        return dataHandler.getProduct(idNbr);
    }

    public List<Product> getProducts() {
        return dataHandler.getProducts();
    }

    public List<Product> getProducts(ProductCategory pc) {
        return dataHandler.getProducts(pc);
    }

    public ShoppingCart getShoppingCart() {
        return dataHandler.getShoppingCart();
    }

    public boolean hasImage(Product p) {
        return dataHandler.hasImage(p);
    }

    public String imatDirectory() {
        return dataHandler.imatDirectory();
    }

    public boolean isCustomerComplete() {
        return dataHandler.isCustomerComplete();
    }

    public boolean isFavorite(Product p) {
        return dataHandler.isFavorite(p);
    }

    public boolean isFirstRun() {
        return dataHandler.isFirstRun();
    }

    public Order placeOrder() {
        return dataHandler.placeOrder();
    }

    public Order placeOrder(boolean clearShoppingCart) {
        return dataHandler.placeOrder(clearShoppingCart);
    }

    public void removeFavorite(Product p) {
        dataHandler.removeFavorite(p);
    }

    public void removeProduct(Product p) {
        dataHandler.removeProduct(p);
    }

    public void reset() {
        dataHandler.reset();
    }

    public void resetFirstRun() {
        dataHandler.resetFirstRun();
    }

    public void setProductImage(Product p, java.io.File f) {
        dataHandler.setProductImage(p, f);
    }

    public void shutDown() {
        dataHandler.shutDown();
    }
}
