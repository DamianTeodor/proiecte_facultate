package presentationLayer;

import bussinessLayer.DeliveryService;
import dataLayer.SerializeIt;

public class Main
{
    public static void main(String[] args)
    {
        SerializeIt serializeIt = new SerializeIt();
        DeliveryService deliveryService = serializeIt.loadObject();
        LoginWindow login = new LoginWindow(deliveryService, serializeIt);
        login.setVisible(true);
    }
}
