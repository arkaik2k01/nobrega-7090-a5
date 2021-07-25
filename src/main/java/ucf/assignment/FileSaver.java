package ucf.assignment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver extends FileUtil
{
    FileWriter out;

    public FileSaver(File file, ObservableList<Item> list)
    {
        this.file = file;
        this.list = list;

        try {
            out = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean readToHTML()
    {
        //Create a html file document
        Document saveFile = buildDocument();
        //Write to file using document template
        try {
            saveFile.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Document buildDocument()
    {
        //Build html file
        Document saveFile = DocumentHelper.createDocument();
        Element html = saveFile.addElement("html");
        Element head = html.addElement("head");
        head.addElement("title").addText("Inventory");

        Element body = html.addElement("body");
        //Create table to print to HTML
        Element table = body.addElement("table");
        Element tableHeader = table.addElement("tr");
        tableHeader.addElement("th").addText("Name");
        tableHeader.addElement("th").addText("Serial Number");
        tableHeader.addElement("th").addText("Price");
        //Add each item from list to table
        for (Item item : list) {
            Element tableRow = table.addElement("tr");
            tableRow.addElement("th").addText(item.getName());
            tableRow.addElement("th").addText(item.getSerialNumber());
            tableRow.addElement("th").addText(item.getPrice());
        }
        return saveFile;
    }

    @Override
    public boolean readToJSON()
    {
        final boolean[] isSuccessful = {true};
        try {
            Gson gsonItem = new GsonBuilder().setPrettyPrinting().create();
            gsonItem.toJson(list, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            isSuccessful[0] = false;
        }

        return isSuccessful[0];
    }

    @Override
    public boolean readToTXT()
    {
        final boolean[] isSuccessful = {true};
        try {
            list.forEach(item -> {
                try {
                    out.write(item.name + "\t" + item.getSerialNumber() + "\t" + item.getPrice() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    isSuccessful[0] = false;
                }
            });
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            isSuccessful[0] = false;
        }

        return isSuccessful[0];
    }
}
