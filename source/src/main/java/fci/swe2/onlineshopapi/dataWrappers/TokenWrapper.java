package fci.swe2.onlineshopapi.dataWrappers;

public class TokenWrapper{
    String token;
    public TokenWrapper(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
}
