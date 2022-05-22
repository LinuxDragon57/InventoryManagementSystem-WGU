package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/** @author Tyler Gautney
 * This class is the application factory for the Inventory Management System JavaFX application.
 * The Application factory also houses several static methods for use application-wide.
 * @see <a href="https://openjfx.io/javadoc/18/">JavaFX Documentation</a>
 */
public class Main extends Application {

    /**
     * This method is called by JavaFXML, and it initializes and starts the GUI.
     * @see controller.MainMenu This local method hands off to the MainMenu class.
     * @param primaryStage initialize the initial scene object.
     * @throws IOException throw an IOException if javafx.fxml.FXMLLoader.load throws one.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 1300, 925));
        primaryStage.show();
    }

    /**This is the main method.
     * This is the first method that gets called when the JavaFX application is launched.
     * When it calls javafx.application.Application.launch, the application is officially launched.
     * Any code run before this call will be executed before the JavaFX GUI starts up.
     * @param args launch the application with a variable number of method arguments.
     */
    public static void main(String[] args) {
        //controller.AddProduct.populateTestProducts();
        //controller.AddPart.populateTestParts();
        launch(args);
    }

    /**
     * Static method that allows any method to easily set up a table view.
     * @param <T> This strong safely-typed static method accepts a Generic of either a Part or a Product object for use
     *           with the generic parameters within javafx.collections.ObservableList and javafx.control.Control.TableView.
     * @param observableList The observable list of objects used within the tableview.
     * @param tableView Use the table view that needs to be populated with data.
     * @param id set the id column within the table view
     * @param name set the name column within the table view
     * @param stock set the stock column within the tableview
     * @param price set the price column within the tableview
     */
    public static <T> void initializeTable(ObservableList<T> observableList, TableView<T> tableView,
                                           TableColumn<Object, Object> id, TableColumn<Object, Object> name,
                                           TableColumn<Object, Object> stock, TableColumn<Object, Object> price) {

        tableView.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This static method allows any method to easily show a dialog.
     * @param title set the title of the dialog window.
     * @param labelTxt set the label of the dialog window.
     */
    public static void showDialog(String title, String labelTxt) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        ButtonType btn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(labelTxt);
        dialog.getDialogPane().getButtonTypes().add(btn);
        dialog.showAndWait();
    }

    /**
     * This static method allows any method within the application to easily prompt for confirmation of an action.
     * @param title set the title of the confirmation dialog.
     * @param headerTxt set the header of the confirmation dialog.
     * @return whether the user confirmed or canceled the action.
     */
    public static boolean confirmationDialog(String title, String headerTxt) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerTxt);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * This static method allows any method to call for a return to the main menu.
     * @param actionEvent The event handler of the button that called this method.
     * @param controllerClass Tell the static method what class you came from.
     * @throws IOException Throw an IOException if javafx.fxml.FXMLLoader.load throws one.
     */
    public static void returnToMainMenu(ActionEvent actionEvent, Class<?> controllerClass) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(controllerClass.getResource("/view/mainMenu.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 1300, 925));
        stage.show();
    }
}
