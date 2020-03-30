package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.*;

import java.sql.SQLException;

public interface Repository<T> {
    public boolean exists(long id) throws SQLException;
    public T retrieve(long id) throws ObjectNotFoundException;
    public T[] retrieveAll() throws SQLException;
    public void store(T obj) throws ValidationException, SQLException;
    public void update(T obj) throws ValidationException, SQLException;
    public void delete(T obj) throws ValidationException, SQLException;
}
