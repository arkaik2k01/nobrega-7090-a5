package ucf.assignment;

import javafx.collections.ObservableList;

import java.io.File;

public class FileUtil
{
    File file;
    ObservableList<Item> list;

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public ObservableList<Item> getList()
    {
        return list;
    }

    public void setList(ObservableList<Item> list)
    {
        this.list = list;
    }

    public ObservableList<Item> readFromTXT()
    {
        return null;
    }

    public ObservableList<Item> readFromJSON()
    {
        return null;
    }

    public ObservableList<Item> readFromHTML()
    {
        return null;
    }

    public boolean readToTXT()
    {
        return true;
    }

    public boolean readToJSON()
    {
        return true;
    }

    public boolean readToHTML()
    {
        return true;
    }
}
