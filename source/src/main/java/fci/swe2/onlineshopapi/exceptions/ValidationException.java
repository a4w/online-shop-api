package fci.swe2.onlineshopapi.exceptions;

public class ValidationException extends Exception implements UserFriendlyError{
    private static final long serialVersionUID = 7840844788649672208L;

    private String userFriendlyMessage;

    public ValidationException(String userFriendlyMessage){
        this.userFriendlyMessage = userFriendlyMessage;
    }

    public String getUserFriendlyError(){
        return this.userFriendlyMessage;
    }

};
