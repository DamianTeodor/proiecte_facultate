package presentationLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.LoginService;
import bussinessLayer.domain.users.Administrator;
import bussinessLayer.domain.users.Client;
import bussinessLayer.domain.users.Employee;
import bussinessLayer.domain.users.User;
import dataLayer.SerializeIt;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame
{
    private DeliveryService deliveryService;

    private LoginService loginService = new LoginService();
    private final JLabel usernameLabel = new JLabel("Username: ");
    private final JLabel passwordLabel = new JLabel("Password: ");
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("Log in");
    private final JPanel mainPanel = new JPanel();
    private final JPanel flowPanel = new JPanel();
    private final JPanel flowPanel2 = new JPanel();
    private final JPanel flowPanel3 = new JPanel();
    private SerializeIt serializeIt;

    public LoginWindow(DeliveryService deliveryService, SerializeIt serializeIt)
    {
        loginService.readFromFile();
        flowPanel.setLayout(new FlowLayout());
        flowPanel2.setLayout(new FlowLayout());
        flowPanel3.setLayout(new FlowLayout());
        flowPanel.add(usernameLabel);
        usernameField.setPreferredSize(new Dimension(200, 20));
        passwordField.setPreferredSize(new Dimension(200, 20));
        flowPanel.add(usernameField);
        flowPanel2.add(passwordLabel);
        flowPanel2.add(passwordField);
        this.deliveryService = deliveryService;
        this.serializeIt = serializeIt;

        loginButton.addActionListener(e ->
        {
            User newUser = loginService.checkLogin(usernameField.getText(), passwordField.getText());
            if (newUser == null)
            {
                JOptionPane.showMessageDialog(null, "INVALID DATA", "ERROR ", JOptionPane.ERROR_MESSAGE);
            }
            if (newUser.getClass().equals(Client.class))
            {
                ClientView clientView = new ClientView(deliveryService, ((Client) newUser).getClientID());
                this.deliveryService.addObserver(clientView);
                clientView.setTitle(((Client) newUser).getName());
                clientView.setVisible(true);
                JOptionPane.showMessageDialog(null, "SUCCESSFULLY LOGGED IN AS CLIENT", "Success ", JOptionPane.PLAIN_MESSAGE);
            }
            else if (newUser.getClass().equals(Employee.class))
            {
                EmployeeView employeeView = new EmployeeView(deliveryService);
                employeeView.setVisible(true);
                this.deliveryService.addObserver(employeeView);
                JOptionPane.showMessageDialog(null, "SUCCESSFULLY LOGGED IN AS EMPLOYEE", "Success ", JOptionPane.PLAIN_MESSAGE);
            }
            else if (newUser.getClass().equals(Administrator.class))
            {
                AdminView adminView = new AdminView(deliveryService, serializeIt);
                this.deliveryService.addObserver(adminView);
                adminView.setVisible(true);
                JOptionPane.showMessageDialog(null, "SUCCESSFULLY LOGGED IN AS ADMINISTRATOR", "Success ", JOptionPane.PLAIN_MESSAGE);
            }

        });

        flowPanel3.add(loginButton);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(flowPanel);
        mainPanel.add(flowPanel2);
        mainPanel.add(flowPanel3);

        this.setTitle("Login");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(500,300));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
