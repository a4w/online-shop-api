package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import fci.swe2.onlineshopapi.dataWrappers.LoginRequestWrapper;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;

public class LoginAPI extends API {
    @Override
    public void handle(HttpExchange exchange) {
        try{
            this.setParserExchange(exchange);
        }catch(Exception e){
            // Do nothing
            return;
        }

        String[] urlParameters = parser.getURLpath();

        if(urlParameters.length != 2){
            sendMalformedRequestError();
            return;
        }

        String userType= urlParameters[1]; /// login/user , login/admin , login/storeowner
        switch (userType){
            case "customer":
                this.loginCustomer();
                break;
            case "admin":
                this.loginAdmin();
                break;
            case "storeowner":
                this.loginStoreOwner();
                break;
            default:
                sendMalformedRequestError();
                break;
        }
    }

    private void loginCustomer(){
        Serializer<LoginRequestWrapper> serializer = SerializerFactory.getSerializer(LoginRequestWrapper.class,this.responseType);
        LoginRequestWrapper wrapper = serializer.unserialize(this.requestBody);
        Customer customer = new Customer();
        customer.setUsername(wrapper.getUsername());
        customer.setPassword(wrapper.getPassword());
        loginAccount(customer);
    }

    private void loginAdmin(){
        Serializer<LoginRequestWrapper> serializer = SerializerFactory.getSerializer(LoginRequestWrapper.class,this.responseType);
        LoginRequestWrapper wrapper = serializer.unserialize(this.requestBody);
        Admin admin = new Admin();
        admin.setUsername(wrapper.getUsername());
        admin.setPassword(wrapper.getPassword());
        loginAccount(admin);
    }

    private void loginStoreOwner(){
        Serializer<LoginRequestWrapper> serializer = SerializerFactory.getSerializer(LoginRequestWrapper.class,this.responseType);
        LoginRequestWrapper wrapper = serializer.unserialize(this.requestBody);
        StoreOwner storeOwner = new StoreOwner();
        storeOwner.setUsername(wrapper.getUsername());
        storeOwner.setPassword(wrapper.getPassword());
        loginAccount(storeOwner);
    }
    /*
    private void loginAccount(){

        Admin admin = new Admin(1,"admin","email@gmail.com","123456789");
        loginAccount((Account) admin);
    }
    */

    private void loginAccount(Account account){
        sendResponse(account.login());
    }
}
