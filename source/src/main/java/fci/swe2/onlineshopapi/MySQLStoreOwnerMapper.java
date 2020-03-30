package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.EmailAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;
import fci.swe2.onlineshopapi.exceptions.UsernameAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.ValidationException;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.sql.*;

public class MySQLStoreOwnerMapper implements Repository<StoreOwner> {

    private static MySQLStoreOwnerMapper instance = null;
    private Connection dbConnection;
    private MySQLStoreOwnerMapper(){
        // Initialize stuff
        dbConnection = DatabaseConnection.getConnection();
    }

    public static MySQLStoreOwnerMapper getInstance(){
        if(instance == null)
            instance = new MySQLStoreOwnerMapper();
        return instance;
    }

    public boolean exists(long id) {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT `id` FROM `StoreOwner` WHERE `id` = ?");
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return true;
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        return false;
    }

    public StoreOwner retrieve(long id) throws ObjectNotFoundException {
        try{
            PreparedStatement statement = dbConnection.prepareStatement("SELECT `id` FROM `StoreOwner` WHERE `id` = ?");
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                return  createStoreOwner(result);
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        throw new ObjectNotFoundException();
    }

    private StoreOwner createStoreOwner(ResultSet result) throws SQLException {
        long id = result.getLong("id");
        String email = result.getString("email");
        String username = result.getString("username");
        String password = result.getString("password");
        return new StoreOwner(id,username,email,password);
    }


    public StoreOwner[] retrieveAll() throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM `StoreOwner`");
        PreparedStatement count = dbConnection.prepareStatement("SELECT COUNT(*) FROM `StoreOwner`");
        ResultSet rows = count.executeQuery();
        rows.next();
        StoreOwner  arr [];
        final  int COUNT = rows.getInt("COUNT(*)");
        arr = new StoreOwner[COUNT];
        ResultSet result = statement.executeQuery();
        int idx = 0;
        while (result.next()){
            arr[idx++] = createStoreOwner(result);
        }
        return arr;
    }
    public void store(StoreOwner obj) throws ValidationException, SQLException {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO `StoreOwner` ('id', `email`, `username`, `password`) VALUES (?, ?, ?, ?)");
            bindStoreOwner(obj,statement);
            statement.executeUpdate();
        }
        catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            if(e.getMessage().contains("email")){
                throw new EmailAlreadyExistsException();
            }
            else if(e.getMessage().contains("username")){
                throw new UsernameAlreadyExistsException();
            }
        }
    }
    /// updates all info(except it) of matches row with same id
    public void update(StoreOwner obj)  {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE StoreOwner set email = ? , username = ? , password = ? " +
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
    public void delete(StoreOwner obj) {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM StoreOwner WHERE id = ?");
            statement.setLong(1, obj.getUserID());
            statement.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }
    private void bindStoreOwner(StoreOwner obj,PreparedStatement statement) throws SQLException {
        statement.setLong(1,obj.getUserID());
        statement.setString(2,obj.getEmail());
        statement.setString(3,obj.getUsername());
        statement.setString(4,obj.getPassword());
    }
}
