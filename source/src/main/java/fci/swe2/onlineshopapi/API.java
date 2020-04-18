package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fci.swe2.onlineshopapi.JWT.UserType;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory.Type;
import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import fci.swe2.onlineshopapi.exceptions.ValidationException;
import io.jsonwebtoken.Claims;
import fci.swe2.onlineshopapi.exceptions.MalformedRequestException;
import fci.swe2.onlineshopapi.exceptions.UnauthorizedRequestException;

import java.io.IOException;
import java.io.OutputStream;

public abstract class API implements HttpHandler {
    HTTPExchangeParser parser = null;
    HttpExchange exchange = null;
    String requestBody = null;
    SerializerFactory.Type responseType = Type.JSON;
    Account loggedInAccount = null;

    @Override
    abstract public void handle(HttpExchange exchange);

    void setParserExchange(HttpExchange exchange) throws Exception{
        this.loggedInAccount = null;
        this.exchange = exchange;
        this.parser = new DefaultParser(exchange);
        try{
            this.requestBody = parser.parseBody();

        }catch(Exception e){
            sendMalformedRequestError();
            throw e;
        }
        Headers headers = exchange.getRequestHeaders();
        String contentType = headers.getFirst("Content-Type");
        try {
            switch (contentType) {
                case "application/json":
                    this.responseType = Type.JSON;
                    break;
                // Add other return types here if required

                default:
                    sendWrongContentTypeError();
                    throw new Exception(); // To be catched in caller
            }
        }
        catch (Exception e){
            sendWrongContentTypeError();
            throw e;
        }
        // Authorization
        try{
            final String jwt = headers.getFirst("Authorization");
            Claims claims = JWT.decodeJWT(jwt);
            // TODO: Find a better way - maybe factory
            final UserType type = UserType.valueOf(claims.get("user_type", String.class));
            final Long user_id = claims.get("user_id", Long.class);
            System.out.println(type);
            System.out.println(user_id);
            switch(type){
                case CUSTOMER:{
                    Repository<Customer> repo = RepoFactory.getMapper(Customer.class);
                    this.loggedInAccount = repo.retrieve(user_id);
                    break;
                }
                case ADMIN:{
                    Repository<Admin> repo = RepoFactory.getMapper(Admin.class);
                    this.loggedInAccount = repo.retrieve(user_id);
                    break;
                }
                case STORE_OWNER:{
                    Repository<StoreOwner> repo = RepoFactory.getMapper(StoreOwner.class);
                    this.loggedInAccount = repo.retrieve(user_id);
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void sendResponse(String str){
        sendResponseObject(str, 200);
    }

    protected void sendResponse(String str, int statusCode){
        try {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, str.length());
            OutputStream os = exchange.getResponseBody();
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendWrongContentTypeError(){
        UserFriendlyError error = new ValidationException("The requested content type is not supported");
        sendResponseObjectAs(UserFriendlyError.class, error, 415);
    }

    protected <T> void sendResponseObject(T object, int statusCode){
        String response = SerializerFactory.defaultSerialize(object, this.responseType);
        sendResponse(response, statusCode);
    }

    protected <T> void sendResponseObjectAs(Class<T> clazz, T object, int statusCode){
        String response = SerializerFactory.defaultSerialize(clazz, object, this.responseType);
        sendResponse(response, statusCode);
    }

    protected void sendUnauthorizedResponse(){
        UserFriendlyError error = new UnauthorizedRequestException();
        sendResponseObjectAs(UserFriendlyError.class, error, 401);
    }

    protected void sendMalformedRequestError(){
        UserFriendlyError error = new MalformedRequestException();
        sendResponseObjectAs(UserFriendlyError.class, error, 400);
    }

    protected void sendOkRequest(){
        sendResponseObject("Request completed successfully", 200);
    }

}
