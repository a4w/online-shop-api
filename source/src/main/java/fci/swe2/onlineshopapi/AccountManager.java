package fci.swe2.onlineshopapi;

public class AccountManager {
    public <T extends Account> T[] getAllAccounts(Class<T> clazz){
        return ((T[]) new Object[0]);
    }
}
