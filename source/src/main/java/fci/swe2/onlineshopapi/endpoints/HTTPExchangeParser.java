public interface HTTPExchangeParser {
    public String getURLpath();
    public String getParameter(String method, String key);
    public String parseBody();
}
