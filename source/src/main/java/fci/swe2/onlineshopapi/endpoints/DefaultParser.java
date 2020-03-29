import com.sun.net.httpserver.HttpExchange;

public class DefaultParser implements HTTPExchangeParser {
    public String getURLpath(){}
    public String getParameter(String method, String key){}
    public String parseBody(){}
    public String parser(HttpExchange exchange){}

    private HttpExchange exchange;

}
