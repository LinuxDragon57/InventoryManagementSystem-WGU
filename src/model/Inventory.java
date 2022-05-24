package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Tyler Gautney
 * The Inventory class has-an observable ArrayList of Parts and an observable ArrayList of Products.
 * All controller classes act on the parts and products within the inventory through this class.
 */
public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     * Adds a Part to the allParts observable ArrayList of Part objects.
     * @param newPart the part to add to the list.
     */
    public void addPart(Part newPart) {allParts.add(newPart);}

    /**
     * Adds a Product to the allProducts observable ArrayList of Product objects.
     * @param newProduct the product to add to the list.
     */
    public void addProduct(Product newProduct) {allProducts.add(newProduct);}

    /**
     * Searches the allParts observable ArrayList for the part based on its Part ID number.
     * @param partId the id to search for.
     * @return the matching Part object, if any.
     */
    public Part lookupPart(int partId) {return allParts.get(partId-1);}

    /**
     * Searches the allProducts observable ArrayList for the product based on its Product ID number.
     * @param productId the id to search for.
     * @return the matching Product object, if any.
     */
    public Product lookupProduct(int productId) {return allProducts.get(productId-1);}

    /**
     * Searches the allParts observable ArrayList for a part that contains the sequence of characters in its name.
     * @param partName the String that represents the whole or partial name of the part.
     * @return the matching Part object, if any.
     */
    public ObservableList<Part> lookupPart(String partName) {
        return allParts.filtered(
                filteredPart -> filteredPart.getName().toLowerCase().contains(partName.toLowerCase())
        );
    }

    /**
     * Searches the allProducts observable ArrayList for a product that contains the sequence of characters in its name.
     * @param productName the String that represents the whole or partial name of the part.
     * @return the matching Product, if any.
     */
    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts.filtered(
                filteredProduct -> filteredProduct.getName().toLowerCase().contains(productName.toLowerCase())
        );
    }

    /**
     * Finds a part at the given index within allParts and replaces the part at that index with the selectedPart.
     * @param index the index to set.
     * @param selectedPart the new Part object chosen to replace the current Part object.
     */
    public void updatePart(int index, Part selectedPart) {allParts.set(index, selectedPart);}

    /**
     * Finds a product at the given index within allProducts and replaces the part at that index with the selectedProduct.
     * @param index the index to set.
     * @param selectedProduct the new Product object chosen to replace the current Product object.
     */
    public void updateProduct(int index, Product selectedProduct) {allProducts.set(index, selectedProduct);}

    /**
     * Removes a Part from the allParts observable ArrayList.
     * @param selectedPart the Part to remove.
     * @return whether removal was successful.
     */
    public boolean deletePart(Part selectedPart) {return allParts.remove(selectedPart);}

    /**
     * Removes a Product from the allProducts observable ArrayList.
     * @param selectedProduct the Product to remove.
     * @return whether the removal was successful.
     */
    public boolean deleteProduct(Product selectedProduct) {return allProducts.remove(selectedProduct);}

    /**
     * Getter for the allParts observable ArrayList.
     * @return the allParts observable ArrayList of Part objects.
     * @see javafx.collections.FXCollections
     * <a href="https://openjfx.io/javadoc/18/javafx.base/javafx/collections/FXCollections.html" target="_blank">
     *     JavaFX Observable Array List</a>
     */
    public ObservableList<Part> getAllParts() {return allParts;}
    /**
     * Getter for the allProducts observable ArrayList.
     * @return the allProducts observable ArrayList of Product objects.
     * @see javafx.collections.FXCollections
     * <a href="https://openjfx.io/javadoc/18/javafx.base/javafx/collections/FXCollections.html" target="_blank">
     *     JavaFX Observable Array List</a>
     */
    public ObservableList<Product> getAllProducts() {return allProducts;}
}
