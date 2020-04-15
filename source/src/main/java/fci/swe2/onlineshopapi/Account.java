package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.ValidationException;

abstract class Account {
    protected long userID;
    protected String username;
    protected String email;
    protected String password;
    protected RegistrationBehaviour registrationBehaviour;
    protected LoginBehaviour loginBehaviour;
    public Account(){
        this.registrationBehaviour = new DefaultRegistration();
        this.loginBehaviour = new DefaultLogin();
    }
    public Account(long userID , String username , String email , String password){
        this.registrationBehaviour = new DefaultRegistration();
        this.userID = userID;
        this.email = email;
        this.username= username;
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setRegistrationBehaviour(RegistrationBehaviour registrationBehaviour) {
        this.registrationBehaviour = registrationBehaviour;
    }

    public void setLoginBehaviour(LoginBehaviour loginBehaviour){
        this.loginBehaviour = loginBehaviour;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserID(){
        return this.userID;
    }

    public String getEmail(){
        return this.email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    protected void register() throws ValidationException{
        // Validate password
        if(this.password.length() < 8)
            throw new ValidationException("Passwords must be atleast 8 charachter");
        this.registrationBehaviour.register(this);
    }

    // to do handle more details?
    protected void login(){
        this.loginBehaviour.login(this);
    }
}





