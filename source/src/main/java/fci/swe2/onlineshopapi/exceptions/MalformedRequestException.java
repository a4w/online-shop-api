package fci.swe2.onlineshopapi.exceptions;

public class MalformedRequestException extends Exception implements UserFriendlyError{
    private static final long serialVersionUID = 7840844788649672208L;

    public String getUserFriendlyError(){
        return "Malformed request";
    }

};
