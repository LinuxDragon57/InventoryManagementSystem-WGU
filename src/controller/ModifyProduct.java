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

public class ModifyProduct extends ChangeProductInventory implements Initializable {

    private Product selectedProduct = null;
    private int selectedProductIndex;

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

    public void modifyProduct(ActionEvent actionEvent) throws IOException {
        if (Integer.parseInt(productMinField.getText()) >= Integer.parseInt(productMaxField.getText())) {
            showDialog("Invalid Input",
                    "The min number should not be greater than or equal to the max number.");
        } else {
            try {
                selectedProduct.setName(productNameField.getText());
                selectedProduct.setStock(Integer.parseInt(productInventoryField.getText()));
                selectedProduct.setPrice(Double.parseDouble(productPriceField.getText()));
                selectedProduct.setMax(Integer.parseInt(productMaxField.getText()));
                selectedProduct.setMin(Integer.parseInt(productMinField.getText()));
                inventory.updateProduct(selectedProductIndex, selectedProduct);
                abort(actionEvent);
            } catch (NumberFormatException exception) {
                showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
            }
        }
    }

    @Override
    public void removeAssociatedPart() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            allPartsList.add(selectedPart);
            selectedProduct.deleteAssociatedPart(selectedPart);
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
