package ucf.assignment;

import java.math.BigDecimal;

public class Item
{
    String name;
    String serialNumber;
    BigDecimal price;

    public Item(String name, String serialNumber, BigDecimal price)
    {
        this.name = name;
        this.serialNumber = serialNumber;
        this.price = price;
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

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
}
