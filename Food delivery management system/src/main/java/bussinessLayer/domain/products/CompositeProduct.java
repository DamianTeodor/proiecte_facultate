package bussinessLayer.domain.products;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

public class CompositeProduct implements MenuItem
{
    private Collection<MenuItem> menuItems = new HashSet<>();
    private double totalRating;
    private String name;
    private double totalCalories;
    private double totalProteins;
    private double totalFats;
    private double totalSodium;
    private double totalPrice;

    public CompositeProduct()
    {
        super();
        this.totalRating = 0;
        this.totalCalories = 0;
        this.totalProteins = 0;
        this.totalFats = 0;
        this.totalSodium = 0;
        this.totalPrice = 0;
    }

    public void add(MenuItem menuItem)
    {
        menuItems.add(menuItem);
        this.totalRating += menuItem.getRating();
        this.totalCalories += menuItem.getCalories();
        this.totalProteins += menuItem.getProteins();
        this.totalFats += menuItem.getFats();
        this.totalSodium += menuItem.getSodium();
        this.totalPrice += menuItem.getPrice();

    }

    public Collection<MenuItem> getMenuItems()
    {
        return menuItems;
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
    public void setRating(double rating)
    {
        this.totalRating = rating;
    }

    @Override
    public void setCalories(double calories)
    {
        this.totalCalories = calories;
    }

    @Override
    public double getRating()
    {
        return this.totalRating;
    }

    @Override
    public double getPrice()
    {
        return this.totalPrice;
    }

    @Override
    public void setProteins(double proteins)
    {
        this.totalProteins = proteins;
    }

    @Override
    public void setFats(double fats)
    {
        this.totalFats = fats;
    }

    @Override
    public void setSodium(double sodium)
    {
        this.totalSodium = sodium;
    }

    @Override
    public void setPrice(double price)
    {
        this.totalPrice = price;
    }

    @Override
    public double getCalories()
    {
        return this.totalCalories;
    }

    @Override
    public double getProteins()
    {
        return this.totalProteins;
    }

    @Override
    public double getFats()
    {
        return this.totalFats;
    }

    @Override
    public double getSodium()
    {
        return this.totalSodium;
    }

    @Override
    public String getLowerName()
    {
        return this.name.toLowerCase(Locale.ROOT);
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("CompositeProduct{ name = \"").append(this.name).append("\", Components: [");

        boolean hadItem = false;

        for (MenuItem item : this.menuItems)
        {
            builder.append(item.toString());
            if(hadItem)
            {
                builder.append(", ");
            }
            hadItem = true;
        }

        builder.append("]}");

        return builder.toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
