package bussinessLayer.domain.products;

import java.util.Locale;
import java.util.Objects;

public class BaseProduct implements MenuItem
{
    private String name;
    private double rating;
    private double calories;
    private double proteins;
    private double fats;
    private double sodium;
    private double price;

    public BaseProduct()
    {

    }
    public BaseProduct(String name, double rating, double calories, double proteins, double fats, double sodium, double price)
    {
        this.name = name;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }
    public void setCalories(double calories)
    {
        this.calories = calories;
    }
    public void setProteins(double proteins)
    {
        this.proteins = proteins;
    }
    public void setFats(double fats)
    {
        this.fats = fats;
    }
    public void setSodium(double sodium)
    {
        this.sodium = sodium;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public double getCalories()
    {
        return this.calories;
    }

    @Override
    public double getProteins()
    {
        return this.proteins;
    }

    @Override
    public double getFats()
    {
        return this.fats;
    }

    @Override
    public double getSodium()
    {
        return this.sodium;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public double getRating()
    {
        return this.rating;
    }

    @Override
    public double getPrice()
    {
        return this.price;
    }

    @Override
    public String getLowerName()
    {
        return this.name.toLowerCase(Locale.ROOT);
    }
    @Override
    public String toString()
    {
        return "BaseProduct{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
