package presentationLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.domain.observer.Observer;
import bussinessLayer.domain.products.BaseProduct;
import bussinessLayer.domain.products.CompositeProduct;
import bussinessLayer.domain.products.MenuItem;
import dataLayer.SerializeIt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AdminView extends JFrame implements Observer
{
    private WindowClosing windowClosing;

    private final DeliveryService deliveryService;
    private Set<MenuItem> menuItems = new HashSet<>();
    private Set<MenuItem> currentItems = new HashSet<>();

    private final JLabel itemNameLabel = new JLabel("Name :");
    private final JLabel itemRatingLabel = new JLabel("Rating :");
    private final JLabel itemCaloriesLabel = new JLabel("Calories :");
    private final JLabel itemProteinsLabel = new JLabel("Proteins :");
    private final JLabel itemFatsLabel = new JLabel("Fats :");
    private final JLabel itemSodiumLabel = new JLabel("Sodium :");
    private final JLabel itemPriceLabel = new JLabel("Price :");

    private final JLabel editItemNameLabel = new JLabel("New Name :");
    private final JLabel editItemRatingLabel = new JLabel("New Rating :");
    private final JLabel editItemCaloriesLabel = new JLabel("New Calories :");
    private final JLabel editItemProteinsLabel = new JLabel("New Proteins :");
    private final JLabel editItemFatsLabel = new JLabel("New Fats :");
    private final JLabel editItemSodiumLabel = new JLabel("New Sodium :");
    private final JLabel editItemPriceLabel = new JLabel("New Price :");
    private final JLabel selectItemLabel = new JLabel("Select item :");

    private final JLabel selectItemsToDeleteLabel = new JLabel("Select item to delete :");

    private final JLabel createItemNameLabel = new JLabel("Name it :");
    private final JLabel createItemSelectLabel = new JLabel("Select items :");
    private final JLabel listLabel = new JLabel("List :");

    private final JLabel startHourLabel = new JLabel("Start hour :");
    private final JLabel endHourLabel = new JLabel("End hour :");

    private final JLabel TimesLabel = new JLabel("Number of times :");
    private final JLabel report2DescriptionLabel = new JLabel("the products ordered more than a specified number of times so far");
    private final JLabel report3DescriptionLabel1 = new JLabel("the clients that have ordered more than a specified number of times");
    private final JLabel report3DescritpionLabel2 = new JLabel("and the value of the order was higher than a specified amount");
    private final JLabel times3Label = new JLabel("Number of times :");
    private final JLabel valueLabel = new JLabel("Value :");

    private final JLabel report4DescriptionLabel1 = new JLabel("the products ordered within a specified day");
    private final JLabel report4DescriptionLabel2 = new JLabel("with the number of times they have");
    private final JLabel report4DescriptionLabel3 = new JLabel("been ordered");
    private final JLabel report4DaySelectLabel = new JLabel("Select Day");

    private final JButton importButton = new JButton("Import items");
    private final JButton addItemButton = new JButton("Add new item");
    private final JButton editItemButton = new JButton("Edit item");
    private final JButton deleteItemButton = new JButton("Delete item");
    private final JButton addProductToListButton = new JButton("Add to list");
    private final JButton finishListButton = new JButton("Finish list");
    private final JButton generateReport1 = new JButton("Generate");
    private final JButton generateReport2 = new JButton("Generate");
    private final JButton generateReport3 = new JButton("Generate");
    private final JButton generateReport4 = new JButton("Generate");

    private JComboBox<MenuItem> editItemsCB = new JComboBox<>();
    private JComboBox<MenuItem> deleteItemsCB = new JComboBox<>();
    private JComboBox<MenuItem> createItemsCB = new JComboBox<>();
    private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private JComboBox selectDayCB = new JComboBox(days);

    private final JTabbedPane mainTab = new JTabbedPane();

    private final JPanel marelePanou = new JPanel();
    private final JPanel addItemsPanel = new JPanel();
    private final JPanel namePanel = new JPanel();
    private final JPanel ratingPanel = new JPanel();
    private final JPanel caloriesPanel = new JPanel();
    private final JPanel proteinsPanel = new JPanel();
    private final JPanel fatsPanel = new JPanel();
    private final JPanel sodiumPanel = new JPanel();
    private final JPanel pricePanel = new JPanel();

    private final JPanel editItemsPanel = new JPanel();
    private final JPanel editNamePanel = new JPanel();
    private final JPanel editRatingPanel = new JPanel();
    private final JPanel editCaloriesPanel = new JPanel();
    private final JPanel editProteinsPanel = new JPanel();
    private final JPanel editFatsPanel = new JPanel();
    private final JPanel editSodiumPanel = new JPanel();
    private final JPanel editPricePanel = new JPanel();
    private final JPanel selectItemPanel = new JPanel();

    private final JPanel deleteItemsPanel = new JPanel();
    private final JPanel selectItemsToDeletePanel = new JPanel();

    private final JPanel createItemPanel = new JPanel();
    private final JPanel createItemNamePanel = new JPanel();
    private final JPanel createItemSelectPanel = new JPanel();
    private final JPanel createItemListPanel = new JPanel();
    private final JPanel importItemsPanel = new JPanel();

    private final JPanel marelePanou2 = new JPanel();
    private final JPanel reportPanel1 = new JPanel();
    private final JPanel reportPanel2 = new JPanel();
    private final JPanel reportPanel3 = new JPanel();
    private final JPanel reportPanel4 = new JPanel();
    private final JPanel reportPanel1StartHour = new JPanel();
    private final JPanel reportPanel1EndHour = new JPanel();
    private final JPanel reportPanel2Times = new JPanel();
    private final JPanel reportPanel2Generate = new JPanel();
    private final JPanel reportPanel3Times = new JPanel();
    private final JPanel reportPanel3Value = new JPanel();
    private final JPanel reportPanel4Day = new JPanel();

    private final JTextField itemNameField = new JTextField();
    private final JTextField itemRatingField = new JTextField();
    private final JTextField itemCaloriesField = new JTextField();
    private final JTextField itemProteinsField = new JTextField();
    private final JTextField itemFatsField = new JTextField();
    private final JTextField itemSodiumField = new JTextField();
    private final JTextField itemPriceField = new JTextField();

    private final JTextField editItemNameField = new JTextField();
    private final JTextField editItemRatingField = new JTextField();
    private final JTextField editItemCaloriesField = new JTextField();
    private final JTextField editItemProteinsField = new JTextField();
    private final JTextField editItemFatsField = new JTextField();
    private final JTextField editItemSodiumField = new JTextField();
    private final JTextField editItemPriceField = new JTextField();

    private final JTextField createItemNameField = new JTextField();

    private final JTextField startHourField = new JTextField();
    private final JTextField endHourField = new JTextField();

    private final JTextField numberOfTimesField = new JTextField();

    private final JTextField numberOfTimes3Field = new JTextField();
    private final JTextField valueField = new JTextField();

    private JTextArea listOfItemsArea = new JTextArea();

    public AdminView(DeliveryService deliveryService, SerializeIt serializeIt)
    {
        this.deliveryService = deliveryService;
        //this.importItems();

        windowClosing = new WindowClosing(deliveryService, serializeIt);
        this.updateDropdown();

        deliveryService.removeNulls();

        marelePanou.setLayout(new GridLayout(0,5));
        addItemsPanel.setLayout(new BoxLayout(addItemsPanel, BoxLayout.Y_AXIS));
        namePanel.setLayout(new FlowLayout());
        ratingPanel.setLayout(new FlowLayout());
        caloriesPanel.setLayout(new FlowLayout());
        proteinsPanel.setLayout(new FlowLayout());
        fatsPanel.setLayout(new FlowLayout());
        sodiumPanel.setLayout(new FlowLayout());
        pricePanel.setLayout(new FlowLayout());
        editItemsPanel.setLayout(new BoxLayout(editItemsPanel, BoxLayout.Y_AXIS));
        itemNameField.setPreferredSize(new Dimension(200, 20));
        itemRatingField.setPreferredSize(new Dimension(200, 20));
        itemCaloriesField.setPreferredSize(new Dimension(200, 20));
        itemProteinsField.setPreferredSize(new Dimension(200, 20));
        itemFatsField.setPreferredSize(new Dimension(200, 20));
        itemSodiumField.setPreferredSize(new Dimension(200, 20));
        itemPriceField.setPreferredSize(new Dimension(200, 20));
        addItemsPanel.add(addItemButton);
        namePanel.add(itemNameLabel);
        namePanel.add(itemNameField);
        ratingPanel.add(itemRatingLabel);
        ratingPanel.add(itemRatingField);
        caloriesPanel.add(itemCaloriesLabel);
        caloriesPanel.add(itemCaloriesField);
        proteinsPanel.add(itemProteinsLabel);
        proteinsPanel.add(itemProteinsField);
        fatsPanel.add(itemFatsLabel);
        fatsPanel.add(itemFatsField);
        sodiumPanel.add(itemSodiumLabel);
        sodiumPanel.add(itemSodiumField);
        pricePanel.add(itemPriceLabel);
        pricePanel.add(itemPriceField);
        addItemsPanel.add(namePanel);
        addItemsPanel.add(ratingPanel);
        addItemsPanel.add(caloriesPanel);
        addItemsPanel.add(proteinsPanel);
        addItemsPanel.add(fatsPanel);
        addItemsPanel.add(sodiumPanel);
        addItemsPanel.add(pricePanel);

        editNamePanel.setLayout(new FlowLayout());
        editRatingPanel.setLayout(new FlowLayout());
        editCaloriesPanel.setLayout(new FlowLayout());
        editProteinsPanel.setLayout(new FlowLayout());
        editFatsPanel.setLayout(new FlowLayout());
        editSodiumPanel.setLayout(new FlowLayout());
        editPricePanel.setLayout(new FlowLayout());
        selectItemPanel.setLayout(new FlowLayout());
        editItemsPanel.setLayout(new BoxLayout(editItemsPanel, BoxLayout.Y_AXIS));
        editItemNameField.setPreferredSize(new Dimension(200, 20));
        editItemRatingField.setPreferredSize(new Dimension(200, 20));
        editItemCaloriesField.setPreferredSize(new Dimension(200, 20));
        editItemProteinsField.setPreferredSize(new Dimension(200, 20));
        editItemFatsField.setPreferredSize(new Dimension(200, 20));
        editItemSodiumField.setPreferredSize(new Dimension(200, 20));
        editItemPriceField.setPreferredSize(new Dimension(200, 20));
        editItemsCB.setPreferredSize(new Dimension(300, 30));
        selectItemPanel.add(selectItemLabel);
        selectItemPanel.add(editItemsCB);
        editItemsPanel.add(editItemButton);
        editNamePanel.add(editItemNameLabel);
        editNamePanel.add(editItemNameField);
        editRatingPanel.add(editItemRatingLabel);
        editRatingPanel.add(editItemRatingField);
        editCaloriesPanel.add(editItemCaloriesLabel);
        editCaloriesPanel.add(editItemCaloriesField);
        editProteinsPanel.add(editItemProteinsLabel);
        editProteinsPanel.add(editItemProteinsField);
        editFatsPanel.add(editItemFatsLabel);
        editFatsPanel.add(editItemFatsField);
        editSodiumPanel.add(editItemSodiumLabel);
        editSodiumPanel.add(editItemSodiumField);
        editPricePanel.add(editItemPriceLabel);
        editPricePanel.add(editItemPriceField);
        editItemsPanel.add(selectItemPanel);
        editItemsPanel.add(editNamePanel);
        editItemsPanel.add(editRatingPanel);
        editItemsPanel.add(editCaloriesPanel);
        editItemsPanel.add(editProteinsPanel);
        editItemsPanel.add(editFatsPanel);
        editItemsPanel.add(editSodiumPanel);
        editItemsPanel.add(editPricePanel);

        deleteItemsPanel.setLayout(new BoxLayout(deleteItemsPanel, BoxLayout.Y_AXIS));
        selectItemsToDeletePanel.setLayout(new FlowLayout());
        deleteItemsCB.setPreferredSize(new Dimension(300, 30));
        deleteItemsPanel.add(deleteItemButton);
        selectItemsToDeletePanel.add(selectItemsToDeleteLabel);
        selectItemsToDeletePanel.add(deleteItemsCB);
        deleteItemsPanel.add(selectItemsToDeletePanel);

        createItemPanel.setLayout(new BoxLayout(createItemPanel, BoxLayout.Y_AXIS));
        listOfItemsArea.setEnabled(false);
        createItemNamePanel.setLayout(new FlowLayout());
        createItemSelectPanel.setLayout(new FlowLayout());
        createItemListPanel.setLayout(new FlowLayout());
        createItemNameField.setPreferredSize(new Dimension(200, 20));
        createItemsCB.setPreferredSize(new Dimension(300, 30));
        listOfItemsArea.setPreferredSize(new Dimension(450, 300));
        createItemNamePanel.add(createItemNameLabel);
        createItemNamePanel.add(createItemNameField);
        createItemSelectPanel.add(createItemSelectLabel);
        createItemSelectPanel.add(createItemsCB);
        createItemListPanel.add(listLabel);
        createItemListPanel.add(listOfItemsArea);
        createItemPanel.add(createItemSelectPanel);
        createItemPanel.add(addProductToListButton);
        createItemPanel.add(createItemListPanel);
        createItemPanel.add(createItemNamePanel);
        createItemPanel.add(finishListButton);

        marelePanou.add(addItemsPanel);
        marelePanou.add(editItemsPanel);
        marelePanou.add(deleteItemsPanel);
        marelePanou.add(createItemPanel);

        importItemsPanel.setLayout(new FlowLayout());
        importButton.setPreferredSize(new Dimension(150, 50));
        importItemsPanel.add(importButton);
        marelePanou.add(importItemsPanel);

        mainTab.add("Administration", marelePanou);

        marelePanou2.setLayout(new GridLayout(0,4));
        reportPanel1.setLayout(new BoxLayout(reportPanel1, BoxLayout.Y_AXIS));
        startHourField.setPreferredSize(new Dimension(200, 20));
        endHourField.setPreferredSize(new Dimension(200, 20));
        reportPanel1.add(generateReport1);
        reportPanel1StartHour.setLayout(new FlowLayout());
        reportPanel1EndHour.setLayout(new FlowLayout());
        reportPanel1StartHour.add(startHourLabel);
        reportPanel1StartHour.add(startHourField);
        reportPanel1EndHour.add(endHourLabel);
        reportPanel1EndHour.add(endHourField);
        reportPanel1.add(reportPanel1StartHour);
        reportPanel1.add(reportPanel1EndHour);

        reportPanel2.setLayout(new BoxLayout(reportPanel2, BoxLayout.Y_AXIS));
        numberOfTimesField.setPreferredSize(new Dimension(200, 20));
        reportPanel2Generate.setLayout(new FlowLayout());
        reportPanel2Times.setLayout(new FlowLayout());
        reportPanel2Generate.add(generateReport2);
        reportPanel2Generate.add(report2DescriptionLabel);
        reportPanel2Times.add(TimesLabel);
        reportPanel2Times.add(numberOfTimesField);
        reportPanel2.add(reportPanel2Generate);
        reportPanel2.add(reportPanel2Times);

        reportPanel3.setLayout(new BoxLayout(reportPanel3, BoxLayout.Y_AXIS));
        reportPanel3.add(report3DescriptionLabel1);
        reportPanel3.add(report3DescritpionLabel2);
        reportPanel3.add(generateReport3);
        numberOfTimes3Field.setPreferredSize(new Dimension(200, 20));
        valueField.setPreferredSize(new Dimension(200, 20));
        reportPanel3Times.setLayout(new FlowLayout());
        reportPanel3Value.setLayout(new FlowLayout());
        reportPanel3Times.add(times3Label);
        reportPanel3Times.add(numberOfTimes3Field);
        reportPanel3Value.add(valueLabel);
        reportPanel3Value.add(valueField);
        reportPanel3.add(reportPanel3Times);
        reportPanel3.add(reportPanel3Value);

        reportPanel4.setLayout(new BoxLayout(reportPanel4, BoxLayout.Y_AXIS));
        reportPanel4.add(report4DescriptionLabel1);
        reportPanel4.add(report4DescriptionLabel2);
        reportPanel4.add(report4DescriptionLabel3);
        reportPanel4.add(generateReport4);
        selectDayCB.setPreferredSize(new Dimension(300, 30));
        reportPanel4Day.setLayout(new FlowLayout());
        reportPanel4Day.add(report4DaySelectLabel);
        reportPanel4Day.add(selectDayCB);
        reportPanel4.add(reportPanel4Day);

        marelePanou2.add(reportPanel1);
        marelePanou2.add(reportPanel2);
        marelePanou2.add(reportPanel3);
        marelePanou2.add(reportPanel4);
        mainTab.add("Reports", marelePanou2);

        this.addWindowListener(windowClosing);

        this.importButton.addActionListener(e ->
        {
            this.importItems();
        });
        this.addItemButton.addActionListener(e ->
        {
            this.addNewItem();
        });
        this.editItemButton.addActionListener(e ->
        {
            this.editProduct();
        });
        this.deleteItemButton.addActionListener(e ->
        {
            this.deleteProduct();
        });
        this.addProductToListButton.addActionListener(e ->
        {
            this.addToList();
        });
        this.finishListButton.addActionListener(e ->
        {
            this.finishList();
        });

        this.editItemsCB.addActionListener(e ->{
            this.editSelectionChanged();
        });
        this.generateReport1.addActionListener(e ->
        {
            this.generateReport1();
        });
        this.generateReport2.addActionListener(e ->
        {
            this.generateReport2();
        });
        this.generateReport3.addActionListener(e ->
        {
            this.generateReport3();
        });
        this.generateReport4.addActionListener(e ->
        {
            this.generateReport4();
        });

        this.editItemNameField.setEditable(false);

        this.setTitle("Admin");
        this.setContentPane(mainTab);
        this.setPreferredSize(new Dimension(1900,700));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void importItems()
    {
        this.deliveryService.getProducts();
        this.menuItems = this.deliveryService.getMenu();
        this.updateDropdown();
    }

    public void generateReport1()
    {
        if (startHourField.getText().isEmpty() || endHourField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "nu lasa gol", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            deliveryService.generateReport1(Integer.parseInt(startHourField.getText()), Integer.parseInt(endHourField.getText()));
        }
    }

    public void generateReport2()
    {
        if (numberOfTimesField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "nu lasa gol", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            deliveryService.generateReport2(Integer.parseInt(numberOfTimesField.getText()));
        }
    }

    public void generateReport3()
    {
        if (this.numberOfTimes3Field.getText().isEmpty() || valueField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "nu lasa gol", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            deliveryService.generateReport3(Integer.parseInt(this.numberOfTimes3Field.getText()), Integer.parseInt(this.valueField.getText()));
        }
    }

    public void generateReport4()
    {
        String date = (String) selectDayCB.getSelectedItem();
        switch (date)
        {
            case "Sunday" ->
            {
                deliveryService.generateReport4(0);
            }
            case "Monday" ->
            {
                deliveryService.generateReport4(1);
            }
            case "Tuesday" ->
            {
                deliveryService.generateReport4(2);
            }
            case "Wednesday" ->
            {
                deliveryService.generateReport4(3);
            }
            case "Thursday" ->
            {
                deliveryService.generateReport4(4);
            }
            case "Friday" ->
            {
                deliveryService.generateReport4(5);
            }
            case "Saturday" ->
            {
                deliveryService.generateReport4(6);
            }
        }
    }

    public void updateDropdown()
    {
        ((DefaultComboBoxModel<MenuItem>)this.editItemsCB.getModel()).removeAllElements();
        ((DefaultComboBoxModel<MenuItem>)this.deleteItemsCB.getModel()).removeAllElements();
        ((DefaultComboBoxModel<MenuItem>)this.createItemsCB.getModel()).removeAllElements();

        menuItems = this.deliveryService.getMenu();

        //System.out.println(this.menuItems.size());

        for(MenuItem menuItem : this.menuItems)
        {
            //System.out.println(menuItem.toString());
            this.editItemsCB.addItem(menuItem);
            this.deleteItemsCB.addItem(menuItem);
            this.createItemsCB.addItem(menuItem);
        }
    }

    public void updateCurrentProducts()
    {
        this.listOfItemsArea.setText("");
        for(MenuItem item : this.currentItems){
            this.listOfItemsArea.append(item.toString() + "\n");
        }
    }

    public BaseProduct createProduct()
    {
        if (itemNameField.getText().equals("")
                || itemCaloriesField.getText().equals("")
                || itemRatingField.getText().equals("")
                || itemProteinsField.getText().equals("")
                || itemFatsField.getText().equals("")
                || itemSodiumField.getText().equals("")
                || itemPriceField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "nu lasa gol", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            return new BaseProduct(itemNameField.getText(),
                    Double.parseDouble(itemRatingField.getText()),
                    Double.parseDouble(itemCaloriesField.getText()),
                    Double.parseDouble(itemProteinsField.getText()),
                    Double.parseDouble(itemFatsField.getText()),
                    Double.parseDouble(itemSodiumField.getText()),
                    Double.parseDouble(itemPriceField.getText()));
        }
        return new BaseProduct();
    }

    public void editProduct()
    {
        if (editItemNameField.getText().equals("")
                || editItemCaloriesField.getText().equals("")
                || editItemRatingField.getText().equals("")
                || editItemProteinsField.getText().equals("")
                || editItemFatsField.getText().equals("")
                || editItemSodiumField.getText().equals("")
                || editItemPriceField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "nu lasa gol", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            MenuItem oldItem = (MenuItem) this.editItemsCB.getSelectedItem();
            MenuItem newItem = new BaseProduct(editItemNameField.getText(),
                Double.parseDouble(editItemRatingField.getText()),
                Double.parseDouble(editItemCaloriesField.getText()),
                Double.parseDouble(editItemProteinsField.getText()),
                Double.parseDouble(editItemFatsField.getText()),
                Double.parseDouble(editItemSodiumField.getText()),
                Double.parseDouble(editItemPriceField.getText()));
            this.deliveryService.editItem(oldItem,newItem);
        }
    }

    public void deleteProduct()
    {
        MenuItem currentItem;
        currentItem = (MenuItem) this.deleteItemsCB.getSelectedItem();


        if(currentItem == null){
            JOptionPane.showMessageDialog(null, "no product selected", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.deliveryService.deleteItem(currentItem);
        }
    }

    public void editSelectionChanged()
    {
        MenuItem currentItem;
        currentItem = (MenuItem) this.editItemsCB.getSelectedItem();


        if(currentItem == null)
        {
            this.clearEditFields();
            //JOptionPane.showMessageDialog(null, "no product selected", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            this.editItemNameField.setText(currentItem.getName());
            this.editItemRatingField.setText(Double.toString(currentItem.getRating()));
            this.editItemCaloriesField.setText(Double.toString(currentItem.getCalories()));
            this.editItemProteinsField.setText(Double.toString(currentItem.getProteins()));
            this.editItemFatsField.setText(Double.toString(currentItem.getFats()));
            this.editItemSodiumField.setText(Double.toString(currentItem.getSodium()));
            this.editItemPriceField.setText(Double.toString(currentItem.getPrice()));
        }
    }

    public void clearEditFields(){
        this.editItemNameField.setText("");
        this.editItemRatingField.setText("");
        this.editItemCaloriesField.setText("");
        this.editItemProteinsField.setText("");
        this.editItemFatsField.setText("");
        this.editItemSodiumField.setText("");
        this.editItemPriceField.setText("");
    }

    public void clearCreateFields()
    {
        this.itemNameField.setText("");
        this.itemRatingField.setText("");
        this.itemCaloriesField.setText("");
        this.itemProteinsField.setText("");
        this.itemFatsField.setText("");
        this.itemSodiumField.setText("");
        this.itemPriceField.setText("");
    }

    public void addToList()
    {
        MenuItem currentItem;
        currentItem = (MenuItem) this.createItemsCB.getSelectedItem();

        if(currentItem == null)
        {
            JOptionPane.showMessageDialog(null, "no product selected", "ERROR ", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            this.currentItems.add(currentItem);
            System.out.println("size dupa de insert" + this.currentItems.size());
            this.updateCurrentProducts();
        }

    }

    public void addNewItem()
    {
        this.deliveryService.addItem(createProduct());
        this.clearCreateFields();
    }

    public void finishList()
    {
        CompositeProduct compositeProduct = new CompositeProduct();

        for(MenuItem item : this.currentItems){
            compositeProduct.add(item);
        }

        compositeProduct.setName(this.createItemNameField.getText());
        this.deliveryService.addItem(compositeProduct);
        this.currentItems.clear();
        this.updateCurrentProducts();
    }

    @Override
    public void update()
    {
        this.updateDropdown();
    }
}