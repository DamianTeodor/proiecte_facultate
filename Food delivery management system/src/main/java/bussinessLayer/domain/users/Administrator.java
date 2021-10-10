package bussinessLayer.domain.users;

public class Administrator extends User
{
    private int adminID;

    private String name;

    public Administrator(String username, String password)
    {
        super(username, password);
        this.setType("admin");
    }

    public int getAdminID()
    {
        return adminID;
    }

    public void setAdminID(int adminID)
    {
        this.adminID = adminID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
