package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Inventory;

import java.io.IOException;

import static main.Main.returnToMainMenu;


/**
 * @author Tyler Gautney
 * This abstract class is the parent class for modifying or adding Parts to the Inventory.
 * The views of its child classes are identical, so it offloads all view fields to this single class.
 * Because the views are identical, some methods are also shared.
 * @see AddPart Uses this abstract class to add new parts to the inventory.
 * @see ModifyPart Uses this abstract class to modify existing products in the inventory.
 */
public abstract class ChangePartInventory {

    public static final Inventory inventory = new Inventory();

    public Label partTypeLabel;
    public RadioButton inHouseRadioBtn;
    public RadioButton outsourcedRadioBtn;

    public TextField partIDField;
    public TextField partNameField;
    public TextField partInventoryField;
    public TextField partPriceField;
    public TextField partMaxField;
    public TextField partMinField;
    public TextField partTypeField;

    public Button saveBtn;
    public Button cancelBtn;

    /** Changes the Text of the partTypeLabel when the inHouseRadioBtn is selected. */
    public void setInHousePart() {partTypeLabel.setText("Machine ID");}

    /** Changes the text of the partTypeLabel whe the outsourcedRadioBtn is selected. */
    public void setOutsourcedPart() {partTypeLabel.setText("Company Name");}

    /**
     * This method takes in the action event and passes it along with the runtime class of the current object to
     * main.main.returnToMainMenu.
     * @param actionEvent the action event passed in by clicking a button. It is used by main.Main.returnToMainMenu to
     *                    recycle the current scene at the time this method was called.
     * @throws IOException if the main.Main.returnToMainMenu method throws an IOException.
     */
    public void resetScene(ActionEvent actionEvent) throws IOException {returnToMainMenu(actionEvent, getClass());}
}
