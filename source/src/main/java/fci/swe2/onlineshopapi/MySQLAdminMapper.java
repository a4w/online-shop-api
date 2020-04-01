package fci.swe2.onlineshopapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class MySQLAdminMapper implements Repository<Admin> {

    private static MySQLAdminMapper instance = null;

    private MySQLAdminMapper(){
        // Initialize stuff
    }

    public static MySQLAdminMapper getInstance(){
        if(instance == null)
            instance = new MySQLAdminMapper();
        return instance;
    }

    public boolean exists(long id){
        return false;
    }

    public Admin retrieve(long id){
        return null;
    }

    public Admin[] retrieveAll(){
        System.out.println("Retrieving all Admins");
        PreparedStatement stmt = dbConnection.prepareStatement("Select * from Admin");
        ResultSet result = stmt.executeQuery();
        ArrayList admins = new ArrayList();
        int i=0;
        while(result.next){
            long ID = result.getLong(1);
            String username = result.getString(2);
            String email = result.getString(3);
            String password = result.getString(4);
            Admin ob = new Admin(ID, username, email, password);
            admins.add(ob);
        }
        return admins;
    }

    public void store(Admin obj){
        try {
            System.out.println("Storing a user");
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO `Admin` (`email`, `username`, `password`) VALUES (?, ?, ?)");
            bindAdmin(obj, stmt);
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
    public void update(Admin obj){}
    public void delete(Admin obj){}

    private void bindAdmin(Admin admin, PreparedStatement stmt) throws SQLException{
        stmt.setString(1, admin.getEmail());
        stmt.setString(2, admin.getUsername());
        stmt.setString(3, admin.getPassword());
    }
}
