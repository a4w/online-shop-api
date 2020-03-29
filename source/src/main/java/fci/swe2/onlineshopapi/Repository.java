package fci.swe2.onlineshopapi;

public interface Repository<T> {
    public long exists(long id);
    public long retrieve(long id);
    public T[] retrieveAll();
    public void Store(T obj);
    public void update(T obj);
    public void delete(T obj);
}
