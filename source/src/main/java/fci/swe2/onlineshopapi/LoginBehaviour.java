package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.InvalidCredentialsException;

public interface LoginBehaviour {
    public <T extends Account> String login(T obj) throws InvalidCredentialsException;
}
