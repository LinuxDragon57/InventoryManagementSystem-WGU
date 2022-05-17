package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Inventory;
import model.Part;

import java.io.IOException;

import static main.Main.*;

public abstract class ChangeProductInventory {

    public static final Inventory inventory = new Inventory();
    public final ObservableList<Part> allPartsList = inventory.getAllParts();
    public ObservableList<Part> associatedPartsList;

    public TextField productIDField;
    public TextField productNameField;
    public TextField productInventoryField;
    public TextField productPriceField;
    public TextField productMaxField;
    public TextField productMinField;

    public TextField searchPartField;
    public TableView<Part> allPartsTable;
    public TableColumn<Object, Object> partID;
    public TableColumn<Object, Object> partName;
    public TableColumn<Object, Object> partStock;
    public TableColumn<Object, Object> partPrice;
    public Button removeAssociatedPartBtn;

    public TableView<Part> associatedPartsTable;
    public TableColumn<Object, Object> associatedPartID;
    public TableColumn<Object, Object> associatedPartName;
    public TableColumn<Object, Object> associatedPartStock;
    public TableColumn<Object, Object> associatedPartPrice;
    public Button addAssociatedPartBtn;

    public Button saveBtn;
    public Button cancelBtn;


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
            allPartsTable.setItems(selectedParts);
        } else {
            ObservableList<Part> selectedParts = inventory.lookupPart(queryString);
            allPartsTable.setItems(selectedParts);
        }
    }

    public void addAssociatedPart() {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedPartsList.add(selectedPart);
            allPartsList.remove(selectedPart);
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

    public void removeAssociatedPart() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            allPartsList.add(selectedPart);
            associatedPartsList.remove(selectedPart);
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

    public void abort(ActionEvent actionEvent) throws IOException {returnToMainMenu(actionEvent, getClass());}

}
