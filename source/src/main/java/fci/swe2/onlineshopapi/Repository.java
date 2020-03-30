package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.*;

public interface Repository<T> {
    public boolean exists(long id);
    public T retrieve(long id) throws ObjectNotFoundException;
    public T[] retrieveAll();
    public void store(T obj) throws ValidationException;
    public void update(T obj) throws ValidationException;
    public void delete(T obj) throws ValidationException;
}
