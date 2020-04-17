package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.MySQLMappers.MySQLAdminMapper;
import junit.framework.TestCase;


public class MySQLAdminMapperTest extends TestCase {
    public void testGetAdminEmail(){
        Admin admin = new Admin(1,"ahmeddraway","ahmedhanfy11111@gmail.com" ,"1211134561" );
        MySQLAdminMapper s =  MySQLAdminMapper.getInstance();
        Admin res = s.findByEmail(admin);
        assertEquals(res.getPassword() , admin.getPassword()) ;
        assertEquals(res.getUsername() , admin.getUsername()) ;
        assertEquals(res.getEmail() , admin.getEmail()) ;

    }
    public void testGetAdminByusername(){
        Admin admin = new Admin(1,"ahmeddraway","ahmedhanfy11111@gmail.com" ,"1211134561" );
        MySQLAdminMapper s =  MySQLAdminMapper.getInstance();
        Admin res = s.findByUsername(admin);
        assertEquals(res.getPassword() , admin.getPassword()) ;
        assertEquals(res.getUsername() , admin.getUsername()) ;
        assertEquals(res.getEmail() , admin.getEmail()) ;

    }
}
