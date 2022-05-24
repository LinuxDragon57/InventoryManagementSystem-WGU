package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import model.Inventory;
import model.Part;
import model.Product;

import static main.Main.*;

/**
 * @author Tyler Gautney
 * The MainMenu class initializes the main menu view. In the main menu, users can view parts and products laid out in
 * separated table views. Further, users may use the buttons to add, modify, or delete parts. The MainMenu also allows
 * for searching for parts and products.
 */
public class MainMenu implements Initializable {

    public Button closeProgramBtn;
    public TextField searchPartField;
    public TextField searchProductField;

    public TableView<model.Part> partsTable;
    public TableColumn<Object, Object> partID;
    public TableColumn<Object, Object> partName;
    public TableColumn<Object, Object> partStock;
    public TableColumn<Object, Object> partPrice;

    public TableView<model.Product> productsTable;
    public TableColumn<Object, Object> productID;
    public TableColumn<Object, Object> productName;
    public TableColumn<Object, Object> productStock;
    public TableColumn<Object, Object> productPrice;

    public Button addPartBtn;
    public Button modifyPartBtn;
    public Button deletePartBtn;
    public Button addProductBtn;
    public Button modifyProductBtn;
    public Button deleteProductBtn;

    private static final Inventory inventory = new Inventory();


