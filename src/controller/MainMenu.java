package controller;

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
import java.util.Optional;
import java.util.ResourceBundle;

import model.Inventory;
import model.Part;
import model.Product;

import static main.Main.*;

public class MainMenu implements Initializable {

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


    public void addPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addPart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(new Scene(root, 800, 925));
        stage.show();
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addProduct.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(new Scene(root,1300,925));
        stage.show();
    }

    public void modifyPart() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            int selectedPartIndex = inventory.getAllParts().indexOf(selectedPart);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyPart.fxml"));
                Parent root = loader.load();

                ModifyPart mp = loader.getController();
                mp.setSelectedPart(selectedPartIndex, selectedPart);

                Stage stage = new Stage();
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

    public void modifyProduct() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            int selectedProductIndex = inventory.getAllProducts().indexOf(selectedProduct);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyProduct.fxml"));
                Parent root = loader.load();

                ModifyProduct mp = loader.getController();
                mp.setSelectedProduct(selectedProductIndex, selectedProduct);

                Stage stage = new Stage();
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


    public void deletePart() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
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
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

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

    public void searchPart() {
        String queryString = searchPartField.getText();
        Integer partIDNumber = null;

        try { //Try to convert the search string into an Integer Object in case it is a part ID number.
            partIDNumber = Integer.valueOf(queryString);
        } catch (NumberFormatException exception) {
            System.out.println("The Query String is not a number. Skipping conversion to an integer...");
        }

        if (partIDNumber != null) {
            Part selectedPart = inventory.lookupPart(partIDNumber);
            ObservableList<Part> selectedParts = FXCollections.observableArrayList();
            selectedParts.add(selectedPart);
            partsTable.setItems(selectedParts);
        } else {
            ObservableList<Part> selectedParts = inventory.lookupPart(queryString);
            partsTable.setItems(selectedParts);
        }
    }

    public void searchProduct() {
        String queryString = searchProductField.getText();
        Integer productIDNumber = null;

        try { //Try to convert the search string into an Integer Object in case it is a part ID number.
            productIDNumber = Integer.valueOf(queryString);
        } catch (NumberFormatException exception) {
            System.out.println("The Query String is not a number. Skipping conversion to an integer...");
        }

        if (productIDNumber != null) {
            Product selectedProduct = inventory.lookupProduct(productIDNumber);
            ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
            selectedProducts.add(selectedProduct);
            productsTable.setItems(selectedProducts);
        } else {
            ObservableList<Product> selectedProducts = inventory.lookupProduct(queryString);
            productsTable.setItems(selectedProducts);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable(inventory.getAllParts(), partsTable, partID, partName, partStock, partPrice);
        initializeTable(inventory.getAllProducts(), productsTable, productID, productName, productStock, productPrice);
    }

}
