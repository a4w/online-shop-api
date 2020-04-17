package fci.swe2.onlineshopapi;

public class Proxy {
    static boolean isAdmin(Account obj ){
        return obj instanceof  Admin;
    }
    static boolean isCustomer(Account obj ){
        return obj instanceof  Customer;
    }
    static boolean isStoreOwner(Account obj ){
        return obj instanceof  StoreOwner;
    }
}
