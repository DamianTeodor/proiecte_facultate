package presentationLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.domain.observer.Observer;
import bussinessLayer.domain.products.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientView extends JFrame implements Observer
{
    private Double totalPrice = (double) 0;
    private int clientID = 0;

    private DeliveryService deliveryService;
    private List<MenuItem> clientItems;
    private List<MenuItem> orderItems = new ArrayList<>();

    private final JLabel ratingLabel = new JLabel("Rating min and max :");
    private final JLabel caloriesLabel = new JLabel("Calories min and max :");
    private final JLabel proteinsLabel = new JLabel("Proteins min and max :");
    private final JLabel fatsLabel = new JLabel("Fats min and max :");
    private final JLabel sodiumLabel = new JLabel("Sodium min and max :");
    private final JLabel priceLabel = new JLabel("Price min and max :");
    private final JLabel nameLabel = new JLabel("Name :");
    private final JLabel totalPriceLabel = new JLabel("Total Price :");

    private final JButton addItemToListButton = new JButton("Add to order");
    private final JButton searchItemButton = new JButton("Search item");
    private final JButton sendOrderButton = new JButton("Send order");
    private final JButton clearlistButton = new JButton("ClearList");

    private final JComboBox<MenuItem> itemsCB = new JComboBox<>();

    private final JPanel mainPanel = new JPanel();
    private final JScrollPane downListPanel;
    private final JPanel upMainPanel = new JPanel();
    private final JPanel upPanel1 = new JPanel();
    private final JPanel upPanel1Rating = new JPanel();
    private final JPanel upPanel1Calories = new JPanel();
    private final JPanel upPanel2 = new JPanel();
    private final JPanel upPanel2Proteins = new JPanel();
    private final JPanel upPanel2Fats = new JPanel();
    private final JPanel upPanel3 = new JPanel();
    private final JPanel upPanel3Sodium = new JPanel();
    private final JPanel upPanel3Price = new JPanel();
    private final JPanel upPanel4 = new JPanel();
    private final JPanel upPanel4Name = new JPanel();
    private final JPanel totalPricePanel = new JPanel();

    private final JTextField minRatingField = new JTextField();
    private final JTextField minCaloriesField = new JTextField();
    private final JTextField minProteinsField = new JTextField();
    private final JTextField minFatsField  = new JTextField();
    private final JTextField minSodiumField = new JTextField();
    private final JTextField minPriceField = new JTextField();
    private final JTextField maxRatingField = new JTextField();
    private final JTextField maxCaloriesField = new JTextField();
    private final JTextField maxProteinsField = new JTextField();
    private final JTextField maxFatsField  = new JTextField();
    private final JTextField maxSodiumField = new JTextField();
    private final JTextField maxPriceField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField totalPriceField = new JTextField();

    private final JTextArea listOfItems = new JTextArea();



    public ClientView(DeliveryService deliveryService, int clientID)
    {
        this.clientID = clientID;
        this.deliveryService = deliveryService;
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        upMainPanel.setLayout(new FlowLayout());
        upPanel1.setLayout(new BoxLayout(upPanel1, BoxLayout.Y_AXIS));
        upPanel1Rating.setLayout(new FlowLayout());
        upPanel1Calories.setLayout(new FlowLayout());
        upPanel2.setLayout(new BoxLayout(upPanel2, BoxLayout.Y_AXIS));
        upPanel2Fats.setLayout(new FlowLayout());
        upPanel2Proteins.setLayout(new FlowLayout());
        upPanel3.setLayout(new BoxLayout(upPanel3, BoxLayout.Y_AXIS));
        upPanel3Price.setLayout(new FlowLayout());
        upPanel3Sodium.setLayout(new FlowLayout());
        upPanel4.setLayout(new BoxLayout(upPanel4, BoxLayout.Y_AXIS));
        upPanel4Name.setLayout(new FlowLayout());
        totalPricePanel.setLayout(new FlowLayout());
        minRatingField.setPreferredSize(new Dimension(100, 20));
        minCaloriesField.setPreferredSize(new Dimension(100, 20));
        minProteinsField.setPreferredSize(new Dimension(100, 20));
        minFatsField.setPreferredSize(new Dimension(100, 20));
        minSodiumField.setPreferredSize(new Dimension(100, 20));
        minPriceField.setPreferredSize(new Dimension(100, 20));
        maxRatingField.setPreferredSize(new Dimension(100, 20));
        maxCaloriesField.setPreferredSize(new Dimension(100, 20));
        maxProteinsField.setPreferredSize(new Dimension(100, 20));
        maxFatsField.setPreferredSize(new Dimension(100, 20));
        maxSodiumField.setPreferredSize(new Dimension(100, 20));
        maxPriceField.setPreferredSize(new Dimension(100, 20));
        nameField.setPreferredSize(new Dimension(200, 20));
        totalPriceField.setPreferredSize(new Dimension(80, 20));
        totalPriceField.setEditable(false);
        listOfItems.setText("");
        listOfItems.setEditable(false);
        downListPanel = new JScrollPane(listOfItems, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        upPanel1Rating.add(ratingLabel);
        upPanel1Rating.add(minRatingField);
        upPanel1Rating.add(maxRatingField);
        upPanel1Calories.add(caloriesLabel);
        upPanel1Calories.add(minCaloriesField);
        upPanel1Calories.add(maxCaloriesField);
        upPanel2Proteins.add(proteinsLabel);
        upPanel2Proteins.add(minProteinsField);
        upPanel2Proteins.add(maxProteinsField);
        upPanel2Fats.add(fatsLabel);
        upPanel2Fats.add(minFatsField);
        upPanel2Fats.add(maxFatsField);
        upPanel3Sodium.add(sodiumLabel);
        upPanel3Sodium.add(minSodiumField);
        upPanel3Sodium.add(maxSodiumField);
        upPanel3Price.add(priceLabel);
        upPanel3Price.add(minPriceField);
        upPanel3Price.add(maxPriceField);
        upPanel4Name.add(nameLabel);
        upPanel4Name.add(nameField);
        upPanel1.add(upPanel1Rating);
        upPanel1.add(upPanel1Calories);
        upPanel2.add(upPanel2Proteins);
        upPanel2.add(upPanel2Fats);
        upPanel3.add(upPanel3Sodium);
        upPanel3.add(upPanel3Price);
        upPanel4.add(upPanel4Name);
        upPanel4.add(searchItemButton);
        upPanel4.add(itemsCB);
        upPanel4.add(addItemToListButton);

        upMainPanel.add(upPanel1);
        upMainPanel.add(upPanel2);
        upMainPanel.add(upPanel3);
        upMainPanel.add(upPanel4);

        totalPricePanel.add(totalPriceLabel);
        totalPricePanel.add(totalPriceField);

        mainPanel.add(upMainPanel);
        mainPanel.add(downListPanel);
        mainPanel.add(clearlistButton);
        mainPanel.add(totalPricePanel);
        mainPanel.add(sendOrderButton);

        clearlistButton.addActionListener(e ->
        {
            this.clearList();
        });

        searchItemButton.addActionListener(e ->
        {
            this.searchItems();
        });
        addItemToListButton.addActionListener(e ->
        {
            this.addToOrder();
        });
        sendOrderButton.addActionListener(e ->
        {
            this.sendOrder();
        });
        this.itemsCB.addActionListener(e ->
        {
            this.selectionChanged();
        });

        this.setTitle("Client");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(1500,700));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void clearList()
    {
        this.orderItems.clear();
        this.listOfItems.setText("");

        totalPrice = (double) 0;
        totalPriceField.setText(totalPrice.toString());
    }

    public void searchItems()
    {
        double minRating, maxRating, minCalories, maxCalories, minProteins, maxProteins, minFats, maxFats, minSodium, maxSodium, minPrice, maxPrice;
        if (minRatingField.getText().isEmpty())
        {
            minRating = -1;
        }
        else
        {
            minRating = Double.parseDouble(minRatingField.getText());
        }
        if (maxRatingField.getText().isEmpty())
        {
            maxRating = -1;
        }
        else
        {
            maxRating = Double.parseDouble(maxRatingField.getText());
        }
        if (minCaloriesField.getText().isEmpty())
        {
            minCalories = -1;
        }
        else
        {
            minCalories = Double.parseDouble(minCaloriesField.getText());
        }
        if (maxCaloriesField.getText().isEmpty())
        {
            maxCalories = -1;
        }
        else
        {
            maxCalories = Double.parseDouble(maxCaloriesField.getText());
        }
        if (minProteinsField.getText().isEmpty())
        {
            minProteins = -1;
        }
        else
        {
            minProteins = Double.parseDouble(minProteinsField.getText());
        }
        if (maxProteinsField.getText().isEmpty())
        {
            maxProteins = -1;
        }
        else
        {
            maxProteins = Double.parseDouble(maxProteinsField.getText());
        }
        if (minFatsField.getText().isEmpty())
        {
            minFats = -1;
        }
        else
        {
            minFats = Double.parseDouble(minFatsField.getText());
        }
        if (maxFatsField.getText().isEmpty())
        {
            maxFats = -1;
        }
        else
        {
            maxFats = Double.parseDouble(maxFatsField.getText());
        }
        if (minSodiumField.getText().isEmpty())
        {
            minSodium = -1;
        }
        else
        {
            minSodium = Double.parseDouble(minSodiumField.getText());
        }
        if (maxSodiumField.getText().isEmpty())
        {
            maxSodium = -1;
        }
        else
        {
            maxSodium = Double.parseDouble(maxSodiumField.getText());
        }
        if (minPriceField.getText().isEmpty())
        {
            minPrice = -1;
        }
        else
        {
            minPrice = Double.parseDouble(minPriceField.getText());
        }
        if (maxPriceField.getText().isEmpty())
        {
            maxPrice = -1;
        }
        else
        {
            maxPrice = Double.parseDouble(maxPriceField.getText());
        }

        this.clientItems = this.deliveryService.filterItems(nameField.getText(), minRating, maxRating, minCalories, maxCalories, minProteins, maxProteins, minFats, maxFats, minSodium, maxSodium, minPrice, maxPrice);

        this.updateDropdown();
    }

    public void addToOrder()
    {
        MenuItem currentItem;
        currentItem = (MenuItem) this.itemsCB.getSelectedItem();

        if (currentItem == null)
        {
            JOptionPane.showMessageDialog(null, "no product selected", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            this.orderItems.add(currentItem);
            this.updateTextArea();
            this.updateTotalPrice();
        }
    }

    public void sendOrder()
    {
        this.deliveryService.createOrder(this.clientID, this.orderItems, totalPrice);
    }

    public void selectionChanged()
    {

    }

    public void updateTotalPrice()
    {
        totalPrice = (double) 0;
        for (MenuItem item : this.orderItems)
        {
            totalPrice += item.getPrice();
        }
        totalPriceField.setText(totalPrice.toString());
    }


    public void updateDropdown()
    {
        ((DefaultComboBoxModel<MenuItem>)this.itemsCB.getModel()).removeAllElements();

        for(MenuItem item : this.clientItems)
        {
            this.itemsCB.addItem(item);
        }

    }

    public void updateTextArea()
    {
        this.listOfItems.setText("");
        for (MenuItem item : this.orderItems)
        {
            this.listOfItems.append(item.toString() + "\n");
        }
    }

    @Override
    public void update()
    {
        //this.updateDropdown();
    }
}
