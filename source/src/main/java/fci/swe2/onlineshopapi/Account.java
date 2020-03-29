package fci.swe2.onlineshopapi;

abstract class Account {
    protected long userID;
    protected String username;
    protected String email;
    protected String password;
    protected RegistrationBehaviour registrationBehaviour;

    protected int getUserID(){
        return this.userID;
    }

    protected String getEmail(){
        return this.email;
    }

    protected String getUsername(){
        return this.username;
    }

    protected void register(){}
}





