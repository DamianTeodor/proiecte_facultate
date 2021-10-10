package bussinessLayer.domain.products;

import java.io.Serializable;


public interface MenuItem extends Serializable
{
     void setRating(double rating);
     void setCalories(double calories);
     double getRating();
     double getPrice();

     void setProteins(double proteins);
     void setFats(double fats);
     void setSodium(double sodium);
     void setPrice(double price);

     double getCalories();
     double getProteins();
     double getFats();
     double getSodium();

     void setName(String name);
     String getName();
     String getLowerName();

    @Override
     String toString();
}
