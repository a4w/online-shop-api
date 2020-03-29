package fci.swe2.onlineshopapi;

public class MySQLStoreOwnerMapper implements Repository<StoreOwner> {

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
