package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import fci.swe2.onlineshopapi.exceptions.ValidationException;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory.Type;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;

public class RegisterAPI extends API {

    @Override
    public void handle(HttpExchange exchange){
        try{
            this.setParserExchange(exchange);
        }catch(Exception e){
            // Do nothing
            return;
        }

        String[] urlParameters = parser.getURLpath();

        if(urlParameters.length < 2){
            sendMalformedRequestError();
            return;
        }

        String userType= urlParameters[1]; /// register/user , register/admin , register/storeowner
        switch (userType){
            case "customer":
                this.registerCustomer();
                break;
            case "admin":
                this.registerAdmin();
                break;
            case "storeowner":
                this.registerStoreOwner();
                break;
            default:
                sendMalformedRequestError();
                break;
        }
    }

    private void registerCustomer(){
        Serializer<Customer> customerWrapper = SerializerFactory.getSerializer(Customer.class, this.responseType);
        Customer customer = customerWrapper.unserialize(this.requestBody);
        registerAccount(customer);
    }

    private void registerStoreOwner(){
        Serializer<StoreOwner> storeOwnerSerializer = SerializerFactory.getSerializer(StoreOwner.class , this.responseType);
        StoreOwner storeOwner =  storeOwnerSerializer.unserialize(this.requestBody);
        registerAccount(storeOwner);
    }

    private void registerAdmin(){
        Serializer<Admin> adminSerializer = SerializerFactory.getSerializer(Admin.class , this.responseType);
        Admin admin = adminSerializer.unserialize(this.requestBody);
        registerAccount(admin);
    }

    private void registerAccount(Account account){
        try {
            account.register();
            sendOkRequest();
        }catch (ValidationException e){
            Serializer<UserFriendlyError> serializer = SerializerFactory.getSerializer(UserFriendlyError.class, this.responseType);
            sendResponse(serializer.serialize(e));
        }
    }
}
