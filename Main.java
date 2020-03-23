import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8080), 0);
        server.createContext("/user", new UserAPI());
        server.start();
    }
};

class Parser{

};

class UserAPI implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String endpoint = exchange.getRequestURI().toString();
        String[] parts = endpoint.split("/");
        final String action = parts[2];
        switch(action){
            case "register":{
                this.register(exchange);
                break;
            }
        }
    }

    private void register(HttpExchange exchange){
        // Parse JSON
        // Create Customer object
        // ActionManger -> register
        // ActionMapper.register(customer);
    }

};

// API 
// UserAPI
// StoreAPI
// ProductAPI

abstract class Account{
};

class Customer extends Account{
};

class MapperFactory{
    private Map<Object, Object> repos;
    private <T> void register(Class<T> clss, DataMapper<T> mapper){
        this.repos.put(clss, mapper);
    }
};

interface DataMapper<T>{
    void save(T obj);
}
class sqlCustomerMapper implements DataMapper<Customer>{
    public void save(Customer cust){
        System.out.println("Saved Customer!!");
    }
}

class ActionMapper{
    public static void register(Account account){
    }
};