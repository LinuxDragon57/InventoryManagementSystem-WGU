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

/**
 * @author Tyler Gautney
 * This abstract class is the parent class for modifying or adding Products to the Inventory.
 * The views of its child classes are identical, so it offloads all view fields to this single class.
 * Because the views are identical, some methods are also shared.
 * @see AddProduct Uses this abstract class to add new products to the inventory.
 * @see ModifyProduct Uses this abstract class to modify existing products in the inventory.
 */
public abstract class ChangeProductInventory {

    public static final Inventory inventory = new Inventory();
    public final ObservableList<Part> allPartsList = FXCollections.observableArrayList(inventory.getAllParts());
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
            allPartsTable.setItems(selectedParts);
        } catch (NumberFormatException exception) {
            ObservableList<Part> selectedParts = inventory.lookupPart(queryString);
            allPartsTable.setItems(selectedParts);
        }
    }

    /**
     * Add an associated Part to the selected Product object.
     * If no part is selected, a dialog (from main.Main.showDialog) requests for one to be selected.
     */
    public void addAssociatedPart() {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedPartsList.add(selectedPart);
            allPartsList.remove(selectedPart);
        } else {
            showDialog("No Part Selected", "Please select a part from the table to delete.");
        }
    }

    /**
     * This method takes in the action event and passes it along with the runtime class of the current object to
     * main.main.returnToMainMenu.
     * @param actionEvent the action event passed in by clicking a button. It is used by main.Main.returnToMainMenu to 
     *                    recycle the current scene at the time this method was called.
     * @throws IOException if the main.Main.returnToMainMenu method throws an IOException.
     */
    public void resetScene(ActionEvent actionEvent) throws IOException {returnToMainMenu(actionEvent, getClass());}

}
