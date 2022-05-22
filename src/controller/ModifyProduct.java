package controller;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.Main.initializeTable;
import static main.Main.showDialog;


/**
 * @author Tyler Gautney
 * This class uses the modifyProduct FXML view to modify an existing product in the inventory.
 */
public class ModifyProduct extends ChangeProductInventory implements Initializable {

    private Product selectedProduct = null;
    private int selectedProductIndex;

    /**
     * Receives data from the MainMenu controller, and uses it to modify the given Product Object.
     * It also initializes the view scene with the object's data.
     * @param selectedProductIndex sets the selectedProductIndex.
     * @param selectedProduct sets the selectedProduct.
     */
    public void setSelectedProduct(int selectedProductIndex, Product selectedProduct) {
        this.selectedProductIndex = selectedProductIndex;
        this.selectedProduct = selectedProduct;
        associatedPartsList = selectedProduct.getAllAssociatedParts();
        productIDField.setText(String.valueOf(selectedProduct.getId()));
        productNameField.setText(selectedProduct.getName());
        productInventoryField.setText(String.valueOf(selectedProduct.getStock()));
        productPriceField.setText(String.valueOf(selectedProduct.getPrice()));
        productMaxField.setText(String.valueOf(selectedProduct.getMax()));
        productMinField.setText(String.valueOf(selectedProduct.getMin()));
        initializeTable(allPartsList, allPartsTable, partID, partName, partStock, partPrice);
        initializeTable(associatedPartsList, associatedPartsTable, associatedPartID,
                associatedPartName, associatedPartStock, associatedPartPrice);
    }

    /**
     * Get the values from the TextFields defined in the parent class and use them to create a new Product object. Then,
     * delete the old Product object at its index within the model.Inventory.allProducts observable ArrayList, and replace
     * it with the newly created object. Ensure that min is less than max.
     * @param actionEvent the action event passed in by clicking the button. It is used by resetScene.
     * @throws IOException if resetScene throws an IOException.
     */
    public void modifyProduct(ActionEvent actionEvent) throws IOException {
        try {
            selectedProduct.setName(productNameField.getText());
            selectedProduct.setStock(Integer.parseInt(productInventoryField.getText()));
            selectedProduct.setPrice(Double.parseDouble(productPriceField.getText()));
            selectedProduct.setMax(Integer.parseInt(productMaxField.getText()));
            selectedProduct.setMin(Integer.parseInt(productMinField.getText()));
        } catch (NumberFormatException exception) {
            showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
        }

        if (selectedProduct.getMin() >= selectedProduct.getMax() &&
                (selectedProduct.getStock() > selectedProduct.getMax() || selectedProduct.getStock() < selectedProduct.getMin())) {
            showDialog("Invalid Input",
                    "The min number should not be greater than or equal to the max number, and max > stock > min.");
        } else {
            inventory.updateProduct(selectedProductIndex, selectedProduct);
            resetScene(actionEvent);
        }
    }

    /**
     * Remove an associated Part from the selected Product object.
     * If no part is selected, a dialog (from main.Main.showDialog) requests for one to be selected.
     * The only reason this method exists is to use the mutator method defined in model.Product.
     */
    public void removeAssociatedPart() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            allPartsList.add(selectedPart);
            selectedProduct.deleteAssociatedPart(selectedPart);
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

    /**
     * Initializes this controller when the FXMLLoader's load method is called.
     * @see javafx.fxml.Initializable
     * <a href="https://openjfx.io/javadoc/18/javafx.fxml/javafx/fxml/Initializable.html">
     *     The initialize method overrides the one in the Initializable interface.</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
