package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static main.Main.showDialog;


public class AddPart extends ChangePartInventory implements Initializable {

    private static final AtomicInteger id = new AtomicInteger(0);

    public static int getNextIDValue() {return id.incrementAndGet();}

    public void addPart(ActionEvent actionEvent) throws IOException {
        try {
            String name = partNameField.getText();
            int stock = Integer.parseInt(partInventoryField.getText());
            double price = Double.parseDouble(partPriceField.getText());
            int max = Integer.parseInt(partMaxField.getText());
            int min = Integer.parseInt(partMinField.getText());

            if (max <= min) {
                showDialog("Invalid Input",
                        "The min number should not be greater than or equal to the max number.");
            } else {
                Part newPart;
                if (inHouseRadioBtn.isSelected()) {
                    int machineID = Integer.parseInt(partTypeField.getText());
                    newPart = new InHouse(getNextIDValue(), name, price, stock, min, max, machineID);
                } else {
                    String companyName = partTypeField.getText();
                    newPart = new Outsourced(getNextIDValue(), name, price, stock, min, max, companyName);
                }
                inventory.addPart(newPart);
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
