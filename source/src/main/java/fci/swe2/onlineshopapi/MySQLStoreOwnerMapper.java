package fci.swe2.onlineshopapi;

public class MySQLStoreOwnerMapper implements Repository<StoreOwner> {

    private static MySQLStoreOwnerMapper instance = null;

    private MySQLStoreOwnerMapper(){
        // Initialize stuff
    }

    public static MySQLStoreOwnerMapper getInstance(){
        if(instance == null)
            instance = new MySQLStoreOwnerMapper();
        return instance;
    }

    public long exists(long id){
        return 0;
    }

    public long retrieve(long id){
        return 0;
    }

    public StoreOwner[] retrieveAll(){
        return null;
    }
    public void store(StoreOwner obj){}
    public void update(StoreOwner obj){}
    public void delete(StoreOwner obj){}

}
