package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class RegisterAPI implements HttpHandler {
    public void registerCustomer(HttpExchange exchange){}
    public void registerStoreOwner(HttpExchange exchange){}
    public void registerAdmin(HttpExchange exchange){}

    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
