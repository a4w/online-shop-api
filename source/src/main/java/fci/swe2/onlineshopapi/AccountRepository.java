package fci.swe2.onlineshopapi;

public interface AccountRepository<T> {
    public T findByUsername(String username);
    public T findByEmail(String email);
}
