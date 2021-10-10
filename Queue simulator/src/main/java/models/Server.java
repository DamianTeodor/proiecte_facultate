package models;

import scheduler.Scheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable, Comparable<Server>
{
    private Scheduler scheduler;
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod = new AtomicInteger(0);
    private int number;

    public Server(Scheduler scheduler, int numberOfClients, int number)
    {
        this.scheduler = scheduler;
        this.number = number;
        this.clients = new ArrayBlockingQueue<>(numberOfClients);
    }

    public BlockingQueue<Client> getClients()
    {
        return clients;
    }

    public AtomicInteger getWaitingPeriod()
    {
        return waitingPeriod;
    }

    public void addClient(Client newClient)
    {
        if (clients.remainingCapacity() != 0)
        {
            clients.add(newClient);
            waitingPeriod.getAndAdd(newClient.getServiceTime());
        }
    }

    @Override
    //aici
    public void run()
    {
        try {
            Client cl = null;
            do {
                while (clients.peek() == null && scheduler.isEnding() == false) ;
                if(scheduler.isEnding())
                    return;
                cl = clients.peek();
                do {
                    Thread.sleep(900);
                    waitingPeriod.decrementAndGet();
                    cl.setServiceTime(cl.getServiceTime() - 1);
                }
                while (cl.getServiceTime() > 0);
                clients.poll();
            } while (!scheduler.isEnding() && waitingPeriod.get() != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public int compareTo(Server o)
    {
        if (this.waitingPeriod.get() > o.getWaitingPeriod().get())
        {
            return 1;
        }
        else if (this.waitingPeriod.get() < o.getWaitingPeriod().get())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Queue ").append(number).append(": ");
        for (Client client : clients)
        {
            str.append(client.toString());
        }
        return str.toString();
    }
}
