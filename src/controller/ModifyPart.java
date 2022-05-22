package controller;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.Main.showDialog;


/**
 * @author Tyler Gautney
 * This class uses the modifyPart FXML view to modify an existing part in the inventory.
 */
public class ModifyPart extends ChangePartInventory implements Initializable {

    private Part selectedPart = null;
    private int selectedPartIndex;

    /**
     * Receives data from the MainMenu controller, and uses it to modify the given Part Object.
     * It also initializes the view scene with the object's data.
     * @param selectedPartIndex sets the selectedPartIndex.
     * @param selectedPart sets the selectedPart.
     */
    public void setSelectedPart(int selectedPartIndex, Part selectedPart) {
        this.selectedPartIndex = selectedPartIndex;
        this.selectedPart = selectedPart;
        if (selectedPart instanceof Outsourced) {
            outsourcedRadioBtn.setSelected(true);
            partTypeField.setText(((Outsourced) selectedPart).getCompanyName());
            partTypeLabel.setText("Company Name");
        } else if (selectedPart instanceof InHouse) {
            inHouseRadioBtn.setSelected(true);
            partTypeField.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
            partTypeLabel.setText("Machine ID");
        }
        partIDField.setText(String.valueOf(selectedPart.getId()));
        partNameField.setText(selectedPart.getName());
        partInventoryField.setText(String.valueOf(selectedPart.getStock()));
        partPriceField.setText(String.valueOf(selectedPart.getPrice()));
        partMaxField.setText(String.valueOf(selectedPart.getMax()));
        partMinField.setText(String.valueOf(selectedPart.getMin()));
    }

    /**
     * Get the values from the TextFields defined in the parent class and use them to create a new Part object. Then,
     * delete the old Part object at its index within the model.Inventory.allParts observable ArrayList, and replace it
     * with the newly created object. Ensure that min is less than max and cast the selectedPart object to either an
     * InHouse object or an Outsourced object depending upon which radio button is selected.
     * @param actionEvent the action event passed in by clicking the button. It is used by resetScene.
     * @throws IOException if resetScene throws an IOException.
     */
    public void modifyPart(ActionEvent actionEvent) throws IOException {
        try {
                selectedPart.setName(partNameField.getText());
                selectedPart.setStock(Integer.parseInt(partInventoryField.getText()));
                selectedPart.setPrice(Double.parseDouble(partPriceField.getText()));
                selectedPart.setMax(Integer.parseInt(partMaxField.getText()));
                selectedPart.setMin(Integer.parseInt(partMinField.getText()));
                if (inHouseRadioBtn.isSelected() && selectedPart instanceof InHouse) {
                    int machineID = Integer.parseInt(partTypeField.getText());
                    ((InHouse) selectedPart).setMachineId(machineID);
                } else if (outsourcedRadioBtn.isSelected() && selectedPart instanceof Outsourced) {
                    String companyName = partTypeField.getText();
                    ((Outsourced) selectedPart).setCompanyName(companyName);
                } else {
                    throw new InstantiationError("Class instance does not match the Radio Button selected.");
                }
        } catch (NumberFormatException exception) {
            showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
        }

        if (selectedPart.getMin() >= selectedPart.getMax() &&
                (selectedPart.getStock() > selectedPart.getMax() || selectedPart.getStock() < selectedPart.getMin())) {
            showDialog("Invalid Input",
                    "The min number should not be greater than or equal to the max number.");
        } else {
            inventory.updatePart(selectedPartIndex, selectedPart);
            resetScene(actionEvent);
        }
    }

    /**
     * Initializes this controller when the FXMLLoader's load method is called.
     * @see javafx.fxml.Initializable
     * <a href="https://openjfx.io/javadoc/18/javafx.fxml/javafx/fxml/Initializable.html">
     *     The initialize method overrides the one in the Initializable interface. </a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
