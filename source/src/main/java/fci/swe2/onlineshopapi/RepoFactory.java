package fci.swe2.onlineshopapi;

import java.util.HashMap;
import java.util.Map;

public class RepoFactory {
    private static Map<Object, Object> repos = new HashMap<Object, Object>();

    public static void initFactory(){
        // Register all mappers in the system
        register(Customer.class, MySQLCustomerMapper.getInstance());
        register(Admin.class, MySQLAdminMapper.getInstance());
        register(StoreOwner.class, MySQLStoreOwnerMapper.getInstance());
    }

    // Static constructor
    static{
        initFactory();
    }

    public static <T> void register(Class<T> clazz, Repository<T> mapper){
        repos.put(clazz, mapper);
    }

    @SuppressWarnings("unchecked")
    public static <T> Repository<T> getMapper(Class<T> clazz){
        return (Repository<T>) repos.get(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> Repository<T> getMapper(Object object){
        return (Repository<T>) repos.get(object.getClass());
    }

}
