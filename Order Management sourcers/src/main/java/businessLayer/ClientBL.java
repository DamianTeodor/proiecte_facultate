package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Client;
import validators.EmailValidator;
import validators.PhoneNumberValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the operations on the class Client performed on the database by the class ClientDAO
 */
public class ClientBL
{
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;


    public ClientBL()
    {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * This method creates an object of class Client and calls the insert method from ClientDAO
     * @param clientID id of the client
     * @param Name name of the client
     * @param Address address of the client
     * @param Email email of the client
     * @param Phone_Number phone number of the client
     */
    public int insert(String clientID, String Name, String Address, String Email, String Phone_Number)
    {
        Client client = new Client();
        client.setId(clientID);
        client.setName(Name);
        client.setAddress(Address);
        client.setEmail(Email);
        client.setPhone_Number(Phone_Number);
        try
        {
            validators.get(0).validate(client);
            validators.get(1).validate(client);
        }
        catch (Exception e)
        {
            return 0;
        }
        return clientDAO.insert(client);
    }

    /**
     * This method calls the searchByID method from ClientDAO to find the id of the client we want to delete and then calls the delete method from ClientDAO
     * @param id the id of the client we want to delete
     */
    public void delete(String id)
    {
        clientDAO.delete(clientDAO.searchByID(Integer.parseInt(id)));
    }

    /**
     * This method creates an object of class Client and then calls the update method from ClientDAO to update the row in the database
     * @param clientID id of the client
     * @param name name of the client
     * @param address address of the client
     * @param email email of the client
     * @param phoneNumber phone number of the client
     */
    public void update(String clientID, String name, String address, String email, String phoneNumber)
    {
        Client client = new Client();
        client.setId(clientID);
        client.setName(name);
        client.setAddress(address);
        client.setEmail(email);
        client.setPhone_Number(phoneNumber);
        clientDAO.update(client);
    }

    /**
     * This method calls the findAll() method from ClientDAO to get all the data from the Clients table
     * @return a list of objects of class Client
     */
    public List<Client> findAll()
    {
        return clientDAO.findAll();
    }

    /**
     * This method calls the getTableHeader method from ClientDAO to recieve the fields of the Clients table
     * @return an array containing the names of the fields of the class Client
     */
    public Object[] getTableHeader()
    {
        Object[] header = clientDAO.getTableHeader();
        return header;
    }

    /**
     * This method calls the getTable method from ClientDAO to get the data from a list of objects of class Client
     * @param clientList a list of objects of class Client
     * @return a two dimensional array containing the data
     */
    public Object[][] getTable(List<Client> clientList)
    {
        Object[][] table = clientDAO.getTable(clientList);
        return table;
    }
}