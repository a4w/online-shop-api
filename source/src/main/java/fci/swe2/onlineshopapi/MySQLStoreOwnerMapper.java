package fci.swe2.onlineshopapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class MySQLStoreOwnerMapper implements Repository<StoreOwner> {

    private static MySQLStoreOwnerMapper instance = null;

    private MySQLStoreOwnerMapper(){
        dbConnection = DatabaseConnection.getConnection();
    }

    public static MySQLStoreOwnerMapper getInstance(){
        if(instance == null)
            instance = new MySQLStoreOwnerMapper();
        return instance;
    }

    public boolean exists(long id){
        return false;
    }

    public StoreOwner retrieve(long id){
        return null;
    }

    public StoreOwner[] retrieveAll(){
        System.out.println("Retrieving all store owners");
        PreparedStatement stmt = dbConnection.prepareStatement("Select * from StoreOwner");
        ResultSet result = stmt.executeQuery();
        ArrayList owners = new ArrayList();
        int i=0;
        while(result.next){
            long ID = result.getLong(1);
            String username = result.getString(2);
            String email = result.getString(3);
            String password = result.getString(4);
            StoreOwner ob = new StoreOwner(ID, username, email, password);
            owners.add(ob);
        }
        return owners;
    }
    public void store(StoreOwner obj) throws ValidationException{
        try {
            System.out.println("Storing a user");
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO `StoreOwner` (`email`, `username`, `password`) VALUES (?, ?, ?)");
            bindStoreOwner(obj, stmt);
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
    public void update(StoreOwner obj){}
    public void delete(StoreOwner obj){}

    private void bindStoreOwner(StoreOwner owner, PreparedStatement stmt) throws SQLException{
        stmt.setString(1, owner.getEmail());
        stmt.setString(2, owner.getUsername());
        stmt.setString(3, owner.getPassword());
    }
}
