package bussinessLayer;

import java.io.Serializable;

public class OrderIdGenerator implements Serializable
{
    private  int id;

    public OrderIdGenerator()
    {
        id = 0;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        id++;
        return id;
    }
}
