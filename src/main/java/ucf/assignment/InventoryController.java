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
        //Get selected item from table
        //Remove item from observable list
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
        //If new string not 8 chars in length
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
