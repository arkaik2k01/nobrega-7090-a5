package ucf.assignment;

import java.io.File;

public class FileLoader
{
    File file;

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
