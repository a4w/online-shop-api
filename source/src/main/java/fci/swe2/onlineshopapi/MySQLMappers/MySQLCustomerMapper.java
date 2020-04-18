package fci.swe2.onlineshopapi.MySQLMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import fci.swe2.onlineshopapi.AccountRepository;
import fci.swe2.onlineshopapi.Customer;
import fci.swe2.onlineshopapi.DatabaseConnection;
import fci.swe2.onlineshopapi.Repository;
import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;
import fci.swe2.onlineshopapi.exceptions.ValidationException;

public class MySQLCustomerMapper implements Repository<Customer>, AccountRepository<Customer> {
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
            if(!result.isAfterLast()){
                result.next();
                return createCustomerFromRow(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ObjectNotFoundException();
    }

    public Customer[] retrieveAll(){
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            PreparedStatement stmt = dbConnection.prepareStatement("Select * from `Customer`");
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                long ID = result.getLong(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                Customer ob = new Customer(ID, username, email, password);
                customers.add(ob);
            }
        }catch(SQLException e){ }
        Customer[] customersa = new Customer[customers.size()];
        return customers.toArray(customersa);
    }

    public void store(Customer obj) throws ValidationException{
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO `Customer` (`email`, `username`, `password`) VALUES (?, ?, ?)");
            bindCustomer(obj, stmt);
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            final String message = e.getMessage();
            // TODO: Maybe find a better way to handle this (this may cause wrong error messages if the value contains a key word)
            if(message.contains("email")){
                throw new ValidationException("Email already exists");
            }else if(message.contains("username")){
                throw new ValidationException("Username already exists");
            }
        } catch (SQLException e){
            throw new ValidationException("An error occured please try again later");
        }
    }
    public void update(Customer obj){}
    public void delete(Customer obj){}

    private Customer createCustomerFromRow(ResultSet result) throws SQLException{
        final long id = result.getLong("id");
        final String email = result.getString("email");
        final String username = result.getString("username");
        final String password = result.getString("password");
        return new Customer(id, username, email, password);
    }

    private void bindCustomer(Customer customer, PreparedStatement stmt) throws SQLException{
        stmt.setString(1, customer.getEmail());
        stmt.setString(2, customer.getUsername());
        stmt.setString(3, customer.getPassword());
    }

    @Override
    public Customer findByUsername(String obj) throws ObjectNotFoundException {
        Customer[]customers = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM `Customer` where username =?");
            stmt.setString(1 ,obj);
            customers= getCustomers(stmt);
        }catch(SQLException e){
            ///todo
        }
        singleResult(customers);
        return customers[0];
    }

    @Override
    public Customer findByEmail(String obj) throws ObjectNotFoundException {
        Customer[]customers = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM `Customer` where email =?");
            stmt.setString(1 ,obj);
            customers= getCustomers(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            /// todo throws it dont catch it = done
        singleResult(customers);
        return customers[0];
    }
    private void singleResult(Customer []customers) throws ObjectNotFoundException {
        if(customers.length <1 || customers.length > 1){
            /// case > 1 will never happen because we use with email and username and they are unique in database
            ///todo object ffrom object not found exception= done
            throw  new ObjectNotFoundException();
//            throw  new Exception("wrong query");

        }
    }
    private Customer getCustomerfromRow(ResultSet result ){
        long ID = 0;
        Customer customer = null;
        try {
            ID = result.getLong(1);
            String username = result.getString(3);
            String email = result.getString(2);
            String password = result.getString(4);
            customer = new Customer(ID, username, email, password);
        } catch (SQLException e) {
            /// todo
            e.printStackTrace();
        }
        return customer;
    }
    private Customer[] getCustomers(PreparedStatement stmt){
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                Customer customer = getCustomerfromRow(result);
                customers.add(customer);
            }
        }catch(SQLException e){ }
        Customer[] customersa = new Customer[customers.size()];
        return customers.toArray(customersa);
    }
}
