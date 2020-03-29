package fci.swe2.onlineshopapi;

public class MySQLCustomerMapper implements Repository<Customer> {
    private static MySQLCustomerMapper instance = null;

    private MySQLCustomerMapper(){
        // Initialize stuff
    }

    public static MySQLCustomerMapper getInstance(){
        if(instance == null)
            instance = new MySQLCustomerMapper();
        return instance;
    }

    public long exists(long id){
        return 0;
    }

    public long retrieve(long id){
        return 0;
    }

    public Customer[] retrieveAll(){
        return null;
    }
    public void store(Customer obj){}
    public void update(Customer obj){}
    public void delete(Customer obj){}
}
