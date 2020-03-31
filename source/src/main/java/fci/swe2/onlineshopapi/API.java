package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public abstract class API implements HttpHandler {
    HTTPExchangeParser myParser = null;
    HttpExchange myExchange = null;
    @Override
    abstract public void handle(HttpExchange exchange) throws IOException ;
    void setParserExchange(HttpExchange exchange){
        myParser= new DefaultParser(exchange);
        myExchange = exchange;
    }
    private void sendResponse(String str){
        try {
            myExchange.sendResponseHeaders(200, str.length());
            OutputStream os = myExchange.getResponseBody();
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            /// todo
            e.printStackTrace();
        }
    }

}
