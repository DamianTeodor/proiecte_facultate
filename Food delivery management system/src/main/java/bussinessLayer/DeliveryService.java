package bussinessLayer;

import bussinessLayer.domain.products.BaseProduct;
import bussinessLayer.domain.products.CompositeProduct;
import bussinessLayer.domain.products.MenuItem;
import bussinessLayer.domain.observer.Observable;


import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable
{

    private final Set<MenuItem> menu = new HashSet<>();
    private final Map<Order, List<MenuItem>> orders = new HashMap<>();
    private final OrderIdGenerator orderIdGenerator = new OrderIdGenerator();

    public DeliveryService()
    {
        if (orders.isEmpty())
        {
            orderIdGenerator.setId(0);
            System.out.println("e gol");
        }
        else
        {
            for (Order order : orders.keySet())
            {
                System.out.println(order.toString());
            }
            System.out.println(orders.keySet().stream().max(Comparator.naturalOrder()).get().getOrderID());
        }
    }

    @Override
    public void getProducts()
    {
        try
        {
            Files.lines(Paths.get("products.csv")).skip(1).distinct().forEach((line) ->
            {
                BaseProduct baseItem = new BaseProduct();
                String[] lines = line.split(",");
                baseItem.setName(lines[0]);
                baseItem.setRating(Double.parseDouble(lines[1]));
                baseItem.setCalories(Integer.parseInt(lines[2]));
                baseItem.setProteins(Integer.parseInt(lines[3]));
                baseItem.setFats(Integer.parseInt(lines[4]));
                baseItem.setSodium(Integer.parseInt(lines[5]));
                baseItem.setPrice(Integer.parseInt(lines[6]));
                menu.add(baseItem);
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        this.notifyAllObservers();
    }

    @Override
    public void addItem(MenuItem menuItem)
    {
        menu.add(menuItem);
        this.notifyAllObservers();
    }
    @Override
    public void deleteItem(MenuItem menuItem)
    {
        menu.remove(menuItem);
        this.notifyAllObservers();
    }
    @Override
    public void editItem(MenuItem oldItem, MenuItem menuItem)
    {
        deleteItem(oldItem);
        addItem(menuItem);
        this.notifyAllObservers();
    }

    @Override
    public Order createOrder (int clientID, List<MenuItem> items, Double totalPrice)
    {
        Date date = new Date();
        StringBuilder itemsString = new StringBuilder();

        for (MenuItem menuItem : items)
        {
            itemsString.append(menuItem.toString() + "\n");
        }

        Order newOrder = new Order(this.orderIdGenerator.getId(),clientID, itemsString.toString(), date, totalPrice);
        this.orders.put(newOrder, items);
        //System.out.println(newOrder.toString());
        this.createBill(newOrder);
        this.notifyAllObservers();
        return newOrder;
    }
    @Override
    public List<MenuItem> filterItems(String name,
                                           Double minRating, Double maxRating,
                                           Double minCalories, Double maxCalories,
                                           Double minProteins, Double maxProteins,
                                           Double minFats, Double maxFats,
                                           Double minSodium, Double maxSodium,
                                           Double minPrice, Double maxPrice)
    {
        List<MenuItem> filteredItems;

        filteredItems = this.menu.stream().filter(item ->
        {
            if(item.getLowerName().contains(name.toLowerCase(Locale.ROOT)) || name.equals(""))
            {
                if(inBound(item.getRating(),minRating,maxRating))
                {
                    if(inBound(item.getCalories(),minCalories,maxCalories))
                    {
                        if(inBound(item.getProteins(),minProteins,maxProteins))
                        {
                            if(inBound(item.getFats(),minFats,maxFats))
                            {
                                if(inBound(item.getSodium(),minSodium,maxSodium))
                                {
                                    return inBound(item.getPrice(), minPrice, maxPrice);
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }).collect(Collectors.toList());

        return filteredItems;
    }

    public static Boolean inBound(double value, double min, double max){

        if(min == -1 && max == -1){
            return true;
        }
        else{
            if(min == -1){
                return value <= max;
            }
            else{
                if(max == -1){
                    return value >= min;
                }
                else{
                    return !(value < min) && !(value > max);
                }
            }
        }
    }

    private HashMap<MenuItem,Integer> getItemsFrequency(){
        HashMap<MenuItem, Integer> frequency = new HashMap<>();

        List<MenuItem> menuItems = new LinkedList<>();
        this.orders.values().forEach(menuItems::addAll);

        for(MenuItem menuItem: menuItems){
            if (!frequency.containsKey(menuItem)){
                frequency.put(menuItem, 1);
            }
            else{
                frequency.put(menuItem, frequency.get(menuItem)+1);
            }
        }

        return frequency;
    }

    @Override
    public void generateReport2(int nrOfTimes)
    {
        HashMap<MenuItem, Integer> frequency = this.getItemsFrequency();

        Set<MenuItem> filteredItems = new HashSet<>();

        Set<MenuItem> keys = frequency.keySet();

        keys.stream().forEach(item -> {
            if(frequency.get(item) >= nrOfTimes){
                filteredItems.add(item);
            }
        });

        StringBuilder newString = new StringBuilder();
        for (MenuItem menuItem : filteredItems)
        {
            newString.append(menuItem.toString() + "\n");
        }
        try
        {
            FileWriter fileWriter = new FileWriter("report2.txt");
            fileWriter.write(newString.toString());
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Integer> getClientsFrequency(){
        HashMap<Integer, Integer> frequency = new HashMap<>();

        List<Integer> clientIDs = new LinkedList<>();
        this.orders.keySet().stream().forEach(order -> {
            clientIDs.add(order.getClientID());
        });

        for(Integer clientId: clientIDs){
            if (!frequency.containsKey(clientId)){
                frequency.put(clientId, 1);
            }
            else{
                frequency.put(clientId, frequency.get(clientId)+1);
            }
        }

        return frequency;
    }

    @Override
    public void generateReport3(int nrOfTimes, int values)
    {
        Set<Order> orders = this.orders.keySet();
        HashMap<Integer, Integer> clientsFrequency = this.getClientsFrequency();

        List<Order> filteredOrders = orders.stream().filter(order -> {
                                        return (clientsFrequency.get(order.getClientID()) >= nrOfTimes) &&
                                                (order.getTotalPrice() >= values);
                                        }).collect(Collectors.toList());

        Set<Integer> filteredClients = new HashSet<>();
        filteredOrders.stream().forEach(order -> {
            filteredClients.add(order.getClientID());
        });

        StringBuilder newString = new StringBuilder();
        for (Integer clientId : filteredClients)
        {
            newString.append("Client with id : ").append(clientId).append("\n");
        }
        try
        {
            FileWriter fileWriter = new FileWriter("report3.txt");
            fileWriter.write(newString.toString());
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void generateReport4(int givenDate){

        Set<Map.Entry<Order,List<MenuItem>>> filteredOrders = this.orders.entrySet().stream()
                .filter(order ->
                    (order.getKey().getDate().getDay() == givenDate))
                .collect(Collectors.toSet());

        HashMap<MenuItem, Integer> itemsFrequency = new HashMap<>();
        filteredOrders.stream().forEach(entry -> {
            entry.getValue().stream().forEach(item -> {
                if(!itemsFrequency.containsKey(item)){
                    itemsFrequency.put(item, 1);
                }
                else{
                    itemsFrequency.put(item, itemsFrequency.get(item)+1);
                }
            });
        });

        StringBuilder newString = new StringBuilder();
        itemsFrequency.entrySet().stream().forEach(menuItemIntegerEntry ->
        {
            newString.append(menuItemIntegerEntry.getKey().toString() + ", times : " + menuItemIntegerEntry.getValue().toString() + "\n");
        });
        try
        {
            FileWriter fileWriter = new FileWriter("report4.txt");
            fileWriter.write(newString.toString());
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void generateReport1(int startHour, int endHour)
    {
        List<Order> report1Results;
        report1Results = this.orders.keySet().stream().filter(order ->
        {
            if (order.getDate().getHours() >= startHour && order.getDate().getHours() <= endHour)
            {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        StringBuilder report1String = new StringBuilder();
        for (Order order : report1Results)
        {
            report1String.append(order.toString() + "\n");
        }
        try
        {
            FileWriter fileWriter = new FileWriter("report1.txt");
            fileWriter.write(report1String.toString());
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public Map<Order, List<MenuItem>> getOrders()
    {
        return orders;
    }

    @Override
    public void createBill(Order order)
    {
        String filename = "Bill_" + order.getOrderID() + ".txt";
        try
        {
            File file = new File(filename);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(order.toString());
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void removeNulls()
    {
        int k = 0;
        for (MenuItem menuItem : menu)
        {
            if (menuItem.getName() == null)
            {
                k++;
                menu.remove(menu);
            }
        }
        //System.out.println("s-au sters : " + k + " elemente");
    }

    public Set<MenuItem> getMenu()
    {
        return menu;
    }
}
