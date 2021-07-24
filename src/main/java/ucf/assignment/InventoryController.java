package ucf.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.math.BigDecimal;

public class InventoryController {

    //Buttons
    @FXML
    private TextField searchText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField serialText;

    @FXML
    private TextField priceText;

    //Table & List
    private ObservableList<Item> invData = FXCollections.observableArrayList();

    @FXML
    private TableView<Item> invTable;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TableColumn<Item, String> serialColumn;

    @FXML
    private TableColumn<Item, BigDecimal> priceColumn;

    //File chooser
    FileChooser filer = new FileChooser();

    public void initialize()
    {
        //Table initializer
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("price"));
        invTable.setItems(invData);

        //File chooser initializer
        filer.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TSV", "*.txt"),
                new FileChooser.ExtensionFilter("HTML", "*.html"),
                new FileChooser.ExtensionFilter("JSON", "*.json"));
    }

    void throwSerialError()
    {
        nameText.clear();
        serialText.clear();
        priceText.clear();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("NON VALID SERIAL");
        alert.setContentText("Your serial number is invalid. Please refer to the readme");
        alert.showAndWait();
    }

    boolean checkIfSerialNotValid(String serial)
    {
        final boolean[] isNotValid = {false};

        if(serial.length() != 10)
        {
            throwSerialError();
            isNotValid[0] = true;
        }
        //For each item in invData, check if serial is equals to item's serial number
        invData.forEach(item -> {
            if(item.getSerialNumber().equalsIgnoreCase(serial))
            {
                throwSerialError();
                isNotValid[0] = true;
            }
        });

        return isNotValid[0];
    }

    //GUI Actions
    @FXML
    void newItem(ActionEvent actionEvent)
    {
        //Get text from nameText
        String name = nameText.getText();
        //Get text from serialText
        String serial = serialText.getText();
        //Check text length == 10 and not repeated, if not return
        if(checkIfSerialNotValid(serial))
        {
            return;
        }
        //Get number from priceText
        BigDecimal price = new BigDecimal(priceText.getText());
        //Create item with given information
        Item newItem = new Item(name, serial, price);
        //Add to list
        invData.add(newItem);
    }

    @FXML
    void deleteItem(ActionEvent event) {
        //Get selected item from table
        Item toRemove = invTable.getFocusModel().getFocusedItem();
        //Remove item from observable list
        invData.remove(toRemove);
    }

    @FXML
    void editName(ActionEvent event) {
        //Save previous name to String
        //Get new string from table
        //If greater than 256 or less than 2 chars
            //return
        //Set item name to new string
    }

    @FXML
    void editPrice(ActionEvent event) {
        //Save previous price to BigDecimal vars
        //Get new price from table
        //If not a number
            //return
        //Set item price to new price
    }

    @FXML
    void editSerial(ActionEvent event) {
        //SAve previous serial to String vars
        //Get new string from table
        //If new string not 10 chars in length
            //return
        //iterate through tableView
            //if serial number to change == existing serial number
                //return
        //Set item serial to new serial
    }

    @FXML
    void searchByName(ActionEvent event) {
        //Get name to search from text field
        //iterate through tableview
            //If name equals Item from list
                //update table view and scroll to item
        //Send alert "No item with name found
    }

    @FXML
    void searchBySerial(ActionEvent event) {
        //Get serial to search from text field
        //iterate through tableview
            //If serial equals Item from list
                //update table view and scroll to item
        //Send alert "No item with serial found"
    }
}
