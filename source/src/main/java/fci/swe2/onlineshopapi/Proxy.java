package fci.swe2.onlineshopapi;

public class Proxy {
    boolean isAdmin(Account obj ){
        return obj instanceof  Admin;
    }
    boolean isCustomer(Account obj ){
        return obj instanceof  Customer;
    }
    boolean isStoreOwner(Account obj ){
        return obj instanceof  StoreOwner;
    }
}
