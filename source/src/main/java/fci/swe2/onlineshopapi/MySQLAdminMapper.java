package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.EmailAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;
import fci.swe2.onlineshopapi.exceptions.UsernameAlreadyExistsException;

import java.sql.*;

public class MySQLAdminMapper implements Repository<Admin> {

    private static MySQLAdminMapper instance = null;
    private Connection dbConnection;
    private MySQLAdminMapper(){
        // Initialize stuff
        dbConnection = DatabaseConnection.getConnection();
    }

    public static MySQLAdminMapper getInstance(){
        if(instance == null)
            instance = new MySQLAdminMapper();
        return instance;
    }
    public boolean exists(long id){
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT `id` FROM `Admin` WHERE `id` = ?");
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

    public Admin retrieve(long id) throws ObjectNotFoundException {
        try{
            PreparedStatement statement = dbConnection.prepareStatement("SELECT `id` FROM `Admin` WHERE `id` = ?");
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                return  createAdmin(result);
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        throw new ObjectNotFoundException();
    }

    private Admin createAdmin(ResultSet result) throws SQLException {
        long id = result.getLong("id");
        String email = result.getString("email");
        String username = result.getString("username");
        String password = result.getString("password");
        return new Admin(id,username,email,password);
    }

    public Admin[] retrieveAll() throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM `Admin`");
        PreparedStatement count = dbConnection.prepareStatement("SELECT COUNT(*) FROM `Admin`");
        ResultSet rows = count.executeQuery();
        rows.next();
        Admin  arr [];
        final  int COUNT = rows.getInt("COUNT(*)");
        arr = new Admin[COUNT];
        ResultSet result = statement.executeQuery();
        int idx = 0;
        while (result.next()){
            arr[idx++] = createAdmin(result);
        }
        return arr;
    }

    public void store(Admin obj) throws SQLException, EmailAlreadyExistsException, UsernameAlreadyExistsException {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO `Admin` ('id', `email`, `username`, `password`) VALUES (?, ?, ?, ?)");
            bindAdmin(obj,statement);
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

    private void bindAdmin(Admin obj, PreparedStatement statement) throws SQLException {
        statement.setLong(1,obj.getUserID());
        statement.setString(2,obj.getEmail());
        statement.setString(3,obj.getUsername());
        statement.setString(4,obj.getPassword());
    }

    public void update(Admin obj){
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE Admin set email = ? , username = ? , password = ? " +
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
    public void delete(Admin obj){
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM Admin WHERE id = ?");
            statement.setLong(1, obj.getUserID());
            statement.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
