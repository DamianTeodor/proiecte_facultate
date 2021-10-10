package dataAccessLayer;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class does the interaction with the database
 * @param <T> represents one of these Classes: Client, Order and Product
 */

public class AbstractDAO <T>
{
    private Logger logger = Logger.getLogger(AbstractDAO.class.getName());
    private Class<T> type;

    public AbstractDAO()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE `" + field + "` =?");
        return stringBuilder.toString();
    }

    /**
     * This mehtod retrieves all data from the table
     * @return a list of objects representing rows in the table
     */
    public List<T> findAll()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `" + type.getSimpleName().toLowerCase() + "`";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        }
        catch (SQLException e)
        {
            logger.log(Level.WARNING, type.getName() + "DAO : findAll " + e.getMessage());
        }
        finally
        {
            /*ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);*/
        }
        return null;
    }

    /**
     * This method retrieves from a table only the rows with the id equal to the parameter
     * @param id represents the searched id
     * @return an instance of the class T containing the data from the table
     */
    public T searchByID(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        }
        catch (SQLException e)
        {
            logger.log(Level.WARNING, type.getName() + "DAO : findByID " + e.getMessage());
        }
        finally
        {
        }
        return null;
    }

    /**
     * This method extracts the data from a ResultSet and creates a list of objects of class T, every object containing the data from a row
     * @param resultSet the set from which we extract the data
     * @return a list of objects of class T
     */
    public List<T> createObjects(ResultSet resultSet)
    {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++)
        {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0)
            {
                break;
            }
        }
        try
        {
            while (resultSet.next())
            {
                constructor.setAccessible(true);
                T entry;
                entry = (T) constructor.newInstance();
                for (Field field : type.getDeclaredFields())
                {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(entry, value);
                }
                list.add(entry);
            }
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (IntrospectionException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method extracts the names of the field of class T through reflection
     * @return an array containing the names of the fields
     */
    public Object[] getTableHeader()
    {
        Field[] fields = type.getDeclaredFields();
        Object[] header = new Object[fields.length];
        for (int i = 0; i < fields.length; i++)
        {
            String name = fields[i].getName();
            header[i] = (String.valueOf((name.charAt(0)))).toUpperCase() + name.substring(1, name.length());
        }
        return header;
    }

    /**
     * This method extracts the data from a list of objects and inserts it in a two dimensional array
     * @param list a list of objects of class T
     * @return a two dimensional array containing the data
     */
    public Object[][] getTable(List<T> list)
    {
        Field[] fields = type.getDeclaredFields();
        Object[][] table = new Object[list.size()][fields.length];
        try
        {
            for (int i = 0; i < list.size(); i++)
            {
                for (int j = 0; j < fields.length; j++)
                {
                    fields[j].setAccessible(true);
                    table[i][j] = fields[j].get(list.get(i));
                }
            }
            return table;
        }
        catch (IllegalAccessException e)
        {
            logger.log(Level.WARNING, type.getName() + "DAO : getTable " + e.getMessage());
        }
        return null;
    }

    /**
     * This method extracts the data from an object of class T and inserts it as a row in the corresponding table
     * @param t an object of class T
     */
    public int insert(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "insert into `" + type.getSimpleName() + "` values(";
        try
        {
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value instanceof String)
                {
                    query = query + "'" + value.toString() + "',";
                }
                else
                {
                    query = query + value.toString() + ",";
                }
            }
            query = query.substring(0, query.length() - 1) + ")";
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            statement.execute();
            return 1;
        }
        catch (SQLException | IllegalAccessException e)
        {
            logger.log(Level.WARNING, type.getName() + "DAO : insert " + e.getMessage());
            return -1;
        }
        finally
        {
            /*ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);*/
        }
    }

    /**
     * This method extracts the data from the object of class T and updates the row with the id of the object T in the corresponding table
     * @param t an object of class T
     */
    public void update (T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "update `" + type.getSimpleName() + "` set ";
        String id = null;
        try
        {
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                Object value = field.get(t);
                if (field.getName().equals("id"))
                {
                    id = (String) value;
                }
                else
                {
                    if (value instanceof String)
                    {
                        query = query + field.getName() + "='" + value + "',";
                    }
                    else
                    {
                        query = query + field.getName() + "=" + value.toString() + ",";
                    }
                }
            }
            query = query.substring(0, query.length() - 1) + " where id=" + id;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            statement.execute();
        }
        catch (SQLException | IllegalAccessException e)
        {
            logger.log(Level.WARNING, type.getName() + "DAO : update " + e.getMessage());
        }
        finally
        {
        }
    }

    /**
     * This method extracts the data from the object of class T and deletes the row with the id of the object T in the corresponding table
     * @param t an object of class T
     */
    public void delete (T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "delete from `" + type.getSimpleName() + "` where id=";
        String id = null;
        try
        {
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                Object value = field.get(t);
                if (field.getName().equals("id"))
                {
                    id = (String) value;
                }
            }
            query = query + id;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            statement.execute();
        }
        catch (SQLException | IllegalAccessException e)
        {
            logger.log(Level.WARNING, type.getName() + "DAO : delete " + e.getMessage());
        }
        finally
        {
        }
    }
}