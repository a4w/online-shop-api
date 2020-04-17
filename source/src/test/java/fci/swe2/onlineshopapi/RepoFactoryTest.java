package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.MySQLMappers.MySQLAdminMapper;
import fci.swe2.onlineshopapi.MySQLMappers.MySQLCustomerMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class RepoFactoryTest extends TestCase {

    public RepoFactoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( RepoFactoryTest.class );
    }

    // This runs once before each test
    public void setUp(){
    }

    public void testFactoryFetchByClass() {
        Repository<Admin> mapper = RepoFactory.getMapper(Admin.class);
        assertEquals(mapper, MySQLAdminMapper.getInstance());
    }

    public void testFactoryFetchByObject() {
        Repository<Customer> mapper = RepoFactory.getMapper(new Customer());
        assertEquals(mapper, MySQLCustomerMapper.getInstance());
    }
}
