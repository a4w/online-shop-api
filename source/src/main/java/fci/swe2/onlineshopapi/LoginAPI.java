package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import fci.swe2.onlineshopapi.dataWrappers.LoginRequestWrapper;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;
import fci.swe2.onlineshopapi.dataWrappers.TokenWrapper;
import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import fci.swe2.onlineshopapi.exceptions.InvalidCredentialsException;

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
                this.loginAccount(new Customer());
                break;
            case "admin":
                this.loginAccount(new Admin());
                break;
            case "storeowner":
                this.loginAccount(new StoreOwner());
                break;
            default:
                sendMalformedRequestError();
                break;
        }
    }

    private void loginAccount(Account account){
        try{
            LoginRequestWrapper wrapper = SerializerFactory.defaultUnserialize(LoginRequestWrapper.class, this.requestBody, this.responseType);
            account.setUsername(wrapper.getUsername());
            account.setPassword(wrapper.getPassword());
            final String token = account.login();
            TokenWrapper tokenObject = new TokenWrapper(token);
            sendResponseObject(tokenObject, 200);
        }catch(InvalidCredentialsException e){
            sendResponseObjectAs(UserFriendlyError.class, e, 401);
        }
    }
}
