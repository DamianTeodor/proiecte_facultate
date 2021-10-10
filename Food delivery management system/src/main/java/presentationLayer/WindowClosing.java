package presentationLayer;

import bussinessLayer.DeliveryService;
import dataLayer.SerializeIt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class WindowClosing extends WindowAdapter
{
    DeliveryService deliveryService;
    SerializeIt serializeIt;

    public WindowClosing(DeliveryService deliveryService, SerializeIt serializeIt)
    {
        this.serializeIt = serializeIt;
        this.deliveryService = deliveryService;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        serializeIt.saveObject(deliveryService);
        System.out.println("se salveaza");
    }
}
