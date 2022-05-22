package model;


/** @author Tyler Gautney
 * The InHouse class is-a Part that models an in-house part.
 */
public class InHouse extends Part {

    private int machineId;


    /**
     * Constructor that initializes all fields within itself and the super class.
     * @param id the id to set.
     * @param name the name to set.
     * @param price the price to set.
     * @param stock the stock (current inventory) to set.
     * @param min the min (minimum inventory) to set.
     * @param max the max (maximum inventory) to set.
     * @param machineId the machine ID to set.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Setter for the InHouse Part machineID.
     * @param machineId the machineId to set.
     */
    public void setMachineId(int machineId) {this.machineId = machineId;}

    /**
     * Getter for the InHouse Part machineID.
     * @return the machineId.
     */
    public int getMachineId() {return this.machineId;}
}
