package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Tyler Gautney
 * The Product class models a product object in the inventory.
 */
public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    /**
     * Constructor initializes all fields except for the list of associatedParts.
     * @param id the id to set.
     * @param name the name to set.
     * @param price the price to set.
     * @param stock the stock (current inventory) to set.
     * @param min the min (minimum inventory) to set.
     * @param max the max (maximum inventory) to set.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Append an associated part to the end of the associatedParts arrayList of observable objects.
     * @param part the part to add.
     */
    public void addAssociatedPart(Part part) {this.associatedParts.add(part);}

    /**
     * Pop a specified associated part from the associatedParts arrayList of observable objects.
     * @param selectedAssociatedPart the part to remove.
     * @return whether the part was successfully removed.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return this.associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Getter for the observable ArrayList of associatedParts.
     * @return the list of associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {return associatedParts;}
    /**
     * Getter for the Product ID.
     * @return the product's ID number.
     */
    public int getId() {return this.id;}
    /**
     * Setter for the Product ID.
     * @param id the Product ID to set.
     */
    public void setId(int id) {this.id = id;}
    /**
     * Getter for the Product name.
     * @return the product's name.
     */
    public String getName() {return this.name;}
    /**
     * Setter for the Product name.
     * @param name the product's name to set.
     */
    public void setName(String name) {this.name = name;}
    /**
     * Getter for the Product stock.
     * @return the product's stock.
     */
    public int getStock() {return this.stock;}
    /**
     * Setter for the Product stock.
     * @param stock the product's stock to set.
     */
    public void setStock(int stock) {this.stock = stock;}
    /**
     * Getter for the Product price.
     * @return the product's price.
     */
    public double getPrice() {return this.price;}
    /**
     * Setter for the Product price.
     * @param price the product's price to set.
     */
    public void setPrice(double price) {this.price = price;}
    /**
     * Getter for the Product min.
     * @return the product's minimum amount of stock allowed.
     */
    public int getMin() {return this.min;}
    /**
     * Setter for the Product min.
     * @param min the product's minimum amount of stock allowed to set.
     */
    public void setMin(int min) {this.min = min;}
    /**
     * Getter for the Product max.
     * @return the product's maximum amount of stock allowed to set.
     */
    public int getMax() {return this.max;}
    /**
     * Setter for the Product max.
     * @param max the product's maximum amount of stock allowed to set.
     */
    public void setMax(int max) {this.max = max;}

}
