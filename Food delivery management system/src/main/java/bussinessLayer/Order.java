package bussinessLayer;

import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable, Comparable<Order>
{
    private int orderID;
    private int clientID;
    private String items;
    private Date date;
    private Double totalPrice;

    public Order(int orderID, int clientID, String items, Date date, Double totalPrice)
    {
        this.orderID = orderID;
        this.clientID = clientID;
        this.items = items;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public int getClientID()
    {
        return clientID;
    }

    public Date getDate()
    {
        return date;
    }
    @Override
    public int hashCode()
    {
        return orderID;
    }
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Order with id : ");
        str.append(orderID + "\n");
        str.append("Containing : \n");
        str.append(this.items);
        str.append("Total price : " + this.totalPrice + "\n");
        str.append(" for client with id : ");
        str.append(clientID + "\n");
        str.append(" on : ");
        str.append(date);
        return str.toString();
    }

    @Override
    public int compareTo(Order o)
    {
        if (this.orderID > o.getOrderID())
        {
            return 1;
        }
        else
            if (this.orderID < o.getOrderID())
            {
                return -1;
            }
            else
            {
                return 0;
            }
    }
}
