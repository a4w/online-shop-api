package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.ValidationException;

abstract class Account {
    protected long userID;
    protected String username;
    protected String email;
    protected String password;
    protected RegistrationBehaviour registrationBehaviour;
    public Account(){
        this.registrationBehaviour = new DefaultRegistration();
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

    public void setUsername(String username) {
        this.username = username;
    }

    protected long getUserID(){
        return this.userID;
    }

    protected String getEmail(){
        return this.email;
    }

    protected String getUsername(){
        return this.username;
    }

    protected String getPassword(){
        return this.password;
    }

    protected void register() throws ValidationException{
        this.registrationBehaviour.register(this);
    }
}





