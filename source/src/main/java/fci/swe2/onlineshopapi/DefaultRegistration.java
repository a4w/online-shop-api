package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.ValidationException;

public class DefaultRegistration implements RegistrationBehaviour{
    public <T> void register(T obj) throws ValidationException{
        Repository<T> repository = RepoFactory.getMapper(obj);
        repository.store(obj);
    }
}
