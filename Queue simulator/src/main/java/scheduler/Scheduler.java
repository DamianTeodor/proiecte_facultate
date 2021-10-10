package scheduler;

import models.Client;
import models.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler
{
    private List<Server> servers;
    boolean ending = false;

    public Scheduler(int maxNoServers, int maxNoClients)
    {

        this.servers = new ArrayList<>(maxNoServers);
        for (int  i = 0; i < maxNoServers; i++)
        {
            servers.add(new Server(this, maxNoClients, i + 1));
        }
    }

    public int getMinServer()
    {
        Server serverMin = Collections.min(servers);
        int index = servers.indexOf(serverMin);
        return index;
    }

    public Float dispatchClient(Client c)
    {
        Float wait = (float) 0;
        int i = getMinServer();
        if (servers.get(i).getClients().isEmpty())
        {
            wait = (float) c.getServiceTime();
        }
        else
        {
            for (Client client : servers.get(i).getClients())
            {
                wait += client.getServiceTime();
            }
            wait = wait - 1 + c.getServiceTime();
        }
        servers.get(i).addClient(c);
        return wait;
    }

    public List<Server> getServers()
    {
        return servers;
    }

    public void setEnding(boolean value) {
        ending = value;
    }

    public boolean isEnding() {
        return ending;
    }
}
