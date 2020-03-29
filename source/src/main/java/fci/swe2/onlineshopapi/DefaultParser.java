package fci.swe2.onlineshopapi;

import java.net.URL;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class DefaultParser implements HTTPExchangeParser {
    private HttpExchange exchange;

    DefaultParser(HttpExchange exchange){
        this.exchange = exchange;
    }

    public String[] getURLpath(){
        String path = new URL(this.exchange.getRequestURI().toString()).getPath();
        return path.split("/");
    }

    public String getParameter(String method, String key){
        return new String();
    }

    public JSONObject parseBody(){
        return new JSONObject();
    }
}
