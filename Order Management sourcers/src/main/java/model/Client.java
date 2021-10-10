package model;

public class Client
{
    private String id;
    private String Name;
    private String Address;
    private String Email;
    private String Phone_Number;

    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return Name;
    }
    public String getAddress()
    {
        return Address;
    }
    public String getEmail()
    {
        return Email;
    }
    public String getPhone_Number()
    {
        return Phone_Number;
    }
    public void setAddress(String address)
    {
        this.Address = address;
    }
    public void setId(String clientID)
    {
        this.id = clientID;
    }
    public void setName(String name)
    {
        this.Name = name;
    }
    public void setEmail(String email)
    {
        this.Email = email;
    }
    public void setPhone_Number(String phoneNumber)
    {
        this.Phone_Number = phoneNumber;
    }
    @Override
    public String toString() {
        return "Client [clientID = " + id + ", name = " + Name + ", address = " + Address + ", email = " + Email + " , phone number = " + Phone_Number + "]";
    }
}
