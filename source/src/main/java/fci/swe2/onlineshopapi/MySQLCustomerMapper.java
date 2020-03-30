package fci.swe2.onlineshopapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import fci.swe2.onlineshopapi.exceptions.EmailAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;
import fci.swe2.onlineshopapi.exceptions.UsernameAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.ValidationException;

public class MySQLCustomerMapper implements Repository<Customer> {
    private static MySQLCustomerMapper instance = null;
    private Connection dbConnection;

    private MySQLCustomerMapper(){
        dbConnection = DatabaseConnection.getConnection();
    }

    public static MySQLCustomerMapper getInstance(){
        if(instance == null)
            instance = new MySQLCustomerMapper();
        return instance;
    }

    public boolean exists(long id){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT `id` FROM `Customer` WHERE `id` = ?");
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            if(!result.isAfterLast())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer retrieve(long id) throws ObjectNotFoundException{
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT `id`, `email`, `username`, `password` FROM `Customer` WHERE `id` = ?");
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            if(!result.isAfterLast())
                return createCustomerFromRow(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ObjectNotFoundException();
    }

    public Customer[] retrieveAll() throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM `Customer`");
        PreparedStatement count = dbConnection.prepareStatement("SELECT COUNT(*) FROM `Customer`");
        ResultSet rows = count.executeQuery();
        rows.next();
        Customer  arr [];
        final  int COUNT = rows.getInt("COUNT(*)");
        arr = new Customer [COUNT];
        ResultSet result = statement.executeQuery();
        int idx = 0;
        while (result.next()){
            arr[idx++] = createCustomerFromRow(result);
        }
        return arr;
    }

    public void store(Customer obj) throws ValidationException{
        try {
            System.out.println("Storing a user");
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO `Customer` (id,`email`, `username`, `password`) VALUES (?, ?, ?, ?)");
            bindCustomer(obj, stmt);
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            final String message = e.getMessage();
            // TODO: Maybe find a better way to handle this (this may cause wrong error messages if the value contains a key word)
            if(message.contains("email")){
                throw new EmailAlreadyExistsException();
            }else if(message.contains("username")){
                throw new UsernameAlreadyExistsException();
            }
        } catch (SQLException e){
            throw new ValidationException();
        }
    }
    public void update(Customer obj){
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE Customer set email = ? , username = ? , password = ? " +
                    "WHERE id = ?");
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getUsername());
            statement.setString(3, obj.getPassword());
            statement.setLong(4, obj.getUserID());
            statement.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
            //
        }
    }
    public void delete(Customer obj){
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM Customer WHERE id = ?");
            statement.setLong(1, obj.getUserID());
            statement.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    private Customer createCustomerFromRow(ResultSet result) throws SQLException{
        final long id = result.getLong("id");
        final String email = result.getString("email");
        final String username = result.getString("username");
        final String password = result.getString("password");
        return new Customer(id, username, email, password);
    }

    private void bindCustomer(Customer customer, PreparedStatement stmt) throws SQLException{
        stmt.setLong(1,customer.getUserID());
        stmt.setString(2, customer.getEmail());
        stmt.setString(3, customer.getUsername());
        stmt.setString(4, customer.getPassword());
    }
}
