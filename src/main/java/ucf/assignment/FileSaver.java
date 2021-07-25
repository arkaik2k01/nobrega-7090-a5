package ucf.assignment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;

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
        return true;
    }

    @Override
    public boolean readToJSON()
    {
        final boolean[] isSuccessful = {true};
        try {
            list.forEach(item -> {
                try {
                    Gson gsonItem = new GsonBuilder().setPrettyPrinting().create();
                    gsonItem.toJson(item, out);
                } catch (Exception e) {
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

    @Override
    public boolean readToTXT()
    {
        final boolean[] isSuccessful = {true};
        try {
            list.forEach(item -> {
                try {
                    out.write(item.name + "\t" + item.getSerialNumber() + "\t" + item.getPrice());
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
