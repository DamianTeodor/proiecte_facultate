package miscellaneous;

import models.Client;

import java.util.Comparator;

public class SortByArrival implements Comparator<Client>
{
    public int compare(Client c1, Client c2)
    {
        return Integer.compare(c1.getArrivalTime(), c2.getArrivalTime());
    }
}
