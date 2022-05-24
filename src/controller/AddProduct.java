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

/**
 * @author Tyler Gautney
 * This class uses the addProduct FXML view to add a new product to the inventory.
 */
public class AddProduct extends ChangeProductInventory implements Initializable {

    private static final AtomicInteger id = new AtomicInteger(0);

    /**
     * Auto-increments the ProductID values based upon a static AtomicInteger.
     * @return the next productID increment.
     */
    private static int getNextIDValue() {return id.incrementAndGet();}

    /**
     * Get the values from the TextFields defined in the parent class and create a new product. Ensure that min is less
     * than max and create a new Product object. Afterwards, go through the associatedParts list and add it to the new
     * Product object's associatedParts observable ArrayList. Finally, add the product to the inventory and reset.
     * @param actionEvent the action event passed in by clicking the button. It is used by resetScene.
     * @throws IOException if resetScene throws an IOException.
     */
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
            } else if (stock > max || stock < min) {
                showDialog("Invalid Input",
                        "The stock should be a number between the minimum and the maximum.");
            } else {
                Product newProduct = new Product(getNextIDValue(), name, price, stock, min, max);
                for (Part part : associatedPartsList) {
                    newProduct.addAssociatedPart(part);
                }
                inventory.addProduct(newProduct);
                resetScene(actionEvent);
            }
        } catch (NumberFormatException exception) {
            showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
        }
    }

    /**
     * Remove an associated Part from the selected Product object.
     * If no part is selected, a dialog (from main.Main.showDialog) requests for one to be selected.
     */
    public void removeAssociatedPart() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            allPartsList.add(selectedPart);
            associatedPartsList.remove(selectedPart);
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }


    /**
     * Initializes this controller when called by FXMLLoader's load method.
     * @see javafx.fxml.Initializable
     * <a href="https://openjfx.io/javadoc/18/javafx.fxml/javafx/fxml/Initializable.html" target="_blank">
     *     The intialize method overrides the one in the Initializable interface.</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        associatedPartsList = FXCollections.observableArrayList();
        initializeTable(allPartsList, allPartsTable, partID, partName, partStock, partPrice);
        initializeTable(associatedPartsList, associatedPartsTable, associatedPartID,
                associatedPartName, associatedPartStock, associatedPartPrice);
    }

    /*
    public static void populateTestProducts() {

        Product [] products = {
                        new Product(getNextIDValue(), "Thelio", 1299.00, 0, 0, 0),
                        new Product(getNextIDValue(), "Thelio Mira", 1499.00, 0, 0, 0),
                        new Product(getNextIDValue(), "Thelio Major", 2699.00, 0, 0, 0),
                        new Product(getNextIDValue(), "Thelio Mega", 5299.00, 0, 0, 0),
                        new Product(getNextIDValue(), "Thelio Massive", 2999.00, 0, 0, 0)
                };

        for (Product product : products) {inventory.addProduct(product);}
    }
     **/
}
