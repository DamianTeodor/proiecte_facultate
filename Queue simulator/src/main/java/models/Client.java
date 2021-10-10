package models;
import java.util.Comparator;

public class Client
{
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Client(int id, int arrivalTime, int serviceTime)
    {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }
    public int getServiceTime()
    {
        return serviceTime;
    }
    public void setServiceTime(int service_t)
    {
        this.serviceTime = service_t;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("(");
        str.append(id);
        str.append(",");
        str.append(arrivalTime);
        str.append(",");
        str.append(serviceTime);
        str.append(")");
        return str.toString();
    }
}
