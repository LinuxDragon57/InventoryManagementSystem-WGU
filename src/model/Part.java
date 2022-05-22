package model;

/**
 * @author Tyler Gautney
 * An abstract class that models a Part object in the inventory.
 * @see InHouse A Part object that represents a part made in-house.
 * @see Outsourced A Part object that represents an outsourced part.
 */
public abstract class Part {

    private int id;
    private String name;
    private int stock;
    private double price;
    private int min;
    private int max;

    /**
     * Constructor initializes all fields.
     * @param id the id to set.
     * @param name the name to set.
     * @param price the price to set.
     * @param stock the stock (current inventory) to set.
     * @param min the min (minimum inventory) to set.
     * @param max the max (maximum inventory) to set.
     */
    Part (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getter for the Part ID.
     * @return the part's ID number.
     */
    public int getId() {return id;}

    /**
     * Setter for the Part ID.
     * @param id the Part ID to set.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Getter for the Part name.
     * @return the part's name.
     */
    public String getName() {return name;}

    /**
     * Setter for the Part name.
     * @param name the part's name to set.
     */
    public void setName(String name) {this.name = name;}

    /**
     * Getter for the Part stock.
     * @return the part's stock.
     */
    public int getStock() {return stock;}

    /**
     * Setter for the Part stock.
     * @param stock the part's stock to set.
     */
    public void setStock(int stock) {this.stock = stock;}

    /**
     * Getter for the Part price.
     * @return the part's price.
     */
    public double getPrice() {return price;}

    /**
     * Setter for the Part price.
     * @param price the part's price to set.
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * Getter for the Part min.
     * @return the part's minimum amount of stock allowed.
     */
    public int getMin() {return min;}

    /**
     * Setter for the Part min.
     * @param min the part's minimum amount of stock allowed to set.
     */
    public void setMin(int min) {this.min = min;}

    /**
     * Getter for the Part max.
     * @return the part's maximum amount of stock allowed.
     */
    public int getMax() {return max;}

    /**
     * Setter for the Part max.
     * @param max the part's maximum amount of stock allowed to set.
     */
    public void setMax(int max) {this.max = max;}
}
