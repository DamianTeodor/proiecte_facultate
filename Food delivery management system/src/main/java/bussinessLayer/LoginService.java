package bussinessLayer;

import bussinessLayer.domain.ClientIdGenerator;
import bussinessLayer.domain.users.Administrator;
import bussinessLayer.domain.users.Client;
import bussinessLayer.domain.users.Employee;
import bussinessLayer.domain.users.User;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginService implements Serializable
{
    private final Set<User> users = new HashSet<>();
    private String filePath = "users.txt";
    private ClientIdGenerator gen = new ClientIdGenerator();

    public LoginService()
    {

    }

    public void readFromFile()
    {
        File file = new File(this.filePath);

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));

            String username;
            String password;
            String type;
            String line;

            while ((line = bufferedReader.readLine()) != null){
                String[] attr = line.split(",");
                User user;


                username = attr[0];
                password = attr[1];
                type = attr[2];

                switch (type)
                {
                    case "client" ->
                    {
                        user = new Client(gen.getId(), username, password);
                        ((Client)user).setName(username);
                    }
                    case "admin" ->
                    {
                        user = new Administrator(username, password);
                    }
                    case "employee" ->
                    {
                        user = new Employee(username, password);
                    }
                    default ->
                    {
                        user = null;
                    }
                }
                users.add(user);
                //System.out.println(user.toString());
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public User checkLogin(String username, String password)
    {
        List<User> foundUsers = this.users.stream().filter(user ->
                                    user.getPassword().equals(password) && user.getUsername().equals(username)
                                    ).collect(Collectors.toList());

        if (foundUsers.size() == 0)
        {
            //System.out.println("ie goala bos");
            return null;
        }


        // foundUsers.get(0).getClass().equals(Client.class);

        return foundUsers.get(0);
    }


}
