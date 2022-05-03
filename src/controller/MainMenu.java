package controller;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;


public class MainMenu implements Initializable {
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am Initialized!");
    }

    public void addPart() {
        System.out.println("Add Part Btn Clicked.");

    }

    public void addProduct() {
        System.out.println("Add Product Btn Clicked.");

    }

    public void modifyPart() {
        System.out.println("Modify Part Btn Clicked.");

    }

    public void modifyProduct() {
        System.out.println("Modify Product Btn Clicked.");

    }

    public void deletePart() {
        System.out.println("Delete Part Btn Clicked.");

    }

    public void deleteProduct() {
        System.out.println("Delete Product Btn Clicked.");

    }

    public void searchPart() {

    }

    public void searchProduct() {
        
    }
}
