package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class DefaultParser implements HTTPExchangeParser {
    private HttpExchange exchange;

    DefaultParser(HttpExchange exchange){}

    public String[] getURLpath(){
        return new String[0];
    }

    public String getParameter(String method, String key){
        return new String();
    }

    public JSONObject parseBody(){
        return new JSONObject();
    }
}
