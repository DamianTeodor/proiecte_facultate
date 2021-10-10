package bussinessLayer;

import bussinessLayer.domain.products.CompositeProduct;
import bussinessLayer.domain.products.MenuItem;

import java.lang.management.ManagementFactory;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IDeliveryServiceProcessing
{
     void getProducts();
     Order createOrder(int clientID, List<MenuItem> items, Double totalPrice);
     void addItem(MenuItem menuItem);
     void deleteItem(MenuItem menuItem);
     void editItem(MenuItem oldItem, MenuItem menuItem);
     List<MenuItem> filterItems(String name,
                                       Double minRating, Double maxRating,
                                       Double minCalories, Double maxCalories,
                                       Double minProteins, Double maxProteins,
                                       Double minFats, Double maxFats,
                                       Double minSodium, Double maxSodium,
                                       Double minPrice, Double maxPrice);
     void generateReport1(int startHour, int endHour);
     void generateReport2(int nrOfTimes);
     void createBill(Order order);
     void generateReport3(int nrOfTimes, int value);
     void generateReport4(int givenDate);
}
