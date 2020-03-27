import java.util.Vector
abstract class Account {
    protected int UserID;         //for the database
    protected String Username;
    protected String Email;
    protected String Password;

    protected int GetUserID(){return this.UserID}
    protected String GetEmail(){return this.Email}
    protected String GetUsername(){return this.Username}
    protected String HashPassword(){}
    protected void Register(){}
}





