package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;


public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {allParts.add(newPart);}
    public void addProduct(Product newProduct) {allProducts.add(newProduct);}
    public Part lookupPart(int partId) {return allParts.get(partId-1);}
    public Product lookupProduct(int productId) {return allProducts.get(productId-1);}
    public ObservableList<Part> lookupPart(String partName) {
        return allParts.filtered(
                filteredPart -> filteredPart.getName().toLowerCase().contains(partName.toLowerCase())
        );
    }
    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts.filtered(
                filteredProduct -> filteredProduct.getName().toLowerCase().contains(productName.toLowerCase())
        );
    }
    public void updatePart(int index, Part selectedPart) {allParts.set(index, selectedPart);}
    public void updateProduct(int index, Product selectedProduct) {allProducts.set(index, selectedProduct);}
    public boolean deletePart(Part selectedPart) {return allParts.remove(selectedPart);}
    public boolean deleteProduct(Product selectedProduct) {return allProducts.remove(selectedProduct);}
    public ObservableList<Part> getAllParts() {return allParts;}
    public ObservableList<Product> getAllProducts() {return allProducts;}
}
