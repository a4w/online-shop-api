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
        String path = "";
        try {
            path = new URL(this.exchange.getRequestURI().toString()).getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return path.split("/");
    }

    public String getParameter(String key){
        return this.params.get(key);
    }

    public JSONObject parseBody(){
        StringBuilder buf = new StringBuilder(512);
        try {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        while((b = br.read()) != -1){
            buf.append((char) b);
        }
        br.close();
        isr.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return new JSONObject(buf.toString());
    }
}
