package ucf.assignment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JSONList
{
    @SerializedName("Item")
    private ArrayList<Item> jsonItemList;

    public ArrayList<Item> getJsonItemList()
    {
        return jsonItemList;
    }
}
