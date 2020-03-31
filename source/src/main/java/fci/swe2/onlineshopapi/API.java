package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory.Type;
import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import fci.swe2.onlineshopapi.exceptions.MalformedRequestException;

import java.io.IOException;
import java.io.OutputStream;

public abstract class API implements HttpHandler {
    HTTPExchangeParser parser = null;
    HttpExchange exchange = null;
    String requestBody = null;

    @Override
    abstract public void handle(HttpExchange exchange);

    void setParserExchange(HttpExchange exchange){
        this.exchange = exchange;
        this.parser = new DefaultParser(exchange);
        try{
            this.requestBody = parser.parseBody();
        }catch(Exception e){
            sendMalformedRequestError();
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

    protected void sendMalformedRequestError(){
        UserFriendlyError error = new MalformedRequestException("Malformed request");
        Serializer<UserFriendlyError> serializer = SerializerFactory.getSerializer(UserFriendlyError.class, Type.JSON);
        sendResponse(serializer.serialize(error));
    }

    protected void sendOkRequest(){
        Serializer<String> serializer = SerializerFactory.getSerializer(String.class, Type.JSON);
        sendResponse(serializer.serialize("Request completed successfully"));
    }

}
