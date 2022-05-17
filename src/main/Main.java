package main;

import controller.AddPart;
import controller.AddProduct;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 1300, 925));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static <T> void initializeTable(ObservableList<T> observableList, TableView<T> tableView,
                                           TableColumn<Object, Object> id, TableColumn<Object, Object> name,
                                           TableColumn<Object, Object> stock, TableColumn<Object, Object> price) {

        tableView.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public static void showDialog(String title, String labelTxt) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        ButtonType btn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(labelTxt);
        dialog.getDialogPane().getButtonTypes().add(btn);
        dialog.showAndWait();
    }

    public static boolean confirmationDialog(String title, String headerTxt) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerTxt);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void returnToMainMenu(ActionEvent actionEvent, Class<?> controllerClass) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(controllerClass.getResource("/view/mainMenu.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 1300, 925));
        stage.show();
    }
}
