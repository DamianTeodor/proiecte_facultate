package bussinessLayer.domain.users;

public class Employee extends User
{
    private int employeeID;
    private String role;
    private String name;

    public Employee(String username, String password)
    {
        super(username, password);
        this.setType("employee");
    }

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
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
