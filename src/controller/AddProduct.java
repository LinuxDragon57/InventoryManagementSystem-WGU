package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static main.Main.initializeTable;
import static main.Main.showDialog;


public class AddProduct extends ChangeProductInventory implements Initializable {

    private static final AtomicInteger id = new AtomicInteger(0);

    public static int getNextIDValue() {return id.incrementAndGet();}

    public void addProduct(ActionEvent actionEvent) throws IOException {
        try {
            String name = productNameField.getText();
            int stock = Integer.parseInt(productInventoryField.getText());
            double price = Double.parseDouble(productPriceField.getText());
            int max = Integer.parseInt(productMaxField.getText());
            int min = Integer.parseInt(productMinField.getText());

            if (max <= min) {
                showDialog("Invalid Input",
                        "The min number should not be greater than or equal to the max number.");
            } else {
                Product newProduct = new Product(getNextIDValue(), name, price, stock, min, max);
                for (Part part : associatedPartsList) {
                    newProduct.addAssociatedPart(part);
                }
                inventory.addProduct(newProduct);
                abort(actionEvent);
            }
        } catch (NumberFormatException exception) {
            showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        associatedPartsList = FXCollections.observableArrayList();
        initializeTable(allPartsList, allPartsTable, partID, partName, partStock, partPrice);
        initializeTable(associatedPartsList, associatedPartsTable, associatedPartID,
                associatedPartName, associatedPartStock, associatedPartPrice);
    }
}
