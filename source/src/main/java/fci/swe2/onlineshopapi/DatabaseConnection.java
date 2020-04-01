package fci.swe2.onlineshopapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection{
    final String DB_HOST = "localhost";
    final String DB_NAME = "online-shop-api";
    final String DB_USER = "root";
    final String DB_PASS = "Socrat_1234";

    private static DatabaseConnection instance = null;

    private Connection connection;

    private DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstace(){
        if(instance == null)
            instance = new DatabaseConnection();
        return instance;
    }

    public static Connection getConnection(){
        DatabaseConnection me = DatabaseConnection.getInstace();
        return me.connection;
    }

};