    /**
     * When the Add button is clicked underneath the Parts' table view; this method is called, and it initializes the
     * addPart view.
     * @see AddPart The scene gets passed off to the AddPart controller class.
     * @param actionEvent the action event passed in by clicking the button. This is used to recycle the current scene.
     * @throws IOException throw an IOException if javafx.fxml.FXMLLoader.load throws one.
     */
    public void addPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addPart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(new Scene(root, 800, 925));
        stage.show();
    }

    /**
     * When the Add button is clicked underneath the Products' table view; this method is called, and it initializes the
     * addProduct view.
     * @see AddProduct The scene gets passed off to the AddProduct controller class.
     * @param actionEvent the action event passed in by clicking the button. This is used to recycle the current scene.
     * @throws IOException throw an IOException if javafx.fxml.FXMLLoader.load throws one.
     */
    public void addProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addProduct.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(new Scene(root,1300,925));
        stage.show();
    }

    /**
     * When the Modify button is clicked underneath the Parts' table view; this method is called, and it initializes the
     * modifyProduct view with data from the selected Part.
     * @see ModifyPart The scene and data get passed off to the ModifyPart controller class.
     * @param actionEvent the action event passed in by clicking the button. This is used to recycle the current scene.
     */
    public void modifyPart(ActionEvent actionEvent) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            int selectedPartIndex = inventory.getAllParts().indexOf(selectedPart);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyPart.fxml"));
                Parent root = loader.load();

                ModifyPart mp = loader.getController();
                mp.setSelectedPart(selectedPartIndex, selectedPart);

                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Modify Part");
                stage.setScene(new Scene(root, 800, 925));
                stage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            showDialog("No Part Selected", "Please select a part from the table to modify.");
        }

    }

    /**
     * When the Modify button is clicked underneath the Products' table view; this method is called; and it initializes
     * the modifyProduct view with data from the selected Product - which uses the ModifyProduct controller class.
     * @see ModifyProduct The scene and data get passed off to the ModifyProduct controller class.
     * @param actionEvent the action event passed in by clicking the button. This is used to recycle the current scene.
     */
    public void modifyProduct(ActionEvent actionEvent) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            int selectedProductIndex = inventory.getAllProducts().indexOf(selectedProduct);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyProduct.fxml"));
                Parent root = loader.load();

                ModifyProduct mp = loader.getController();
                mp.setSelectedProduct(selectedProductIndex, selectedProduct);

                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Modify Product");
                stage.setScene(new Scene(root, 1300, 925));
                stage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            showDialog("No Product Selected", "Please select a product from the table to modify.");
        }

    }


    /**
     * Gets a selected Part in the Parts' table view, and removes that Part from the allParts observable arrayList.
     * Then it checks all products in the inventory for an associated part matching this part. If it finds this part in
     * any product's associated parts list, it flips the canDelete bit to 0 (which starts out as a 1), and breaks out of the loop.
     * If no part is selected, a dialog notifies the user and requests for them to make a selection using main.Main.showDialog.
     * Otherwise, it confirms whether the user really wants to delete a part using main.Main.confirmationDialog before
     * actually deleting the part.
     */
    public void deletePart() {
        boolean canDelete = true;
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        for (Product product : inventory.getAllProducts()) {
            if (product.getAllAssociatedParts().contains(selectedPart)) {
                canDelete = false;
                break;
            }
        }

        if (selectedPart != null && canDelete) {
            boolean confirmDeletion = confirmationDialog("Delete Part?",
                    "Are you sure you really want to delete " + selectedPart.getName() + " part?");
            if (!confirmDeletion) {
                showDialog("Part Deletion Cancelled.", "No part was deleted.");
            } else {
                if (inventory.deletePart(selectedPart))
                    showDialog("Part Deleted", "Part " + selectedPart.getName() + " was successfully deleted.");
                else
                    showDialog("Part Deletion Failed", "Part " + selectedPart.getName() + "couldn't be deleted.");
            }
        } else if (!canDelete) {
            showDialog("Cannot Delete Part", "The part is associated with one or more products.");
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

    /**
     * Gets a selected Product in the Products' table view, and removes that Product from the allProducts observable arrayList.
     * If no product is selected, a dialog notifies the user and requests for them to make a selection using main.Main.showDialog.
     * Otherwise, it confirms whether the user really wants to delete a product using main.Main.confirmationDialog before
     * actually deleting the product.
     */
    public void deleteProduct() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean confirmDeletion = confirmationDialog("Delete Product?",
                    "Are you sure you really want to delete " + selectedProduct.getName() + " product?");
            if (!confirmDeletion) {
                showDialog("Product Deletion Cancelled.", "No product was deleted.");
            } else {
                if (inventory.deleteProduct(selectedProduct))
                    showDialog("Product Deleted", "Product " + selectedProduct.getName() + " was deleted.");
                else
                    showDialog("Product Deletion Failed", "Product " + selectedProduct.getName() + "couldn't be deleted.");
            }
        } else {
            showDialog("No Product Selected", "Please select a product from the table to delete.");
        }
    }

    /**
     * The searchPartField TextField takes in a String that represents either an integer, the partial name of a Part,
     * or the whole name of a Part. This method first tries to convert the String to an integer and search for a part based on
     * its partID number. If that fails; a NumberFormatException will be thrown, so it will simply search for the part
     * based on its partial or entire name.
     */
    public void searchPart() {
        String queryString = searchPartField.getText();

        try { //Try to convert the search string into an Integer Object in case it is a part ID number.
            int partIDNumber = Integer.parseInt(queryString);
            Part selectedPart = inventory.lookupPart(partIDNumber);
            ObservableList<Part> selectedParts = FXCollections.observableArrayList();
            selectedParts.add(selectedPart);
            partsTable.setItems(selectedParts);
        } catch (NumberFormatException exception) {
                ObservableList<Part> selectedParts = inventory.lookupPart(queryString);
                partsTable.setItems(selectedParts);
        }
    }

    /**
     * The searchProductField TextField takes in a String that represents either an integer, the partial name of a Product,
     * or the whole name of a Product. This method first tries to convert the String to an integer and search for a product based
     * on its productID number. If that fails; a NumberFormatException will be thrown, so it will simply search for the
     * Product based on its partial or entire name.
     */
    public void searchProduct() {
        String queryString = searchProductField.getText();

        try { //Try to convert the search string into an Integer Object in case it is a part ID number.
            int productIDNumber = Integer.parseInt(queryString);
            Product selectedProduct = inventory.lookupProduct(productIDNumber);
            ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
            selectedProducts.add(selectedProduct);
            productsTable.setItems(selectedProducts);
        } catch (NumberFormatException exception) {
            ObservableList<Product> selectedProducts = inventory.lookupProduct(queryString);
            productsTable.setItems(selectedProducts);
        }
    }

    /**
     * Calls the Platform.exit() method call from the JavaFX library to close the app.
     * @see <a href="https://openjfx.io/javadoc/18/javafx.graphics/javafx/application/Platform.html" target="_blank">
     *     javafx.application.Platform</a>
     */
    public int closeProgram() {
        Platform.exit();
        return 0;
    }

    /**
     * Initializes this controller when the FXMLLoader's load method is called.
     * @see javafx.fxml.Initializable
     * <a href="https://openjfx.io/javadoc/18/javafx.fxml/javafx/fxml/Initializable.html" target="_blank">
     *     The initialize method overrides the one in the Initializable interface.</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable(inventory.getAllParts(), partsTable, partID, partName, partStock, partPrice);
        initializeTable(inventory.getAllProducts(), productsTable, productID, productName, productStock, productPrice);
    }

}
