package ucf.assignment;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver extends FileUtil
{
    FileWriter out;

    public FileSaver(File file, ObservableList list)
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
        return true;
    }

    @Override
    public boolean readToTXT()
    {
        final boolean[] isSuccesful = {true};
        list.forEach(item -> {
            try {
                out.write(item.name+"\t"+item.getSerialNumber()+"\t"+item.getPrice());
            } catch (IOException e) {
                e.printStackTrace();
                isSuccesful[0] = false;
            }
        });
        return isSuccesful[0];
    }
}
