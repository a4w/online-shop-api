package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class RegisterAPI implements HttpHandler {
    HTTPExchangeParser myParser = null;
    JSONObject jsonObject = null ;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        myParser = new DefaultParser(exchange);
        String []urlParameters=  myParser.getURLpath();
        jsonObject = myParser.parseBody();
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

    private void registerCustomer(HttpExchange exchange){
        Customer customer = getCustomerFromJSON();
        customer.register();
        //todo handle the exceptions in setting the attributes
    }
    private void registerStoreOwner(HttpExchange exchange){
        StoreOwner storeOwner = getStoreOwnerFromJSON();
        storeOwner.register();
        //todo handle the exceptions in setting the attributes
    }
    private void registerAdmin(HttpExchange exchange){
        Admin admin = getAdminFromJSON();
        admin.register();
        //todo handle the exceptions in setting the attributes
    }
    private Customer getCustomerFromJSON(){
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("email").toString();
        long userID = jsonObject.getLong("userID");
        return new Customer(userID,username , email,password) ;
    }
    private Admin getAdminFromJSON(){
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("email").toString();
        long userID = jsonObject.getLong("userID");
        return new Admin(userID,username , email,password) ;
    }
    private StoreOwner getStoreOwnerFromJSON(){
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("email").toString();
        long userID = jsonObject.getLong("userID");
        return new StoreOwner(userID,username , email,password) ;
    }

}
