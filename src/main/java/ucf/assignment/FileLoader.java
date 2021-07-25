package ucf.assignment;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
        return null;
    }
}
