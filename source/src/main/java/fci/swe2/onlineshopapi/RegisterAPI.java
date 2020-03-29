package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RegisterAPI implements HttpHandler {
    HTTPExchangeParser myParser = null;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        myParser = new DefaultParser(exchange);
        String []urlParameters=  myParser.getURLpath();
        if(urlParameters.length <2){
            /// todo error
            return;
        }
        String userType= urlParameters[1]; /// register/user , register/admin , register/storeowner
        switch (userType){
            case "user":
                this.registerCustomer(exchange);
                break;
            case "admin":
                this.registerAdmin(exchange);
                break;
            case "storeowner":
                this.registerStoreOwner(exchange);
                break;
            default:
                ///todo
                break;
        }
    }

    public void registerCustomer(HttpExchange exchange){

    }
    public void registerStoreOwner(HttpExchange exchange){}
    public void registerAdmin(HttpExchange exchange){}

}
