package managing;

import graphic.Graphics;
import miscellaneous.SortByArrival;
import models.Client;
import models.Server;
import scheduler.Scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Manager implements Runnable
{
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberOfServers;
    private int numberOfClients;
    private Scheduler scheduler;
    private List<Client> generatedClients = new ArrayList<>();
    private FileWriter outputWriter;
    private Graphics input;
    private ArrayList<Float> waitingValues;

    public Manager(Graphics input, FileWriter outputWriter, int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfServers, int numberOfClients)
    {
        this.input = input;
        this.outputWriter = outputWriter;
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.scheduler = new Scheduler(this.numberOfServers, this.numberOfClients);
        this.waitingValues = new ArrayList<>(numberOfClients);
        generateNRandomClients();
    }

    public float averageWaiting()
    {
        Float sum = Float.valueOf(0);
        Float numberClients = Float.valueOf(numberOfClients);
        float media;
        for (Float num : waitingValues)
            sum += num;
        media = sum.floatValue() / numberClients.floatValue();
        return media;
    }

    private void generateNRandomClients()
    {
        for(int i = 1; i <= numberOfClients; i++)
            generatedClients.add(new Client(i, (int) ((Math.random() * (this.maxArrivalTime - this.minArrivalTime)) + this.minArrivalTime), (int) ((Math.random() * (this.maxProcessingTime - this.minProcessingTime)) + this.minProcessingTime)));
        Collections.sort(generatedClients, new SortByArrival());
        printClients();
    }
    public boolean serversDone()
    {
        for(Server server : scheduler.getServers())
            if(!server.getClients().isEmpty())
                return false;
        return true;
    }

    public String printClients()
    {
        StringBuilder str = new StringBuilder();
        str.append("Clients waiting : ");
        for (Client client : generatedClients)
        {
            str.append(client.toString());
            str.append(";");
        }
        str.append("\n");
        return str.toString();
    }
    public String printServers()
    {
        StringBuilder str = new StringBuilder();
        for(Server server : scheduler.getServers())
        {
            str.append(server.toString());
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    //aici
    public void run()
    {
        try {
            System.out.println("Se ruleaza");
            int currentTime = 0;
            while ((!generatedClients.isEmpty() || !serversDone()) && currentTime < timeLimit) {
                int finalCurrentTime = currentTime;
                ArrayList<Client> clients = new ArrayList<>(generatedClients);
                clients.stream().filter(e -> e.getArrivalTime() == finalCurrentTime).forEach(e -> {
                    Server server = scheduler.getServers().get(scheduler.getMinServer());
                    if (server.getClients().isEmpty())
                        new Thread(server).start();
                    waitingValues.add(scheduler.dispatchClient(e));

                    generatedClients.remove(e);
                });
                Thread.sleep(1000);
                String s = "Time " + currentTime + "\n" + printClients() + printServers() + "\n";
                outputWriter.write(s);
                input.setUpdateText(s);
                currentTime++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {

            outputWriter.write("Average Time: " + averageWaiting());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            outputWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("S-a scris in fisier");
    }
}