package fci.swe2.onlineshopapi;

public class MySQLCustomerMapper implements Repository<Customer> {
    public long exists(long id){
        return 0;
    }

    public long retrieve(long id){
        return 0;
    }

    public Customer[] retrieveAll(){
        return new Customer[0];
    }
    public void store(Customer obj){}
    public void update(Customer obj){}
    public void delete(Customer obj){}
}
