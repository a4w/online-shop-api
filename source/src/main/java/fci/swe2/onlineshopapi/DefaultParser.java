package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class DefaultParser implements HTTPExchangeParser {
    private HttpExchange exchange;

    DefaultParser(HttpExchange exchange){}

    public String[] getURLpath(){}
    public String getParameter(String method, String key){}
    public JSONObject parseBody(){}
}
