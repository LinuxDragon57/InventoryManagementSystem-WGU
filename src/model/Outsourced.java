package model;

/**
 * @author Tyler Gautney
 * The Outsourced class is-a Part that models an outsourced part.
 */
public class Outsourced extends Part {

    private String companyName;


    /**
     * Constructor that initializes all fields within itself and the super class.
     * @param id the id to set.
     * @param name the name to set.
     * @param price the price to set.
     * @param stock the stock (current inventory) to set.
     * @param min the min (minimum inventory) to set.
     * @param max the max (maximum inventory) to set.
     * @param companyName the company name to set.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Setter for the Outsourced Part companyName.
     * @param companyName the companyName to set.
     */
    public void setCompanyName(String companyName) {this.companyName = companyName;}

    /**
     * Getter for the Outsourced Part companyName.
     * @return the companyName.
     */
    public String getCompanyName() {return this.companyName;}
}
