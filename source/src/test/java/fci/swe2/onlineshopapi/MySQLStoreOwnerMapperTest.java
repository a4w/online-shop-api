package fci.swe2.onlineshopapi;
import fci.swe2.onlineshopapi.MySQLMappers.MySQLStoreOwnerMapper;
import junit.framework.TestCase;
public class MySQLStoreOwnerMapperTest  extends TestCase {
    public void testGetStoreOwnerEmail(){
        StoreOwner storeowner = new StoreOwner(1,"ahmeddrawy","ahmedhanfy1111@gmail.com" ,"123456" );
        MySQLStoreOwnerMapper s =  MySQLStoreOwnerMapper.getInstance();
        StoreOwner res = s.findByEmail(storeowner.getEmail());
        assertEquals(res.getPassword() , storeowner.getPassword()) ;
        assertEquals(res.getUsername() , storeowner.getUsername()) ;
        assertEquals(res.getEmail() , storeowner.getEmail()) ;

    }
    public void testGetStoreOwnerByusername(){
        StoreOwner storeowner = new StoreOwner(1,"ahmeddrawy","ahmedhanfy1111@gmail.com" ,"123456" );
        MySQLStoreOwnerMapper s =  MySQLStoreOwnerMapper.getInstance();
        StoreOwner res = s.findByUsername(storeowner.getUsername());
        assertEquals(res.getPassword() , storeowner.getPassword()) ;
        assertEquals(res.getUsername() , storeowner.getUsername()) ;
        assertEquals(res.getEmail() , storeowner.getEmail()) ;
    }
}
