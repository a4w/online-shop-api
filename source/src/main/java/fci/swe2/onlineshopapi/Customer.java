package fci.swe2.onlineshopapi;

public class Customer extends Account{
    public Customer(long userID , String username , String email , String password){
        super(userID , username , email,password);
    }

    public Customer() {

    }
}
