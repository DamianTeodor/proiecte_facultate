package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Client;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class manages the operations on the class Product performed on the database by the class ProductDAO
 */

public class ProductBL
{
    private ProductDAO productDAO;

    public ProductBL()
    {
        productDAO = new ProductDAO();
    }

    /**
     * This method creates an object of class Product and calls the method from ProductDAO to insert the data into the Product table
     * @param productID id of the product
     * @param name name of the product
     * @param manufacture manufacturer of the product
     * @param price price of the product
     * @param stock the amount of products available in the shop
     */
    public void update(String productID, String name, String manufacture, double price, int stock)
    {
        Product product = new Product();
        product.setId(productID);
        product.setName(name);
        product.setManufacture(manufacture);
        product.setPrice(price);
        product.setStock(stock);
        productDAO.update(product);
    }

    /**
     * This method deletes the product with the id recieved as parameter using the method in the ProductDAO class
     * @param id the id of the product wanted to be deleted
     */
    public void delete(String id)
    {
        productDAO.delete(productDAO.searchByID(Integer.parseInt(id)));
    }

    /**
     * This method creates an object of class Product and insert the data into the table using the method from the ProductDAO class
     * @param productID id of the product
     * @param Name name of the product
     * @param Manufacture manufacturer of the product
     * @param Price price of the product
     * @param Stock the amount of the products available in the shop
     */
    public void insert(String productID, String Name, String Manufacture, Double Price, int Stock)
    {
        Product product = new Product();
        product.setId(productID);
        product.setName(Name);
        product.setManufacture(Manufacture);
        product.setPrice(Price);
        product.setStock(Stock);
        productDAO.insert(product);
        //validators.get(0).validate(client);
    }

    /**
     * This method gets the data from the Product table calling the method from the ProductDAO class
     * @return a list of objects of Product class
     */
    public List<Product> findAll()
    {
        return productDAO.findAll();
    }

    /**
     * This method calls the getTableHeader method from ProductDAO to recieve the fields of the Product table
     * @return an array containing the names of the fields of the class Product
     */
    public Object[] getTableHeader()
    {
        Object[] header = productDAO.getTableHeader();
        return header;
    }
    /**
     * This method calls the getTable method from ProductDAO to get the data from a list of objects of class Product
     * @param productList a list of objects of class Product
     * @return a two dimensional array containing the data
     */
    public Object[][] getTable(List<Product> productList)
    {
        Object[][] table = productDAO.getTable(productList);
        return table;
    }
}
