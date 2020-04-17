package fci.swe2.onlineshopapi;

public class proxy {

    public static boolean IsCustomer(Object obj){
        return (obj.getClass() == Customer.class);
    }

    public static boolean IsStoreOwner(Object obj){
        return (obj.getClass() == StoreOwner.class);
    }

    public static boolean IsAdmin(Object obj){
        return (obj.getClass() == Admin.class);
    }

}
