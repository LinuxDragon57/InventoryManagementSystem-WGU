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


/**
 * @author Tyler Gautney
 * This class uses the addPart FXML view to add a new part to the inventory.
 */
public class AddPart extends ChangePartInventory implements Initializable {

    private static final AtomicInteger id = new AtomicInteger(0);

    /**
     * Auto-increments PartID values based upon a static AtomicInteger.
     * @return the next partID increment.
     */
    private static int getNextIDValue() {return id.incrementAndGet();}

    /**
     * Get the values from the TextFields defined in the parent class and create a new part. Ensure that min is less than
     * max and create an InHouse or an Outsourced Part object depending upon which radio button is selected. Finally, add
     * the part to the object and reset.
     * @param actionEvent the action event passed in by clicking the button. It is used by resetScene.
     * @throws IOException if resetScene throws an IOException.
     */
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
            } else if (stock > max || stock < min) {
                showDialog("Invalid Input",
                        "The stock should be a number between the minimum and the maximum.");
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
                resetScene(actionEvent);
            }
        } catch (NumberFormatException exception) {
            showDialog("Invalid Data", "Ensure you have only entered numbers where required.");
        }
    }

    /** Initializes this controller when the FXMLLoader's load method is called.
     * @see javafx.fxml.Initializable
     * <a href="https://openjfx.io/javadoc/18/javafx.fxml/javafx/fxml/Initializable.html" target="_blank">
     *     The intialize method overrides the one in the Initializable interface.</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /*
    public static void populateTestParts() {
        InHouse [] inHouseParts = {
                        new InHouse(getNextIDValue(), "ThelioIO Daughter-board", 35.78, 63, 15, 100, 673865),
                        new InHouse(getNextIDValue(), "Thelio Chassis", 154.38, 11, 5, 30, 81809),
                        new InHouse(getNextIDValue(), "Thelio Mira Chassis", 168.35, 8, 5, 30, 81809),
                        new InHouse(getNextIDValue(), "Thelio Major Chassis", 189.88, 5, 5, 30, 81809),
                        new InHouse(getNextIDValue(), "Thelio Mega Chassis", 203.99, 6, 5, 30, 81809),
                        new InHouse(getNextIDValue(), "Thelio Massive Chassis", 219.68, 7, 5, 30, 81809),
                        new InHouse(getNextIDValue(), "Launch Keyboard", 285, 50, 10, 50, 23641),
                };
        Outsourced [] outsourcedParts = {
                        new Outsourced(getNextIDValue(), "Ryzen 5 5600X", 299.00, 4, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "Ryzen 7 5800X3D", 449.00, 8, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "Ryzen 9 5900X", 549.00, 1, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "Ryzen 9 5950X", 799.00, 5, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "Ryzen 5 5600G", 259.00, 1, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "Ryzen 7 5700G", 359.00, 1, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "Radeon RX 6600XT", 599.00, 1, 0, 10, "AMD" ),
                        new Outsourced(getNextIDValue(), "Radeon RX 6700XT", 799.00, 1, 0, 10, "AMD"),
                        new Outsourced(getNextIDValue(), "GeForce RTX 3050", 499.00, 1, 0, 10, "NVIDIA"),
                        new Outsourced(getNextIDValue(), "GeForce RTX 3060", 599.00, 1, 0, 10, "NVIDIA"),
                        new Outsourced(getNextIDValue(), "GeForce RTX 3060 TI", 799.00, 1, 0, 10, "NVIDIA"),
                        new Outsourced(getNextIDValue(), "GeForce RTX 3070", 1025.00,1, 0, 10, "NVIDIA"),
                        new Outsourced(getNextIDValue(), "450 Watt PSU", 69.95, 35, 10, 50, "Seasonic"),
                        new Outsourced(getNextIDValue(), "650 Watt PSU", 149.95, 85, 25, 150, "Seasonic"),
                        new Outsourced(getNextIDValue(), "ROG Strix B550-F Gaming Wifi 6", 209.99, 123, 25, 125, "Asus"),
                        new Outsourced(getNextIDValue(), "HyperX Fury", 209.70, 638, 100, 1000, "Kingston"),
                        new Outsourced(getNextIDValue(), "980 Evo", 109.99, 715, 100, 1000, "Samsung")
                };

        for (InHouse part : inHouseParts) {inventory.addPart(part);}
        for (Outsourced part : outsourcedParts) {inventory.addPart(part);}
    }
     **/
}
