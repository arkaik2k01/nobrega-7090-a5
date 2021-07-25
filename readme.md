#Inventory App
-This app is an inventory app that allows you to manage items in an inventory, allowing you to add, delete, edit items, sort and search.

    To add an item:
        -Type a name for your item between 2 and 256 characters in the "Name..." text field
        -Type a serial number for your item in the "Serial..." text field. Serial number has to be 10
        characters in length and only be alphanumeric characters.
        -Type a price for your item in the "Price..." text field
        -When done, click on the "New..." button

    To delete an item:
        -Click the item to delete
        -Click the "Delete Selected..." button

    To edit an item:
        -Double click the desired field to edit
        -Make your changes
        -Once done, commit by clicking out of the text field, or by pressing the TAB or ENTER key
            -Edits have to abide by the same rules as when adding a new Item

    To sort:
        -Click on the column that you desire to sort by
        -Continue clicking to sort by ascending, descending or stop sorting

    To search:
        -Type the desired name or serial number to search for
        -Click on the "Search by..." drop down and click the respective search mode

-This app also allows you to save and load previous inventory. The app supports save & load from *.txt (to load must be tab separated values), *.html (to load must be in tabloid format) and *.json

    To save an inventory:
        -Click on the "File" menu, click on "Save..."
        -Type a name for the file
        -Choose the desired file extension in the drop down menu
        -Click save
    
    To load an inventory:
        -Click on the "File" menu, click on "Load..."
        -Select file to load
        -Click open