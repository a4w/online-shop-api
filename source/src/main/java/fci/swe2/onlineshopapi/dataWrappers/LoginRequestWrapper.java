package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.LoginBehaviour;

public class LoginRequestWrapper {
    private String username,password;

    LoginRequestWrapper(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}
