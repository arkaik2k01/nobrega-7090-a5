package ucf.assignment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item
{
    String name;
    String serialNumber;
    String price;

    public Item(String name, String serialNumber, BigDecimal price)
    {
        this.name = name;
        this.serialNumber = serialNumber;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
