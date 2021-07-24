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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import java.math.BigDecimal;
import java.util.Comparator;

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
    private TableColumn<Item, String> priceColumn;

    //File chooser
    FileChooser filer = new FileChooser();

    public void initialize()
    {
        //Table initializer
        invTable.setEditable(true);
        initializeNameColumn();
        initializeSerialColumn();
        initializePriceColumn();
        invTable.setItems(invData);

        //File chooser initializer
        filer.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TSV", "*.txt"),
                new FileChooser.ExtensionFilter("HTML", "*.html"),
                new FileChooser.ExtensionFilter("JSON", "*.json"));
    }

    //Util classes
    void initializeNameColumn() {
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        nameColumn.setOnEditCommit(
                event -> {
                    String newName = event.getNewValue();
                    if(checkIfNameNotValid(newName))
                    {
                        newName = event.getOldValue();
                    }
                    event.getTableView().getItems().get(event.getTablePosition().getRow())
                            .setName(newName);
                    invTable.refresh();
                }
        );
    }

    void initializeSerialColumn() {
        serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        serialColumn.setOnEditCommit(event -> {
            String newSerial = event.getNewValue();
            if(checkIfSerialNotValid(newSerial, invData))
            {
                newSerial = event.getOldValue();
            }
            event.getTableView().getItems().get(event.getTablePosition().getRow())
                    .setSerialNumber(newSerial);
            invTable.refresh();
        });
    }

    void initializePriceColumn() {
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        priceColumn.setOnEditCommit(event -> {
            BigDecimal newPrice = new BigDecimal(event.getNewValue());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPrice(newPrice.toString());
            invTable.refresh();
        });
        priceColumn.setComparator((o1, o2) -> {
            BigDecimal num1 = new BigDecimal(o1);
            BigDecimal num2 = new BigDecimal(o2);
            if(num1.compareTo(num2) == 0)
            {
                return 0;
            }
            else if (num1.compareTo(num2) == -1) {
                return -1;
            }
            else
            {
                return 1;
            }
        });
    }

    void throwError(String title, String desc)
    {
        nameText.clear();
        serialText.clear();
        priceText.clear();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(desc);
        alert.showAndWait();
    }

    boolean checkIfSerialNotValid(String serial, ObservableList<Item> invData)
    {
        final boolean[] isNotValid = {false};

        if(serial.length() != 10)
        {
            throwError("NON VALID SERIAL", "Your serial number is not 10 characters in length");
            isNotValid[0] = true;
        }
        //For each item in invData, check if serial is equals to item's serial number
        invData.forEach(item -> {
            if(item.getSerialNumber().equalsIgnoreCase(serial))
            {
                throwError("NON VALID SERIAL", "The serial number you have added already exists");
                isNotValid[0] = true;
            }
        });

        return isNotValid[0];
    }

    boolean checkIfNameNotValid(String name)
    {
        boolean isNotValid = false;
        if(!(name.length() >= 2 && name.length() <= 256))
        {
            throwError("NON VALID NAME", "Your name is not within the range of chars allowed for names");
            isNotValid = true;
        }
        return isNotValid;
    }

    //GUI Actions
    @FXML
    void newItem(ActionEvent actionEvent)
    {
        //Get text from nameText
        String name = nameText.getText();
        if(checkIfNameNotValid(name))
        {
            return;
        }
        //Get text from serialText
        String serial = serialText.getText();
        //Check text length == 10 and not repeated, if not return
        if(checkIfSerialNotValid(serial, invData))
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
