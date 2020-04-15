package fci.swe2.onlineshopapi;

public class proxy {

    public boolean IsCustomer(Object obj){
        return (obj instanceof Customer);
    }

    public boolean IsStoreOwner(Object obj){
        return (obj instanceof StoreOwner);
    }

    public boolean IsAdmin(Object obj){
        return (obj instanceof Admin);
    }

}
