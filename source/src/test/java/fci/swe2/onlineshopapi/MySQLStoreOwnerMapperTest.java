package fci.swe2.onlineshopapi;
import junit.framework.TestCase;
public class MySQLStoreOwnerMapperTest  extends TestCase {
    public void testGetStoreOwnerEmail(){
        StoreOwner storeowner = new StoreOwner(1,"ahmeddrawy","ahmedhanfy1111@gmail.com" ,"123456" );
        MySQLStoreOwnerMapper s =  MySQLStoreOwnerMapper.getInstance();
        StoreOwner res = s.findByEmail(storeowner);
        assertEquals(res.getPassword() , storeowner.getPassword()) ;
        assertEquals(res.getUsername() , storeowner.getUsername()) ;
        assertEquals(res.getEmail() , storeowner.getEmail()) ;

    }
    public void testGetStoreOwnerByusername(){
        StoreOwner storeowner = new StoreOwner(1,"ahmeddrawy","ahmedhanfy1111@gmail.com" ,"123456" );
        MySQLStoreOwnerMapper s =  MySQLStoreOwnerMapper.getInstance();
        StoreOwner res = s.findByUsername(storeowner);
        assertEquals(res.getPassword() , storeowner.getPassword()) ;
        assertEquals(res.getUsername() , storeowner.getUsername()) ;
        assertEquals(res.getEmail() , storeowner.getEmail()) ;
    }
}
