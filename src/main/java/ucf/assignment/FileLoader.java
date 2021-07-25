package ucf.assignment;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileLoader extends FileUtil
{
    Scanner in;

    public FileLoader(File file, ObservableList<Item> list)
    {
        this.file = file;
        this.list = list;

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Item> readFromTXT()
    {
        ObservableList<Item> newList = FXCollections.observableArrayList();
        try {
            while (in.hasNextLine()) {
                String itemRead = in.nextLine();
                String[] tsvInput = itemRead.split("\t");
                newList.add(new Item(tsvInput[0], tsvInput[1], new BigDecimal(tsvInput[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return newList;
    }

    @Override
    public ObservableList<Item> readFromJSON()
    {
        ObservableList<Item> newList;
        Gson gsonItem = new Gson();
        try {
            StringBuilder jsonString = new StringBuilder();
            while (in.hasNextLine())
            {
                jsonString.append(in.nextLine());
            }
            newList = FXCollections.observableArrayList(Arrays.asList(gsonItem.fromJson(jsonString.toString(), Item.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return newList;
    }

    @Override
    public ObservableList<Item> readFromHTML()
    {
        ObservableList<Item> list;
        //Read the HTML into a jsoup document
        Document doc = Jsoup.parse(readHTMLintoString());
        //From ArrayList created by Jsoup based on HTML doc, make a new observableArrayList
        list = FXCollections.observableArrayList(createArrayListFromHTMLDoc(doc));

        return list;
    }

    private ArrayList<Item> createArrayListFromHTMLDoc(Document doc)
    {
        ArrayList<Item> tempList = new ArrayList<>();
        for(Element table : doc.select("table"))
        {
            //Skipping the header, iterate through each row
            for(Element row : table.select("tr").subList(1, table.select("tr").size()))
            {
                Elements infos = row.select("td");
                //Get information from row and create an item with info from row
                Item newItem = new Item(infos.get(0).text(), infos.get(1).text(), new BigDecimal(infos.get(2).text()));
                //Add new item to list
                tempList.add(newItem);
            }
        }
        //Return arrayList
        return tempList;
    }

    public String readHTMLintoString()
    {
        StringBuilder toReturn = new StringBuilder();

        while (in.hasNextLine())
        {
            toReturn.append(in.nextLine());
        }
        return toReturn.toString();
    }
}
