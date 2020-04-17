package fci.swe2.onlineshopapi.MySQLMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import fci.swe2.onlineshopapi.*;
import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;
import fci.swe2.onlineshopapi.exceptions.ValidationException;

public class MySQLStoreOwnerMapper implements Repository<StoreOwner>, AccountRepository<StoreOwner> {

    private static MySQLStoreOwnerMapper instance = null;
    private Connection dbConnection;

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

    public StoreOwner retrieve(long id) throws ObjectNotFoundException {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT `id`, `email`, `username`, `password` FROM `StoreOwner` WHERE `id` = ?");
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            if(!result.isAfterLast())
                return (createStoreOwnerFromRow(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ObjectNotFoundException();
    }

    public StoreOwner[] retrieveAll(){
        ArrayList<StoreOwner> owners = new ArrayList<>();
        try{
            PreparedStatement stmt = dbConnection.prepareStatement("Select * from `StoreOwner`");
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                long ID = result.getLong(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                StoreOwner ob = new StoreOwner(ID, username, email, password);
                owners.add(ob);
            }
        }catch(SQLException e){ }
        StoreOwner[] sowners = new StoreOwner[owners.size()];
        return owners.toArray(sowners);
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

    @Override
    public StoreOwner findByUsername(String obj) {
        StoreOwner[]storeowners = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM `StoreOwner` where username =?");
            stmt.setString(1 ,obj);
            storeowners= getStoreOwners(stmt);
        }catch(SQLException e){
            ///todo
        }
        try {
            handleGetEmailAndUsername(storeowners);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeowners[0];
    }

    @Override
    public StoreOwner findByEmail(String obj) {
        StoreOwner[]storeowners = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM `StoreOwner` where email =?");
            stmt.setString(1 ,obj);
            storeowners= getStoreOwners(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            handleGetEmailAndUsername(storeowners);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeowners[0];
    }
    private void handleGetEmailAndUsername(StoreOwner []storeowners) throws Exception {
        if(storeowners.length <1 || storeowners.length > 1){
            /// todo WE NEED TO THROW EXCEPTION
            throw  new Exception("wrong query");


        }
    }
    private StoreOwner getStoreOwnerfromRow(ResultSet result ){
        long ID = 0;
        StoreOwner storeowner = null;
        try {
            ID = result.getLong(1);
            String username = result.getString(3);
            String email = result.getString(2);
            String password = result.getString(4);
            storeowner = new StoreOwner(ID, username, email, password);
        } catch (SQLException e) {
            /// todo
            e.printStackTrace();
        }
        return storeowner;
    }

    private StoreOwner createStoreOwnerFromRow(ResultSet result) throws SQLException{
        final long id = result.getLong("id");
        final String email = result.getString("email");
        final String username = result.getString("username");
        final String password = result.getString("password");
        return new StoreOwner(id, username, email, password);
    }

    private StoreOwner[] getStoreOwners(PreparedStatement stmt){
        ArrayList<StoreOwner> storeowners = new ArrayList<>();
        try{
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                StoreOwner storeowner = getStoreOwnerfromRow(result);
                storeowners.add(storeowner);
            }
        }catch(SQLException e){ }
        StoreOwner[] storeownersa = new StoreOwner[storeowners.size()];
        return storeowners.toArray(storeownersa);
    }
}
