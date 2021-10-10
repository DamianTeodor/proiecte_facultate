package dataLayer;

import bussinessLayer.DeliveryService;

import java.io.*;

public class SerializeIt
{

    public SerializeIt()
    {

    }

    public DeliveryService loadObject() {
        DeliveryService service = new DeliveryService();
        try {
                new File("Object.txt");
                FileInputStream fileInputStream = new FileInputStream("Object.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                service = (DeliveryService) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
                System.out.println("loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    public void saveObject(DeliveryService service) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Object.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(service);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
