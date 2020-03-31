package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fci.swe2.onlineshopapi.dataWrappers.CustomerWrapper;
import fci.swe2.onlineshopapi.dataWrappers.JsonCustomerWrapper;
import fci.swe2.onlineshopapi.exceptions.EmailAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.PasswordTooShortException;
import fci.swe2.onlineshopapi.exceptions.UsernameAlreadyExistsException;
import fci.swe2.onlineshopapi.exceptions.ValidationException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

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
            jobj.put("message", "Malformed request");
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
        CustomerWrapper customerWrapper = new JsonCustomerWrapper(); /// todo this is code to implementation we need a solution
        Customer customer = customerWrapper.getCustomer(getRequestJson.toString());
        registerAccount(exchange,customer);
    }
    private void registerStoreOwner(HttpExchange exchange){
        StoreOwner storeOwner = getStoreOwnerFromJSON();
        registerAccount(exchange,storeOwner);
    }
    private void registerAdmin(HttpExchange exchange){
        Admin admin = getAdminFromJSON();
        registerAccount(exchange,admin);
    }
    private void registerAccount(HttpExchange exchange,Account account){
        try {
            account.register();
        } catch (EmailAlreadyExistsException e) {
            sendResponse(exchange , emailExceptionJson());
        }
        catch (UsernameAlreadyExistsException e){
            sendResponse(exchange,userNameExceptionJson());
        }
        catch (PasswordTooShortException e){
            sendResponse(exchange , passwordExceptionJson());
        }
        catch (ValidationException e){

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
    private Customer getCustomerFromJSON(){
        String email= getRequestJson.get("email").toString();
        String username= getRequestJson.get("username").toString();
        String password= getRequestJson.get("password").toString();
        return new Customer(0, username , email,password) ;
    }
    private Admin getAdminFromJSON(){
        String email= getRequestJson.get("email").toString();
        String username= getRequestJson.get("username").toString();
        String password= getRequestJson.get("email").toString();
        long userID = getRequestJson.getLong("userID");
        return new Admin(userID,username , email,password) ;
    }
    private StoreOwner getStoreOwnerFromJSON(){
        String email= getRequestJson.get("email").toString();
        String username= getRequestJson.get("username").toString();
        String password= getRequestJson.get("email").toString();
        long userID = getRequestJson.getLong("userID");
        return new StoreOwner(userID,username , email,password) ;
    }
    /// to be removed
    private JSONObject emailExceptionJson(){
        JSONObject json =  new JSONObject();
        json.put("error" ,"true");
        json.put("error_type" , "Email already exists");
        return json;
    }
    private JSONObject userNameExceptionJson(){
        JSONObject json =  new JSONObject();
        json.put("error" ,"true");
        json.put("error_type" , "username already exists");
        return json;
    }
    private JSONObject passwordExceptionJson(){
        JSONObject json =  new JSONObject();
        json.put("error" ,"true");
        json.put("error_type" , "Password already exists");
        return json;
    }


}
