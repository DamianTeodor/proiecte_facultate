package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Order;

import java.util.List;

/**
 * This class manages the operations on the class Order performed on the database by the class Order
 */
public class OrderBL
{
    private OrderDAO orderDAO;

    public OrderBL()
    {
        orderDAO = new OrderDAO();
    }

    /**
     * This method creates an object of class Order and then calls the method from the OrderDAO class to insert the data into the table
     * @param orderID id of the order
     * @param clientID id of the client
     * @param productID id of the product
     * @param quantity the amount of products the client ordered
     */
    public void insert(String orderID, String clientID, String productID, int quantity)
    {
        Order order = new Order();
        order.setOrderID(orderID);
        order.setClientID(clientID);
        order.setProductID(productID);
        order.setQuantity(quantity);
        orderDAO.insert(order);
    }

    /**
     * This method calls the findAll() method from the OrderDAO class to get all the data in the Order table
     * @return a list of objects of class Order
     */
    public List<Order> findAll()
    {
        return orderDAO.findAll();
    }
}
