package fci.swe2.onlineshopapi;

import junit.framework.TestCase;

public class MySQLCusotmerMapperTest extends TestCase {
    public void testGetCustomerEmail(){
        Customer customer = new Customer(1,"ahmeddrawy","ahmedhanfy1111@gmail.com" ,"123456" );
        MySQLCustomerMapper s =  MySQLCustomerMapper.getInstance();
        Customer res = s.findByEmail(customer);
        assertEquals(res.getPassword() , customer.getPassword()) ;
        assertEquals(res.getUsername() , customer.getUsername()) ;
        assertEquals(res.getEmail() , customer.getEmail()) ;

    }
    public void testGetCustomerByusername(){
        Customer customer = new Customer(1,"ahmeddrawy","ahmedhanfy1111@gmail.com" ,"123456" );
        MySQLCustomerMapper s =  MySQLCustomerMapper.getInstance();
        Customer res = s.findByUsername(customer);
        assertEquals(res.getPassword() , customer.getPassword()) ;
        assertEquals(res.getUsername() , customer.getUsername()) ;
        assertEquals(res.getEmail() , customer.getEmail()) ;
    }
}
