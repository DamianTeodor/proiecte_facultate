package bussinessLayer.domain.users;

import bussinessLayer.domain.ClientIdGenerator;

public class Client extends User{

    private int clientID;
    private String name;

    public Client(Integer id, String username, String password)
    {
        super(username, password);
        this.clientID = id;
        this.setType("client");
    }

    public Client(int clientID, String username, String password)
    {
        super(username, password);
        this.clientID = clientID;
        this.setType("client");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }

    public int getClientID()
    {
        return clientID;
    }

}
