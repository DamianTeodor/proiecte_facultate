package presentationLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.Order;
import bussinessLayer.domain.observer.Observer;
import bussinessLayer.domain.products.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeView extends JFrame implements Observer
{
    DeliveryService deliveryService;

    private final JPanel mainPanel = new JPanel();
    private final JTextArea orders = new JTextArea();

    public EmployeeView(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
        orders.setEditable(false);

        mainPanel.add(orders);

        this.setTitle("Employee");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(1500,700));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateTextArea()
    {
        StringBuilder inter = new StringBuilder();
        Map<Order, List<MenuItem>> displayOrders = deliveryService.getOrders();
        for (Order order : displayOrders.keySet())
        {
            inter.append(order.toString() + "\n\n");
        }

        this.orders.setText(inter.toString());
    }

    @Override
    public void update()
    {
        this.updateTextArea();
    }
}
