package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;

public interface AccountRepository<T> {
    public T findByUsername(String obj) throws ObjectNotFoundException;
    public T findByEmail(String obj) throws ObjectNotFoundException;
}
