package fci.swe2.onlineshopapi.MySQLMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import fci.swe2.onlineshopapi.AccountRepository;
import fci.swe2.onlineshopapi.Admin;
import fci.swe2.onlineshopapi.DatabaseConnection;
import fci.swe2.onlineshopapi.Repository;
import fci.swe2.onlineshopapi.exceptions.ValidationException;

public class MySQLAdminMapper implements Repository<Admin>, AccountRepository<Admin> {

    private static MySQLAdminMapper instance = null;
    private Connection dbConnection;

    private MySQLAdminMapper(){
        dbConnection = DatabaseConnection.getConnection();
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
        ArrayList<Admin> admins = new ArrayList<>();
        try{
            PreparedStatement stmt = dbConnection.prepareStatement("Select * from `Admin`");
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                long ID = result.getLong(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                Admin admin = new Admin(ID, username, email, password);
                admins.add(admin);
            }
        }catch(SQLException e){ }
        Admin[] adminsa = new Admin[admins.size()];
        return admins.toArray(adminsa);
    }

    public void store(Admin obj) throws ValidationException{
        try {
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

    @Override
    public Admin findByUsername(String obj) {
        Admin[]admins = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM `Admin` where username =?");
            stmt.setString(1 ,obj);
            admins= getAdmins(stmt);
        }catch(SQLException e){
            ///todo
        }
        try {
            handleGetEmailAndUsername(admins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins[0];
    }

    @Override
    public Admin findByEmail(String obj) {
        Admin[]admins = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM `Admin` where email =?");
            stmt.setString(1 ,obj);
            admins= getAdmins(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            handleGetEmailAndUsername(admins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins[0];
    }
    private void handleGetEmailAndUsername(Admin []admins) throws Exception {
        if(admins.length <1 || admins.length > 1){
            /// todo WE NEED TO THROW EXCEPTION
            throw  new Exception("wrong query");

        }
    }
    private Admin getAdminfromRow(ResultSet result ){
        long ID = 0;
        Admin admin = null;
        try {
            ID = result.getLong(1);
            String username = result.getString(3);
            String email = result.getString(2);
            String password = result.getString(4);
            admin = new Admin(ID, username, email, password);
        } catch (SQLException e) {
            /// todo
            e.printStackTrace();
        }
        return admin;
    }
    private Admin[] getAdmins(PreparedStatement stmt){
        ArrayList<Admin> admins = new ArrayList<>();
        try{
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                Admin admin = getAdminfromRow(result);
                admins.add(admin);
            }
        }catch(SQLException e){ }
        Admin[] adminsa = new Admin[admins.size()];
        return admins.toArray(adminsa);
    }

}
