package fci.swe2.onlineshopapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import org.json.JSONObject;

public class DefaultParser implements HTTPExchangeParser {
    private HttpExchange exchange;
    private Map<String, String> params;

    DefaultParser(HttpExchange exchange){
        this.exchange = exchange;
        // Parse GET parameters
        params = new HashMap<>();
        String query = exchange.getRequestURI().getQuery();
        if(query == null) query = "";
        for(String param : query.split("&")){
            String[] entry = param.split("=");
            if(entry.length > 1){
                params.put(entry[0], entry[1]);
            }else{
                params.put(entry[0], "");
            }
        }
    }

    public String[] getURLpath(){
        String path = this.exchange.getRequestURI().toString();
        path = path.substring(1); // Remove '/' from prefix
        return path.split("/");
    }

    public String getParameter(String key){
        return this.params.get(key);
    }

    public String parseBody() throws Exception{
        StringBuilder buf = new StringBuilder(512);

        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        while((b = br.read()) != -1){
            buf.append((char) b);
        }

        br.close();
        isr.close();

        return buf.toString();
    }
}
