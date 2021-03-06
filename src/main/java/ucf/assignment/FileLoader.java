package ucf.assignment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.lang.reflect.Type;
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
                return newList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public ObservableList<Item> readFromJSON()
    {
        ObservableList<Item> newList;
        Gson gsonItem = new Gson();
        //Read in all the json lines and append to a string
        try {
            StringBuilder jsonString = new StringBuilder();
            while (in.hasNextLine())
            {
                jsonString.append(in.nextLine());
            }

            //To prevent errors from EOF symbols, we set reader to lenient
            JsonReader reader = new JsonReader(new StringReader(jsonString.toString()));
            reader.setLenient(true);
            //Deserializer
            //Pass reader to fromJson which parses the string and deserializes it
            Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();
            ArrayList<Item> tempList = gsonItem.fromJson(reader, itemListType);
            newList = FXCollections.observableArrayList(tempList);
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
                Elements infos = row.select("th");
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
