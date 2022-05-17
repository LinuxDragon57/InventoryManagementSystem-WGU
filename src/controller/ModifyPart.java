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

public class ModifyPart extends ChangePartInventory implements Initializable {

    private Part selectedPart = null;
    private int selectedPartIndex;

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

    public void modifyPart(ActionEvent actionEvent) throws IOException {
        try {
            if (Integer.parseInt(partMinField.getText()) >= Integer.parseInt(partMaxField.getText())) {
                showDialog("Invalid Input",
                        "The min number should not be greater than or equal to the max number.");
            } else {
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
                inventory.updatePart(selectedPartIndex, selectedPart);
                abort(actionEvent);
            }
        } catch (NumberFormatException exception) {
            showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
