package graphic;

import managing.Manager;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;

public class Graphics extends JFrame
{
    private FileWriter fileWriter;

    private JTextField field_simulation = new JTextField();
    private JTextArea bigTextField = new JTextArea();
    String numbers[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"};
    JComboBox clientsJ = new JComboBox(numbers);
    JComboBox queuesJ = new JComboBox(numbers);
    JComboBox maxArrivalTimeJ = new JComboBox(numbers);
    JComboBox minArrivalTimeJ = new JComboBox(numbers);
    JComboBox minServiceTimeJ = new JComboBox(numbers);
    JComboBox maxServiceTimeJ = new JComboBox(numbers);
    private JLabel nr_clients = new JLabel("Number of clients:");
    private JLabel nr_queues = new JLabel("Number of queues:");
    private JLabel simulation_interval = new JLabel("Maximum simulation time:");
    private JLabel minArrivalTime = new JLabel("Minimum Arrival time: ");
    private JLabel maxArrivalTime = new JLabel("Maximum Arrival time: ");
    private JLabel minSerivceTime = new JLabel("Minimum Service time: ");
    private JLabel maxServiceTime = new JLabel("Maximum Service time: ");

    private JButton start = new JButton("Start");
    private JButton reset = new JButton("Reset");

    private JPanel mainPanel = new JPanel();
    private JScrollPane secondPanel;
    private JTabbedPane tabs = new JTabbedPane();

    public Graphics(FileWriter fileWriter)
    {
        this.fileWriter = fileWriter;
        mainPanel.setLayout(new GridLayout(0, 2));
        mainPanel.add(nr_clients);
        mainPanel.add(clientsJ);
        mainPanel.add(nr_queues);
        mainPanel.add(queuesJ);
        mainPanel.add(simulation_interval);
        mainPanel.add(field_simulation);
        mainPanel.add(minArrivalTime);
        mainPanel.add(minArrivalTimeJ);
        mainPanel.add(maxArrivalTime);
        mainPanel.add(maxArrivalTimeJ);
        mainPanel.add(minSerivceTime);
        mainPanel.add(minServiceTimeJ);
        mainPanel.add(maxServiceTime);
        mainPanel.add(maxServiceTimeJ);
        start.addActionListener(e ->
        {
            int timeLimit;
            int maxProcessingTime = 0;
            int minProcessingTime = 0;
            int maxArrivalTime = 0;
            int minArrivalTime = 0;
            int numberOfServers;
            int numberOfClients;
            try {
                timeLimit = Integer.parseInt(this.getSimulationTime());
                numberOfServers = Integer.parseInt(this.getQueues());
                numberOfClients = Integer.parseInt(this.getNrOfClients());
                minArrivalTime = Integer.parseInt(this.getMinArrivalTime());
                maxArrivalTime = Integer.parseInt(this.getMaxArrivalTime());
                minProcessingTime = Integer.parseInt(this.getMinServiceTime());
                maxProcessingTime = Integer.parseInt(this.getMaxServiceTime());
                Manager man = new Manager(this, fileWriter, timeLimit, maxProcessingTime, minProcessingTime, maxArrivalTime, minArrivalTime, numberOfServers, numberOfClients);
                Thread mainThread = new Thread(man);
                mainThread.start();
            }
            catch (Exception ex)
            {
                this.ShowInvalidInputError();
            }
        });
        reset.addActionListener(e ->
        {
            field_simulation.setText("");
             clientsJ.setSelectedIndex(0);
             queuesJ.setSelectedIndex(0);
             maxArrivalTimeJ.setSelectedIndex(0);
             minArrivalTimeJ.setSelectedIndex(0);
             minServiceTimeJ.setSelectedIndex(0);
             maxServiceTimeJ.setSelectedIndex(0);
        });
        mainPanel.add(start);
        mainPanel.add(reset);
        bigTextField.setText("");
        secondPanel = new JScrollPane(bigTextField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabs.add("Inputs", mainPanel);
        tabs.add("Outputs", secondPanel);
        this.setPreferredSize(new Dimension(700, 700));
        this.setContentPane(tabs);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUpdateText(String updated)
    {
        String s = "";
        s = bigTextField.getText();
        bigTextField.setText(s + "\n" + updated);
    }

    public void ShowInvalidInputError(){
        JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }

    public String getNrOfClients()
    {
        return (String) clientsJ.getSelectedItem();
    }
    public String getQueues()
    {
        return (String) queuesJ.getSelectedItem();
    }
    public String getSimulationTime()
    {
        return field_simulation.getText();
    }
    public String getMinArrivalTime()
    {
        return (String) minArrivalTimeJ.getSelectedItem();
    }
    public String getMaxArrivalTime()
    {
        return (String) maxArrivalTimeJ.getSelectedItem();
    }
    public String getMinServiceTime()
    {
        return (String) minServiceTimeJ.getSelectedItem();
    }
    public String getMaxServiceTime()
    {
        return (String) maxServiceTimeJ.getSelectedItem();
    }
}
