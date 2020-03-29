package fci.swe2.onlineshopapi;

abstract class Account {
    protected long userID;
    protected String username;
    protected String email;
    protected String password;
    protected RegistrationBehaviour registrationBehaviour;
    Account(){

    }
    Account(long userID , String username , String email , String password){
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

    protected void register(){}
}





