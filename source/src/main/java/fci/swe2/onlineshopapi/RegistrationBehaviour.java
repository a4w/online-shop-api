package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.ValidationException;

public interface RegistrationBehaviour {
    public <T> void register(T obj) throws ValidationException;
}
