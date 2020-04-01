package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory.Type;
import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import fci.swe2.onlineshopapi.exceptions.ValidationException;
import fci.swe2.onlineshopapi.exceptions.MalformedRequestException;

import java.io.IOException;
import java.io.OutputStream;

public abstract class API implements HttpHandler {
    HTTPExchangeParser parser = null;
    HttpExchange exchange = null;
    String requestBody = null;
    SerializerFactory.Type responseType = Type.JSON;

    @Override
    abstract public void handle(HttpExchange exchange);

    void setParserExchange(HttpExchange exchange) throws Exception{
        this.exchange = exchange;
        this.parser = new DefaultParser(exchange);
        try{
            this.requestBody = parser.parseBody();
        }catch(Exception e){
            System.out.println("Malformed ");
            sendMalformedRequestError();
            throw e;
        }
        Headers headers = exchange.getRequestHeaders();
        String contentType = headers.getFirst("Content-Type");
        switch(contentType){
            case "application/json":
                this.responseType = Type.JSON;
                break;
            // Add other return types here if required

            default:
                System.out.println("Content ");
                sendWrongContentTypeError();
                throw new Exception(); // To be catched in caller
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
