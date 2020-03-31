package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public abstract class API implements HttpHandler {
    HTTPExchangeParser parser = null;
    HttpExchange exchange = null;
    String requestBody = null;

    @Override
    abstract public void handle(HttpExchange exchange);

    void setParserExchange(HttpExchange exchange){
        this.parser = new DefaultParser(exchange);
        this.exchange = exchange;
        try{
            this.requestBody = parser.parseBody();
        }catch(Exception e){
            // TODO: Error occured
        }
    }

    protected void sendResponse(String str){
        try {
            exchange.sendResponseHeaders(200, str.length());
            OutputStream os = exchange.getResponseBody();
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            /// TODO
            e.printStackTrace();
        }
    }

}
