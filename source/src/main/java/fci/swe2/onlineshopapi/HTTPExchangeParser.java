package fci.swe2.onlineshopapi;

import org.json.JSONObject;

public interface HTTPExchangeParser {
    public String[] getURLpath();
    public String getParameter(String method, String key);
    public JSONObject parseBody();
}
