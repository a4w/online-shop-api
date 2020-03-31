package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import fci.swe2.onlineshopapi.exceptions.ValidationException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory.Type;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;

public class RegisterAPI implements HttpHandler {
    HTTPExchangeParser myParser = null;
    JSONObject getRequestJson = null ;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        myParser = new DefaultParser(exchange);
        String []urlParameters=  myParser.getURLpath();
        try{
            getRequestJson = myParser.parseBody();
        }catch(Exception e){
            JSONObject jobj = new JSONObject();
            jobj.put("error", true);
            jobj.put("error_type", "Malformed request");
            sendResponse(exchange, jobj);
        }
        if(urlParameters.length <2){
            /// todo error
            return;
        }
        String userType= urlParameters[1]; /// register/user , register/admin , register/storeowner
        switch (userType){
            case "customer":
                this.registerCustomer(exchange);
                break;
            case "admin":
                //this.registerAdmin(exchange);
                break;
            case "storeowner":
                //this.registerStoreOwner(exchange);
                break;
            default:
                ///todo
                break;
        }
    }

    private void registerCustomer(HttpExchange exchange){
        Serializer<Customer> customerWrapper = SerializerFactory.getSerializer(Customer.class, Type.JSON); // code to implementation we need a solution
        Customer customer = customerWrapper.unserialize(getRequestJson.toString());
        registerAccount(exchange,customer);
    }

    private void registerStoreOwner(HttpExchange exchange){
        Serializer<StoreOwner> storeOwnerSerializer = SerializerFactory.getSerializer(StoreOwner.class , Type.JSON);
        StoreOwner storeOwner =  storeOwnerSerializer.unserialize(getRequestJson.toString());
        registerAccount(exchange , storeOwner);
    }
    private void registerAdmin(HttpExchange exchange){
        Serializer<Admin> adminSerializer = SerializerFactory.getSerializer(Admin.class , Type.JSON);
        Admin admin = adminSerializer.unserialize(getRequestJson.toString());
        registerAccount(exchange,admin);
    }

    private void registerAccount(HttpExchange exchange,Account account){
        try {
            account.register();
        }catch (ValidationException e){
            Serializer<UserFriendlyError> serializer = SerializerFactory.getSerializer(UserFriendlyError.class, Type.JSON);
            sendResponse(exchange, serializer.serialize(e));
        }
    }
    private void sendResponse(HttpExchange exchange,JSONObject jsonObject){
        String response = jsonObject.toString();
        try {
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            /// todo
            e.printStackTrace();
        }
    }
    private void sendResponse(HttpExchange exchange,String str){
        try {
            exchange.sendResponseHeaders(200, str.length());
            OutputStream os = exchange.getResponseBody();
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            /// todo
            e.printStackTrace();
        }
    }
}
