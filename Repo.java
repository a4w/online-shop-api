import java.util.HashMap;
import java.util.Map;

class MapperFactory {
    private Map<Object, Object> repos;
    MapperFactory(){
        this.repos = new HashMap<Object, Object>();

        this.register(Customer.class, new SqlCustomerMapper());
    }
    public <T> void register(Class<T> clss, DataMapper<T> mapper){
        this.repos.put(clss, mapper);
    }
    public <T> DataMapper<T> getMapper(Class<T> cls){
        return (DataMapper<T>) this.repos.get(cls);
    }
    public <T> DataMapper<T> getMapper(Object cls){
        return (DataMapper<T>) this.repos.get(cls.getClass());
    }
};

class Repo{
    public static void main(String args[]){
        Customer c = new Customer();
        ActionManager.register(c);
    }
};

interface DataMapper<T>{
    void save(T obj);
    // T get();
    // Higher level functions of persistant layer
}

class SqlCustomerMapper implements DataMapper<Customer>{
    public void save(Customer cust){
        System.out.println("Saved Customer!!");
    }
}

abstract class Account{
};

class Customer extends Account{
};

class ActionManager{
    public static <T extends Account> void register(T account){
        MapperFactory fact = new MapperFactory();
        DataMapper<T> mapper = fact.getMapper(account);
        mapper.save(account);
    }
};