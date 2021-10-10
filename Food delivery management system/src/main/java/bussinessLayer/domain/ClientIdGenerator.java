package bussinessLayer.domain;

import java.io.Serializable;

public class ClientIdGenerator implements Serializable
{
    private static int id;

    public ClientIdGenerator(){
        id = 0;
    }

    public int getId(){
        id++;
        return id;
    }
}
