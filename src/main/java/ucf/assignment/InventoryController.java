package ucf.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryController {

    @FXML
    private TextField searchText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField serialText;

    @FXML
    private TextField priceText;

    @FXML
    private TableView<?> invTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> serialColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    void deleteItem(ActionEvent event) {

    }

    @FXML
    void editName(ActionEvent event) {

    }

    @FXML
    void editPrice(ActionEvent event) {

    }

    @FXML
    void editSerial(ActionEvent event) {

    }

    @FXML
    void searchByName(ActionEvent event) {

    }

    @FXML
    void searchBySerial(ActionEvent event) {

    }

}
