package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Inventory;

import java.io.IOException;

import static main.Main.returnToMainMenu;

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

    public void setInHousePart() {partTypeLabel.setText("Machine ID");}
    public void setOutsourcedPart() {partTypeLabel.setText("Company Name");}
    public void abort(ActionEvent actionEvent) throws IOException {returnToMainMenu(actionEvent, getClass());}
}
