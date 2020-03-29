package fci.swe2.onlineshopapi;

import java.util.HashMap;
import java.util.Map;

public class RepoFactory {
    private static Map<Object, Object> repos = new HashMap<Object, Object>();

    public void initFactory(){}

    public static <T> void register(Class<T> clazz, Repository<T> mapper){
    }

    @SuppressWarnings("unchecked")
    public static <T> Repository<T> getMapper(Class<T> clazz){
        return (Repository<T>) new Object();
    }

    @SuppressWarnings("unchecked")
    public static <T> Repository<T> getMapper(Object object){
        return (Repository<T>) new Object();
    }

}
