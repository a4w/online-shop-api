package fci.swe2.onlineshopapi;

public interface AccountRepository<T> {
    public T findByUsername(T obj);
    public T findByEmail(T obj);
    public T findByToken(T obj);
}
