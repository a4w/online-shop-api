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
        final String jwt = headers.getFirst("Authorization");
        try{
            Claims claims = JWT.decodeJWT(jwt);
            // TODO: Find a better way
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
            System.out.println("User: " + this.loggedInAccount.getUsername());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void sendResponse(String str){
        try {
            exchange.sendResponseHeaders(200, str.length());
            OutputStream os = exchange.getResponseBody();
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sendResponse(String str, int statusCode){
        try {
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
        Serializer<UserFriendlyError> serializer = SerializerFactory.getSerializer(UserFriendlyError.class, this.responseType);
        sendResponse(serializer.serialize(error), 415);
    }

    protected void sendMalformedRequestError(){
        UserFriendlyError error = new MalformedRequestException("Malformed request");
        Serializer<UserFriendlyError> serializer = SerializerFactory.getSerializer(UserFriendlyError.class, this.responseType);
        sendResponse(serializer.serialize(error), 400);
    }

    protected void sendOkRequest(){
        Serializer<String> serializer = SerializerFactory.getSerializer(String.class, this.responseType);
        sendResponse(serializer.serialize("Request completed successfully"));
    }

}
