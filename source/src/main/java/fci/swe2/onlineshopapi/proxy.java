package fci.swe2.onlineshopapi;

public class Proxy {

    public static boolean IsCustomer(Object obj){
        return (obj.getClass() == Customer.class);
    }
    public static boolean IsCustomer(Class cl){
        return (cl == Customer.class);
    }

    public static boolean IsStoreOwner(Object obj){
        return (obj.getClass() == StoreOwner.class);
    }
    public static boolean IsStoreOwner(Class cl){
        return (cl == StoreOwner.class);
    }

    public static boolean IsAdmin(Object obj){
        return (obj.getClass() == Admin.class);
    }
    public static boolean IsAdmin(Class cl){
        return (cl == Admin.class);
    }

}
