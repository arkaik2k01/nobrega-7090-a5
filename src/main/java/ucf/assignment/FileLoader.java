package ucf.assignment;

import javafx.collections.ObservableList;

import java.io.File;

public class FileLoader
{
    File file;
    ObservableList<Item> list;

    public FileLoader()
    {
        //Files are set up on controller with setFile()
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }
}
