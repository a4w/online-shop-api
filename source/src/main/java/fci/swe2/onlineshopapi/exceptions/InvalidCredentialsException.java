package fci.swe2.onlineshopapi.exceptions;

public class InvalidCredentialsException extends Exception implements UserFriendlyError {

    private static final long serialVersionUID = -4708211025588564049L;

    @Override
    public String getUserFriendlyError() {
        return "Credentials supplied are incorrect";
    }


}
